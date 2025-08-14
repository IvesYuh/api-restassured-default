package br.iyk.validacaoSchema;

import static io.restassured.RestAssured.given;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

public class SchemaXml {
	@Test
	public void devoFazerPesquisasAvancadasComXMLEJava() throws SAXException, IOException {
		String schemaPath = "src\\main\\resources\\schema\\schema.xsd";

		String xml = given()
				.when()
					.get("http://restapi.wcaquino.me/usersXML")
				.then()
					.statusCode(200)
					.extract().asString() // Extrai o xml
					;
		System.out.println(xml);

		// Passo 2: Carregar o esquema XSD
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(new StreamSource(schemaPath));

		// Passo 3: Validar o XML contra o XSD
		Validator validator = schema.newValidator();
		try (ByteArrayInputStream xmlStream = new ByteArrayInputStream(xml.getBytes())) {
			Source xmlSource = new StreamSource(xmlStream);
			validator.validate(xmlSource);
			System.out.println("XML é válido conforme o esquema.");
		} catch (SAXException e) {
			Assert.fail("Falha na validação do XML: " + e.getMessage());
		}
	}
}

package br.iyk.manipulacaoArquivos;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ValidarPdf {
	@Test
	public void validaDeclaracaoPdf() {
		String dataAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		String conteudoPdf = "SOLUS COMPUTACAO - CNPJ: 03.533.726/0001-88\r\n"
				+ "RUA JOSE ROQUE SALTON, 285\r\n"
				+ "LONDRINA - PR\r\n"
				+ "86047-622\r\n"
				+ "DECLARAÇÃO DE CARÊNCIA\r\n"
				+ "Informamos os dados cadastrais, constantes em nosso sistema, correspondente ao(s) beneficiário(s) abaixo relacionado(s):\r\n"
				+ "Código Nome Situação Segmentação Modalidade Abrangência Inclusão\r\n"
				+ "3912345678912345BENEFICIARIO AUTOMACAO JOILE Beneficiário ativo. Incluído em 08/07/2021 Ambulatorial mais\r\n"
				+ "Hospitalar com\r\n"
				+ "obstetrícia (06)\r\n"
				+ "Pre-pagamento Nacional 08/07/21\r\n"
				+ "Carência\r\n"
				+ "CARANECIA OCULTA - 03/05/25\r\n"
				+ "TESTE CARENCIA - 03/05/25\r\n"
				+ "Page 1/2\r\n"
				+ "SOLUS COMPUTACAO - CNPJ: 03.533.726/0001-88\r\n"
				+ "RUA JOSE ROQUE SALTON, 285\r\n"
				+ "LONDRINA - PR\r\n"
				+ "86047-622\r\n"
				+ "Código Nome Situação Segmentação Modalidade Abrangência Inclusão\r\n"
				+ "3912345678912268API NOTUS BENEFICIARIO Beneficiário ativo. Incluído em 26/05/2023 Ambulatorial mais\r\n"
				+ "Hospitalar com\r\n"
				+ "obstetrícia (06)\r\n"
				+ "Pre-pagamento Nacional 26/05/23\r\n"
				+ "Carência\r\n"
				+ "DOENÇAS E LESÕES PRÉ-EXISTENTES - \r\n"
				+ "Impresso Em: " + dataAtual + "\r\n"
				+ "Page 2/2";
		
		Response pdf = given()
		.when()
			.get("/carencia/beneficiario/49/declaracao/pdf")
		.then()
			.statusCode(200)
			.extract()
			.response()
			;
		
		 if (pdf.getContentType().equals("application/octet-stream") || pdf.getContentType().contains("application/pdf")) {
	            try (InputStream pdfStream = pdf.asInputStream();
	                 PDDocument document = PDDocument.load(pdfStream)) {
	                PDFTextStripper pdfStripper = new PDFTextStripper();
	                String pdfText = pdfStripper.getText(document);
	                
	                assert pdfText.contains(conteudoPdf);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("A resposta não é um PDF.");
	        }
	}
	
	@Test
	public void validaComprovantePdf() throws IOException {
		
		Response retorno = RestAssured.given()
		.when()
			.get("/beneficiario/3821/mensalidade/5395/fatura/comprovante/pdf")
		.then()
			.statusCode(200)
			.extract()
			.response()
			;
		
		if (retorno.getContentType().equals("application/octet-stream") || retorno.getContentType().contains("application/pdf")) {
            try (InputStream pdfStream = retorno.asInputStream();
                 PDDocument document = PDDocument.load(pdfStream)) {
            	 byte[] pdfBytes = retorno.getBody().asByteArray();
                 String pdfHeader = new String(pdfBytes, 0, 5);
                 assert pdfHeader.equals("%PDF-") : "O conteúdo não começa com %PDF-";
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("A resposta não é um PDF.");
		}
	}
	
	@Test
	public void validaFaturaCsv() throws IOException {
		
		Response retorno = RestAssured.given()
		.when()
			.get("/beneficiario/3821/mensalidade/5395/fatura/csv")
		.then()
			.statusCode(200)
			.extract()
			.response()
			;
		
		String base64Pdf = retorno.jsonPath().getString("mensagem");
		byte[] pdfConvertido = Base64.getDecoder().decode(base64Pdf);
		
		File pdfFile = new File("validacao.pdf");
        FileUtils.writeByteArrayToFile(pdfFile, pdfConvertido);;
        try (org.apache.pdfbox.pdmodel.PDDocument document = org.apache.pdfbox.pdmodel.PDDocument.load(pdfFile)) {
            System.out.println("O PDF é válido e contém " + document.getNumberOfPages() + " página(s).");
        } catch (Exception e) {
            System.err.println("Falha ao validar o PDF: " + e.getMessage());
        }
        pdfFile.delete();
	}
}

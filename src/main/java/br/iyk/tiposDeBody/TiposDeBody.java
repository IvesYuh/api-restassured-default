package br.iyk.tiposDeBody;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import br.iyk.core.Banco;
import br.iyk.core.BaseToken;
import br.iyk.entidades.DadosTiposDeBody;
import br.iyk.restauraTeste.RestaurarTeste;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public class TiposDeBody {
    @Test
    public void validaBodyPadrao() {
        given()
            .contentType(ContentType.JSON) // Tipo de conteúdo da requisição
            .body("{\"name\": \"Joaozinho\",\"age\": 26}") // Corpo da requisição com os dados do usuário
        .when()
            .post("http://restapi.wcaquino.me/users") // Envia a requisição POST para o endpoint especificado
        .then()
            .log().all() // Log de todas as informações da resposta
             .statusCode(201) // Verifica se o código de status da resposta é 201 (Created)
             .body("name", is("Joaozinho"))
             ; // Verifica se o campo "name" da resposta é "Joaozinho"
    }
    
    @Test
    public void validaBodyUtilizandoMap() {
        // Cria um mapa com os dados do usuário
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("name", "Usuario via map");
        body.put("age", 25);
        
        given()
            .log().all() // Log de todas as informações da requisição
            .contentType("application/json") // Tipo de conteúdo da requisição
            .body(body) // Corpo da requisição com os dados do usuário
        .when()
            .post("http://restapi.wcaquino.me/users") // Envia a requisição POST para o endpoint especificado
        .then()
            .log().all() // Log de todas as informações da resposta
             .statusCode(201) // Verifica se o código de status da resposta é 201
             .body("id", is(notNullValue())) // Verifica se o campo "id" da resposta não é nulo
             .body("name", is("Usuario via map")) // Verifica se o campo "name" da resposta é "Usuario via map"
             .body("age", is(25));	// Verifica se o campo "age" da resposta é 25
    }
    
	@Test
	public void validaBodyComVariasListas() {
		// Adiciona dados ao mapa, representando informações de um reembolso.
		Map<String, Object> body = new HashMap<>();
		body.put("idOperador", 572014);
		body.put("valorSolicitado", 100);
		body.put("valor", 500.000);
		body.put("mensagem", "Motivo: teste de solicitação de reembolso\n\nE-mail: enrico@solus.inf.br\nCelular: (43)99156-9973\nOutro celular: \nForma de pagamento: Depósito\nBanco: BANCO AILOS \nAgência bancária: 01082\nConta corrente: 822418\nCPF conta bancária: 808.545.790-36\nPossui nota: N\nNúmero da nota fiscal: 123456\nValor solicitado: 500.00");
		body.put("idBancoContaBancaria", 10777306);
		body.put("cpfContaBancaria", "808.545.790-36");
		body.put("cpfPrestador", "");
		body.put("dataAtendimento", "09/07/2021");
		body.put("siglaFormaPagamento", "D");
		body.put("bancoContaBancaria", "BANCO AILOS ");
		body.put("contaBancaria", "822418");
		body.put("agenciaBancaria", "01082");
		body.put("protocoloCrmInicial", "");
		body.put("email", "enrico@solus.inf.br");
		body.put("celular", "(43)99156-9973");
		body.put("outroCelular", "");
		body.put("nominalNaoBeneficiario", "JOAO SILVA");
		body.put("nominalNaoBeneficiarioCNPJ", "999.089.710-84");
		body.put("origemSigla", "W");
		body.put("tipoVinculoFlag", "B");
		body.put("idNominalBeneficiario", 3641);
		body.put("recibo", "123456");
		body.put("valorPagamento", "5.000");
		body.put("prestadorServico", "4BIO MEDICAMENTOS SA PALMAS");
		body.put("siglaTipoContaBancaria", "C");
		
	    // Cria uma lista de atendimentos e anexa informações detalhadas.
		List<Map<String, Object>> atendimento = new ArrayList<>();
		Map<String, Object> atendimentoItem = new HashMap<>();
		List<Map<String, Object>> anexos = new ArrayList<>();
		Map<String, Object> anexoItem = new HashMap<>();
		anexoItem.put("nome", "cnh_Teste.jpg");
		anexoItem.put("descricao", "cnh_Teste.jpg");
		anexoItem.put("imagem", "oi");
		anexoItem.put("extensao", "jpg");
		anexos.add(anexoItem); 
		atendimentoItem.put("anexos", anexos);  // Adiciona os anexos ao item de atendimento. 

	    // Adiciona um anexo ao atendimento (por exemplo, imagem de um documento).
		List<Map<String, Object>> mensagemList = new ArrayList<>();
		Map<String, Object> mensagemItem = new HashMap<>();
		mensagemItem.put("mensagem", "Motivo: teste de solicitação de reembolso\n\nE-mail: enrico@solus.inf.br\nCelular: (43)99156-9973\nOutro celular: \nForma de pagamento: Depósito\nBanco: BANCO AILOS \nAgência bancária: 01082\nConta corrente: 822418\nCPF conta bancária: 808.545.790-36\nPossui nota: N\nNúmero da nota fiscal: 123456\nValor solicitado: 500.00");
		mensagemList.add(mensagemItem);
		atendimentoItem.put("mensagem", mensagemList);
		atendimento.add(atendimentoItem);
		body.put("atendimento", atendimento);       // Adiciona mensagens detalhadas sobre o atendimento.

	    // Cria uma lista de taxas de reembolso e adiciona detalhes de cada taxa.
		List<Map<String, Object>> reembolsoTaxa = new ArrayList<>();
		Map<String, Object> reembolsoTaxaItem = new HashMap<>();
		reembolsoTaxaItem.put("idTaxa", 21865);
		reembolsoTaxaItem.put("valorSolicitado", 100);
		reembolsoTaxaItem.put("valor", 100);
		reembolsoTaxaItem.put("quantidade", 1);
		reembolsoTaxaItem.put("observacao", "reembolso taxa");
		reembolsoTaxa.add(reembolsoTaxaItem);
		body.put("reembolsoTaxa", reembolsoTaxa);
		
	    // Cria e adiciona informações de procedimentos de reembolso.
		List<Map<String, Object>> reembolsoProcedimento = new ArrayList<>();
		Map<String, Object> reembolsoProcedimentoItem = new HashMap<>();
		reembolsoProcedimentoItem.put("codigoProcedimento", "10101012");
		reembolsoProcedimentoItem.put("valorSolicitado", 100);
		reembolsoProcedimentoItem.put("valor", 100);
		reembolsoProcedimentoItem.put("quantidade", 1);
		reembolsoProcedimentoItem.put("observacao", "reembolso procedimento");
		reembolsoProcedimento.add(reembolsoProcedimentoItem);
		body.put("reembolsoProcedimento", reembolsoProcedimento);
		
	    // Adiciona dados de reembolso de materiais e medicamentos.
		List<Map<String, Object>> reembolsoMatMed = new ArrayList<>();
		Map<String, Object> reembolsoMatMedItem = new HashMap<>();
		reembolsoMatMedItem.put("idProduto", 1785388);
		reembolsoMatMedItem.put("valorSolicitado", 100);
		reembolsoMatMedItem.put("valor", 100);
		reembolsoMatMedItem.put("quantidade", 1);
		reembolsoMatMedItem.put("observacao", "reembolso matmed");
		reembolsoMatMed.add(reembolsoMatMedItem);
		body.put("reembolsoMatMed", reembolsoMatMed);
		
	    // Adiciona dados de reembolso de OPME (órteses, próteses e materiais especiais).
		List<Map<String, Object>> reembolsoOpme = new ArrayList<>();
		Map<String, Object> reembolsoOpmeItem = new HashMap<>();
		reembolsoOpmeItem.put("idProduto", 1785388);
		reembolsoOpmeItem.put("valorSolicitado", 100);
		reembolsoOpmeItem.put("valor", 100);
		reembolsoOpmeItem.put("quantidade", 1);
		reembolsoOpmeItem.put("observacao", "reembolso opme");
		reembolsoOpme.add(reembolsoOpmeItem);
		body.put("reembolsoOpme", reembolsoOpme);

		given()
			.pathParam("idBeneficiario", 3641)
			.body(body)
		.when()
			.post("/beneficiario/{idBeneficiario}/reembolso")
		.then()
			.statusCode(201)
			.body("id", not(equalTo(0)))
			.body("codigo", not(equalTo(0)))
			.body("mensagem", is("Inclusão aceita com sucesso!"))
			;
	}
    
    @Test
    public void validaBodyUtilizandoObjeto() {
        // Cria um objeto do tipo User com os dados do usuário
    	DadosTiposDeBody dados = new DadosTiposDeBody("Usuario via objeto", 35);
         
        given()
            .contentType(ContentType.JSON) // Tipo de conteúdo da requisição
            .body(dados) // Corpo da requisição com os dados do usuário
        .when()
            .post("http://restapi.wcaquino.me/users") // Envia a requisição POST para o endpoint especificado
        .then()
             .log().all() // Log de todas as informações da resposta
             .statusCode(201) // Verifica se o código de status da resposta é 201
             .body("name", is("Usuario via objeto")) // Verifica se o campo "name" da resposta é "Usuario via objeto"
             .body("age", is(35));	// Verifica se o campo "age" da resposta é 35
    }
    
    @Test
    public void validaBodyUtilizandoXml() {
        given()
	        .log().all() // Log de todas as informações da requisição
	        .contentType(ContentType.XML) // Tipo de conteúdo da requisição
	        .body("<user><name>Joaozinho</name><age>26</age></user>") // Corpo da requisição com os dados do usuário em XML
	    .when()
        	.post("http://restapi.wcaquino.me/usersXML") // Envia a requisição POST para o endpoint especificado
        .then()
	        .log().all() // Log de todas as informações da resposta
	         .statusCode(201) // Verifica se o código de status da resposta é 201 (Created)
	         .body("id.@id", is(notNullValue())) // Verifica se o campo "id" da resposta não é nulo
	         .body("user.name", is("Joaozinho")) // Verifica se o campo "name" da resposta é "Joaozinho"
	         .body("user.age", is("26"));	// Verifica se o campo "age" da resposta é 26
    }
    
    @Test
    public void validaCustomizacaoUrl() {
        given()
            .contentType(ContentType.JSON) // Tipo de conteúdo da requisição
            .body("{\"name\": \"Usuario Alterado\",\"age\": 80}") // Corpo da requisição com os dados do usuário
            .pathParam("entidade", "users") //adicionado uma entidade como parametro para ser substituido na url
            .pathParam("userId", "1") //adicionado uma entidade como parametro para ser substituido na url
        .when()
            .put("http://restapi.wcaquino.me/{entidade}/{userId}") // Envia a requisição PUT com url customizada
        .then()
            .log().all() // Log de todas as informações da resposta
            .statusCode(200)
            ; // Verifica se o código de status da resposta é 200
    }
    
    @Test
    public void validaTextoComPadraoRegex() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder.setContentType(ContentType.JSON);
		RestAssured.requestSpecification = requestSpecBuilder.build();
		
    	RestaurarTeste.excluiEncaminhamentoDeGuia(Banco.obterConexao(), 28080L);

    	BaseToken.retornaToken();
    	
    	Map<String, Object> body = new HashMap<>();
    	body.put("guiaId", 28080);
    	body.put("especialidadeId", 71);
    	
        given()
            .body(body)
        .when()
            .post("http://172.16.80.21:15180/v1/guia/28080/encaminhamento") 
        .then()
            .statusCode(201)
            .body("id", not("0"))
            .body("id", greaterThan("0"))
            .body("codigo", is(""))
            .body("mensagem", matchesPattern("Encaminhamento gerado com sucesso! Número \\d+")); 
        
       // matchesPattern: Verifica se a string corresponde a um padrão regex.
       // Regex Encaminhamento gerado com sucesso! Número \\d+:
       // Encaminhamento gerado com sucesso!: Parte fixa da mensagem.
       // Número \\d+: Verifica que há a palavra "Número" seguida de um ou mais dígitos (\\d+).
    }
}

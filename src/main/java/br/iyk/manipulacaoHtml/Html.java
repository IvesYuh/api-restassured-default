package br.iyk.manipulacaoHtml;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasXPath;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;

public class Html {
    @Test
    // Realiza buscas utilizando HTML
    public void validaBuscaComHtml() {
        given()
            .log().all() // Habilita o log de todas as requisições e respostas
        .when()
            .get("http://restapi.wcaquino.me/v2/users") // Faz uma requisição GET para a URL especificada
        .then()
            .log().all() // Habilita o log de todas as validações
            .statusCode(200) // Valida se o status code da resposta é 200
            .contentType(ContentType.HTML) // Valida se o tipo de conteúdo da resposta é HTML
            .body("html.body.div.table.tbody.tr.size()", Matchers.is(3)) // Valida o tamanho da lista de elementos tr
            .body("html.body.div.table.tbody.tr[1].td[2]", is("25")) // Valida o valor do elemento td[2] da segunda linha
            .appendRootPath("html.body.div.table.tbody") // Define o caminho raiz para as próximas validações
            .body("tr[1].td[1]", is("Maria Joaquina")) // Valida o valor do elemento td[1] da segunda linha
            .body("tr[0].td[1]", is("João da Silva")) // Valida o valor do elemento td[1] da primeira linha
            .body("tr[2].td[1]", is("Ana Júlia")) // Valida o valor do elemento td[1] da terceira linha
            .body("tr[0].td[2]", is("30")) // Valida o valor do elemento td[2] da primeira linha
            .body("tr[1].td[2]", is("25")) // Valida o valor do elemento td[2] da segunda linha
            .body("tr[2].td[2]", is("20")); // Valida o valor do elemento td[2] da terceira linha
        }
    
    @Test
    // Realiza buscas utilizando XPath
    public void validaBuscaComXpath() {
        given()
            .log().all() // Habilita o log de todas as requisições e respostas
        .when()
            .get("http://restapi.wcaquino.me/v2/users?format=clean") // Faz uma requisição GET para a URL especificada com um formato específico
        .then()
            .log().all() // Habilita o log de todas as validações
            .statusCode(200) // Valida se o status code da resposta é 200
            .contentType(ContentType.HTML) // Valida se o tipo de conteúdo da resposta é HTML
            .body(hasXPath("count(//table/tr)", is("4"))) // Valida a contagem de elementos tr dentro de uma tabela
            .body(hasXPath("//td[text() = '2']/../td[2]", is("Maria Joaquina"))); // Valida o valor do elemento td[2] da linha que contém o texto "2" no elemento td
        }
}

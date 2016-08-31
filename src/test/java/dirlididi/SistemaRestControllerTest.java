package dirlididi;

import static org.hamcrest.CoreMatchers.containsString;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class SistemaRestControllerTest {

    @Test
    public void testGetUsuarios() throws UnirestException {
	HttpResponse<String> response = Unirest.get("http://localhost:8080/api/user")
		.header("content-type", "application/json;charset=UTF-8").asString();

	Assert.assertThat(response.getBody(),
		containsString("{\"email\":\"xpto@kkk.com\",\"problemas\":[],\"solucoes\":[]}"));
    }

    @Test
    public void testGetUsuariosQueMaisResolveram() {
	try {
	    HttpResponse<String> response = Unirest.get("http://localhost:8080/api/rank/")
		    .header("cache-control", "no-cache").header("postman-token", "4393f735-f103-b3fb-5623-da053ac13323")
		    .asString();
	} catch (UnirestException e) {
	    Assert.fail();
	}
    }

    @Test
    public void testCadastrar() throws UnirestException {
	HttpResponse<String> response = Unirest.post("http://localhost:8080/api/usuer")
		.header("content-type", "multipart/form-data; boundary=---011000010111000001101001")
		.header("cache-control", "no-cache").header("postman-token", "e24c2b9b-255f-d3f9-ec5d-306b3f789335")
		.body("-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"email\"\r\n\r\nxpto@kkk.com\r\n-----011000010111000001101001\r\nContent-Disposition: form-data; name=\"senha\"\r\n\r\n1234\r\n-----011000010111000001101001--")
		.asString();
    }

    @Test
    public void testCriarProblema() {
	try {
	    HttpResponse<String> response = Unirest.post("http://localhost:8080/api/problem/")
		    .header("cache-control", "no-cache").header("postman-token", "77f3bc83-229a-0402-5086-7fde3834967b")
		    .asString();
	} catch (UnirestException e) {
	    Assert.fail();
	}
    }

    @Test
    public void testEditarProblema() {
	try {
	    HttpResponse<String> response = Unirest.put("http://localhost:8080/api/problem/%7Bid%7D")
		    .header("cache-control", "no-cache").header("postman-token", "d0f4b2b6-2e75-8866-9f79-09623d5a587d")
		    .asString();
	} catch (UnirestException e) {
	    Assert.fail();
	}
    }

    @Test
    public void testSubmeterSolucao() {
	try {
	    HttpResponse<String> response = Unirest.post("http://localhost:8080/api/solved/")
		    .header("cache-control", "no-cache").header("postman-token", "6045e420-6641-698f-eeac-38ee918e9a93")
		    .asString();
	} catch (UnirestException e) {
	    Assert.fail();
	}
    }

    @Test
    public void testGetEstatisticasGlobais() throws UnirestException {
	HttpResponse<String> response = Unirest.get("http://localhost:8080/api/stats/global")
		.header("cache-control", "no-cache").header("postman-token", "28bdf8c4-30c4-e268-66e8-7432da72e76e")
		.asString();

	Assert.assertEquals("{\"submitters\":0,\"problems\":0}", response.getBody());
    }

    @Test
    public void testGetEstatisticarDoUsuario() throws UnirestException {
	HttpResponse<String> response = Unirest.get("http://localhost:8080/api/stats/user")
		.header("cache-control", "no-cache").header("postman-token", "dae71548-adf0-eef9-f9e7-2d6f163aa5a1")
		.asString();

	Assert.assertEquals("{\"pass\":0}", response.getBody());
    }

    @Test
    public void testGetProblemas() throws UnirestException {
	HttpResponse<String> response = Unirest.get("http://localhost:8080/api/problem/")
		.header("cache-control", "no-cache").header("postman-token", "93274050-fdde-2fb4-865f-0512384806c5")
		.asString();

	Assert.assertEquals("[]", response.getBody());
    }

    @Test
    public void testGetProblemasPaginacao() throws UnirestException {
	HttpResponse<String> response = Unirest.get("http://localhost:8080/api/problem/pagination/")
		.queryString("numeroDaPagina", 1).header("cache-control", "no-cache")
		.header("postman-token", "c157e05b-5d11-023b-9690-475f15f89457").asString();

	Assert.assertEquals("[]", response.getBody());
    }

    @Test
    public void testGetProblemasResolvidos() throws UnirestException {
	HttpResponse<String> response = Unirest.get("http://localhost:8080/api/solved/")
		.header("cache-control", "no-cache").header("postman-token", "fc7e231d-e860-a3ca-d435-7a134a825528")
		.asString();

	Assert.assertEquals("{\"Q4wuqKxnM\":true,\"Q8lIaDijI\":true}", response.getBody());
    }
}

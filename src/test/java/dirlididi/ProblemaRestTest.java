package dirlididi;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static com.jayway.restassured.RestAssured.*;

import dirlididi.domain.*;
import dirlididi.repositories.*;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

@SpringApplicationConfiguration(classes = DirlididApplication.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProblemaRestTest {
	
	@Value("${local.server.port}")
    private int port;
	
	@Autowired
	private ProblemaRepository problemaRepo;
	
	private Normal usuarioNormal;
    
    @Autowired
    private NormalRepository normalRepository;
	
	@Autowired
	private SolucaoRepository solucaoRepo;
	
	//@Autowired
	//private TesteRepository testeRepo;
	
	private Problema problema;
	
	@Before
	public void setUp() {
		problema = new Problema("SOMA", "Somar dois numeros "
				+ "inteiros e imprimir o resultado", null);
		problemaRepo.save(problema);
		
		usuarioNormal = new Normal("jadyBolt@rio2016.com", "marmita_carioca");
		normalRepository.save(usuarioNormal);
		
		solucaoRepo.save(new Solucao("print raw_input()", "1989", "1989"));
	}
	
	@After
	public void tearDown() {
		problemaRepo.deleteAll();
		normalRepository.deleteAll();
		solucaoRepo.deleteAll();
	}
	
	@Test
	public void testGetProblemas() {
		given().contentType(ContentType.JSON)
    	.when()
        .port(this.port)
        .get("/api/problem/")
        .then()
        .assertThat()
        .statusCode(HttpStatus.SC_OK);
	}
	
	@Test
	public void testGetProblemaEspecifico() {
		given().contentType(ContentType.JSON)
    	.when()
        .port(this.port)
        .get("/api/problem/{id}", 1)
        .then()
        .assertThat()
        .statusCode(HttpStatus.SC_OK)
        .body("nome", Matchers.is(problema.getNome()));
	}
	
	@Test
	public void testProblemasResolvidos() {
		given().contentType(ContentType.JSON)
    	.when()
        .port(this.port)
        .get("/api/solved/")
        .then()
        .assertThat()
        .statusCode(HttpStatus.SC_UNAUTHORIZED);
		
		RestAssured.authentication = basic("jadyBolt@rio2016.com", "marmita_carioca");
		given().contentType(ContentType.JSON)
    	.when()
        .port(this.port)
        .get("/api/solved/")
        .then()
        .assertThat()
        .statusCode(HttpStatus.SC_OK);
	}

}

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
import dirlididi.repositories.NormalRepository;

import com.google.gson.*;
import com.jayway.restassured.http.ContentType;

@SpringApplicationConfiguration(classes = DirlididApplication.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class UsuarioTest {
	
	@Value("${local.server.port}")
    private int port;
    
    @Autowired
    private NormalRepository normalRepository;
    
    private Normal usuarioNormal;
    
    private Gson gson = new Gson();
    
    @Before
    public void setUp(){
        usuarioNormal = new Normal("usuario.normal@gmail.com", "99999999");
        normalRepository.save(usuarioNormal);
    }

    @After
    public void tearDown() {
        normalRepository.deleteAll();
    }
    
    @Test
    public void testCadastrarUsuario() throws Exception {
    	
    	Normal novoUsuario = new Normal("normalzinho@gmail.com", "muitofulodavida");
        given().contentType(ContentType.JSON)
                .body(gson.toJson(novoUsuario))
                .when()
                .port(this.port)
                .post("/api/user")
                .then().assertThat()
                .body("email", Matchers.is(novoUsuario.getEmail()));
    }

    @Test
    public void testGetUsuarios() {
        given().when()
                .port(this.port)
                .get("/api/user")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }
    
    @Test
    public void testAtualizarUsuario() {
    	
    	usuarioNormal.setEmail("usuarioMaluco@gmail.com");
    	
    	given().contentType(ContentType.JSON)
    	.body(gson.toJson(usuarioNormal))
    	.when()
        .port(this.port)
        .put("/api/user/{id}", usuarioNormal.getId())
        .then()
        .assertThat()
        .statusCode(HttpStatus.SC_OK)
        .body("email", Matchers.is("usuarioMaluco@gmail.com"));
    }
    
    @Test
    public void testDeletarUsuario() {
    	
    	given()
    	.when()
        .port(this.port)
        .delete("/api/user/{id}", usuarioNormal.getId())
        .then()
        .assertThat()
        .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    
    @Test
    public void testTentarDeletarUsuarioInexistente() {
    	given()
    	.when()
        .port(this.port)
        .delete("/api/user/{id}", usuarioNormal.getId());
    	
    	given()
    	.when()
        .port(this.port)
        .delete("/api/user/{id}", usuarioNormal.getId())
        .then()
        .assertThat()
        .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}

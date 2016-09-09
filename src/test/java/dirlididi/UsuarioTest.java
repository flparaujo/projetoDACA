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
    
    @Before
    public void setup(){
        usuarioNormal = new Normal("usuario.normal@gmail.com", "99999999");
        normalRepository.save(usuarioNormal);
    }

    @After
    public void tearDown() {
        normalRepository.deleteAll();
    }
    
    @Test
    public void testCadastrarUsuario() throws Exception {
        Gson gson = new Gson();
        Normal usuarioNormal = new Normal("usuarioanormal@gmail.com", "000000000000");

        given()
                .contentType(ContentType.JSON)
                .when()
                .port(this.port)
                .post("/api/user?email=email@jssl.com&senha=123345454545")
                .then().assertThat()
                .body("email", Matchers.is("email@jssl.com"));
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
    public void testGetUsuarioPeloID() {
    	
    }
}

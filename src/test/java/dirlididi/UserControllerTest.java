package dirlididi;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static com.jayway.restassured.RestAssured.*;

import dirlididi.domain.Usuario;
import dirlididi.repositories.NormalRepository;

@SpringApplicationConfiguration(classes = DirlididApplication.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {
	
	@Value("${local.server.port}")
    private int port;
    
    @Autowired
    private NormalRepository normalRepository;
    
    private Usuario usuario;

    @Test
    public void testGetUsuarios() {
        given()
                .when()
                .port(this.port)
                .get("/api/user")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }
}

package tests;

import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;

public class HomeWorkTest {

    @Test
    void listUsers() {
        given()
                .filter(withCustomTemplates())
                .log().all()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .statusCode(200)
                .body("total", is(12))
                .body("data.email[0]", is("michael.lawson@reqres.in"));
    }

    @Test
    void singleUsers() {
        given()
                .filter(withCustomTemplates())
                .log().all()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.email", is("janet.weaver@reqres.in"));
    }

    @Test
    void createUsers()   {
        String data = "{\"name\": \"morpheus\",\"job\": \"leader\"}";

        given()
                .filter(withCustomTemplates())
                .log().all()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void updateUsers()   {
        String data = "{\"name\": \"morpheus\",\"job\": \"zion resident\"}";

        given()
                .filter(withCustomTemplates())
                .log().all()
                .contentType(JSON)
                .body(data)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    void deleteUsers()   {

        given()
                .filter(withCustomTemplates())
                .log().all()
                .contentType(JSON)
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(204);

    }
}
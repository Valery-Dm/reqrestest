package dmv.spring.demo;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import dmv.spring.demo.entities.User;
import io.restassured.RestAssured;

public class ReqResInApiTests {

    /*
     * {
    "page": "2",
    "per_page": 3,
    "total": 12,
    "total_pages": 4,
    "data": [
        {
            "id": 4,
            "first_name": "eve",
            "last_name": "holt",
            "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/marcoramires/128.jpg"
        },
        {
            "id": 5,
            "first_name": "gob",
            "last_name": "bluth",
            "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/stephenmoon/128.jpg"
        },
        {
            "id": 6,
            "first_name": "tracey",
            "last_name": "bluth",
            "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/bigmancho/128.jpg"
        }
    ]
}
     */
    private static final String HOST = "https://reqres.in/api";
    
    @Test
    public void testGetUsers() {
        RestAssured.get(HOST + "/users?page=2")
        .then()
        .statusCode(200)
        .body("page", is("2"))
        .body("data.id", hasItems(4, 5, 6));
    }
    
    @Test
    public void testGetUser404() {
        RestAssured.get(HOST + "/users/23")
        .then()
        .statusCode(404);
    }
    
    /*
     * Message message = new Message();
message.setMessage("My messagee");
given().
       contentType("application/json").
       body(message).
when().
      post("/message");
      {
    "name": "morpheus",
    "job": "leader"
}
     */
    @Test
    public void testCreateUser() {
        RestAssured.given()
        .contentType("application/json")
        .body(new User("morpheus", "leader"))
        .when()
        .post(HOST + "/api/users")
        .then()
        .statusCode(201)
        .body("name", is("morpheus"))
        .body("job", is("leader"));
        
    }
}

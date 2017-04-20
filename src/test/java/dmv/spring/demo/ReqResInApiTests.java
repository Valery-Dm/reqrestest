package dmv.spring.demo;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

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
    public void testGetRole() {
        RestAssured.get(HOST + "/users?page=2")
        .then()
        .statusCode(200)
        .body("page", is("2"))
        .body("data.id", hasItems(4, 5, 6));
    }
}

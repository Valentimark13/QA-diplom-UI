package services;

import dto.UserDTO;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserService {
    private static final String BASE_PATH = "https://stellarburgers.nomoreparties.site/api/auth";

    public void register(UserDTO user) {
        given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(BASE_PATH + "/register");
    }

    public String login(UserDTO user) {
            return given()
                    .contentType("application/json")
                    .body(user)
                    .when()
                    .post(BASE_PATH + "/login").path("accessToken");
    }

    public void deleteUser(String token) {
        given()
                .header("Authorization", token)
                .when()
                .delete(BASE_PATH + "/user");
    }
}

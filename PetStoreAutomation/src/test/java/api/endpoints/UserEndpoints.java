package api.endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.*;

public class UserEndpoints {

	public static Response createUser(User payload) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(Route.post_url);

		System.out.println("response:" + response);
		return response;
	}

	public static Response getUser(String username) {
		Response response = given().pathParam("username", username)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.get(Route.get_url);

		return response;
	}

	public static Response updateUser(String username, User payload) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.put(Route.update_url);

		return response;
	}

	public static Response deleteUser(String username) {
		Response response = given().pathParam("username", username).when().delete(Route.delete_url);

		return response;
	}
}

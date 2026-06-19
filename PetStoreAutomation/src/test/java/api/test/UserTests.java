package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

Faker faker;
User  userPayload;
	
@BeforeClass
	public void setupData()
	{
	faker=new Faker();
	userPayload=new User();
	
	userPayload.setId(faker.idNumber().hashCode());
	userPayload.setUsername(faker.name().username());
	userPayload.setFirstName(faker.name().firstName());
	userPayload.setEmail(faker.internet().emailAddress());
	userPayload.setLastName(faker.name().lastName());
	userPayload.setPassword(faker.internet().password());
	userPayload.setPhone(faker.phoneNumber().cellPhone());
	userPayload.setUserStatus(0);
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
	Response response=UserEndpoints.createUser(userPayload);	
	response.then().log().all();
	System.out.println(response);
	
	Assert.assertEquals(response.statusCode(),200);
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
	Response response=UserEndpoints.getUser(this.userPayload.getUsername());
	response.then().log().all();
	System.out.println(response);
		
	}
	
	@Test(priority=3)
	public void testUpdateUserBYName()
	{
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setLastName(faker.name().lastName());
		Response response=UserEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
		//response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		Response responseAfterUpdate=UserEndpoints.getUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		
		//Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
			
	}
	
	
		
	
	}


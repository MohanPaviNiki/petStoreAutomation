package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endPoints.UserEndPoints2properties;
import api.payLoads.User;
import io.restassured.response.Response;


public class UserTests2 {
	
	Faker faker;
	User userPayload;
	
	public Logger logger; //For logs
	@BeforeClass
	//public void setupData()
	public void setup() //this for logs
	{
		faker = new Faker();
		userPayload= new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//Logs
		logger= LogManager.getLogger(this.getClass());
		
	}

	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("*************** Creating user*****************");
		Response response=UserEndPoints2properties.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(),200);
		logger.info("*************** user is Created*****************");
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("*************** Reading user information *****************");
		Response response=UserEndPoints2properties.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(),200);
		
		logger.info("***************user information Available *****************");
	}
	
	
	@Test(priority=3)
	public void tesUpdateUserByName()
	{
		//Update data using payload
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		logger.info("*************** Updating  user information *****************");
		Response response=UserEndPoints2properties.UpdateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().body().statusCode(200);
		//Assert.assertEquals(response.statusCode(),200);
		logger.info("*************** user is Updated *****************");
		

		Response responseAfterUpdate=UserEndPoints2properties.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(response.statusCode(),200);
	}
	
	@Test(priority=4)
	public void tesDeleteUserByName()
	{
		logger.info("*************** Deleting  user information *****************");
		Response response=UserEndPoints2properties.deletUser(this.userPayload.getUsername());
		response.then().log().body().statusCode(200);
		//Assert.assertEquals(response.statusCode(),200);
		logger.info("*************** user is Deleted *****************");
		
	}
}

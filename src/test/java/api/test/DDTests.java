package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endPoints.UserEndPoints;
import api.payLoads.*;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String UserID,String UserName,String FirstName,String LastName,String Email,String Password,String Phone) //This data taken form xlx sheet not faker
	{
		User userPayload=new User();
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(UserName);
		userPayload.setFirstName(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setEmail(Email);
		userPayload.setPassword(Password);
		userPayload.setPhone(Phone);
		
		Response response=UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.statusCode(),200);
	}
	
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
	Response response=UserEndPoints.deletUser(userName);
	Assert.assertEquals(response.getStatusCode(),200);
	}
	
	
	
}

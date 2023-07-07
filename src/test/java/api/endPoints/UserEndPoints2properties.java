package api.endPoints;

//import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payLoads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints.jav
//Created for perform CURD Operations

public class UserEndPoints2properties {
	
	//method created for getting URL's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");//Load the properties file //name of the properties file
		
		return routes;
	}

	   public static Response createUser(User payload)
	   {
		  String post_url =getURL().getString("post_url");
		  Response response= given()
		     .contentType(ContentType.JSON)
		     .accept(ContentType.JSON)
		     .body(payload)
		     
		   .when()
		    .post(post_url);
		  
		   return response;
		   
	   }
	   
	   
	   public static Response readUser(String userName)
	   {
		   String get_url =getURL().getString("get_url");
		  Response response= given()
		     .pathParam("username", userName)
		     
		   .when()
		    .get(get_url);
		  
		   return response;
		   
	   }
	   
	   
	   public static Response UpdateUser(String userName, User payload)
	   {
		   String update_url =getURL().getString("update_url");
		   Response response= given()
				     .contentType(ContentType.JSON)
				     .accept(ContentType.JSON)
				     .pathParam("username",userName)
				     .body(payload)
				     
				   .when()
				    .put(update_url);
				  
				   return response;
		   
	   }
	   
	   
	   public static Response deletUser(String userName)
	   {
		   String delete_url =getURL().getString("delete_url");
		  Response response= given()
		     .pathParam("username", userName)
		     
		   .when()
		    .delete(delete_url);
		  
		   return response;
		   
	   }
}

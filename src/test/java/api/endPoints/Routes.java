package api.endPoints;

/* Swagger URI--> https://petstore.swagger.io/
	
	CreatUser: https://petstore.swagger.io/v2/user
	GetUser: https://petstore.swagger.io/v2/user/{username}
    UpdateUser:	https://petstore.swagger.io/v2/user/{username}
	DeleteUser: https://petstore.swagger.io/v2/user/{username} */

public class Routes {
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	//User Model
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{username}";
	public static String update_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";
	
   //Pet Model
	 //Here Create Pet Model 
	
   // Store Model
	 //Here Create Store Model
	
}

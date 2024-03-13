package test;

import static org.hamcrest.CoreMatchers.equalTo;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import net.minidev.json.JSONObject;

@SpringBootTest
class FoodieAppApplicationTests {

	@Test(priority = 1)
	public void register() {
		JSONObject req=new JSONObject();
		baseURI="http://localhost:8091/api/v1/admin";
		req.put("admin_id", 3);
		req.put("email", "abcd123@gmail.com");
		req.put("password", "abcd123");
		
		given()
		.contentType("application/json")
		.when()
		.body(req.toJSONString())
		.post("/register")
		.then()
		.statusCode(200)
		.log().all();
	}
	
	@Test(priority = 2)
	public void login() {
		JSONObject req=new JSONObject();
		baseURI="http://localhost:8091/api/v1/admin";
		req.put("email", "abc123@gmail.com");
		req.put("password", "abcd123");
		
		given()
		.contentType("application/json")
		.when()
		.body(req.toJSONString())
		.post("/login")
		.then()
		.statusCode(200)
		.log().all();
	}
	
	@Test(priority = 3)
	public void login1() {
		JSONObject req=new JSONObject();
		baseURI="http://localhost:8091/api/v1/admin";
		req.put("email", "abcd123@gmail.com");
		req.put("password", "abc123");
		
		given()
		.contentType("application/json")
		.when()
		.body(req.toJSONString())
		.post("/login")
		.then()
		.statusCode(200)
		.log().all();
	}
	
	@Test(priority = 4)
	public void login2() {
		JSONObject req=new JSONObject();
		baseURI="http://localhost:8091/api/v1/admin";
		req.put("email", "abcd123@gmail.com");
		req.put("password", "abcd123");
		
		given()
		.contentType("application/json")
		.when()
		.body(req.toJSONString())
		.post("/login")
		.then()
		.statusCode(200)
		.log().all();
	}
	
	@Test(priority = 5)
	public void Rest_Post1() {
		JSONObject req=new JSONObject();
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant";
		req.put("restaurant_id", 101);
		req.put("restaurant_name", "holige_mane");
		req.put("restaurant_cat", "veg");
		req.put("restaurant_loc", "bengaluru");
		req.put("restaurant_contact", "123456789");
		
		given()
		.contentType("application/json")
		.when()
		.body(req.toJSONString())
		.post("/post")
		.then()
		.statusCode(201)
		.log().all();
	}
	
	@Test(priority = 6)
	public void Rest_Post2() {
		JSONObject req=new JSONObject();
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant";
		req.put("restaurant_id", 101);
		req.put("restaurant_name", "holige_mane");
		req.put("restaurant_cat", "veg");
		req.put("restaurant_loc", "bengaluru");
		req.put("restaurant_contact", "123456789");
		
		given()
		.contentType("application/json")
		.when()
		.body(req.toJSONString())
		.post("/post")
		.then()
		.statusCode(409)
		.log().all();
	}
	
	@Test(priority = 7)
	public void Rest_Put() {
		JSONObject req=new JSONObject();
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant";
		req.put("restaurant_id", 101);
		req.put("restaurant_name", "holige_mane");
		req.put("restaurant_cat", "veg");
		req.put("restaurant_loc", "bengaluru");
		req.put("restaurant_contact", "987654321");
		
		given()
		.contentType("application/json")
		.when()
		.body(req.toJSONString())
		.put("/put")
		.then()
		.statusCode(200)
		.log().all();
	}
	
	@Test(priority = 8)
	public void Rest_GetAll() {
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant";
		
		given()
		  .get("/get")
		  .then()
		  .statusCode(200)
		  .log().all();
	}
	
	@Test(priority = 9)
	public void Rest_GetById() {
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant";
		
		given()
		  .get("/get/101")
		  .then()
		  .body("restaurant_cat", equalTo("veg"))
		  .body("restaurant_contact",equalTo("987654321") )
		  .statusCode(200)
		  .log().all();
	}

	@Test(priority = 10)
	public void Rest_Delete() {
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant";
		
		when()
		.delete("/delete/101")
		.then()
		.statusCode(200)
		.log().all();
	}
	
	@Test(priority = 11)
	public void Rest_Delete1() {
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant";
		
		when()
		.delete("/delete/101")
		.then()
		.statusCode(404)
		.log().all();
	}
	
	@Test(priority = 12)
	public void Dish_Post1() {
		JSONObject req=new JSONObject();
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant/dish";
		String jsonPayload = "{\n" +
		        "    \"dish_id\": 63,\n" +
		        "    \"dish_name\": \"nonveg\",\n" +
		        "    \"dish_price\": 299,\n" +
		        "    \"dish_rating\": 3,\n" +
		        "    \"restaurant\": {\n" +
		        "        \"restaurant_id\": 123\n" +
		        "    }\n" +
		        "}";
		
		given()
		.contentType(ContentType.JSON)
		.body(jsonPayload)
		.when()
		.post("/post")
		.then()
		.statusCode(201)
		.log().all();
	}
	
	@Test(priority = 13)
	public void Dish_Post2() {
		JSONObject req=new JSONObject();
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant/dish";
		String jsonPayload = "{\n" +
		        "    \"dish_id\": 60,\n" +
		        "    \"dish_name\": \"nonveg\",\n" +
		        "    \"dish_price\": 299,\n" +
		        "    \"dish_rating\": 3,\n" +
		        "    \"restaurant\": {\n" +
		        "        \"restaurant_id\": 123\n" +
		        "    }\n" +
		        "}";
		
		 given()
		  .contentType(ContentType.JSON)
         .body(jsonPayload)
		  .when()
		  .post("/post")
		  .then()
		  .statusCode(409)
		  .log().all();
	}
	
	@Test(priority = 14)
	public void Dish_Put() {
		JSONObject req=new JSONObject();
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant/dish";
		String jsonPayload = "{\n" +
		        "    \"dish_id\": 63,\n" +
		        "    \"dish_name\": \"veg\",\n" +
		        "    \"dish_price\": 299,\n" +
		        "    \"dish_rating\": 3,\n" +
		        "    \"restaurant\": {\n" +
		        "        \"restaurant_id\": 123\n" +
		        "    }\n" +
		        "}";

		 given()
		  .contentType(ContentType.JSON)
         .body(jsonPayload)
		  .when()
		  .put("/put")
		  .then()
		  .statusCode(200)
		  .log().all();
	}
	
	@Test(priority = 15)
	public void Dish_GetAll() {
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant/dish";
		
		given()
		  .get("/get")
		  .then()
		  .statusCode(200)
		  .log().all();
	}
	
	@Test(priority = 16)
	public void Dish_GetById() {
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant/dish";
		
		given()
		  .get("/get/63")
		  .then()
		  .body("dish_rating", equalTo(3))
		  .body("dish_name",equalTo("veg") )
		  .statusCode(200)
		  .log().all();
	}

	@Test(priority = 17)
	public void Dish_Delete() {
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant/dish";
		
		when()
		.delete("/delete/63")
		.then()
		.statusCode(200)
		.log().all();
	}
	
	@Test(priority = 18)
	public void Dish_Delete1() {
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant/dish";
		
		when()
		.delete("/delete/63")
		.then()
		.statusCode(404)
		.log().all();
	}
	
	@Test(priority = 19)
	public void Order_Post1() {
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant/dish/order";
		  String jsonPayload = "{\n" +
	              "    \"order_id\": 62,\n" +
	              "    \"order_date\": \"2023-08-31T14:30:00.000Z\",\n" +
	              "    \"order_status\": \"pending\",\n" +
	              "    \"dish\": {\n" +
	              "        \"dish_id\": 60\n" +
	              "    }\n" +
	              "}";
		  
				  given()
				  .contentType(ContentType.JSON)
		          .body(jsonPayload)
				  .when()
				  .post("/post")
				  .then()
				  .log().all();
	}
	
	@Test(priority = 20)
	public void Order_Post2() {
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant/dish/order";
		  String jsonPayload = "{\n" +
	              "    \"order_id\": 64,\n" +
	              "    \"order_date\": \"2023-08-31T14:30:00.000Z\",\n" +
	              "    \"order_status\": \"pending\",\n" +
	              "    \"dish\": {\n" +
	              "        \"dish_id\": 60\n" +
	              "    }\n" +
	              "}";

				  given()
				  .contentType(ContentType.JSON)
		          .body(jsonPayload)
				  .when()
				  .post("/post")
				  .then()
				  .statusCode(409)
				  .log().all();
	}
	
	@Test(priority = 21)
	public void Order_Put() {
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant/dish/order";
		  String jsonPayload = "{\n" +
	              "    \"order_id\": 60,\n" +
	              "    \"order_date\": \"2023-08-31T14:30:00.000Z\",\n" +
	              "    \"order_status\": \"success\",\n" +
	              "    \"dish\": {\n" +
	              "        \"dish_id\": 60\n" +
	              "    }\n" +
	              "}";
		  
				  given()
				  .contentType(ContentType.JSON)
		          .body(jsonPayload)
				  .when()
				  .put("/put")
				  .then()
				  .statusCode(200)
				  .log().all();
	}
	
	@Test(priority = 22)
	public void Order_GetAll() {
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant/dish/order";
		
		given()
		  .get("/get")
		  .then()
		  .statusCode(200)
		  .log().all();
	}
	
	@Test(priority = 23)
	public void Order_GetById() {
		baseURI="http://localhost:8092/api/v1/admin/login/restaurant/dish/order";
		
		given()
		  .get("/get/60")
		  .then()
		  .body("order_status",equalTo("success") )
		  .statusCode(200)
		  .log().all();
	}
	
	@Test(priority = 24)
	public void User_GetByName() {
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant";
		
		given()
		  .get("/getByName/KFC")
		  .then()
		  .statusCode(200)
		  .log().all();
	}
	
	@Test(priority = 25)
	public void User_GetByCategory() {
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant";
		
		given()
		  .get("/getByCategory/Non-Veg")
		  .then()
		  .statusCode(200)
		  .log().all();
	}
	
	@Test(priority = 26)
	public void User_GetByLocation() {
		baseURI="http://localhost:8091/api/v1/admin/login/restaurant";
		
		given()
		  .get("/getByLocation/OldCity")
		  .then()
		  .statusCode(200)
		  .log().all();
	}
	
}

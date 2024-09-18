/* Integration Test */
// test all layers (Web Layer, Service Layer, Data Layer)
// UsersController.java, UsersService.java, UsersRepository.java
package com.appsdeveloperblog.tutorials.junit.ui.controllers;

import com.appsdeveloperblog.tutorials.junit.security.SecurityConstants;
import com.appsdeveloperblog.tutorials.junit.ui.response.UserRest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

//@SpringBootTest // Integration Test for all layers (Web Layer, Service Layer, Data Layer)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // this is by default = @SpringBootTest

// METHOD 1: to define server port number for test in application.properties, server.port
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

// METHOD 2: to replace server.port of application.properties
// this server.port has high priority over application.properties
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
//properties = {"server.port=8081", "hostname=192.168.0.2"})

// METHOD 3: to use a specific/different .properties file
// this server.port has high priority over application-test.properties
//@TestPropertySource(locations = "/application-test.properties",
//    properties = "server.port=8081")

// METHOD 4: to use random port
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// RANDOM_PORT has high priority than METHOD 1, 2, 3
// put start random port in application.properties, server.port=0

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // to apply order to my test methods
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // PER_CLASS: use the same instance for all test
                                                // to use authorizationToken in all tests
                                                // to avoid the default JUnit behavior of creating a new instance of a test class per test method.
                                                // to use the same authorizationToken is all test methods.
public class UsersControllerIntegrationTest {

    @Value("${server.port}") // get the value from .properties (METHOD 3)
    private int serverPort;

    // @LocalServerPort: pick up the actual port number when the embedded server is running.
    // for METHOD 4
    @LocalServerPort
    private int localServerPort;

    // to print values from properties
    // for METHOD 1 to 4
    @Test
    void contextLoads() {
        System.out.println("server.port= " + serverPort);
        System.out.println("localServerPort=" + localServerPort);
    }

    // TestRestTemplate: is API client to send request and receive packaged responses for testing.
    @Autowired
    private TestRestTemplate testRestTemplate;

    private String authorizationToken;

    /*
    test for create user:
    UsersController.java
    @PostMapping
    public UserRest createUser(@RequestBody @Valid UserDetailsRequestModel userDetails) throws Exception {

    for this integration test we need to create full request (header, body).

    usersService.createUser()
        usersRepository.save(userEntity) // is persisted (database)
     */
    @Test
    @DisplayName("User can be created")
    @Order(1)
        // create a new user
    void testCreateUser_whenValidDetailsProvided_returnsUserDetails() throws JSONException {
        // Arrange
            // create a JSON String
//        String createUserJson = "{\n" +
//            "   \"firstName\":\"Sergey\",\n" +
//            "   \"lastName\":\"Kargopolov\",\n + " +
//            "   \"email\":\"test3@test.com\",\n + " +
//            "   \"password\":\"12345678\",\n + " +
//            "   \"repeatPassword\":\"12345678\"\n" +
//            "}";
        JSONObject userDetailsRequestJson = new JSONObject();
        userDetailsRequestJson.put("firstName", "Sergey");
        userDetailsRequestJson.put("lastName", "Kargopolov");
        userDetailsRequestJson.put("email", "test@test.com");
        userDetailsRequestJson.put("password", "12345678");
        userDetailsRequestJson.put("repeatedPassword", "12345678");

            // set headers in HTTP request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

            // build the HTTP request
        HttpEntity<String> request = new HttpEntity<>(userDetailsRequestJson.toString(), headers);

        // Act
            // execute the request
        ResponseEntity<UserRest> createdUserDetailsEntity = testRestTemplate.postForEntity("/users",
                request,
                UserRest.class); // response data type
        UserRest createdUserDetails = createdUserDetailsEntity.getBody();

        // Assert
        Assertions.assertEquals(HttpStatus.OK, createdUserDetailsEntity.getStatusCode());
        Assertions.assertEquals(userDetailsRequestJson.getString("firstName"),
                createdUserDetails.getFirstName(),
                "Returned user's first name seems to be incorrect");

        Assertions.assertEquals(userDetailsRequestJson.getString("lastName"),
                createdUserDetails.getLastName(),
                "Returned user's lasst name seems to be incorrect");

        Assertions.assertEquals(userDetailsRequestJson.getString("email"),
                createdUserDetails.getEmail(),
                "Returned user's email seems to be incorrect");

        Assertions.assertFalse(createdUserDetails.getUserId().trim().isEmpty(),
                "User id should not be empty");
    }

    /*
    test to validate if JWT is missing, then my API endpoint responds back with 403:
    UsersController.java
    @RequestMapping("/users")

    test only authorization access token, for that no body.
    validate is JWT is missing, then my API endpoint responds back with 403.

    - Protected API endpoint with JWT:
    more used to get response of list of objects.

    to communicate with this API endpoint, our request will need to include a valid authorization access token.
    if we do not provide valid access token, then get back HTTP status code 403 (Forbidden).
    */
    @Test
    @DisplayName("GET /users requires JWT")
    @Order(2)
    void testGetUsers_whenMissingJWT_returns403() {
        // Arrange
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        HttpEntity requestEntity = new HttpEntity(null, headers); // body = null

        // Act
            // to send the HTTP, get request and then get back a list of objects
            // .exchange(): returns list of objects
            // get response status code, response header, response body
        ResponseEntity<List<UserRest>> response = testRestTemplate.exchange("/users",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<UserRest>>() {
                }); // response data type

        // Assert
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode(),
                "HTTP Status code 403 Forbidden should have been returned");
    }

    /*
    test Authentication (login credentials in database) and Authorization access token.
    WebSecurity.java, getAuthenticationFilter(), getAuthentication() from .unit.security package.

    This application uses Sprint Security and by default this API endpoint is protected, that means
    our HTTP get request will need to contain a valid JWT access token in Authorization header, otherwise
    return 403 Forbidden.

    Steps of Login:
    1. Create a user login

    2. Authentication
    WebSecurity.java: contains Authentication and Authorization
        getAuthenticationFilter(): configure the URL path to a login endpoint.
    AuthenticationFilter.java
        successfulAuthentication(): generate JWT access token and add this as Authorization header to the response
        and I also add UserID as additional HTTP header.
        These 2 headers are sent in the request and response: Authorization and UserID.

        validate login credentials from database (in-memory database) for Authorization.
        when valid user credentials are provided, it should return JWT access token in Authorization header.

    3. Authorization access token
    AuthorizationFilter.java
        getAuthentication(): after Authentication, get token from Authorization header and validate it.

    if user authentication is successful, HTTP status code = 200

    4. Use this token to test communication with protected API endpoints

    Order to test:
    1. create a user.
    2. login and get authorization access token.
    3. use this access token to test communication with protected API endpoints.
     */
    @Test
    @DisplayName("/login works")
    @Order(3)
    void testUserLogin_whenValidCredentialsProvided_returnedJWTinAuthorizationHeader() throws JSONException {

        // Arrange
            // create a JSON String, to validate login credentials from database (in-memory database) for Authorization
//        String loginCredentialsJson = "{\n" +
//            "   \"email\":\"test3@test.com\",\n + " +
//            "   \"password\":\"12345678\",\n + " +
//            "}";
        // or:
        JSONObject loginCredentials = new JSONObject();
        loginCredentials.put("email", "test@test.com");
        loginCredentials.put("password", "12345678");

        HttpEntity<String> request = new HttpEntity<>(loginCredentials.toString());

        // Act
            // Authentication:
            // .postForEntity(): returns response entity object.
            // "/users/login": is located in WebSecurity.java, getAuthenticationFilter().
        ResponseEntity response = testRestTemplate.postForEntity("/users/login",
                request,
                null); // response data type, no body (null)

            // Authorization:
            // get headers from Authentication (Authorization and UserID).
        authorizationToken = response.getHeaders().
                getValuesAsList(SecurityConstants.HEADER_STRING).get(0); // get header "Authorization"

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(),
                "HTTP Status code should be 200");
        Assertions.assertNotNull(authorizationToken,
                "Response should contain Authorization header with JWT");
        Assertions.assertNotNull(response.getHeaders().
                        getValuesAsList("UserID").get(0),
                "Response should contain UserID in a response header"); // get header "UserID"
    }

    /*
    test to get list of users:
    UsersController.java
    List<UserRest> getUsers

    test method that includes authorization access token in HTTP request.
     */
    @Test
    @DisplayName("GET /users works")
    @Order(4) // to GET users
    void testGetUsers_whenValidJWTProvided_returnUsers() throws JSONException {

        // Arrange
            // build the header:
            // include JWT access token (authorizationToken) in Authorization header.
            // authorizationToken is built from @Test @Order(3).
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(authorizationToken); // without this Authorization, this test results 403 FORBIDDEN

        HttpEntity requestEntity = new HttpEntity(headers);

        // Act
            // execute the request and get the response:
        ResponseEntity<List<UserRest>> response = testRestTemplate.exchange("/users",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<UserRest>>() {
                }); // response data type

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(),
                "HTTP Staus code should be 200");
        Assertions.assertTrue(response.getBody().size() == 1,
                "There should be exactly 1 user in the list");
    }
}

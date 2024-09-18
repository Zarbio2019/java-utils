/* Unit test */
// test REST Controller (UsersController.java)
package com.appsdeveloperblog.tutorials.junit.ui.controllers;

import com.appsdeveloperblog.tutorials.junit.service.UsersService;
import com.appsdeveloperblog.tutorials.junit.shared.UserDto;
import com.appsdeveloperblog.tutorials.junit.ui.request.UserDetailsRequestModel;
import com.appsdeveloperblog.tutorials.junit.ui.response.UserRest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.UUID;

/* @WebMvcTest
Spring Framework scan my SpringBoot application only for classes related to web player only.
for testing Web Layer
only UsersController.java
exclude Security Layer
 */
@WebMvcTest(controllers = UsersController.class,
excludeAutoConfiguration = {SecurityAutoConfiguration.class})
//@AutoConfigureMockMvc(addFilters = false) // because it is included in @WebMvcTest
//@MockBean({UsersServiceImpl.class}) // used for usersService
                                      // to specify all classes that implement UsersService
                                      // only use with @Autowired:
                                      //   @Autowired
                                      //   UsersService usersService;
public class UsersControllerWebLayerTest {

    @Autowired // inject MockMvc into this class
    private MockMvc mockMvc;

    @MockBean // @MockBean({UsersServiceImpl.class}) + @Autowired
              // if there are other classes that implement the same interface,
              // they will need to use @Qualifier annotation.
              // @Mock vs @MockBean:
              //   @MockBean: it will create a mock object and it will automatically put this mock
              //   object into sprint application context.
              //   @Mock: it will not automatically put this mock object into sprint application context
    //@Autowired
    UsersService usersService;

    private UserDetailsRequestModel userDetailsRequestModel;

    @BeforeEach
    void setup() {
        userDetailsRequestModel = new UserDetailsRequestModel();
        userDetailsRequestModel.setFirstName("Sergey");
        userDetailsRequestModel.setLastName("Kargopolov");
        userDetailsRequestModel.setEmail("test@test.com");
        userDetailsRequestModel.setPassword("12345678");
        userDetailsRequestModel.setRepeatPassword("12345678");
    }

    /*
    test for: UsersController.java
    @PostMapping
    public UserRest createUser(@RequestBody @Valid UserDetailsRequestModel userDetails) throws Exception {

    Can be called as a Integration test because is integrated with mock of the UsersService,
    and also is a Unit test because test an endpoint of post request.
    */
    @Test
    @DisplayName("User can be created")
    void testCreateUser_whenValidUserDetailsProvided_returnsCreatedUserDetails() throws Exception {

        // this test not involve Service Layer (UsersService) nor Repository (data) Layer

        // Arrange
            // mapping entity
//        UserDto userDto = new UserDto();
//        userDto.setFirstName("Sergey");
//        userDto.setLastName("Kargopolov");
//        userDto.setEmail("test@test.com");
//        userDto.setUserId((UUID.randomUUID().toString()));
        // or:
        UserDto userDto = new ModelMapper().map(userDetailsRequestModel, UserDto.class);

        userDto.setUserId((UUID.randomUUID().toString()));
        when(usersService.createUser(any(UserDto.class))).thenReturn(userDto);

            // build the request
            // mock the POST request
            // MockMvcRequestBuilders: mock for request
            // .content: can be an json text or object bean (userDetailsRequestModel)
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetailsRequestModel)); // convert Object to JSON String

        // Act
            // execute the request
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        UserRest createdUser = new ObjectMapper()
                .readValue(responseBodyAsString, UserRest.class); // convert JSON String to Object

        // Assert
        Assertions.assertEquals(userDetailsRequestModel.getFirstName(),
                createdUser.getFirstName(), "The returned user first name is most likely incorrect");

        Assertions.assertEquals(userDetailsRequestModel.getLastName(),
                createdUser.getLastName(), "The returned user last name is incorrect");

        Assertions.assertEquals(userDetailsRequestModel.getEmail(),
                createdUser.getEmail(), "The returned user mail is incorrect");

        Assertions.assertFalse(createdUser.getUserId().isEmpty(), "userId should into be empty");
    }

    /*
    test for:
    @PostMapping
    public UserRest createUser(@RequestBody @Valid UserDetailsRequestModel userDetails) throws Exception {

    Check if firstName is empty, then the response is 400 that means is not provide one of the required fields.
    */
    @Test
    @DisplayName("First name is not empty")
    void testCreateUser_whenFirstNameIsNotProvided_Returns400StatusCode() throws Exception {
        // Arrange
        userDetailsRequestModel.setFirstName("");

            // build the request
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetailsRequestModel));

        // Act
            // execute the request
            // use @Valid of the request
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        // Assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),
                mvcResult.getResponse().getStatus(),
                "Incorrect HTTP Status Code returned");
    }

    /*
    test for:
    @PostMapping
    public UserRest createUser(@RequestBody @Valid UserDetailsRequestModel userDetails) throws Exception {
    */
    @Test
    @DisplayName("First name cannot be shorter that 2 characters")
    void testCreateUser_whenFirstNameIsOnlyOneCharacter_returns400StatusCode() throws Exception {
        // Arrange
        userDetailsRequestModel.setFirstName("a");

            // build the request
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetailsRequestModel));

        // Act
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        // Assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),
                result.getResponse().getStatus(), "HTTP Status code is not set to 400");
    }
}
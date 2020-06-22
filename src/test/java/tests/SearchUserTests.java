package tests;

import dto.ListOfUsers;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.SearchUserService;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;


public class SearchUserTests {
    public static final String USER = "diwallace";
    public static final String SEARCH_USERS = "/search/users";

    private static SearchUserService searchUserService = new SearchUserService();
    private static ListOfUsers expectedResponse;
    private ListOfUsers retrievedUsers;
    private Response response;

    @BeforeClass
    public static void setUp() {
        expectedResponse = searchUserService.getExpectedResponseWithOneUser();
    }

    @Test
    public void statusCodeDeveSer200() {
        searchUserService.getResponse(USER, SEARCH_USERS);
    }

    @Test
    public void totalDeUsuariosDeveSerUm() {
        response = searchUserService.getResponse(USER, SEARCH_USERS);
        retrievedUsers = searchUserService.getBodyOfRequest(response);

        searchUserService.validateAmountOfUsersReturned(expectedResponse, retrievedUsers, 1);
    }

    @Test
    public void loginDeveSerIgualAoEsperado() {
        response = searchUserService.getResponse(USER, SEARCH_USERS);
        retrievedUsers = searchUserService.getBodyOfRequest(response);

        assertEquals(USER, retrievedUsers.getItems().get(0).getLogin());
    }

    @Test
    public void gravatarDeveRetornarVazio() {
        response = searchUserService.getResponse(USER, SEARCH_USERS);
        retrievedUsers = searchUserService.getBodyOfRequest(response);

        assertNull(retrievedUsers.getItems().get(0).getGravatarId());
    }
}

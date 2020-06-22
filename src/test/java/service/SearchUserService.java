package service;

import dto.ListOfUsers;
import dto.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchUserService {


    public Response getResponse(String user, String path) {
        return given()
            .spec(this.configRequest())
            .queryParam("q", user)
        .when()
            .get(path)
        .then()
            .statusCode(200)
            .extract()
                .response();
    }

    public ListOfUsers getBodyOfRequest(Response response) {
        return response.as(ListOfUsers.class);
    }

    public void validateAmountOfUsersReturned(ListOfUsers expectedUsers, ListOfUsers retrievedUsers, int total) {
        assertEquals(expectedUsers.getTotalCount(), retrievedUsers.getTotalCount());
        assertEquals(total, retrievedUsers.getItems().size());
        assertTrue(!retrievedUsers.isIncompleteResults());
    }

    public ListOfUsers getExpectedResponseWithOneUser() {
        List<User> users = new ArrayList<>();

        users.add(new User()
                .setLogin("diwallace")
                .setId(17225742L)
                .setNodeId("MDQ6VXNlcjE3MjI1NzQy")
                .setAvatarUrl("https://avatars3.githubusercontent.com/u/17225742?v=4")
                .setGravatarId("")
                .setUrl("https://api.github.com/users/diwallace")
                .setHtmlUrl("https://github.com/diwallace")
                .setFollowersUrl("https://api.github.com/users/diwallace/followers")
                .setFollowingUrl("https://api.github.com/users/diwallace/following{/other_user}")
                .setGistsUrl("https://api.github.com/users/diwallace/gists{/gist_id}")
                .setStarredUrl("https://api.github.com/users/diwallace/starred{/owner}{/repo}")
                .setSubscriptionsUrl("https://api.github.com/users/diwallace/subscriptions")
                .setOrganizationsUrl("https://api.github.com/users/diwallace/orgs")
                .setReposUrl("https://api.github.com/users/diwallace/repos")
                .setEventsUrl("https://api.github.com/users/diwallace/events{/privacy}")
                .setReceivedEventsUrl("https://api.github.com/users/diwallace/received_events")
                .setType("User")
                .setSiteAdmin(false)
                .setScore(1.0));

        return new ListOfUsers()
                .setTotalCount(1)
                .setIncompleteResults(false)
                .setItems(users);
    }

    private RequestSpecification configRequest() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setRelaxedHTTPSValidation()
                .setBaseUri(Utils.getUri())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }
}
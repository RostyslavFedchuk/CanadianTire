package org.canadian.tire.api.service;

import io.restassured.response.Response;
import org.canadian.tire.api.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserService extends BaseService {

    private static final String USERS_ENDPOINT = "/users";
    private static final String USER_ENDPOINT = "/users/%s";

    public Response getAllUsers() {
        return restClient.get(USERS_ENDPOINT);
    }

    public Response createUser(User user) {
        return restClient.postWthAuth(USERS_ENDPOINT, user);
    }

    public Response getUsersDetails(User user) {
        return restClient.getWithAuth(USER_ENDPOINT.formatted(user.getId()));
    }

    public Response updateUserDetails(User user) {
        return restClient.putWithAuth(USER_ENDPOINT.formatted(user.getId()), user);
    }

    public Response deleteUser(User user) {
        return restClient.deleteWithAuth(USER_ENDPOINT.formatted(user.getId()));
    }
}

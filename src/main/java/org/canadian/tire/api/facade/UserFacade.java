package org.canadian.tire.api.facade;

import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import org.canadian.tire.api.model.User;
import org.canadian.tire.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

@Component
public class UserFacade {

    @Autowired
    private UserService userService;

    @Step("Get all users")
    public List<User> getAllUsers() {
        return List.of(userService.getAllUsers()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(User[].class));
    }

    @Step("Create new user: {user.name}")
    public User createNewUser(User user) {
        return userService.createUser(user)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(User.class);
    }

    @Step("Get user details for user: {user.id}")
    public User getUserDetails(User user) {
        return userService.getUsersDetails(user)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(User.class);
    }

    @Step("Verify user is not existing: {user.id}")
    public void verifyUserNotExisting(User user) {
        userService.getUsersDetails(user)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("message", equalTo("Resource not found"));
    }

    @Step("Update user details for user: {user.id}")
    public User updateUserDetails(User user) {
        return userService.updateUserDetails(user)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(User.class);
    }

    @Step("Delete user: {user.id}")
    public void deleteUser(User user) {
        userService.deleteUser(user)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}

package org.canadian.tire.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.canadian.tire.api.facade.UserFacade;
import org.canadian.tire.api.model.User;
import org.canadian.tire.util.CucumberUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.canadian.tire.util.Validator.*;

public class UsersSteps {

    @Autowired
    private UserFacade userFacade;

    private static List<User> allUsers;
    private static User newUser;

    @Given("a list of non-empty users and a new user to create")
    public void aListOfNonEmptyUsersAndANewUserToCreate(List<List<String>> data) {
        allUsers = userFacade.getAllUsers();
        newUser = CucumberUtil.convertToUserModel(data);

        verifyUserNonEmpty(allUsers);
        verifyUserNotExisting(allUsers, newUser);
        userFacade.verifyUserNotExisting(newUser);
    }

    @When("I create a new user")
    public void iCreateANewUser() {
        User createdUser = userFacade.createNewUser(newUser);
        newUser.setId(createdUser.getId());
        verifyUsersEquality(createdUser, newUser, "User was not created properly");
    }

    @Then("I should see created user in the list of users")
    public void iShouldSeeCreatedUserInTheListOfUsers() {
        User actualUserDetails = userFacade.getUserDetails(newUser);
        verifyUsersEquality(actualUserDetails, newUser, "User details are not correct");
    }

    @Given("a created user")
    public void aCreatedUser() {
    }

    @When("I update the user")
    public void iUpdateTheUser(List<List<String>> data) {
        User updatedUserModel = CucumberUtil.convertToUserModel(data);
        updatedUserModel.setId(newUser.getId());

        User updatedUser = userFacade.updateUserDetails(updatedUserModel);
        verifyUsersEquality(updatedUser, updatedUserModel, "User was not updated properly");
        newUser = updatedUser;
    }

    @Then("I should see updated user in the list of users")
    public void iShouldSeeUpdatedUserInTheListOfUsers() {
        User actualUserDetails = userFacade.getUserDetails(newUser);
        verifyUsersEquality(actualUserDetails, newUser, "User details are not correct after update");
    }

    @When("I delete the user")
    public void iDeleteTheUser() {
        userFacade.deleteUser(newUser);
    }

    @Then("I should not see deleted user in the list of users")
    public void iShouldNotSeeDeletedUserInTheListOfUsers() {
        allUsers = userFacade.getAllUsers();
        verifyUserNotExisting(allUsers, newUser);
        userFacade.verifyUserNotExisting(newUser);
    }
}

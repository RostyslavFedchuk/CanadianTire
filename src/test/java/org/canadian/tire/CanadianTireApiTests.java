package org.canadian.tire;

import org.canadian.tire.api.facade.UserFacade;
import org.canadian.tire.api.model.User;
import org.canadian.tire.spring.SpringConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

import static org.canadian.tire.api.data.UserModels.getNewUserModel;
import static org.canadian.tire.api.data.UserModels.getUpdatedUserModel;
import static org.canadian.tire.api.validator.UserValidatorManager.*;

@SpringBootTest
@ContextConfiguration(classes = {SpringConfig.class})
public class CanadianTireApiTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserFacade userFacade;

    @Test
    public void verifyNewUserEndToEndFlow() {
        User newUser = getNewUserModel();

        List<User> allUsers = userFacade.getAllUsers();
        verifyUserNonEmpty(allUsers);
        verifyUserNotExisting(allUsers, newUser);
        userFacade.verifyUserNotExisting(newUser);

        User createdUser = userFacade.createNewUser(newUser);
        newUser.setId(createdUser.getId());
        verifyUsersEquality(createdUser, newUser, "User was not created properly");

        User userById = userFacade.getUserDetails(createdUser);
        verifyUsersEquality(userById, createdUser, "User details are not correct");

        User updatedUserModel = getUpdatedUserModel(userById);
        User updatedUser = userFacade.updateUserDetails(updatedUserModel);
        verifyUsersEquality(updatedUser, updatedUserModel, "User was not updated properly");

        userById = userFacade.getUserDetails(createdUser);
        verifyUsersEquality(userById, updatedUserModel, "User details are not correct after update");

        userFacade.deleteUser(updatedUser);
        allUsers = userFacade.getAllUsers();
        verifyUserNotExisting(allUsers, updatedUser);
        userFacade.verifyUserNotExisting(updatedUser);
    }
}

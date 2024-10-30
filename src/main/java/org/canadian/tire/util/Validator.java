package org.canadian.tire.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.canadian.tire.api.model.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Validator {

    public static void verifyUsersEquality(User originalUser, User userToCompare, String errorMessage) {
        assertThat(originalUser)
                .withFailMessage(errorMessage)
                .isEqualTo(userToCompare);
    }

    public static void verifyUserNotExisting(List<User> users, User userToCompare) {
        assertThat(users)
                .withFailMessage("User with id %s already exists", userToCompare.getId())
                .noneMatch(user -> user.getId().equals(userToCompare.getId()));
    }

    public static void verifyUserNonEmpty(List<User> users) {
        assertThat(users)
                .withFailMessage("User's list is empty")
                .isNotEmpty();
    }
}

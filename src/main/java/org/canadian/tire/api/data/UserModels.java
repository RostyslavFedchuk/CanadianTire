package org.canadian.tire.api.data;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.canadian.tire.api.model.User;
import org.canadian.tire.ui.model.TireModel;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserModels {

    private final static Faker FAKER = new Faker();

    public static User getNewUserModel() {
        return User.builder()
                .name(FAKER.name().fullName())
                .email(FAKER.internet().emailAddress())
                .gender(FAKER.options().option("male", "female"))
                .status(FAKER.options().option("active", "inactive"))
                .build();
    }

    public static User getUpdatedUserModel(User user) {
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .email(FAKER.internet().emailAddress())
                .gender(user.getGender())
                .status(FAKER.options().option("active", "inactive"))
                .build();
    }
}

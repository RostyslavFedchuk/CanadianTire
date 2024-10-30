package org.canadian.tire.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.canadian.tire.api.model.User;
import org.canadian.tire.ui.model.TireModel;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CucumberUtil {

    public static TireModel convertToTireModel(List<List<String>> data) {
        List<TireModel> tireModels = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            TireModel tireModel = new TireModel();
            for (int j = 0; j < data.get(i).size(); j++) {
                if (data.get(0).get(j).equals("searchKeyword")) {
                    tireModel.setSearchKeyword(data.get(i).get(j));
                } else if (data.get(0).get(j).equals("name")) {
                    tireModel.setName(data.get(i).get(j));
                } else if (data.get(0).get(j).equals("width")) {
                    tireModel.setWidth(data.get(i).get(j));
                } else if (data.get(0).get(j).equals("aspectRatio")) {
                    tireModel.setAspectRatio(data.get(i).get(j));
                } else if (data.get(0).get(j).equals("diameter")) {
                    tireModel.setDiameter(data.get(i).get(j));
                } else if (data.get(0).get(j).equals("tireCount")) {
                    tireModel.setTireCount(Integer.valueOf(data.get(i).get(j)));
                }
                tireModels.add(tireModel);
            }
        }
        return tireModels.stream().findFirst().orElseThrow();
    }

    public static User convertToUserModel(List<List<String>> data) {
        List<User> users = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            User user = new User();
            for (int j = 0; j < data.get(i).size(); j++) {
                if (data.get(0).get(j).equals("name")) {
                    user.setName(data.get(i).get(j));
                } else if (data.get(0).get(j).equals("email")) {
                    user.setEmail(data.get(i).get(j));
                } else if (data.get(0).get(j).equals("gender")) {
                    user.setGender(data.get(i).get(j));
                } else if (data.get(0).get(j).equals("status")) {
                    user.setStatus(data.get(i).get(j));
                }
                users.add(user);
            }
        }
        return users.stream().findFirst().orElseThrow();
    }
}

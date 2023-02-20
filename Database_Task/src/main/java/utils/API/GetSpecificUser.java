package utils.API;

import models.API.User;

import java.util.List;

public class GetSpecificUser {

    public static User getUserFromListById(List<User> users, int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}

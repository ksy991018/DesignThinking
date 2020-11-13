package com.icemelon.scheduler.entity.strategy;

import com.icemelon.scheduler.dto.Password;
import com.icemelon.scheduler.dto.UserId;
import com.icemelon.scheduler.entity.User;

import java.util.Collection;

public class DefaultLoginStrategy implements  ILoginStrategy {

    @Override
    public void login(Collection<User> users, UserId id, Password password) throws Exception {

        User result = search(users, id);

        if (result == null) {

            
        }
    }

    private User search(Collection<User> users, UserId id) {

        for (User user : users) {

            if (user.getId().equals(id)) return user;
        }

        return null;
    }
}

package com.icemelon.scheduler.entity.strategy;

import com.icemelon.scheduler.dto.Password;
import com.icemelon.scheduler.dto.UserId;
import com.icemelon.scheduler.entity.User;
import com.icemelon.scheduler.exception.LoginException;

import java.util.Collection;

public class DefaultLoginStrategy implements  ILoginStrategy {

    @Override
    public void login(Collection<User> users, UserId id, Password password) throws LoginException {

        User result = search(users, id);

        if (result == null) {

            addUser(users, id, password);

            return;
        }

        if (!result.matchPassword(password)) {

            throw new LoginException();
        }

    }

    private void addUser(Collection<User> users , UserId id, Password password) {

        User user = new User(id, password);

        users.add(user);


    }
    private User search(Collection<User> users, UserId id) {

        for (User user : users) {

            if (user.getId().equals(id)) return user;
        }

        return null;
    }
}

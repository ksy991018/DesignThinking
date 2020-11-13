package com.icemelon.scheduler.entity.strategy;

import com.icemelon.scheduler.dto.Password;
import com.icemelon.scheduler.dto.UserId;
import com.icemelon.scheduler.entity.User;

import java.util.Collection;

public interface ILoginStrategy {

    public void login(Collection<User> users, UserId id, Password password) throws Exception;  //TODO - make loginexception class
}

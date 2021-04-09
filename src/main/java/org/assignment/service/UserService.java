package org.assignment.service;

import org.assignment.model.UserModel;

public interface UserService {

    void addUser(UserModel user);
    boolean authenticateUser(UserModel user);
}

package org.assignment.controller;

import org.assignment.model.UserModel;
import org.assignment.service.UserService;
import org.assignment.service.UserServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @PostMapping("/login")
    public String registerUser(@RequestParam("password") String password,
                               @RequestParam("username") String username) {


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("org.assignment.service");
        context.refresh();

        UserService userService = context.getBean(UserServiceImpl.class);

        UserModel user = new UserModel();
        user.setUserName(username);
        user.setPassword(password);

        if(userService.authenticateUser(user)){
            return "search";
        }
        return "index";
    }

}

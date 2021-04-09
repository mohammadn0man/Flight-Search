package org.assignment.controller;

import org.assignment.model.UserModel;
import org.assignment.service.UserService;
import org.assignment.service.UserServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @PostMapping("/register")
    public String registerUser(@RequestParam("full_name") String fullName,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("username") String username) {

        UserModel user = new UserModel(fullName, username, email, password);
        System.out.println(user);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("org.assignment.service");
        context.refresh();

        UserService userService = context.getBean(UserServiceImpl.class);

        userService.addUser(user);

        return "index";
    }


}

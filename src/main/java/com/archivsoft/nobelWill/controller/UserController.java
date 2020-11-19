package com.archivsoft.nobelWill.controller;

import com.archivsoft.nobelWill.payload.SignRequest;
import com.archivsoft.nobelWill.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;


@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/hello")
    public ModelAndView helloPage() {
        return new ModelAndView("init");
    }

    @GetMapping("/signin")
    public ModelAndView signInPage() {
        return new ModelAndView("login");
    }

    @PostMapping("/signin")
    public ModelAndView signIn() {
        return new ModelAndView("login");
    }

//    @GetMapping("/signup")
//    public ModelAndView signupPage(@RequestBody User user) {
//        return new ModelAndView("joinForm", userService.createUser(user));
//    }

    @PostMapping("/signup")
    public void signup(@ModelAttribute SignRequest signRequest) throws ServletException, IOException {
        userService.createUser(signRequest);
    }

    @RequestMapping("/denied")
    public String getDeni() {
        return "denied";
    }

}


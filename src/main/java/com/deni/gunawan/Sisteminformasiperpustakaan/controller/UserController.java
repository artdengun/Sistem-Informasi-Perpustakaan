package com.deni.gunawan.Sisteminformasiperpustakaan.controller;


import com.deni.gunawan.Sisteminformasiperpustakaan.model.User;
import com.deni.gunawan.Sisteminformasiperpustakaan.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    void injectDependency(UserService userService) {
        this.userService = userService;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView signIn() {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public ModelAndView signUp() {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("register");

        return model;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User userExist = userService.findByEmail(user.getEmail());

        if (userExist != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exist!");
        }

        if (bindingResult.hasErrors()) {
            model.setViewName("register");
        } else {
            userService.save(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("register");
            model.setViewName("register");
        }

        return model;
    }

    @RequestMapping(value = {"/accept"}, method = RequestMethod.POST)
    public ModelAndView acceptUser(@RequestParam(name = "id") String id) {
        ModelAndView model = new ModelAndView();
        Optional<User> currentUser = userService.findById(id);
        currentUser.ifPresent(user -> {
            user.setActive(1);
            userService.updateStatus(user);
            model.setViewName("redirect:/dashboard#user");
        });
        return model;
    }

    @RequestMapping(value = {"/reject"}, method = RequestMethod.POST)
    public ModelAndView rejectUser(@RequestParam(name = "id") String id) {
        ModelAndView model = new ModelAndView();
        Optional<User> currentUser = userService.findById(id);
        currentUser.ifPresent(user -> {
            user.setActive(2);
            userService.save(user);
            model.setViewName("redirect:/dashboard");
        });
        return model;
    }

    @RequestMapping(value = {"/access_denied"}, method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }
}
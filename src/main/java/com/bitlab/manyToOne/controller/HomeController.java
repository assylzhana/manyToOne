package com.bitlab.manyToOne.controller;

import com.bitlab.manyToOne.models.User;
import com.bitlab.manyToOne.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.bitlab.manyToOne.models.ApplicationRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.bitlab.manyToOne.services.ApplicationRequestService;
import com.bitlab.manyToOne.config.*;

import java.util.List;

@Controller
public class HomeController {


    @Autowired
    private ApplicationRequestService applicationRequestService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String homePage(Model model){
        List<ApplicationRequest> task =  applicationRequestService.getTasks();
        model.addAttribute("task",task);
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("newRequest", new ApplicationRequest());
        return "ex";
    }

    @PostMapping("/add-request")
    public String addRequest(@ModelAttribute("newRequest") ApplicationRequest newRequest) {
        User newUser = new User();
        newUser.setFullName(newRequest.getUser().getFullName());
        User savedUser = userService.addUser(newUser);

        newRequest.setUser(savedUser);
        newRequest.setHandled(false);
        applicationRequestService.addNewTask(newRequest);

        return "redirect:/";
    }
    @GetMapping("/details/{id}")
    public String detailsPage(@PathVariable Long id,Model model){
        ApplicationRequest task = applicationRequestService.getTaskById(id);
        model.addAttribute("task",task);
        return "details";
    }
    @PostMapping("/delete-task/{id}")
    public String deletePage(@PathVariable Long id){
        applicationRequestService.deleteTask(id);
        return "redirect:/";
    }
    @PostMapping("/done/{id}")
    public String makeDone(@PathVariable Long id){
        applicationRequestService.markTaskAsHandled(id);
        return "redirect:/";
    }
}
//  xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
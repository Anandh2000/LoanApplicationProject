package com.dualtricks.assignment.controller;

import com.dualtricks.assignment.repository.UserDataRepository;
import com.dualtricks.assignment.entity.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserDataRepository userDataRepository;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model, RedirectAttributes redirectAttributes) {
        UserData user = userDataRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {

            redirectAttributes.addAttribute("username", username);
            return "redirect:/client-details";
        } else {
            // Login failed, display error message
            model.addAttribute("error", true);
            return "login";
        }
    }

    @PostMapping("/admin-login")
    public String adminLogin(String username, String password, Model model) {
        if ("admin".equals(username) && "admin".equals(password)) {
            return "redirect:/client-list";
        } else {
            model.addAttribute("error", true);
            return "redirect:/admin-login";
        }
    }

    @GetMapping("/admin-login")
    public String showAdminLoginForm() {
        return "admin-login";
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(String username, String password, String confirmPassword,
                         String phone, String email, Model model) {
        if (!password.equals(confirmPassword)) {
            // Password and confirmation password don't match, display error message
            model.addAttribute("error", "Passwords do not match");
            return "signup";
        }

        if (userDataRepository.findByUsername(username) != null) {
            // Username already exists, display error message
            model.addAttribute("error", "Username already exists");
            return "signup";
        }

        // Create new user and save to database
        UserData newUser = new UserData();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setPhone(phone);
        newUser.setEmail(email);
        userDataRepository.save(newUser);

        // Signup successful, redirect to success page
        return "redirect:/signup-success";
    }

    @GetMapping("/signup-success")
    public String showSignupSuccessPage() {
        return "signup-success";
    }
}


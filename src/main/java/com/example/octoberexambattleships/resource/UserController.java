package com.example.octoberexambattleships.resource;

import com.example.octoberexambattleships.domain.model.binding.UserLoginBindingModel;
import com.example.octoberexambattleships.domain.model.binding.UserRegisterBindingModel;
import com.example.octoberexambattleships.domain.model.service.UserServiceModel;
import com.example.octoberexambattleships.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    public UserController(UserService userService, ModelMapper modelMapper, HttpSession httpSession) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.httpSession = httpSession;
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (this.httpSession.getAttribute("user") != null) {
            return "redirect:/home";
        }

        if (!model.containsAttribute("userFound")) {
            model.addAttribute("userFound", true);
        }

        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute(
                            "userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);

            return "redirect:/users/login";
        }

        UserServiceModel user =
                this.userService.findByUsernameAndPassword(
                        userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());

        if (user == null) {
            redirectAttributes
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("userFound", false);
            return "redirect:/users/login";
        }

        this.httpSession.setAttribute("user", user);

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        this.httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register() {
        if (this.httpSession.getAttribute("user") != null) {
            return "redirect:/home";
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute(
                            "userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
                bindingResult.addError(
                        new FieldError("differentConfirmPassword", "confirmPassword", "Passwords must be the same."));
            }

            return "redirect:/users/register";
        }

        this.userService.register(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:/users/login";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }
}

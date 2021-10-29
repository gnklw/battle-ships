package com.example.octoberexambattleships.resource;

import com.example.octoberexambattleships.domain.model.binding.HomeBindingModel;
import com.example.octoberexambattleships.domain.model.service.UserServiceModel;
import com.example.octoberexambattleships.domain.model.view.ShipViewModel;
import com.example.octoberexambattleships.service.ShipService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final ShipService shipService;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    public HomeController(ShipService shipService, ModelMapper modelMapper, HttpSession httpSession) {
        this.shipService = shipService;
        this.modelMapper = modelMapper;
        this.httpSession = httpSession;
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (this.httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }

        UserServiceModel currentUser = modelMapper.map(httpSession.getAttribute("user"), UserServiceModel.class);
        Collection<ShipViewModel> ships = this.shipService.getAllShips();

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("allShips", ships);
        model.addAttribute("attackerShips",
                ships.stream().filter(e -> e.getUser().getId().equals(currentUser.getId())).collect(Collectors.toList()));
        model.addAttribute("defenderShips",
                ships.stream().filter(e -> !e.getUser().getId().equals(currentUser.getId())).collect(Collectors.toList()));

        return "home";
    }

    @PostMapping("/home")
    public String battle(@Valid HomeBindingModel homeBindingModel, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (this.httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute(
                            "homeBindingModel", homeBindingModel)
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.homeBindingModel", bindingResult);

            return "redirect:/home";
        }

        this.shipService.fight(homeBindingModel);

        return "redirect:/home";
    }


    @ModelAttribute
    public HomeBindingModel homeBindingModel() {
        return new HomeBindingModel();
    }
}

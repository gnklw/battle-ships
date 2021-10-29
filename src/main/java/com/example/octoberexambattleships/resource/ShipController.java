package com.example.octoberexambattleships.resource;

import com.example.octoberexambattleships.domain.model.binding.AddShipBindingModel;
import com.example.octoberexambattleships.domain.model.service.ShipServiceModel;
import com.example.octoberexambattleships.domain.model.service.UserServiceModel;
import com.example.octoberexambattleships.service.ShipService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/ships")
public class ShipController {

    private final ShipService shipService;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    public ShipController(ShipService shipService, ModelMapper modelMapper, HttpSession httpSession) {
        this.shipService = shipService;
        this.modelMapper = modelMapper;
        this.httpSession = httpSession;
    }

    @GetMapping("/add")
    public String addShip() {
        if (this.httpSession.getAttribute("user") == null) {
            return "redirect:/users/login";
        }

        return "ship-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid AddShipBindingModel addShipBindingModel, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (this.httpSession.getAttribute("user") == null) {
            return "redirect:/users/login";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute(
                            "addShipBindingModel", addShipBindingModel)
                    .addFlashAttribute(
                            "org.springframework.validation.BindingResult.addShipBindingModel", bindingResult);

            return "redirect:/ships/add";
        }

        ShipServiceModel ship = this.modelMapper.map(addShipBindingModel, ShipServiceModel.class);
        UserServiceModel currentUser = modelMapper.map(httpSession.getAttribute("user"), UserServiceModel.class);
        this.shipService.add(ship, currentUser);

        return "redirect:/home";
    }

    @ModelAttribute
    public AddShipBindingModel addShipBindingModel() {
        return new AddShipBindingModel();
    }
}

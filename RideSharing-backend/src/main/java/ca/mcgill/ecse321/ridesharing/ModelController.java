package ca.mcgill.ecse321.ridesharing;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ModelController {

    @GetMapping("/PassSignUp/Destination")
    public Passenger greetingForm(Model model) {
        model.addAttribute("greeting", Passenger passOne = new Passenger());
        return passOne;
    }

    @PostMapping("/PassSignUp/Destination")
    public String greetingSubmit(@ModelAttribute Passenger greeting) {
        return "result";
    }

}
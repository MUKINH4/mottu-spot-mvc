package mottu_spot.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/moto")
public class MotoController {

    @GetMapping
    public String moto(Model model){
        return "moto";
    }

}

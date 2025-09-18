package mottu_spot.mvc.controller;

import mottu_spot.mvc.model.Moto;
import mottu_spot.mvc.model.Patio;
import mottu_spot.mvc.service.MotoService;
import mottu_spot.mvc.service.PatioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/motos")
public class MotoController {

    private final MotoService motoService;
    private final PatioService patioService;

    public MotoController(MotoService motoService, PatioService patioService) {
        this.motoService = motoService;
        this.patioService = patioService;
    }

    @GetMapping
    public String moto(Model model){
        return "moto";
    }

    @PostMapping
    public String criarMoto(@ModelAttribute Moto moto, @RequestParam Long patioId) {

        Patio patio = patioService.encontrarPatio(patioId);

        moto.setPatio(patio);

        motoService.criarMoto(moto);
        return "redirect:/patios/" + patioId;
    }

    @GetMapping("/adicionarMoto")
    public String adicionarMoto(@RequestParam Long patioId, Model model) {
        Patio patio = patioService.encontrarPatio(patioId);
        model.addAttribute("patio", patio);
        model.addAttribute("moto", new Moto());
        return "adicionarMoto";
    }

}

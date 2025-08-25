package mottu_spot.mvc.controller;

import mottu_spot.mvc.model.Moto;
import mottu_spot.mvc.model.Patio;
import mottu_spot.mvc.repository.PatioRepository;
import mottu_spot.mvc.service.MotoService;
import mottu_spot.mvc.service.PatioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class PatioController {

    private final PatioService patioService;
    private final MotoService motoService;
    public PatioController(PatioService patioService, MotoService motoService) {
        this.patioService = patioService;
        this.motoService = motoService;
    }

    @GetMapping
    public String index(Model model) {
        List<Patio> patios = patioService.listarPatios();
        model.addAttribute("patios", patios);
        model.addAttribute("patio", new Patio());
        model.addAttribute("headerAction", "patio");
        return "index";
    }

    @GetMapping("/patios/{id}")
    public String motosPorPatio(Model model, @PathVariable Long id){
        Patio patio = patioService.encontrarPatio(id);
        List<Moto> motos = motoService.encontrarMotoPorPatio(id);

        model.addAttribute("patio", patio);
        model.addAttribute("motos", motos);
        model.addAttribute("moto", new Moto());
        model.addAttribute("headerAction", "moto");
        return "motosPorPatio";
    }

    @PostMapping("/patios")
    public String salvarPatio(@ModelAttribute Patio patio) {
        patioService.salvarPatio(patio);
        return "redirect:/";
    }

    @DeleteMapping("/patios/delete/{id}")
    public ResponseEntity<Void> deletarPatio(@PathVariable Long id) {
        patioService.deletePatio(id);
        return ResponseEntity.ok().build();
    }

}

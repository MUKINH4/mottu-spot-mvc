package mottu_spot.mvc.controller;

import mottu_spot.mvc.model.Patio;
import mottu_spot.mvc.repository.PatioRepository;
import mottu_spot.mvc.service.PatioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class PatioController {

    private final PatioService patioService;

    public PatioController(PatioService patioService) {
        this.patioService = patioService;
    }

    @GetMapping
    public String index(Model model) {
        List<Patio> patios = patioService.listarPatios();
        model.addAttribute("patios", patios);
        model.addAttribute("patio", new Patio());
        return "index";
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

package mottu_spot.mvc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import mottu_spot.mvc.model.Moto;
import mottu_spot.mvc.model.Patio;
import mottu_spot.mvc.service.MotoService;
import mottu_spot.mvc.service.PatioService;

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
        // Não passa patio para o header, garante que será null
        return "index";
    }

    @GetMapping("/adicionarPatio")
    public String adicionarPatio(Model model) {
        // Não passa patio para o header, garante que será null
        return "adicionarPatio";
    }

    @GetMapping("/patios/{id}")
    public String motosPorPatio(Model model, @PathVariable Long id){
        Patio patio = patioService.encontrarPatio(id);
        List<Moto> motos = motoService.encontrarMotoPorPatio(id);
        patio.setMotos(motos);

        model.addAttribute("patio", patio);
        model.addAttribute("motos", motos);
        model.addAttribute("moto", new Moto());
        model.addAttribute("headerAction", "moto");
        return "motosPorPatio";
    }

    @PostMapping("/patios")
    public String salvarPatio(@Valid @ModelAttribute Patio patio, BindingResult result, RedirectAttributes redirect) {

        if (result.hasErrors()) {
            return "adicionarPatio";
        }

        patioService.salvarPatio(patio);

        redirect.addFlashAttribute("message", "Pátio criado com sucesso");
        return "redirect:/";
    }

    @DeleteMapping("/patios/delete/{id}")
    public ResponseEntity<Void> deletarPatio(@PathVariable Long id) {
        patioService.deletePatio(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/patios/edit/{id}")
    public String editarPatio(@PathVariable Long id, @Valid @ModelAttribute Patio patio, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "index";
        }

        patioService.editarPatio(id, patio);
        redirect.addFlashAttribute("message", "Pátio editado com sucesso");
        return "redirect:/";
    }
}

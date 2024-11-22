package sn.niit.gestionbudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.niit.gestionbudget.model.Departement;
import sn.niit.gestionbudget.service.DepartementService;

@Controller
@RequestMapping("/departements")
public class DepartementController {
    @Autowired
    private final DepartementService departementService;

    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    @GetMapping
    public String getAllDepartement(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Departement> departementPage = departementService.getAllDepartement(pageable);

        model.addAttribute("listdepartements", departementPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", departementPage.getTotalPages());

        return "list-departement";
    }

    @GetMapping("/new")
    public String newDepartementForm(Departement departement) {
        return "new-departement";
    }

    @PostMapping("/save")
    public String saveDepartement(@ModelAttribute("departement") Departement departement) {
        departementService.addDepartement(departement);
        return "redirect:/departements";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartement(@PathVariable("id") Long id) {
        departementService.deleteDepartement(id);
        return "redirect:/departements";
    }

    @GetMapping("/edit/{id}")
    public String departementUpdateForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("departement", departementService.getDepartementById(id));
        return "update-departement";
    }

    @PostMapping("/update/{id}")
    public String updateDepartement(@PathVariable Long id, Departement departement, Model model) {
        departementService.updateDepartement(departement, id);
        return "redirect:/departements";
    }
}

package sn.niit.gestionbudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.niit.gestionbudget.model.Depense;
import sn.niit.gestionbudget.repository.DepenseRepository;
import sn.niit.gestionbudget.service.CategorieService;
import sn.niit.gestionbudget.service.DepartementService;
import sn.niit.gestionbudget.service.DepenseService;

@RequestMapping("/depenses")
@Controller
public class DepenseController {
    @Autowired
    private final DepenseService depenseService;
    private final CategorieService categorieService;
    private final DepartementService departementService;
    public DepenseController(DepenseService depenseService, CategorieService categorieService, DepartementService departementService) {
        this.depenseService = depenseService;
        this.categorieService = categorieService;
        this.departementService = departementService;
    }

    @GetMapping
    public String getAllDepense(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Depense> depensePage = depenseService.getDepenses(pageable);

        model.addAttribute("listdepenses", depensePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", depensePage.getTotalPages());
        return "list-depenses";
    }



    @GetMapping("/new")
    public String newDepenseForm(Depense depense, Model model)
    {
        model.addAttribute("categories", categorieService.getAllCategorie());
        model.addAttribute("departements", departementService.getAllDepartement());
        return "new-depense";
    }
    @PostMapping("/save")
    public String saveDepense(@ModelAttribute("depense") Depense depense){
        depenseService.addDepense(depense);
        return "redirect:/depenses";
    }
    @GetMapping("/delete/{id}")
    public String deleteDepense(@PathVariable("id") Long id){
        depenseService.deleteDepense(id);
        return "redirect:/depenses";
    }
    @GetMapping("/edit/{id}")
    public String depenseUpdateForm(Model model, @PathVariable("id") Long id){
        model.addAttribute("departements", departementService.getAllDepartement());
        model.addAttribute("categories", categorieService.getAllCategorie());
        model.addAttribute("depense", depenseService.getDepenseById(id));
        return "update-depense";
    }

    @PostMapping("/update/{id}")
    public String updateDepense(@PathVariable Long id, Depense depense, Model model){
       depenseService.updateDepense(depense, id);
        return "redirect:/depenses";
    }

}

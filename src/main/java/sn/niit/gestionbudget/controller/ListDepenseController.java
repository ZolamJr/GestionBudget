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

@RequestMapping("/listdepense")
@Controller
public class ListDepenseController {
    @Autowired
    private final DepenseService depenseService;
    private final CategorieService categorieService;
    private final DepartementService departementService;
    public ListDepenseController(DepenseService depenseService, CategorieService categorieService, DepartementService departementService) {
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
        return "listdep";
    }
}
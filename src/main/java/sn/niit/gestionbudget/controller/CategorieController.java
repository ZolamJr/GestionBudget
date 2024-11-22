package sn.niit.gestionbudget.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.niit.gestionbudget.model.Budget;
import sn.niit.gestionbudget.model.Categorie;
import sn.niit.gestionbudget.model.Depense;
import sn.niit.gestionbudget.service.CategorieService;


@RequestMapping("/categories")
@Controller
public class CategorieController {
    @Autowired
    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }


    @GetMapping
    public String getAllCategorie(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Categorie> categoriePage = categorieService.getAllCategorie(pageable);

        model.addAttribute("listcategorie", categoriePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", categoriePage.getTotalPages());

        return "list-categories";
    }
    @GetMapping("/new")
    public String newCategorieForm(Categorie categorie){
        return "new-categorie";
    }
    @PostMapping("/save")
    public String saveCategorie(@ModelAttribute("categorie") Categorie categorie){
        categorieService.addCategorie(categorie);
        return "redirect:/categories";
    }
    @GetMapping("/delete/{id}")
    public String deleteCategorie(@PathVariable("id") Long id){
       categorieService.deleteCategorie(id);
        return "redirect:/categories";
    }
    @GetMapping("/edit/{id}")
    public String categorieUpdateForm(Model model, @PathVariable("id") Long id){
        model.addAttribute("categorie", categorieService.getCategorieById(id));
        return "update-categorie";
    }

    @PostMapping("/update/{id}")
    public String updateCategorie(@PathVariable Long id, Categorie categorie, Model model){
       categorieService.updateCategorie(categorie, id);
        return "redirect:/categories";
    }
}

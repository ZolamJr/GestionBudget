package sn.niit.gestionbudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.niit.gestionbudget.model.Budget;
import sn.niit.gestionbudget.service.BudgetService;
import sn.niit.gestionbudget.service.CategorieService;
import sn.niit.gestionbudget.service.DepartementService;

@RequestMapping("/budgets")
@Controller
public class BudgetController {
    @Autowired
    private final BudgetService budgetService;
    private  final CategorieService categorieService;
    private  final DepartementService departementService;

    public BudgetController(BudgetService budgetService, CategorieService categorieService, DepartementService departementService) {
        this.budgetService = budgetService;
        this.categorieService = categorieService;
        this.departementService = departementService;
    }
    @GetMapping
    public String getAllBudget(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Budget> budgetPage = budgetService.getAllBudget(pageable);

        model.addAttribute("listbudgets", budgetPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", budgetPage.getTotalPages());

        return "list-budgets";
    }
    @GetMapping("/new")
    public String newBudgetForm(Budget budget, Model model){
        model.addAttribute("categories", categorieService.getAllCategorie());
        model.addAttribute("departements", departementService.getAllDepartement());
        return "new-budget";
    }
    @PostMapping("/save")
    public String saveBudget(@ModelAttribute("budget") Budget budget){
        budgetService.addBudget(budget);
        return "redirect:/budgets";
    }
    @GetMapping("/delete/{id}")
    public String deleteBudget(@PathVariable("id") Long id){
       budgetService.deleteBudget(id);
        return "redirect:/budgets";
    }
    @GetMapping("/edit/{id}")
    public String budgetUpdateForm(Model model, @PathVariable("id") Long id){
        model.addAttribute("departements", departementService.getAllDepartement());
        model.addAttribute("categories", categorieService.getAllCategorie());
        model.addAttribute("budget",budgetService.getBudgetById(id));
        return "update-budget";
    }

    @PostMapping("/update/{id}")
    public String updateBudget(@PathVariable Long id, Budget budget, Model model)
    {
        budgetService.updateBudget(budget, id);
        return "redirect:/budgets";
    }
}

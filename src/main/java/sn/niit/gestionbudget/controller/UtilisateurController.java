package sn.niit.gestionbudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.niit.gestionbudget.model.Categorie;
import sn.niit.gestionbudget.model.Depense;
import sn.niit.gestionbudget.model.Utilisateur;
import sn.niit.gestionbudget.service.UtilisateurService;

import java.util.List;

@Controller
@RequestMapping("/utilisateurs")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }


    @GetMapping
    public String getALLUtilisateur(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "3") int size,
                                 Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Utilisateur> utilisateurPage = utilisateurService.getALLUtilisateur(pageable);

        model.addAttribute("listutilisateurs", utilisateurPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", utilisateurPage.getTotalPages());
        return "list-utilisateurs";
    }
    @GetMapping("/new")
    public String newUtilisateursForm(Utilisateur utilisateur){
        return "new-utilisateur";
    }



    // Save new user
    @PostMapping("/save")
    public String saveUtilisateur(@ModelAttribute("utilisateur") Utilisateur utilisateur){
       utilisateurService.addUtilisateur(utilisateur);
        return "redirect:/utilisateurs";
    }



    @GetMapping("/edit/{id}")
    public String editUtilisateurForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("utilisateur", utilisateurService.getUtilisateurById(id)); // Ajouter l'utilisateur au modèle
        return "update-utilisateur";
    }




    @PostMapping("/edit/{id}")
    public String updateUtilisateur(@PathVariable Long id, @ModelAttribute Utilisateur utilisateur) {
        utilisateur.setId(id); // Ensure the ID is set before updating
        utilisateurService.updateUtilisateur(utilisateur, id); // Save the updated user data
        return "redirect:/utilisateurs";
    }



    @GetMapping("/delete/{id}")
    public String deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return "redirect:/utilisateurs";
    }

}

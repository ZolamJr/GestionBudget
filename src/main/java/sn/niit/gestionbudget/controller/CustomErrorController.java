package sn.niit.gestionbudget.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController {

    @GetMapping("/403")
    public String accessDenied() {
        return "403";  // Correspond à la page d'erreur 403.jsp ou 403.html
    }
}

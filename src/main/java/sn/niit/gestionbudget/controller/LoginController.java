package sn.niit.gestionbudget.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/loginp")
    public String showLoginPage() {
        return "login";
    }
}


// Pour la personnalisation du login, pas utilisé pour l'instant

package sn.niit.gestionbudget.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.niit.gestionbudget.model.Depense;
import sn.niit.gestionbudget.model.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    void addUtilisateur(Utilisateur utilisateur);
    void updateUtilisateur(Utilisateur utilisateur, Long id);
    void deleteUtilisateur(Long id);
    Utilisateur getUtilisateurById(Long id);

    Page<Utilisateur> getALLUtilisateur(Pageable pageable);

}

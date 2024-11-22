package sn.niit.gestionbudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.niit.gestionbudget.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByUsername(String username);
}

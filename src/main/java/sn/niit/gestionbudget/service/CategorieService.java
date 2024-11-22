package sn.niit.gestionbudget.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.niit.gestionbudget.model.Budget;
import sn.niit.gestionbudget.model.Categorie;
import sn.niit.gestionbudget.model.Depense;

import java.util.List;

public interface CategorieService {
    void addCategorie(Categorie categorie);
    void updateCategorie(Categorie categorie, Long id);
    void deleteCategorie(Long id);
    Categorie getCategorieById(Long id);
    List<Categorie> getAllCategorie();

    // Nouvelle méthode avec pagination
    Page<Categorie> getAllCategorie(Pageable pageable);
}

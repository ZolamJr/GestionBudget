package sn.niit.gestionbudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.niit.gestionbudget.model.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}

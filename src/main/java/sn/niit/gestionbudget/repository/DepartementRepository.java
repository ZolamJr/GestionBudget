package sn.niit.gestionbudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.niit.gestionbudget.model.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
}

package sn.niit.gestionbudget.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.niit.gestionbudget.model.Departement;

import java.util.List;

public interface DepartementService {
    void addDepartement(Departement departement);
    void updateDepartement(Departement departement, Long id);
    void deleteDepartement(Long id);
    Departement getDepartementById(Long id);
    List<Departement> getAllDepartement();

    // Nouvelle méthode avec pagination
    Page<Departement> getAllDepartement(Pageable pageable);
}

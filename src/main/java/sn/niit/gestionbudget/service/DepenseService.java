package sn.niit.gestionbudget.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.niit.gestionbudget.model.Depense;

import java.util.List;

public interface DepenseService {

    void addDepense(Depense depense);
    void updateDepense(Depense depense, Long id);
    void deleteDepense(Long id);
    Depense getDepenseById(Long id);
    List<Depense> getAllDepense();

    Page<Depense> getDepenses(Pageable pageable); // Nouvelle méthode pour la pagination
}

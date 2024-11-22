package sn.niit.gestionbudget.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sn.niit.gestionbudget.model.Depense;
import sn.niit.gestionbudget.repository.DepenseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepenseServiceImpl implements DepenseService{
private final DepenseRepository depenseRepository;
    private  final CategorieService categorieService;
    private  final DepartementService departementService;

    public DepenseServiceImpl(DepenseRepository depenseRepository, CategorieService categorieService, DepartementService departementService) {
        this.depenseRepository = depenseRepository;
        this.categorieService = categorieService;
        this.departementService = departementService;
    }


    @Override
    public void addDepense(Depense depense) {

        depenseRepository.save(depense);
    }

    @Override
    public void updateDepense(Depense depense, Long id) {
        Optional<Depense> optionalDepense= depenseRepository.findById(id);
        if (optionalDepense.isPresent()){
            Depense updateDepense = depenseRepository.findById(id).get();
            updateDepense.setCategorie(depense.getCategorie());
            updateDepense.setDate(depense.getDate());
            updateDepense.setDepartement(depense.getDepartement());
            updateDepense.setDescription(depense.getDescription());
            updateDepense.setEmployee(depense.getEmployee());
            updateDepense.setMontant(depense.getMontant());
            updateDepense.setType_paiement(depense.getType_paiement());
           depenseRepository.save(updateDepense);
        }
        else {
            throw  new RuntimeException();
        }

    }

    @Override
    public void deleteDepense(Long id) {
        depenseRepository.deleteById(id);

    }

    @Override
    public Depense getDepenseById(Long id) {
        Optional<Depense> produit= depenseRepository.findById(id);
        if (produit.isPresent()){
            return depenseRepository.findById(id).get();
        }
        else {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Depense> getAllDepense() {
        return depenseRepository.findAll();
    }

    @Override
    public Page<Depense> getDepenses(Pageable pageable) {
        return depenseRepository.findAll(pageable); // Utilisation de la méthode de pagination
    }

}




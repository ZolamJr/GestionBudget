package sn.niit.gestionbudget.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import sn.niit.gestionbudget.model.Categorie;
import sn.niit.gestionbudget.model.Departement;
import sn.niit.gestionbudget.repository.DepartementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartementServiceImpl implements DepartementService{
    private final DepartementRepository departementRepository;

    public DepartementServiceImpl(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    @Override
    public void addDepartement(Departement departement) {
        departementRepository.save(departement);
    }

    @Override
    public void updateDepartement(Departement departement, Long id) {
        Optional<Departement> optionalDepartement= departementRepository.findById(id);
        if (optionalDepartement.isPresent()){
            Departement updateDepartement = departementRepository.findById(id).get();
            updateDepartement.setNom(departement.getNom());
            departementRepository.save(updateDepartement);
        }
        else {
            throw  new RuntimeException();
        }

    }

    @Override
    public void deleteDepartement(Long id) {
        departementRepository.deleteById(id);
    }

    @Override
    public Departement getDepartementById(Long id) {
        Optional<Departement> departement= departementRepository.findById(id);
        if (departement.isPresent()){
            return departementRepository.findById(id).get();
        }
        else {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Departement> getAllDepartement() {
        return departementRepository.findAll();
    }

    @Override
    public Page<Departement> getAllDepartement(Pageable pageable) {
        return departementRepository.findAll(pageable);
    }

}

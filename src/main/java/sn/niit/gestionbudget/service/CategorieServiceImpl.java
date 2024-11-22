package sn.niit.gestionbudget.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sn.niit.gestionbudget.model.Budget;
import sn.niit.gestionbudget.model.Categorie;
import sn.niit.gestionbudget.model.Depense;
import sn.niit.gestionbudget.repository.CategorieRepository;
import sn.niit.gestionbudget.repository.DepenseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieServiceImpl implements CategorieService {
    private final CategorieRepository categorieRepository;

    public CategorieServiceImpl(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public void addCategorie(Categorie categorie) {

        categorieRepository.save(categorie);
    }

    @Override
    public void updateCategorie(Categorie categorie, Long id) {
        Optional<Categorie> optionalDepense= categorieRepository.findById(id);
        if (optionalDepense.isPresent()){
           Categorie updateCategorie = categorieRepository.findById(id).get();
            updateCategorie.setNom(categorie.getNom());
            categorieRepository.save(updateCategorie);
        }
        else {
            throw  new RuntimeException();
        }

    }

    @Override
    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }

    @Override
    public Categorie getCategorieById(Long id) {
        Optional<Categorie> categorie= categorieRepository.findById(id);
        if (categorie.isPresent()){
            return categorieRepository.findById(id).get();
        }
        else {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Categorie> getAllCategorie() {
        return categorieRepository.findAll();
    }

    // Nouvelle méthode pour la pagination
    @Override
    public Page<Categorie> getAllCategorie(Pageable pageable) {
        return categorieRepository.findAll(pageable);
    }
}

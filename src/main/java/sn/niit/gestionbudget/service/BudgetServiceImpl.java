package sn.niit.gestionbudget.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sn.niit.gestionbudget.model.Budget;
import sn.niit.gestionbudget.model.Depense;
import sn.niit.gestionbudget.repository.BudgetRepository;
import sn.niit.gestionbudget.repository.CategorieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService{
    private final BudgetRepository budgetRepository;

    public BudgetServiceImpl(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }


    @Override
    public void addBudget(Budget budget) {
        budgetRepository.save(budget);
    }

    @Override
    public void updateBudget(Budget budget, Long id) {
        Optional<Budget> optionalBudget= budgetRepository.findById(id);
        if (optionalBudget.isPresent()){
           Budget updateBudget = budgetRepository.findById(id).get();
            updateBudget.setMontant(budget.getMontant());
            updateBudget.setDate_debut(budget.getDate_debut());
            updateBudget.setCategorie(budget.getCategorie());
            updateBudget.setDepartement(budget.getDepartement());


            budgetRepository.save(updateBudget);
        }
        else {
            throw  new RuntimeException();
        }

    }

    @Override
    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }

    @Override
    public Budget getBudgetById(Long id) {
        Optional<Budget> budget= budgetRepository.findById(id);
        if (budget.isPresent()){
            return budgetRepository.findById(id).get();
        }
        else {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Budget> getAllBudget() {
        return budgetRepository.findAll();
    }

    // Nouvelle méthode pour la pagination
    @Override
    public Page<Budget> getAllBudget(Pageable pageable) {
        return budgetRepository.findAll(pageable);
    }
}

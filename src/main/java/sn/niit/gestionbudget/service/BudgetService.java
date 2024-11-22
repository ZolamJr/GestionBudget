package sn.niit.gestionbudget.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.niit.gestionbudget.model.Budget;


import java.util.List;

public interface BudgetService {
    void addBudget(Budget budget);
    void updateBudget(Budget budget, Long id);
    void deleteBudget(Long id);
    Budget getBudgetById(Long id);
    List<Budget> getAllBudget();

    // Nouvelle méthode avec pagination
    Page<Budget> getAllBudget(Pageable pageable);
}

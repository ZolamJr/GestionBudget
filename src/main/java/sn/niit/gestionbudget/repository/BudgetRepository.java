package sn.niit.gestionbudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.niit.gestionbudget.model.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}

package sn.niit.gestionbudget.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nom;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "departement")
    List<Depense> depenses;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "departement")
    List<Budget> budgets;

    public Departement() {
    }

    public Departement( String nom) {

        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getNom() {
        return nom;
    }

    public void setNom(@NotNull String nom) {
        this.nom = nom;
    }

    public List<Depense> getDepenses() {
        return depenses;
    }

    public void setDepenses(List<Depense> depenses) {
        this.depenses = depenses;
    }

    public List<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", depenses=" + depenses +
                ", budgets=" + budgets +
                '}';
    }
}

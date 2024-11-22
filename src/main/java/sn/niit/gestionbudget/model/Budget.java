package sn.niit.gestionbudget.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @NotNull(message = "Le montant est obligatoire")
    @Min(value = 0, message = "Le montant doit être positif")
    private Double montant;
    @NotNull(message = "La date est obligatoire")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_debut;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "categorie_id"),
            name = "categorie_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Categorie categorie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "departement_id"), name = "departement_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Departement departement;

    public Budget() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Le montant est obligatoire") @Min(value = 0, message = "Le montant doit être positif") Double getMontant() {
        return montant;
    }

    public void setMontant(@NotNull(message = "Le montant est obligatoire") @Min(value = 0, message = "Le montant doit être positif") Double montant) {
        this.montant = montant;
    }

    public @NotNull(message = "La date est obligatoire") Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(@NotNull(message = "La date est obligatoire") Date date_debut) {
        this.date_debut = date_debut;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "id=" + id +
                ", montant=" + montant +
                '}';
    }
}

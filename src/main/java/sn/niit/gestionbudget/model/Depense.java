package sn.niit.gestionbudget.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Depense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "La date est obligatoire")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @NotNull
    @Size(min=7, max=70)
    private String description;
    @NotNull
    @Size(min=4, max=30)
    private String employee;
    @NotNull(message = "Le montant est obligatoire")
    @Min(value = 0, message = "Le montant doit être positif")
    private Double montant;
    @NotNull
    @Size(min=4, max=30)
    private String type_paiement;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "categorie_id"),
            name = "categorie_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Categorie categorie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "departement_id"), name = "departement_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Departement departement;

    public Depense() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }



    public void setDate(Date date) {
        this.date = date;
    }


    public @NotNull @Size(min = 7, max = 70) String getDescription() {
        return description;
    }

    public void setDescription(@NotNull @Size(min = 7, max = 70) String description) {
        this.description = description;
    }

    public @NotNull @Size(min = 4, max = 30) String getEmployee() {
        return employee;
    }

    public void setEmployee(@NotNull @Size(min = 4, max = 30) String employee) {
        this.employee = employee;
    }

    public @NotNull(message = "Le montant est obligatoire") @Min(value = 0, message = "Le montant doit être positif") Double getMontant() {
        return montant;
    }

    public void setMontant(@NotNull(message = "Le montant est obligatoire") @Min(value = 0, message = "Le montant doit être positif") Double montant) {
        this.montant = montant;
    }

    public @NotNull @Size(min = 4, max = 30) String getType_paiement() {
        return type_paiement;
    }

    public void setType_paiement(@NotNull @Size(min = 4, max = 30) String type_paiement) {
        this.type_paiement = type_paiement;
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
        return "Depense{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", employee='" + employee + '\'' +
                ", montant=" + montant +
                ", type_paiement='" + type_paiement + '\'' +
                '}';
    }
}

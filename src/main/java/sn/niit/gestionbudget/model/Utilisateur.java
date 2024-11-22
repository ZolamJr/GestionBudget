package sn.niit.gestionbudget.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nom;
    @NotNull
    private String prenom;
    @NotNull(message = "La date est obligatoire")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String role; // "ADMIN", "EMPLOYEE"

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Utilisateur() {
    }

    public @NotNull String getNom() {
        return nom;
    }

    public void setNom(@NotNull String nom) {
        this.nom = nom;
    }

    public @NotNull String getPrenom() {
        return prenom;
    }

    public void setPrenom(@NotNull String prenom) {
        this.prenom = prenom;
    }

    public @NotNull(message = "La date est obligatoire") Date getDate() {
        return date;
    }

    public void setDate(@NotNull(message = "La date est obligatoire") Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}

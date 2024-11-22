package sn.niit.gestionbudget.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sn.niit.gestionbudget.model.Depense;
import sn.niit.gestionbudget.model.Utilisateur;
import sn.niit.gestionbudget.repository.UtilisateurRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public void addUtilisateur(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);

    }

    @Override
    public void updateUtilisateur(Utilisateur utilisateur, Long id) {
        Optional<Utilisateur> optionalDepense= utilisateurRepository.findById(id);
        if (optionalDepense.isPresent()){
            Utilisateur updateUtilisateur = utilisateurRepository.findById(id).get();
            updateUtilisateur.setNom(utilisateur.getNom());
            updateUtilisateur.setPrenom(utilisateur.getPrenom());
            updateUtilisateur.setDate(utilisateur.getDate());
            updateUtilisateur.setUsername(utilisateur.getUsername());
            updateUtilisateur.setPassword(utilisateur.getPassword());
            updateUtilisateur.setRole(utilisateur.getRole());
            utilisateurRepository.save(updateUtilisateur);
        }
        else {
            throw  new RuntimeException();
        }

    }

    @Override
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public Utilisateur getUtilisateurById(Long id) {
        Optional<Utilisateur> produit= utilisateurRepository.findById(id);
        if (produit.isPresent()){
            return utilisateurRepository.findById(id).get();
        }
        else {
            throw new RuntimeException();
        }
    }

    @Override
    public Page<Utilisateur> getALLUtilisateur(Pageable pageable) {
        return utilisateurRepository.findAll(pageable);
    }
}

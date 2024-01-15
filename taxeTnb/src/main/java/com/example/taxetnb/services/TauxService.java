package com.example.taxetnb.services;

import com.example.taxetnb.dao.TauxDao;
import com.example.taxetnb.entities.Categorie;
import com.example.taxetnb.entities.Taux;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TauxService {
    @Autowired
    private TauxDao tauxRepo;
    @Autowired
    private CategorieService catSer;

    public List<Taux> findAll(){
        return tauxRepo.findAll();

    }

    public Optional<Taux> findById(int id) {
        return tauxRepo.findById(id);
    }
    public Taux findByCategorie(String libelle) {
        System.out.println("Searching for libelle: " + libelle);

        Categorie categorie = catSer.findByLibelle(libelle);

        if (categorie != null) {
            System.out.println("Found Categorie: " + categorie.getLibelle());

            Taux taux = tauxRepo.findByCategorie(categorie);

            if (taux != null) {
                System.out.println("Found Taux: " + taux);
                return taux;
            } else {
                System.out.println("No Taux found for libelle: " + libelle);
            }
        } else {
            System.out.println("No Categorie found for libelle: " + libelle);
        }

        return null;
    }

    public Taux save(Taux taux) {
        return tauxRepo.save(taux);
    }

    public void deleteById(int id) {
        tauxRepo.deleteById(id);
    }
}

package com.example.taxetnb.services;

import com.example.taxetnb.dao.CategorieDao;
import com.example.taxetnb.entities.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
    @Autowired
    private CategorieDao catRepo;

    public List<Categorie> findAll(){
        return catRepo.findAll();

    }

    public Categorie findByLibelle(String libelle){
        return catRepo.findByLibelle(libelle);
    }

    public Optional<Categorie> findById(int id) {
        return catRepo.findById(id);
    }

    public Categorie save(Categorie categorie) {
        return catRepo.save(categorie);
    }

    public void deleteById(int id) {
        catRepo.deleteById(id);
    }
}

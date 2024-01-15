package com.example.taxetnb.dao;

import com.example.taxetnb.entities.Categorie;
import com.example.taxetnb.entities.Taux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TauxDao extends JpaRepository<Taux, Integer> {
    public Taux findByCategorie(Categorie Categorie);
}

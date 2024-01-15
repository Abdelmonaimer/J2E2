package com.example.taxetnb.dao;

import com.example.taxetnb.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieDao extends JpaRepository<Categorie, Integer> {
    public Categorie findByLibelle(String libelle);
}

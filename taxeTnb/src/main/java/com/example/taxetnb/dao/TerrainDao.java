package com.example.taxetnb.dao;

import com.example.taxetnb.entities.Categorie;
import com.example.taxetnb.entities.Terrain;
import com.example.taxetnb.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerrainDao extends JpaRepository<Terrain, Integer> {
    public Terrain findByNom(String Nom);
    public List<Terrain> findByCategorie(Categorie categorie);
    public  List<Terrain> findByUser(User user);
}

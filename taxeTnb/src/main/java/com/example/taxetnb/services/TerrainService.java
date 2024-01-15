package com.example.taxetnb.services;

import com.example.taxetnb.dao.TerrainDao;
import com.example.taxetnb.entities.Terrain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.taxetnb.entities.User;

import java.util.List;
import java.util.Optional;

@Service
public class TerrainService {
    @Autowired
    private TerrainDao terrainDao;
    @Autowired
    private UserService userService;

    public List<Terrain> findAll(){
        return terrainDao.findAll();

    }

    public Optional<Terrain> findById(int id) {
        return terrainDao.findById(id);
    }
    public List<Terrain> findByUser(String cin){
        User user = userService.findByCin(cin);
        return terrainDao.findByUser(user);
    }
    public Terrain findByNom(String nom){
        return terrainDao.findByNom(nom);
    }

    public Terrain save(Terrain terrain) {
        return terrainDao.save(terrain);
    }

    public void deleteById(int id) {
        terrainDao.deleteById(id);
    }
}

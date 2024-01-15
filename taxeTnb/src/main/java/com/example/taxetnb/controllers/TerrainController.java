package com.example.taxetnb.controllers;

import com.example.taxetnb.entities.Terrain;
import com.example.taxetnb.services.TerrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/terrains")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TerrainController {

    @Autowired
    private TerrainService terrainService;

    @GetMapping
    public ResponseEntity<List<Terrain>> getAllTerrains() {
        List<Terrain> terrainList = terrainService.findAll();
        return new ResponseEntity<>(terrainList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Terrain> getTerrainById(@PathVariable int id) {
        return terrainService.findById(id)
                .map(terrain -> new ResponseEntity<>(terrain, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

   @GetMapping("/user/{cin}")
    public ResponseEntity<List<Terrain>> getTerrainsByUser(@PathVariable String cin) {
        List<Terrain> terrainList = terrainService.findByUser(cin);
        return new ResponseEntity<>(terrainList, HttpStatus.OK);
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<Terrain> getTerrainByNom(@PathVariable String nom) {
        Terrain terrain = terrainService.findByNom(nom);
        if (terrain != null) {
            return new ResponseEntity<>(terrain, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Terrain> createTerrain(@RequestBody Terrain terrain) {
        Terrain savedTerrain = terrainService.save(terrain);
        return new ResponseEntity<>(savedTerrain, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTerrain(@PathVariable int id) {
        terrainService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

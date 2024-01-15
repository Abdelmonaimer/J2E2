package com.example.taxetnb.controllers;

import com.example.taxetnb.entities.Taux;
import com.example.taxetnb.services.TauxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taux")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TauxController {

    @Autowired
    private TauxService tauxService;

    @GetMapping
    public ResponseEntity<List<Taux>> getAllTaux() {
        List<Taux> tauxList = tauxService.findAll();
        return new ResponseEntity<>(tauxList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taux> getTauxById(@PathVariable int id) {
        return tauxService.findById(id)
                .map(taux -> new ResponseEntity<>(taux, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/categorie/{libelle}")
    public ResponseEntity<Taux> getTauxByCategorie(@PathVariable String libelle) {
        Taux taux = tauxService.findByCategorie(libelle);

        if (taux != null) {
            return new ResponseEntity<>(taux, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Taux> createTaux(@RequestBody Taux taux) {
        Taux savedTaux = tauxService.save(taux);
        return new ResponseEntity<>(savedTaux, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaux(@PathVariable int id) {
        tauxService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

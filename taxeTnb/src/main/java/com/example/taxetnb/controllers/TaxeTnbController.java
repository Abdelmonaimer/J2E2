package com.example.taxetnb.controllers;

import com.example.taxetnb.entities.TaxeTnb;
import com.example.taxetnb.services.TaxeTnbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taxe-tnb")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TaxeTnbController {

    @Autowired
    private TaxeTnbService taxeTnbService;

    @GetMapping
    public ResponseEntity<List<TaxeTnb>> getAllTaxeTnb() {
        List<TaxeTnb> taxeTnbList = taxeTnbService.findAll();
        return new ResponseEntity<>(taxeTnbList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaxeTnb> getTaxeTnbById(@PathVariable int id) {
        return taxeTnbService.findById(id)
                .map(taxeTnb -> new ResponseEntity<>(taxeTnb, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/year/{tnbYear}")
    public ResponseEntity<TaxeTnb> getTaxeTnbByYear(@PathVariable int tnbYear) {
        TaxeTnb taxeTnb = taxeTnbService.findByTnbYear(tnbYear);
        if (taxeTnb != null) {
            return new ResponseEntity<>(taxeTnb, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/terrain/{nom}")
    public ResponseEntity<List<TaxeTnb>> getTaxeTnbByTerrain(@PathVariable String nom) {
        List<TaxeTnb> taxeTnbList = taxeTnbService.findByTerrain(nom);
        return new ResponseEntity<>(taxeTnbList, HttpStatus.OK);
    }

    @GetMapping("/year-terrain/{year}/{nom}")
    public ResponseEntity<List<TaxeTnb>> getTaxeTnbByYearAndTerrain(@PathVariable int year, @PathVariable String nom) {
        List<TaxeTnb> taxeTnbList = taxeTnbService.findByTnbYearAndTerrain(year, nom);
        return new ResponseEntity<>(taxeTnbList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaxeTnb> createTaxeTnb(@RequestBody TaxeTnb taxeTnb) {
        TaxeTnb savedTaxeTnb = taxeTnbService.save(taxeTnb);
        return new ResponseEntity<>(savedTaxeTnb, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaxeTnb(@PathVariable int id) {
        taxeTnbService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Uncomment this method if you want to expose the calculateTaxeTNB functionality through an API

    @GetMapping("/calculate/{cin}/{terrainNom}/{taxeTnbId}")
    public ResponseEntity<Double> calculateTaxeTNB(
            @PathVariable String cin,
            @PathVariable String terrainNom,
            @PathVariable int taxeTnbId
    ) {
        try {
            Double taxeAmount = taxeTnbService.calculateTaxeTNB(cin, terrainNom, taxeTnbId);
            return new ResponseEntity<>(taxeAmount, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(0.0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

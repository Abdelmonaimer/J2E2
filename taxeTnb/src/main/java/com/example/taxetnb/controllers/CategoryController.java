package com.example.taxetnb.controllers;

import com.example.taxetnb.entities.Categorie;
import com.example.taxetnb.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {

    @Autowired
    private CategorieService categorieService;

    @GetMapping
    public ResponseEntity<List<Categorie>> getAllCategories() {
        List<Categorie> categories = categorieService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{libelle}")
    public ResponseEntity<Categorie> getCategoryByLibelle(@PathVariable String libelle) {
        Categorie category = categorieService.findByLibelle(libelle);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Categorie> getCategoryById(@PathVariable int id) {
        return categorieService.findById(id)
                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Categorie> createCategory(@RequestBody Categorie categorie) {
        Categorie savedCategory = categorieService.save(categorie);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategory(@PathVariable int id, @RequestBody Categorie categorie) {
        return categorieService.findById(id)
                .map(existingCategory -> {
                    existingCategory.setLibelle(categorie.getLibelle());
                    // Update other fields if needed
                    Categorie updatedCategory = categorieService.save(existingCategory);
                    return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categorieService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

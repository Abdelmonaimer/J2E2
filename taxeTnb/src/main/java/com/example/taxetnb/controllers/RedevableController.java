
package com.example.taxetnb.controllers;

import com.example.taxetnb.entities.Redevable;
import com.example.taxetnb.services.RedevableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/redevables")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RedevableController {

    @Autowired
    private RedevableService redevableService;

    @GetMapping
    public ResponseEntity<List<Redevable>> getAllRedevables() {
        List<Redevable> redevables = redevableService.findAll();
        return new ResponseEntity<>(redevables, HttpStatus.OK);
    }

    @GetMapping("/{cin}")
    public ResponseEntity<Redevable> getRedevableByCin(@PathVariable String cin) {
        Redevable redevable = redevableService.findByCin(cin);
        if (redevable != null) {
            return new ResponseEntity<>(redevable, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Redevable> getRedevableById(@PathVariable int id) {
        return redevableService.findById(id)
                .map(redevable -> new ResponseEntity<>(redevable, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Object> createRedevable(@RequestBody Redevable redevable) {
        int result = redevableService.save(redevable);
        if (result == -1) {
            return new ResponseEntity<>("Redevable with CIN " + redevable.getCin() + " already exists.", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRedevable(@PathVariable int id) {
        redevableService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.example.taxetnb.services;

import com.example.taxetnb.dao.TauxDao;
import com.example.taxetnb.dao.TaxeTnbDao;
import com.example.taxetnb.entities.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaxeTnbService {

    @Autowired
    private TaxeTnbDao taxeRepo;
    @Autowired
    private TerrainService terrainService;
    @Autowired
    private UserService userService;
    @Autowired
    private TauxService tauxService;
    @Autowired
    private TaxeTnbService taxeTnbService;
    

    public List<TaxeTnb> findAll(){
        return taxeRepo.findAll();

    }

    public Optional<TaxeTnb> findById(int id) {
        return taxeRepo.findById(id);
    }
    public TaxeTnb findByTnbYear(int tnbYear){
        return taxeRepo.findByTnbYear(tnbYear);
    }
    public List<TaxeTnb> findByTerrain(String nom){
        Terrain terrain = terrainService.findByNom(nom);
        return taxeRepo.findByTerrain(terrain);
    }

    public List<TaxeTnb> findByTnbYearAndTerrain(int year, String nom){
        Terrain terrain = terrainService.findByNom(nom);
        return taxeRepo.findByTnbYearAndTerrain(year, terrain);
    }

    public TaxeTnb save(TaxeTnb taxeTnb) {
        return taxeRepo.save(taxeTnb);
    }

    public void deleteById(int id) {
        taxeRepo.deleteById(id);
    }
    public Double calculateTaxeTNB(String cin, String terrainNom, int taxeTnbId) {
        // Step 1: Get the client by CIN
        User client = userService.findByCin(cin);

        if (client == null) {
            // Handle the case when the client is not found
            throw new EntityNotFoundException("Client not found with CIN: " + cin);
        }

        // Step 2: Get the terrains of the client
        List<Terrain> terrains = terrainService.findByUser(cin);

        // Step 3: Find the terrain that matches the provided nom
        Terrain selectedTerrain = terrains.stream()
                .filter(terrain -> terrain.getNom().equals(terrainNom))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Terrain not found with nom: " + terrainNom));

        // Step 4: Get the category of the selected terrain
        Categorie categorie = selectedTerrain.getCategorie();
        String catNom = categorie.getLibelle();
        System.out.println(" taux : " + catNom);

        // Step 5: Get the taux from the category
        Taux taux = tauxService.findByCategorie(catNom);
        System.out.println(" taux : " + taux);

        Double tauxMnt = taux.getMontant();
        Double surface = selectedTerrain.getSurface();

        // Fetch the existing TaxeTnb entity from the database using the provided ID
        TaxeTnb existingTax = taxeTnbService.findById(taxeTnbId)
                .orElseThrow(() -> new EntityNotFoundException("TaxeTnb not found with ID: " + taxeTnbId));

        // Logging to check if the tax is paid or not
        System.out.println("Tax is paid: " + existingTax.isPaid());

        // Step 6: Check if the tax is paid or not and calculate accordingly
        if (existingTax.isPaid()) {
            return 0.0; // Some tax is paid, return 0.0 or handle it as per your logic
        } else {
            // No tax is paid, perform the calculation
            Double calculatedTax = tauxMnt * surface;

            // Update the montant in the existing TaxeTnb entity
            existingTax.setMontant(calculatedTax);
            existingTax.setPaid(true); // Set the paid status accordingly

            // Save the updated TaxeTnb entity
            taxeTnbService.save(existingTax);

            return calculatedTax;
        }
    }


    

}

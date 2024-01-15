package com.example.taxetnb.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Transactional
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaxeTnb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Label;
    private String description;
    private double montant;
    private int tnbYear;
    private boolean paid;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "terrain_id")
    private Terrain terrain;
    
    
    
}

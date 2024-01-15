package com.example.taxetnb.entities;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Transactional
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Terrain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String description;
    private Double Surface;
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    /*@ManyToOne
    @JoinColumn(name = "client_cin")
    private Redevable client;*/
    
   

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "client_cin", referencedColumnName = "id")
    private User user;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "terrain", cascade = CascadeType.ALL)
    private List<TaxeTnb> taxeTnb;

}

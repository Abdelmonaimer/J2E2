package com.example.taxetnb.dao;

import com.example.taxetnb.entities.Redevable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedevableDao extends JpaRepository<Redevable, Integer> {
    public Redevable findByCin(String cin);
}

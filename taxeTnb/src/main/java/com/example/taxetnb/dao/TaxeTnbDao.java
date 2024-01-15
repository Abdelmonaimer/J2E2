package com.example.taxetnb.dao;

import com.example.taxetnb.entities.TaxeTnb;
import com.example.taxetnb.entities.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxeTnbDao extends JpaRepository<TaxeTnb, Integer> {
    public TaxeTnb findByTnbYear(int tnbYear);
    public List<TaxeTnb> findByTerrain(Terrain terrain);
    public List<TaxeTnb> findByTnbYearAndTerrain(int tnbYear, Terrain terrain);

}

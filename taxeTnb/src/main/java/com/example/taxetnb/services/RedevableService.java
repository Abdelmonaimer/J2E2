package com.example.taxetnb.services;

import com.example.taxetnb.dao.RedevableDao;
import com.example.taxetnb.entities.Redevable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RedevableService {
    @Autowired
    private RedevableDao redRepo;

    public List<Redevable> findAll(){
        return redRepo.findAll();

    }
    public Redevable findByCin(String cin) {
        return redRepo.findByCin(cin);
    }

    public Optional<Redevable> findById(int id) {
        return redRepo.findById(id);
    }

    public int save(Redevable redevable) {
        Redevable existingRedevable = findByCin(redevable.getCin());
        if (existingRedevable!=null) {
            return -1;
        }else {
            redRepo.save(redevable);
            return 1;
        }
    }

    public void deleteById(int id) {
        redRepo.deleteById(id);
    }
}

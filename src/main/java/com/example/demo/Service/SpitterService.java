package com.example.demo.Service;

import com.example.demo.Repository.SpitterRepository;
import com.example.demo.Model.Spitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpitterService {

    @Autowired
    private SpitterRepository spitterRepository;

    public Spitter findSpitterByUserName(String userName){
        return spitterRepository.findByUsername(userName);
    }

    public Spitter saveSpitter(Spitter spitter){
        Spitter spitterEntity=new Spitter(
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getFirstName(),
                spitter.getLastName(),
                spitter.getEmail());
       return spitterRepository.save(spitterEntity);

    }

    public List<Spitter> getAllSpitters(){
        return spitterRepository.findAll();
    }
}

package com.example.demo.Service;

import com.example.demo.Repository.SpitterRepository;
import com.example.demo.Model.Spitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpitterService {

    @Autowired
    private SpitterRepository spitterRepository;
    public Spitter findSpitterByUserName(String userName){
        return spitterRepository.findByUsername(userName);
    }
}

package com.example.demo.Service;

import com.example.demo.Repository.SpitterRepository;
import com.example.demo.Model.Spitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpitterService {

    @Autowired
    private SpitterRepository spitterRepository;

    public List<Spitter> findSpitterByUserName(String userName){
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

    public Spitter getSpitterById(long id){
        return spitterRepository.findById(id);
    }
    public Page<Spitter> getSpittersByPage(int page,int size){
        return spitterRepository.findAll(PageRequest.of(page,size));
    }
}

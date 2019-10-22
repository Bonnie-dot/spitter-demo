package com.example.demo.Repository;

import com.example.demo.Model.Spitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpitterRepository extends JpaRepository<Spitter,Long> {
    Spitter findByUsername(String username);

}

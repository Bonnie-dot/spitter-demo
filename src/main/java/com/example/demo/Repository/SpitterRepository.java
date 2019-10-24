package com.example.demo.Repository;

import com.example.demo.Model.Spitter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SpitterRepository extends PagingAndSortingRepository<Spitter,Long> {

    List<Spitter> findByUsername(String username);

    Spitter save(Spitter spitter);

    List<Spitter> findAll();

    Spitter findById(long id);

    Page<Spitter> findAll(Pageable pageable);

}

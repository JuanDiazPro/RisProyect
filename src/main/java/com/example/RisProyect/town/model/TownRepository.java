package com.example.RisProyect.town.model;
import com.example.practica_completa.state.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TownRepository extends JpaRepository<com.example.practica_completa.town.model.Town, Long> {
    @Query(value = "SELECT * FROM towns WHERE name LIKE ?1 AND id != ?2 LIMIT 1", nativeQuery = true)
    Optional<com.example.practica_completa.town.model.Town> searchByNameAndId(String name, Long id);

    List<com.example.practica_completa.town.model.Town> findAllByStatusOrderByName(boolean status);

    List<com.example.practica_completa.town.model.Town> findAllByStateAndStatusOrderByName(State state, boolean status);
}
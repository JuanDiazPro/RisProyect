package com.example.practica_completa.state.model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    @Query(value = "SELECT * FROM states WHERE name LIKE ?1 AND id != ?2 LIMIT 1", nativeQuery = true)
    Optional<State> searchByNameAndId(String name, Long id);

    List<State> findAllByStatusOrderByName(boolean status);
}
package com.enigma.Football.repositories;

import com.enigma.Football.entity.Player;
import com.enigma.Football.entity.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ResultRepo extends JpaRepository<Result, String> {
    @Query(value = "SELECT * FROM football.result", nativeQuery = true)
    Page<Result> getAll(Pageable pageable);

    @Query(value = "SELECT * FROM football.result WHERE football.result.name LIKE %:name%", nativeQuery = true)
    Page<Result> searchByName(@Param("name") String name, Pageable pageable);

    @Query(value = "SELECT * FROM football.result WHERE football.result.id = ?1", nativeQuery = true)
    Result getById(String id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM football.result WHERE football.result.id = :id", nativeQuery = true)
    void deleteRowById(@Param("id") String id);
}

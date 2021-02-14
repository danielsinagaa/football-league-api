package com.enigma.Football.repositories;

import com.enigma.Football.entity.Referee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface RefereeRepo extends JpaRepository<Referee, String> {

    @Query(value = "SELECT * FROM referee", nativeQuery = true)
    Page<Referee> getAll(Pageable pageable);

    @Query(value = "SELECT * FROM referee WHERE referee.name LIKE %:name%", nativeQuery = true)
    Page<Referee> searchByName(@Param("name") String name, Pageable pageable);

    @Query(value = "SELECT * FROM referee WHERE referee.id = ?1", nativeQuery = true)
    Referee getById(String id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM referee WHERE referee.id = :id", nativeQuery = true)
    void deleteRowById(@Param("id") String id);
}

package com.enigma.Football.repositories;

import com.enigma.Football.entity.Matchs;
import com.enigma.Football.entity.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface MatchRepo extends JpaRepository<Matchs, String> {
    @Query(value = "SELECT * FROM matchs", nativeQuery = true)
    Page<Matchs> getAll(Pageable pageable);

    @Query(value = "SELECT * FROM matchs WHERE matchs.id = ?1", nativeQuery = true)
    Matchs getById(String id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM matchs WHERE matchs.id = :id", nativeQuery = true)
    void deleteRowById(@Param("id") String id);
}

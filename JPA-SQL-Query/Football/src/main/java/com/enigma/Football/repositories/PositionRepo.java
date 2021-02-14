package com.enigma.Football.repositories;

import com.enigma.Football.entity.PlayerPosition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface PositionRepo extends JpaRepository<PlayerPosition, String> {
    @Query(value = "SELECT * FROM player_position", nativeQuery = true)
    Page<PlayerPosition> getAll(Pageable pageable);

    @Query(value = "SELECT * FROM player_position WHERE player_position.name LIKE %:name%", nativeQuery = true)
    Page<PlayerPosition> searchByName(@Param("name") String name,Pageable pageable);

    @Query(value = "SELECT * FROM player_position WHERE player_position.id = ?1", nativeQuery = true)
    PlayerPosition getById(String id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM player_position WHERE player_position.id = :id", nativeQuery = true)
    void deleteRowById(@Param("id") String id);
}

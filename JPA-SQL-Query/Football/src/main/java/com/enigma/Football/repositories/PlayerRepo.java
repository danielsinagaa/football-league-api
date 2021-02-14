package com.enigma.Football.repositories;

import com.enigma.Football.entity.Player;
import com.enigma.Football.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface PlayerRepo extends JpaRepository<Player, String> {
    @Query(value = "SELECT * FROM player", nativeQuery = true)
    Page<Player> getAll(Pageable pageable);

    @Query(value = "SELECT * FROM player WHERE player.name LIKE %:name%", nativeQuery = true)
    Page<Player> searchByName(@Param("name") String name, Pageable pageable);

    @Query(value = "SELECT * FROM player WHERE player.id = ?1", nativeQuery = true)
    Player getById(String id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM player WHERE player.id = :id", nativeQuery = true)
    void deleteRowById(@Param("id") String id);
}

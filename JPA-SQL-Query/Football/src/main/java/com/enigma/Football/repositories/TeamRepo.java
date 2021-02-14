package com.enigma.Football.repositories;

import com.enigma.Football.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;

public interface TeamRepo extends JpaRepository<Team, String> {
    @Query(value = "SELECT * FROM team", nativeQuery = true)
    Page<Team> getAll(Pageable pageable);

    @Query(value = "SELECT * FROM team WHERE team.name LIKE %:name%", nativeQuery = true)
    Page<Team> searchByName(@Param("name") String name,Pageable pageable);

    @Query(value = "SELECT * FROM team WHERE team.id = ?1", nativeQuery = true)
    Team getById(String id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM team WHERE team.id = :id", nativeQuery = true)
    void deleteRowById(@Param("id") String id);
}

package com.enigma.Football.repositories;

import com.enigma.Football.entity.Fixture;
import com.enigma.Football.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface FixtureRepo extends JpaRepository<Fixture, String> {
    @Query(value = "SELECT * FROM fixture", nativeQuery = true)
    Page<Fixture> getAll(Pageable pageable);

    @Query(value = "SELECT * FROM fixture WHERE fixture.id = ?1", nativeQuery = true)
    Fixture getById(String id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM fixture WHERE fixture.id = :id", nativeQuery = true)
    void deleteRowById(@Param("id") String id);

}

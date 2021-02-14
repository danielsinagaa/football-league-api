package com.enigma.Football.repositories;

import com.enigma.Football.entity.TeamStatistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface TeamStatsRepo extends JpaRepository<TeamStatistic, String> {

    @Query(value = "SELECT * FROM team_statistic", nativeQuery = true)
    Page<TeamStatistic> getAll(Pageable pageable);

    @Query(value = "SELECT * FROM team_statistic WHERE team_statistic.id = ?1", nativeQuery = true)
    TeamStatistic getById(String id);

    @Query(value = "SELECT * FROM team_statistic WHERE team_statistic.team_id = ?1", nativeQuery = true)
    TeamStatistic getByItemId(String id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM team_statistic WHERE team_statistic.id = :id", nativeQuery = true)
    void deleteRowById(@Param("id") String id);
}

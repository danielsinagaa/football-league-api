package com.enigma.Football.repositories;

import com.enigma.Football.entity.PlayerStatistic;
import com.enigma.Football.entity.TeamStatistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;

public interface PlayerStatsRepo extends JpaRepository<PlayerStatistic, String> {
    @Query(value = "SELECT * FROM player_statistic", nativeQuery = true)
    Page<PlayerStatistic> getAll(Pageable pageable);

    @Query(value = "SELECT * FROM player_statistic WHERE player_statistic.id = ?1", nativeQuery = true)
    PlayerStatistic getById(String id);

    @Query(value = "SELECT * FROM player_statistic WHERE player_statistic.player_id = ?1", nativeQuery = true)
    PlayerStatistic getByPlayerId(String id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM player_statistic WHERE player_statistic.id = :id", nativeQuery = true)
    void deleteRowById(@Param("id") String id);
}


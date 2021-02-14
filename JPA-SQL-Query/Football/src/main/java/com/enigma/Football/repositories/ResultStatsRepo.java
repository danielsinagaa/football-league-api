package com.enigma.Football.repositories;

import com.enigma.Football.entity.ResultStatistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ResultStatsRepo extends JpaRepository<ResultStatistic, String> {

    @Query(value = "SELECT * FROM result_statistic", nativeQuery = true)
    Page<ResultStatistic> getAll(Pageable pageable);

    @Query(value = "SELECT * FROM result_statistic WHERE result_statistic.id = ?1", nativeQuery = true)
    ResultStatistic getById(String id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM result_statistic WHERE result_statistic.id = :id", nativeQuery = true)
    void deleteRowById(@Param("id") String id);
}

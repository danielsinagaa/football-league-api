package com.enigma.Football.services;

import com.enigma.Football.entity.Team;
import com.enigma.Football.entity.TeamStatistic;
import com.enigma.Football.repositories.TeamStatsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamStatsService extends AbstractService<TeamStatistic,String> implements ServiceInterface<TeamStatistic,String> {

    @Autowired
    public TeamStatsService(TeamStatsRepo repository) {
        super(repository);
    }

    @Autowired
    TeamStatsRepo repo;

    public Page<TeamStatistic> getAll(int page, int size, Sort.Direction direction){
        Sort sort = Sort.Direction.DESC.equals(direction) ?
                Sort.by(direction, "points") : Sort.by("points");
        return repo.getAll(PageRequest.of(page, size, sort));
    }

    public TeamStatistic getByItemId(String id){
        return repo.getByItemId(id);
    }

    public TeamStatistic getById(String id){
        return repo.getById(id);
    }

    public TeamStatistic deleteById(String id){
        TeamStatistic team = getById(id);
        if (team != null){
            repo.deleteRowById(id);
            return team;
        } else return null;
    }
}

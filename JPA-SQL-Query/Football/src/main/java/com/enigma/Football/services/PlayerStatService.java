package com.enigma.Football.services;

import com.enigma.Football.entity.PlayerStatistic;
import com.enigma.Football.repositories.PlayerStatsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PlayerStatService extends AbstractService<PlayerStatistic,String> implements ServiceInterface<PlayerStatistic,String> {

    @Autowired
    public PlayerStatService(PlayerStatsRepo repository) {
        super(repository);
    }

    @Autowired
    PlayerStatsRepo repo;

    public Page<PlayerStatistic> getAll(int page, int size, Sort.Direction direction){
        Sort sort = Sort.Direction.DESC.equals(direction) ?
                Sort.by(direction, "id") : Sort.by("id");
        return repo.getAll(PageRequest.of(page, size, sort));
    }

    public PlayerStatistic getById(String id){
        return repo.getById(id);
    }

    public PlayerStatistic getByPlayer(String id){
        return repo.getByPlayerId(id);
    }

    public PlayerStatistic deleteById(String id){
        PlayerStatistic team = getById(id);
        if (team != null){
            repo.deleteRowById(id);
            return team;
        } else return null;
    }
}


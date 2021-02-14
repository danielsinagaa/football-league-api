package com.enigma.Football.services;

import com.enigma.Football.entity.Team;
import com.enigma.Football.repositories.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TeamService extends AbstractService<Team,String> implements ServiceInterface<Team,String> {

    @Autowired
    public TeamService(TeamRepo repository) {
        super(repository);
    }

    @Autowired
    private TeamRepo repo;

    public Page<Team> getAll(int page, int size, Sort.Direction direction){
        Sort sort = Sort.Direction.DESC.equals(direction) ?
                Sort.by(direction, "id") : Sort.by("id");
        return repo.getAll(PageRequest.of(page, size, sort));
    }

    public Page<Team> searchByName(String name,int page, int size, Sort.Direction direction){
        Sort sort = Sort.Direction.DESC.equals(direction) ?
                Sort.by(direction, "id") : Sort.by("id");
        return repo.searchByName(name,PageRequest.of(page, size, sort));
    }

    public Team getById(String id){
        return repo.getById(id);
    }

    public Team deleteById(String id){
        Team team = getById(id);
        if (team != null){
            repo.deleteRowById(id);
            return team;
        } else return null;
    }

}

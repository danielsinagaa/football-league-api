package com.enigma.Football.services;

import com.enigma.Football.entity.Matchs;
import com.enigma.Football.entity.Player;
import com.enigma.Football.repositories.MatchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchService extends AbstractService<Matchs, String> implements ServiceInterface<Matchs, String> {
    @Autowired
    public MatchService(MatchRepo repository) {
        super(repository);
    }

    @Autowired
    MatchRepo repo;

    public Page<Matchs> getAll(int page, int size, Sort.Direction direction){
        Sort sort = Sort.Direction.DESC.equals(direction) ?
                Sort.by(direction, "id") : Sort.by("id");
        return repo.getAll(PageRequest.of(page, size, sort));
    }

    public Matchs getById(String id){
        return repo.getById(id);
    }

    public Matchs deleteById(String id){
        Matchs team = getById(id);
        if (team != null){
            repo.deleteRowById(id);
            return team;
        } else return null;
    }
}

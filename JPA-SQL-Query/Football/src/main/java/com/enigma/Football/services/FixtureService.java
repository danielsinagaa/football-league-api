package com.enigma.Football.services;

import com.enigma.Football.entity.Fixture;
import com.enigma.Football.repositories.FixtureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FixtureService extends AbstractService<Fixture,String> implements ServiceInterface<Fixture,String> {
    @Autowired
    public FixtureService(FixtureRepo repository) {
        super(repository);
    }

    @Autowired
    FixtureRepo repo;

    public Page<Fixture> getAll(int page, int size, Sort.Direction direction){
        Sort sort = Sort.Direction.DESC.equals(direction) ?
                Sort.by(direction, "week") : Sort.by("week");
        return repo.getAll(PageRequest.of(page, size, sort));
    }

    public Fixture getById(String id){
        return repo.getById(id);
    }

    public Fixture deleteById(String id){
        Fixture team = getById(id);
        if (team != null){
            repo.deleteRowById(id);
            return team;
        } else return null;
    }
}

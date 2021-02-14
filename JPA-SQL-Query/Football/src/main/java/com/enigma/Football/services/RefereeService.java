package com.enigma.Football.services;

import com.enigma.Football.entity.Referee;
import com.enigma.Football.repositories.RefereeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RefereeService extends AbstractService<Referee,String> implements ServiceInterface<Referee,String>{
    @Autowired
    public RefereeService(RefereeRepo repository) {
        super(repository);
    }

    @Autowired
    RefereeRepo repo;

    public Page<Referee> getAll(int page, int size, Sort.Direction direction){
        Sort sort = Sort.Direction.DESC.equals(direction) ?
                Sort.by(direction, "id") : Sort.by("id");
        return repo.getAll(PageRequest.of(page, size, sort));
    }

    public Page<Referee> searchByName(String name, int page, int size, Sort.Direction direction){
        Sort sort = Sort.Direction.DESC.equals(direction) ?
                Sort.by(direction, "id") : Sort.by("id");
        return repo.searchByName(name,PageRequest.of(page, size, sort));
    }

    public Referee getById(String id){
        return repo.getById(id);
    }

    public Referee deleteById(String id){
        Referee entity = getById(id);
        if (entity != null){
            repo.deleteRowById(id);
            return entity;
        } else return null;
    }
}

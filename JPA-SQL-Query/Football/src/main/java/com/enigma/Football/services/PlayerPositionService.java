package com.enigma.Football.services;

import com.enigma.Football.entity.PlayerPosition;
import com.enigma.Football.repositories.PositionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerPositionService extends AbstractService<PlayerPosition,String> implements ServiceInterface<PlayerPosition,String> {

    @Autowired
    public PlayerPositionService(JpaRepository<PlayerPosition, String> repository) {
        super(repository);
    }

    @Autowired
    PositionRepo repo;

    public Page<PlayerPosition> getAll(int page, int size, Sort.Direction direction){
        Sort sort = Sort.Direction.DESC.equals(direction) ?
                Sort.by(direction, "id") : Sort.by("id");
        return repo.getAll(PageRequest.of(page, size, sort));
    }

    public Page<PlayerPosition> searchByName(String name,int page, int size, Sort.Direction direction){
        Sort sort = Sort.Direction.DESC.equals(direction) ?
                Sort.by(direction, "id") : Sort.by("id");
        return repo.searchByName(name,PageRequest.of(page, size, sort));
    }

    public PlayerPosition getById(String id){
        return repo.getById(id);
    }

    public PlayerPosition deleteById(String id){
        PlayerPosition team = getById(id);
        if (team != null){
            repo.deleteRowById(id);
            return team;
        } else return null;
    }

}

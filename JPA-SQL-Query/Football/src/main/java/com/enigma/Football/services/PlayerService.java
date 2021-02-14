package com.enigma.Football.services;

import com.enigma.Football.entity.Player;
import com.enigma.Football.repositories.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PlayerService extends AbstractService<Player,String> implements ServiceInterface<Player,String> {
    @Autowired
    public PlayerService(PlayerRepo repository) {
        super(repository);
    }

    @Autowired
    PlayerRepo repo;

    public Page<Player> getAll(int page, int size, Sort.Direction direction){
        Sort sort = Sort.Direction.DESC.equals(direction) ?
                Sort.by(direction, "name") : Sort.by("name");
        return repo.getAll(PageRequest.of(page, size, sort));
    }

    public Page<Player> searchByName(String name,int page, int size, Sort.Direction direction){
        Sort sort = Sort.Direction.DESC.equals(direction) ?
                Sort.by(direction, "name") : Sort.by("name");
        return repo.searchByName(name,PageRequest.of(page, size, sort));
    }

    public Player getById(String id){
        return repo.getById(id);
    }

    public Player deleteById(String id){
        Player team = getById(id);
        if (team != null){
            repo.deleteRowById(id);
            return team;
        } else return null;
    }

}

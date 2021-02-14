package com.enigma.Football.services;

import com.enigma.Football.entity.Result;
import com.enigma.Football.repositories.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ResultService extends AbstractService<Result, String> implements ServiceInterface<Result, String> {
    @Autowired
    public ResultService(ResultRepo repository) {
        super(repository);
    }

    @Autowired
    ResultRepo repo;

    public Page<Result> getAll(int page, int size, Sort.Direction direction){
        Sort sort = Sort.Direction.DESC.equals(direction) ?
                Sort.by(direction, "id") : Sort.by("id");
        return repo.getAll(PageRequest.of(page, size, sort));
    }

    public Page<Result> searchByName(String name,int page, int size, Sort.Direction direction){
        Sort sort = Sort.Direction.DESC.equals(direction) ?
                Sort.by(direction, "id") : Sort.by("id");
        return repo.searchByName(name,PageRequest.of(page, size, sort));
    }

    public Result getById(String id){
        return repo.getById(id);
    }

    public Result deleteById(String id){
        Result team = getById(id);
        if (team != null){
            repo.deleteRowById(id);
            return team;
        } else return null;
    }
}

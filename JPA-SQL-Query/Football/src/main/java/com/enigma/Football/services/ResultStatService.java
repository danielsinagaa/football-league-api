package com.enigma.Football.services;

import com.enigma.Football.entity.ResultStatistic;
import com.enigma.Football.repositories.ResultStatsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ResultStatService extends AbstractService<ResultStatistic,String> implements ServiceInterface<ResultStatistic,String>{
    @Autowired
    public ResultStatService(ResultStatsRepo repository) {
        super(repository);
    }

    @Autowired
    ResultStatsRepo repo;

    public Page<ResultStatistic> getAll(int page, int size, Sort.Direction direction){
        Sort sort = Sort.Direction.DESC.equals(direction) ?
                Sort.by(direction, "id") : Sort.by("id");
        return repo.getAll(PageRequest.of(page, size, sort));
    }

    public ResultStatistic getById(String id){
        return repo.getById(id);
    }

    public ResultStatistic deleteById(String id){
        ResultStatistic entity = getById(id);
        if (entity != null){
            repo.deleteRowById(id);
            return entity;
        } else return null;
    }
}

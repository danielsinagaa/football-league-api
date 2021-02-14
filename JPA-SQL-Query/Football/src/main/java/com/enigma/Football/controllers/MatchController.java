package com.enigma.Football.controllers;

import com.enigma.Football.entity.Matchs;
import com.enigma.Football.exceptions.EntityNotFoundException;
import com.enigma.Football.models.matchs.MatchRequest;
import com.enigma.Football.models.matchs.MatchResponse;
import com.enigma.Football.models.matchs.MatchSearch;
import com.enigma.Football.models.pages.PagedList;
import com.enigma.Football.models.responses.ResponseMessage;
import com.enigma.Football.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/matches")
@RestController
public class MatchController {
    @Autowired
    private MatchService service;

    @Autowired
    private TeamService teamService;

    @Autowired
    private FixtureService fixtureService;

    @Autowired
    private RefereeService refereeService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseMessage<MatchResponse> add(@RequestBody @Valid MatchRequest request) {
        Matchs entity = modelMapper.map(request, Matchs.class);
        entity.setAway(teamService.getById(request.getAway()));
        entity.setHome(teamService.getById(request.getHome()));
        entity.setFixture(fixtureService.getById(request.getFixture()));
        entity.setReferee(refereeService.getById(request.getReferee()));

        entity = service.save(entity);

        MatchResponse data = modelMapper.map(entity, MatchResponse.class);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<MatchResponse> edit(@PathVariable String id,
                                                @RequestBody @Valid MatchRequest request){

        Matchs entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        modelMapper.map(request, entity);

        entity = service.save(entity);

        MatchResponse data = modelMapper.map(entity, MatchResponse.class);
        return ResponseMessage.success(data);
    }

    @GetMapping
    public ResponseMessage<PagedList<MatchResponse>> getAll(@Valid MatchSearch request){
        Page<Matchs> entityPage = service.getAll(request.getPage(),
                request.getSize(), request.getSort());
        List<Matchs> entities = entityPage.toList();

        List<MatchResponse> models = entities.stream()
                .map(e -> modelMapper.map(e, MatchResponse.class))
                .collect(Collectors.toList());

        PagedList<MatchResponse> data = new PagedList(models,
                entityPage.getNumber(), entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage getById(@PathVariable String id){
        Matchs entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        MatchResponse data = modelMapper.map(entity, MatchResponse.class);

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<MatchResponse> deleteById(@PathVariable String id){
        Matchs entity = service.deleteById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        MatchResponse data = modelMapper.map(entity, MatchResponse.class);
        return ResponseMessage.success(data);
    }
}
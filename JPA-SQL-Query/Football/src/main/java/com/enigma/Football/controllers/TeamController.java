package com.enigma.Football.controllers;

import com.enigma.Football.entity.Team;
import com.enigma.Football.entity.TeamStatistic;
import com.enigma.Football.exceptions.EntityNotFoundException;
import com.enigma.Football.models.pages.PagedList;
import com.enigma.Football.models.responses.ResponseMessage;
import com.enigma.Football.models.teams.TeamRequest;
import com.enigma.Football.models.teams.TeamResponse;
import com.enigma.Football.models.teams.TeamSearch;
import com.enigma.Football.services.TeamService;
import com.enigma.Football.services.TeamStatsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/teams")
@RestController
public class TeamController {
    @Autowired
    private TeamService service;

    @Autowired
    private TeamStatsService teamStatsService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseMessage<TeamResponse> add(@RequestBody @Valid TeamRequest request){
        Team entity = modelMapper.map(request, Team.class);

        entity = service.save(entity);
        TeamStatistic teamStatistic = new TeamStatistic();
        teamStatistic.setTeam(entity);
        teamStatsService.save(teamStatistic);

        TeamResponse data = modelMapper.map(entity, TeamResponse.class);

        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<TeamResponse> edit(@PathVariable String id,
                                              @RequestBody @Valid TeamRequest request){

        Team entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        modelMapper.map(request, entity);

        entity = service.save(entity);

        TeamResponse data = modelMapper.map(entity, TeamResponse.class);
        return ResponseMessage.success(data);
    }

    @GetMapping
    public ResponseMessage<PagedList<TeamResponse>> getAll(@Valid TeamSearch request){
        Page<Team> entityPage = service.getAll(request.getPage(),
                request.getSize(), request.getSort());
        List<Team> entities = entityPage.toList();

        List<TeamResponse> models = entities.stream()
                .map(e -> modelMapper.map(e, TeamResponse.class))
                .collect(Collectors.toList());

        PagedList<TeamResponse> data = new PagedList(models,
                entityPage.getNumber(), entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }

    @GetMapping("/name/{name}")
    public ResponseMessage<PagedList<TeamResponse>> searchByName(@PathVariable String name,@Valid TeamSearch request){
        Page<Team> entityPage = service.searchByName(name,request.getPage(),
                request.getSize(), request.getSort());
        List<Team> entities = entityPage.toList();

        List<TeamResponse> models = entities.stream()
                .map(e -> modelMapper.map(e, TeamResponse.class))
                .collect(Collectors.toList());

        PagedList<TeamResponse> data = new PagedList(models,
                entityPage.getNumber(), entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage getById(@PathVariable String id){
        Team entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        TeamResponse data = modelMapper.map(entity, TeamResponse.class);

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<TeamResponse> deleteById(@PathVariable String id){
        Team entity = service.deleteById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        TeamResponse data = modelMapper.map(entity, TeamResponse.class);
        return ResponseMessage.success(data);
    }
}

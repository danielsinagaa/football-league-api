package com.enigma.Football.controllers;

import com.enigma.Football.entity.TeamStatistic;
import com.enigma.Football.exceptions.EntityNotFoundException;
import com.enigma.Football.models.TeamsStats.TeamStatsResponse;
import com.enigma.Football.models.TeamsStats.TeamStatsRequest;
import com.enigma.Football.models.TeamsStats.TeamStatsSearch;
import com.enigma.Football.models.pages.PagedList;
import com.enigma.Football.models.responses.ResponseMessage;
import com.enigma.Football.services.TeamService;
import com.enigma.Football.services.TeamStatsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/teamstats")
@RestController
public class TeamStatsController {
    @Autowired
    private TeamStatsService service;

    @Autowired
    private TeamService teamService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseMessage<TeamStatsResponse> add(@RequestBody @Valid TeamStatsRequest request){
        TeamStatistic entity = modelMapper.map(request, TeamStatistic.class);
        entity.setTeam(teamService.getById(request.getTeam_id()));

        entity = service.save(entity);

        TeamStatsResponse data = modelMapper.map(entity, TeamStatsResponse.class);

        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<TeamStatsResponse> edit(@PathVariable String id,
                                              @RequestBody @Valid TeamStatsRequest request){

        TeamStatistic entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        modelMapper.map(request, entity);

        entity.setTeam(teamService.getById(request.getTeam_id()));
        entity = service.save(entity);

        TeamStatsResponse data = modelMapper.map(entity, TeamStatsResponse.class);
        return ResponseMessage.success(data);
    }

    @GetMapping
    public ResponseMessage<PagedList<TeamStatsResponse>> getAll(@Valid TeamStatsSearch request){
        Page<TeamStatistic> entityPage = service.getAll(request.getPage(),
                request.getSize(), request.getSort());
        List<TeamStatistic> entities = entityPage.toList();

        List<TeamStatsResponse> models = entities.stream()
                .map(e -> modelMapper.map(e, TeamStatsResponse.class))
                .collect(Collectors.toList());

        PagedList<TeamStatsResponse> data = new PagedList(models,
                entityPage.getNumber(), entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage getById(@PathVariable String id){
        TeamStatistic entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        TeamStatsResponse data = modelMapper.map(entity, TeamStatsResponse.class);

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<TeamStatsResponse> deleteById(@PathVariable String id){
        TeamStatistic entity = service.deleteById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        TeamStatsResponse data = modelMapper.map(entity, TeamStatsResponse.class);
        return ResponseMessage.success(data);
    }

}

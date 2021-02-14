package com.enigma.Football.controllers;

import com.enigma.Football.entity.*;
import com.enigma.Football.exceptions.EntityNotFoundException;
import com.enigma.Football.models.pages.PagedList;
import com.enigma.Football.models.playerstats.PlayerStatResponse;
import com.enigma.Football.models.playerstats.PlayerStatSearch;
import com.enigma.Football.models.responses.ResponseMessage;
import com.enigma.Football.models.results.ResultRequest;
import com.enigma.Football.models.results.ResultResponse;
import com.enigma.Football.models.results.ResultSearch;
import com.enigma.Football.models.teams.TeamRequest;
import com.enigma.Football.models.teams.TeamResponse;
import com.enigma.Football.services.MatchService;
import com.enigma.Football.services.ResultService;
import com.enigma.Football.services.TeamService;
import com.enigma.Football.services.TeamStatsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/result")
@RestController
public class ResultController {
    @Autowired
    ResultService service;

    @Autowired
    MatchService matchService;

    @Autowired
    TeamStatsService teamStatsService;

    @Autowired
    TeamService teamService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseMessage<ResultResponse> add(@RequestBody @Valid ResultRequest request) {
        Result entity = modelMapper.map(request, Result.class);
        Matchs matchs = matchService.getById(request.getMatchs());
        entity.setMatchs(matchs);

        Team win;
        TeamStatistic statsAway = teamStatsService.getByItemId(matchs.getAway().getId());
        TeamStatistic statsHome = teamStatsService.getByItemId(matchs.getHome().getId());

        Team away = teamService.getById(matchs.getAway().getId());
        Team home = teamService.getById(matchs.getHome().getId());

        if (request.getAwayGoal() == request.getHomeGoal()){
            win = null;
            statsAway.setDraw(statsAway.getDraw()+1);
            statsHome.setDraw(statsHome.getDraw()+1);

            statsAway.setPoints(statsAway.getPoints() + 1);
            statsHome.setPoints(statsHome.getPoints() + 1);
        } else if (request.getAwayGoal() > request.getHomeGoal()){
            win = away;
            statsAway.setWin(statsAway.getWin()+1);
            statsAway.setPoints(statsAway.getPoints() + 3);
            statsHome.setLose(statsHome.getLose()+1);
        } else {
            win = home;
            statsHome.setWin(statsHome.getWin()+1);
            statsHome.setPoints(statsHome.getPoints() + 3);
            statsAway.setLose(statsAway.getLose()+1);
        }

        statsAway = teamStatsService.save(statsAway);
        statsHome = teamStatsService.save(statsHome);

        entity.setResult(win);
        entity = service.save(entity);

        ResultResponse data = modelMapper.map(entity, ResultResponse.class);

        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<ResultResponse> edit(@PathVariable String id,
                                              @RequestBody @Valid ResultSearch request){

        Result entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        modelMapper.map(request, entity);

        entity = service.save(entity);

        ResultResponse data = modelMapper.map(entity, ResultResponse.class);
        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage getById(@PathVariable String id){
        Result entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        ResultResponse data = modelMapper.map(entity, ResultResponse.class);

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<ResultResponse> deleteById(@PathVariable String id){
        Result entity = service.deleteById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        ResultResponse data = modelMapper.map(entity, ResultResponse.class);
        return ResponseMessage.success(data);
    }

    @GetMapping
    public ResponseMessage<PagedList<ResultResponse>> getAll(@Valid ResultSearch request){
        Page<Result> entityPage = service.getAll(request.getPage(),
                request.getSize(), request.getSort());
        List<Result> entities = entityPage.toList();

        List<ResultResponse> models = entities.stream()
                .map(e -> modelMapper.map(e, ResultResponse.class))
                .collect(Collectors.toList());

        PagedList<ResultResponse> data = new PagedList(models,
                entityPage.getNumber(), entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }
}

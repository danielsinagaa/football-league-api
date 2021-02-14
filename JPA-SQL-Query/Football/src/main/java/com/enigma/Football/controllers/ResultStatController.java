package com.enigma.Football.controllers;

import com.enigma.Football.entity.PlayerStatistic;
import com.enigma.Football.entity.Referee;
import com.enigma.Football.entity.ResultStatistic;
import com.enigma.Football.entity.Team;
import com.enigma.Football.exceptions.EntityNotFoundException;
import com.enigma.Football.models.pages.PagedList;
import com.enigma.Football.models.referee.RefereeRequest;
import com.enigma.Football.models.referee.RefereeResponse;
import com.enigma.Football.models.responses.ResponseMessage;
import com.enigma.Football.models.resultstats.ResultStatResponse;
import com.enigma.Football.models.resultstats.ResultStatSearch;
import com.enigma.Football.models.resultstats.ResultStatsRequest;
import com.enigma.Football.models.teams.TeamRequest;
import com.enigma.Football.models.teams.TeamResponse;
import com.enigma.Football.models.teams.TeamSearch;
import com.enigma.Football.services.PlayerService;
import com.enigma.Football.services.PlayerStatService;
import com.enigma.Football.services.ResultService;
import com.enigma.Football.services.ResultStatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/resultstats")
@RestController
public class ResultStatController {
    @Autowired
    private ResultStatService service;

    @Autowired
    private PlayerStatService playerStatService;

    @Autowired
    private ResultService resultService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseMessage<ResultStatResponse> add(@RequestBody ResultStatsRequest request){
        ResultStatistic entity = modelMapper.map(request, ResultStatistic.class);
        PlayerStatistic playerStatistic = playerStatService.getByPlayer(request.getPlayer());
        entity.setResult(resultService.getById(request.getResult()));
        entity.setPlayer(playerService.getById(request.getPlayer()));

        playerStatistic.setAssist(playerStatistic.getAssist() + request.getAssit());
        playerStatistic.setGoal(playerStatistic.getGoal() + request.getGoal());
        playerStatistic.setRedCard(playerStatistic.getRedCard() + request.getRedCard());
        playerStatistic.setYellowCard(playerStatistic.getYellowCard() + request.getYellowCard());

        entity = service.save(entity);

        ResultStatResponse data = modelMapper.map(entity, ResultStatResponse.class);

        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<ResultStatResponse> edit(@PathVariable String id,
                                              @RequestBody @Valid ResultStatSearch request){

        ResultStatistic entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        modelMapper.map(request, entity);

        entity = service.save(entity);

        ResultStatResponse data = modelMapper.map(entity, ResultStatResponse.class);
        return ResponseMessage.success(data);
    }

    @GetMapping
    public ResponseMessage<PagedList<ResultStatResponse>> getAll(@Valid ResultStatSearch request){
        Page<ResultStatistic> entityPage = service.getAll(request.getPage(),
                request.getSize(), request.getSort());
        List<ResultStatistic> entities = entityPage.toList();

        List<ResultStatResponse> models = entities.stream()
                .map(e -> modelMapper.map(e, ResultStatResponse.class))
                .collect(Collectors.toList());

        PagedList<ResultStatResponse> data = new PagedList(models,
                entityPage.getNumber(), entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage getById(@PathVariable String id){
        ResultStatistic entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        ResultStatResponse data = modelMapper.map(entity, ResultStatResponse.class);

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<ResultStatResponse> deleteById(@PathVariable String id){
        ResultStatistic entity = service.deleteById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        ResultStatResponse data = modelMapper.map(entity, ResultStatResponse.class);
        return ResponseMessage.success(data);
    }
}

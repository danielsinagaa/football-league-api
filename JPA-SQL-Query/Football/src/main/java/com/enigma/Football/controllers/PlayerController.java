package com.enigma.Football.controllers;

import com.enigma.Football.entity.Player;
import com.enigma.Football.entity.PlayerStatistic;
import com.enigma.Football.exceptions.EntityNotFoundException;
import com.enigma.Football.models.pages.PagedList;
import com.enigma.Football.models.players.PlayerRequest;
import com.enigma.Football.models.players.PlayerResponse;
import com.enigma.Football.models.players.PlayerSearch;
import com.enigma.Football.models.responses.ResponseMessage;
import com.enigma.Football.services.PlayerPositionService;
import com.enigma.Football.services.PlayerService;
import com.enigma.Football.services.PlayerStatService;
import com.enigma.Football.services.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/players")
@RestController
public class PlayerController {
    @Autowired
    private PlayerService service;

    @Autowired
    private PlayerPositionService playerPositionService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerStatService playerStatService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseMessage<PlayerResponse> add(@RequestBody @Valid PlayerRequest request){
        Player entity = modelMapper.map(request, Player.class);
        entity.setTeam(teamService.getById(request.getTeam()));
        entity.setPlayerPosition(playerPositionService.getById(request.getPosition()));

        entity = service.save(entity);
        PlayerStatistic playerStatistic = new PlayerStatistic();
        playerStatistic.setPlayer(entity);
        playerStatService.save(playerStatistic);
        PlayerResponse data = modelMapper.map(entity, PlayerResponse.class);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<PlayerResponse> edit(@PathVariable String id,
                                              @RequestBody @Valid PlayerRequest request){

        Player entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        modelMapper.map(request, entity);

        entity = service.save(entity);

        PlayerResponse data = modelMapper.map(entity, PlayerResponse.class);
        return ResponseMessage.success(data);
    }

    @GetMapping
    public ResponseMessage<PagedList<PlayerResponse>> getAll(@Valid PlayerSearch request){
        Page<Player> entityPage = service.getAll(request.getPage(),
                request.getSize(), request.getSort());
        List<Player> entities = entityPage.toList();

        List<PlayerResponse> models = entities.stream()
                .map(e -> modelMapper.map(e, PlayerResponse.class))
                .collect(Collectors.toList());

        PagedList<PlayerResponse> data = new PagedList(models,
                entityPage.getNumber(), entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }

    @GetMapping("/name/{name}")
    public ResponseMessage<PagedList<PlayerResponse>> searchByName(@PathVariable String name, @Valid PlayerSearch request){
        Page<Player> entityPage = service.searchByName(name,request.getPage(),
                request.getSize(), request.getSort());
        List<Player> entities = entityPage.toList();

        List<PlayerResponse> models = entities.stream()
                .map(e -> modelMapper.map(e, PlayerResponse.class))
                .collect(Collectors.toList());

        PagedList<PlayerResponse> data = new PagedList(models,
                entityPage.getNumber(), entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage getById(@PathVariable String id){
        Player entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        PlayerResponse data = modelMapper.map(entity, PlayerResponse.class);

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<PlayerResponse> deleteById(@PathVariable String id){
        List<PlayerStatistic> statistics = playerStatService.findAll().stream().filter(s -> s.getPlayer().equals(service.getById(id))).collect(Collectors.toList());

        PlayerStatistic playerStatistic = playerStatService.deleteById(statistics.get(0).getId());

        Player entity = service.deleteById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        PlayerResponse data = modelMapper.map(entity, PlayerResponse.class);
        return ResponseMessage.success(data);
    }
}

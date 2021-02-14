package com.enigma.Football.controllers;

import com.enigma.Football.entity.PlayerStatistic;
import com.enigma.Football.exceptions.EntityNotFoundException;
import com.enigma.Football.models.pages.PagedList;
import com.enigma.Football.models.playerstats.PlayerStatRequest;
import com.enigma.Football.models.playerstats.PlayerStatResponse;
import com.enigma.Football.models.playerstats.PlayerStatSearch;
import com.enigma.Football.models.responses.ResponseMessage;
import com.enigma.Football.services.PlayerService;
import com.enigma.Football.services.PlayerStatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/playerstats")
@RestController
public class PlayerStatsController {

    @Autowired
    private PlayerStatService service;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseMessage<PlayerStatResponse> add(@RequestBody @Valid PlayerStatRequest request){
        PlayerStatistic entity = modelMapper.map(request, PlayerStatistic.class);
        entity.setPlayer(playerService.getById(request.getPlayer()));
        entity = service.save(entity);

        PlayerStatResponse data = modelMapper.map(entity, PlayerStatResponse.class);

        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<PlayerStatResponse> edit(@PathVariable String id,
                                              @RequestBody @Valid PlayerStatRequest request){

        PlayerStatistic entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        modelMapper.map(request, entity);

        entity = service.save(entity);

        PlayerStatResponse data = modelMapper.map(entity, PlayerStatResponse.class);
        return ResponseMessage.success(data);
    }

    @GetMapping
    public ResponseMessage<PagedList<PlayerStatResponse>> getAll(@Valid PlayerStatSearch request){
        Page<PlayerStatistic> entityPage = service.getAll(request.getPage(),
                request.getSize(), request.getSort());
        List<PlayerStatistic> entities = entityPage.toList();

        List<PlayerStatResponse> models = entities.stream()
                .map(e -> modelMapper.map(e, PlayerStatResponse.class))
                .collect(Collectors.toList());

        PagedList<PlayerStatResponse> data = new PagedList(models,
                entityPage.getNumber(), entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage getById(@PathVariable String id){
        PlayerStatistic entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        PlayerStatResponse data = modelMapper.map(entity, PlayerStatResponse.class);

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<PlayerStatResponse> deleteById(@PathVariable String id){
        PlayerStatistic entity = service.deleteById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        PlayerStatResponse data = modelMapper.map(entity, PlayerStatResponse.class);
        return ResponseMessage.success(data);
    }
}

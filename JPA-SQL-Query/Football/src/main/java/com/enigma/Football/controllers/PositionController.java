package com.enigma.Football.controllers;

import com.enigma.Football.entity.PlayerPosition;
import com.enigma.Football.exceptions.EntityNotFoundException;
import com.enigma.Football.models.pages.PagedList;
import com.enigma.Football.models.positions.PlayerPositionRequest;
import com.enigma.Football.models.positions.PlayerPositionResponse;
import com.enigma.Football.models.positions.PlayerPositionSearch;
import com.enigma.Football.models.responses.ResponseMessage;
import com.enigma.Football.services.PlayerPositionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/positions")
@RestController
public class PositionController {
    @Autowired
    private PlayerPositionService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseMessage<PlayerPositionResponse> add(@RequestBody PlayerPositionRequest request){
        PlayerPosition entity = modelMapper.map(request, PlayerPosition.class);

        entity = service.save(entity);

        PlayerPositionResponse data = modelMapper.map(entity, PlayerPositionResponse.class);

        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<PlayerPositionResponse> edit(@PathVariable String id,
                                              @RequestBody @Valid PlayerPositionRequest request){

        PlayerPosition entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        modelMapper.map(request, entity);

        entity = service.save(entity);

        PlayerPositionResponse data = modelMapper.map(entity, PlayerPositionResponse.class);
        return ResponseMessage.success(data);
    }

    @GetMapping
    public ResponseMessage<PagedList<PlayerPositionResponse>> getAll(@Valid PlayerPositionSearch request){
        Page<PlayerPosition> entityPage = service.getAll(request.getPage(),
                request.getSize(), request.getSort());
        List<PlayerPosition> entities = entityPage.toList();

        List<PlayerPositionResponse> models = entities.stream()
                .map(e -> modelMapper.map(e, PlayerPositionResponse.class))
                .collect(Collectors.toList());

        PagedList<PlayerPositionResponse> data = new PagedList(models,
                entityPage.getNumber(), entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }

    @GetMapping("/name/{name}")
    public ResponseMessage<PagedList<PlayerPositionResponse>> searchByName(@PathVariable String name,@Valid PlayerPositionSearch request){
        Page<PlayerPosition> entityPage = service.searchByName(name,request.getPage(),
                request.getSize(), request.getSort());
        List<PlayerPosition> entities = entityPage.toList();

        List<PlayerPositionResponse> models = entities.stream()
                .map(e -> modelMapper.map(e, PlayerPositionResponse.class))
                .collect(Collectors.toList());

        PagedList<PlayerPositionResponse> data = new PagedList(models,
                entityPage.getNumber(), entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage getById(@PathVariable String id){
        PlayerPosition entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        PlayerPositionResponse data = modelMapper.map(entity, PlayerPositionResponse.class);

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<PlayerPositionResponse> deleteById(@PathVariable String id){
        PlayerPosition entity = service.deleteById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        PlayerPositionResponse data = modelMapper.map(entity, PlayerPositionResponse.class);
        return ResponseMessage.success(data);
    }

}

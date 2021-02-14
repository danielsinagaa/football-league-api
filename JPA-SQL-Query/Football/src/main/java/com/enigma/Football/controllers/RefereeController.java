package com.enigma.Football.controllers;

import com.enigma.Football.entity.Referee;
import com.enigma.Football.exceptions.EntityNotFoundException;
import com.enigma.Football.models.pages.PagedList;
import com.enigma.Football.models.referee.RefereeRequest;
import com.enigma.Football.models.referee.RefereeResponse;
import com.enigma.Football.models.referee.RefereeSearch;
import com.enigma.Football.models.responses.ResponseMessage;
import com.enigma.Football.services.RefereeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/referee")
@RestController
public class RefereeController {
    @Autowired
    private RefereeService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseMessage<RefereeResponse> add(@RequestBody RefereeRequest request){
        Referee entity = modelMapper.map(request, Referee.class);

        entity = service.save(entity);

        RefereeResponse data = modelMapper.map(entity, RefereeResponse.class);

        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<RefereeResponse> edit(@PathVariable String id,
                                                        @RequestBody @Valid RefereeRequest request){

        Referee entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        modelMapper.map(request, entity);

        entity = service.save(entity);

        RefereeResponse data = modelMapper.map(entity, RefereeResponse.class);
        return ResponseMessage.success(data);
    }

    @GetMapping
    public ResponseMessage<PagedList<RefereeResponse>> getAll(@Valid RefereeSearch request){
        Page<Referee> entityPage = service.getAll(request.getPage(),
                request.getSize(), request.getSort());
        List<Referee> entities = entityPage.toList();

        List<RefereeResponse> models = entities.stream()
                .map(e -> modelMapper.map(e, RefereeResponse.class))
                .collect(Collectors.toList());

        PagedList<RefereeResponse> data = new PagedList(models,
                entityPage.getNumber(), entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }

    @GetMapping("/name/{name}")
    public ResponseMessage<PagedList<RefereeResponse>> searchByName(@PathVariable String name,@Valid RefereeSearch request){
        Page<Referee> entityPage = service.searchByName(name,request.getPage(),
                request.getSize(), request.getSort());
        List<Referee> entities = entityPage.toList();

        List<RefereeResponse> models = entities.stream()
                .map(e -> modelMapper.map(e, RefereeResponse.class))
                .collect(Collectors.toList());

        PagedList<RefereeResponse> data = new PagedList(models,
                entityPage.getNumber(), entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage getById(@PathVariable String id){
        Referee entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        RefereeResponse data = modelMapper.map(entity, RefereeResponse.class);

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<RefereeResponse> deleteById(@PathVariable String id){
        Referee entity = service.deleteById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        RefereeResponse data = modelMapper.map(entity, RefereeResponse.class);
        return ResponseMessage.success(data);
    }
}

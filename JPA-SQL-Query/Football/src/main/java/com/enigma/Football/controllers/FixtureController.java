package com.enigma.Football.controllers;

import com.enigma.Football.entity.Fixture;
import com.enigma.Football.exceptions.EntityNotFoundException;
import com.enigma.Football.models.fixtures.FixtureRequest;
import com.enigma.Football.models.fixtures.FixtureResponse;
import com.enigma.Football.models.fixtures.FixtureSearch;
import com.enigma.Football.models.pages.PagedList;
import com.enigma.Football.models.responses.ResponseMessage;
import com.enigma.Football.services.FixtureService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/fixture")
@RestController
public class FixtureController {
    @Autowired
    private FixtureService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseMessage<FixtureResponse> add(@RequestBody FixtureRequest request){
        Fixture entity = modelMapper.map(request, Fixture.class);

        entity = service.save(entity);

        FixtureResponse data = modelMapper.map(entity, FixtureResponse.class);

        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<FixtureResponse> edit(@PathVariable String id,
                                                        @RequestBody @Valid FixtureRequest request){

        Fixture entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        modelMapper.map(request, entity);

        entity = service.save(entity);

        FixtureResponse data = modelMapper.map(entity, FixtureResponse.class);
        return ResponseMessage.success(data);
    }

    @GetMapping
    public ResponseMessage<PagedList<FixtureResponse>> getAll(@Valid FixtureSearch request){
        Page<Fixture> entityPage = service.getAll(request.getPage(),
                request.getSize(), request.getSort());
        List<Fixture> entities = entityPage.toList();

        List<FixtureResponse> models = entities.stream()
                .map(e -> modelMapper.map(e, FixtureResponse.class))
                .collect(Collectors.toList());

        PagedList<FixtureResponse> data = new PagedList(models,
                entityPage.getNumber(), entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage getById(@PathVariable String id){
        Fixture entity = service.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        FixtureResponse data = modelMapper.map(entity, FixtureResponse.class);

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<FixtureResponse> deleteById(@PathVariable String id){
        Fixture entity = service.deleteById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        FixtureResponse data = modelMapper.map(entity, FixtureResponse.class);
        return ResponseMessage.success(data);
    }
}

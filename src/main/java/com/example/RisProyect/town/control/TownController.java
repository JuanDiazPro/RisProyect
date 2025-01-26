package com.example.RisProyect.town.control;

import com.example.RisProyect.town.model.TownDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/town")
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class TownController {

    private final com.example.RisProyect.town.control.TownService service;

    @Autowired
    public TownController(com.example.RisProyect.town.control.TownService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return service.findAll();
    }

    @GetMapping("/active")
    public ResponseEntity<Object> findAllEnabled() {
        return service.findAllEnabled();
    }

    @PostMapping
    public ResponseEntity<Object> save(@Validated(TownDto.Register.class) @RequestBody TownDto dto) {
        return service.save(dto);
    }

    @PutMapping
    public ResponseEntity<Object> update(@Validated({TownDto.Modify.class}) @RequestBody TownDto dto) {
        return service.update(dto);
    }

    @PutMapping("/change-status")
    public ResponseEntity<Object> changeStatus(@Validated(TownDto.ChangeStatus.class) @RequestBody TownDto dto) {
        return service.changeStatus(dto);
    }
}
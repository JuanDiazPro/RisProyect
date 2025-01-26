package com.example.RisProyect.state.control;
import com.example.RisProyect.state.model.StateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/state")
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class StateController {

    private final StateService service;

    @Autowired
    public StateController(StateService service) {
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
    public ResponseEntity<Object> save(@Validated(StateDto.Register.class) @RequestBody StateDto dto) {
        return service.save(dto);
    }

    @PutMapping
    public ResponseEntity<Object> update(@Validated({StateDto.Modify.class}) @RequestBody StateDto dto) {
        return service.update(dto);
    }

    @PutMapping("/change-status")
    public ResponseEntity<Object> changeStatus(@Validated(StateDto.ChangeStatus.class) @RequestBody StateDto dto) {
        return service.changeStatus(dto);
    }
}
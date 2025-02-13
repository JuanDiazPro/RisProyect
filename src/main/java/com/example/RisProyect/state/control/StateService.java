package com.example.RisProyect.state.control;

import com.example.practica_completa.state.model.State;
import com.example.RisProyect.state.model.StateDto;
import com.example.RisProyect.state.model.StateRepository;
import com.example.RisProyect.utils.Message;
import com.example.practica_completa.utils.TypesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class StateService {
    private final static Logger logger = LoggerFactory.getLogger(StateService.class);

    private final StateRepository repository;

    @Autowired
    public StateService(StateRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(new Message(repository.findAll(), "Listado de estados de residencia activos", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAllEnabled() {
        return new ResponseEntity<>(new Message(repository.findAllByStatusOrderByName(true), "Listado de estados de residencia activos", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> save(StateDto dto) {
        dto.setName(dto.getName().toLowerCase());
        if(dto.getName().length()<3){
            return new ResponseEntity<>(new Message("El nombre del Estado de Residencia no puede tener menos de 3 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        Optional<State> optionalState = repository.searchByNameAndId(dto.getName(), 0L);
        if (optionalState.isPresent()) {
            return new ResponseEntity<>(new Message("El nombre del Estado de Residencia ya existe", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        State state = new State(dto.getName(), true);
        state = repository.saveAndFlush(state);
        if (state == null) {
            logger.error("Error al guardar el estado de residencia");
            return new ResponseEntity<>(new Message("No se registró el Estado de Residencia", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Message(state, "Se registró el Estado de Residencia", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> update(StateDto dto) {
        dto.setName(dto.getName().toLowerCase());
        if(dto.getName().length()<3){
            return new ResponseEntity<>(new Message("El nombre del Estado de Residencia no puede tener menos de 3 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        Optional<State> optional = repository.findById(dto.getId());
        if (!optional.isPresent()) {
            return new ResponseEntity<>(new Message("No se encontró el Estado de Residencia", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }
        Optional<State> optionalState = repository.searchByNameAndId(dto.getName(), dto.getId());
        if (optionalState.isPresent()) {
            return new ResponseEntity<>(new Message("El nombre del Estado de Residencia ya existe", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        State state = optional.get();
        state.setName(dto.getName());
        state = repository.saveAndFlush(state);
        if (state == null) {
            logger.error("No se pudo guardar el Estado de Residencia");
            return new ResponseEntity<>(new Message("No se modificó el Estado de Residencia", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Message(state, "Se modificó el Estado de Residencia", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> changeStatus(StateDto dto) {
        Optional<State> optional = repository.findById(dto.getId());
        if (!optional.isPresent()) {
            return new ResponseEntity<>(new Message("No se encontró el Estado de Residencia", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }
        State state = optional.get();
        state.setStatus(!state.isStatus());
        state = repository.saveAndFlush(state);
        if (state == null) {
            logger.error("No se pudo modificar el Estado de Residencia");
            return new ResponseEntity<>(new Message("No se modificó el estado del Estado de Residencia", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Message(state, "Se modificó el estado del Estado de Residencia", TypesResponse.SUCCESS), HttpStatus.OK);
    }

}
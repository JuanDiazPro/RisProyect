package com.example.practica_completa.town.control;
import com.example.practica_completa.state.model.State;
import com.example.practica_completa.state.model.StateRepository;
import com.example.practica_completa.town.model.Town;
import com.example.practica_completa.town.model.TownDto;
import com.example.practica_completa.town.model.TownRepository;
import com.example.practica_completa.utils.Message;
import com.example.practica_completa.utils.TypesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TownService {
    private final static Logger logger = LoggerFactory.getLogger(TownService.class);

    private final TownRepository repository;
    private final StateRepository stateRepository;

    @Autowired
    public TownService(TownRepository repository, StateRepository stateRepository) {
        this.repository = repository;
        this.stateRepository = stateRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAll() {
        return new ResponseEntity<>(new Message(repository.findAll(), "Listado de municipios", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findAllEnabled() {
        return new ResponseEntity<>(new Message(repository.findAllByStatusOrderByName(true), "Listado de municipios activos", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> save(TownDto dto) {
        dto.setName(dto.getName().toLowerCase());
        if(dto.getName().length()<3){
            return new ResponseEntity<>(new Message("El nombre del Municipio no puede tener menos de 3 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        Optional<Town> optionalTown = repository.searchByNameAndId(dto.getName(), 0L);
        if (optionalTown.isPresent()) {
            return new ResponseEntity<>(new Message("El nombre del Municipio ya existe", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        Optional<State> optionalState = stateRepository.findById(dto.getState().getId());
        if (!optionalState.isPresent()) {
            return new ResponseEntity<>(new Message("El estado no existe", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        Town town = new Town(dto.getName(), true, optionalState.get());
        town = repository.saveAndFlush(town);
        if (town == null) {
            logger.error("Error al guardar el town");
            return new ResponseEntity<>(new Message("No se registró el Municipio", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Message(town, "Se registró el Municipio", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> update(TownDto dto) {
        dto.setName(dto.getName().toLowerCase());
        if(dto.getName().length()<3){
            return new ResponseEntity<>(new Message("El nombre del Municipio no puede tener menos de 3 caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        Optional<Town> optional = repository.findById(dto.getId());
        if (!optional.isPresent()) {
            return new ResponseEntity<>(new Message("No se encontró el Municipio", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }
        Optional<Town> optionalTown = repository.searchByNameAndId(dto.getName(), dto.getId());
        if (optionalTown.isPresent()) {
            return new ResponseEntity<>(new Message("El nombre del Municipio ya existe", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        Optional<State> optionalState = stateRepository.findById(dto.getState().getId());
        if (!optionalState.isPresent()) {
            return new ResponseEntity<>(new Message("El estado no existe", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        Town town = optional.get();
        town.setName(dto.getName());
        town.setState(optionalState.get());
        town = repository.saveAndFlush(town);
        if (town == null) {
            logger.error("No se pudo modificar el Municipio");
            return new ResponseEntity<>(new Message("No se modificó el Municipio", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Message(town, "Se modificó el Municipio", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Object> changeStatus(TownDto dto) {
        Optional<Town> optional = repository.findById(dto.getId());
        if (!optional.isPresent()) {
            return new ResponseEntity<>(new Message("No se encontró el Municipio", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }
        Town town = optional.get();
        town.setStatus(!town.isStatus());
        town = repository.saveAndFlush(town);
        if (town == null) {
            logger.error("No se pudo cambiar el estado del municio");
            return new ResponseEntity<>(new Message("No se modificó el estado del Municipio", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Message(town, "Se modificó el estado del Municipio", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> findByState(TownDto dto){
        Optional<State> optionalState = stateRepository.findById(dto.getState().getId());
        if (!optionalState.isPresent()) {
            logger.warn("El estado no existe");
            return new ResponseEntity<>(new Message("No se encontró el estado", TypesResponse.WARNING), HttpStatus.NOT_FOUND);
        }
        List<Town> list = repository.findAllByStateAndStatusOrderByName(optionalState.get(), true);
        return new ResponseEntity<>(new Message(list, "Listado de municipios por estado", TypesResponse.SUCCESS), HttpStatus.OK);
    }
}
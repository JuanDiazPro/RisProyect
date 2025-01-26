package com.example.practica_completa.town.model;
import com.example.practica_completa.state.model.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TownDto {
    @NotNull(groups = {Modify.class, ChangeStatus.class},message = "Es necesario el id")
    private Long id;
    @NotBlank(groups = {Modify.class, Register.class}, message = "Es necesario el nombre")
    private String name;

    @NotNull(groups = {Modify.class, Register.class}, message = "Es necesario el estado")
    private State state;


    public TownDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public interface Register {
    }

    public interface Modify {
    }

    public interface ChangeStatus {
    }

    public interface FindByState {
    }
}
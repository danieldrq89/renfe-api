package com.controller;

import com.model.Estacion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.services.EstacionService;

import java.util.List;

@RestController
public class EstacionController {

    private final EstacionService estacionService;

    EstacionController(EstacionService estacionService) {
        this.estacionService = estacionService;
    }

    @GetMapping("/estaciones")
    public List<Estacion> getAllUsers() {
        return estacionService.getAll();
    }

    @GetMapping("/estacion/name/{name}")
    public List<Estacion> getEstacionByName(@PathVariable("name") String name) {
        return estacionService.getEstacionByName(name);
    }

    @GetMapping("/estacion/id/{id}")
    public List<Estacion> getEstacionById(@PathVariable("id") String id){
        return estacionService.getEstacionById(Integer.parseInt(id));
    }
}

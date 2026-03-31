package com.services;

import com.model.Estacion;
import com.repository.EstacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstacionService {
    private final EstacionRepository estacionRepository;

    public EstacionService(EstacionRepository estacionRepository) {
        this.estacionRepository = estacionRepository;
    }

    public List<Estacion> getAll() {
        return estacionRepository.getAllEstaciones();
    }

    public List<Estacion> getEstacionByName(String name) {
        return estacionRepository.getEstacionesByLikeName(name) ;
    }
}

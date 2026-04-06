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

    public List<Estacion> getEstacionById(int id){return estacionRepository.getEstacionById(id);}

    public List<Estacion> getEstacionByCode(String code){return estacionRepository.getEstacionByCode(code);};

    public List<Estacion> getEstacionByDireccion(String direccion){return estacionRepository.getEstacionByDireccion(direccion);};

    public List<Estacion> getEstacionByProvincia(String provincia){return estacionRepository.getEstacionByProvincia(provincia);};

    public List<Estacion> getEstacionByPoblacion(String poblacion){return estacionRepository.getEstacionByPoblacion(poblacion);};
}

package com.platzi.pizzeria.service;

import com.platzi.pizzeria.persistence.entity.PizzaEntity;
import com.platzi.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> getAll() {
        return pizzaRepository.findAll();
    }

    public PizzaEntity get(int id) {
        return pizzaRepository.findById(id)
                .orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizzaEntity) {
        return pizzaRepository.save(pizzaEntity);
    }

    public boolean exists(int pizzaId) {
        return pizzaRepository.existsById(pizzaId);
    }

    public boolean delete(int id) {
        try {
            pizzaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

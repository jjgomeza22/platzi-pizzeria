package com.platzi.pizzeria.web.controller;

import com.platzi.pizzeria.persistence.entity.PizzaEntity;
import com.platzi.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int elements) {
        return ResponseEntity.ok(this.pizzaService.getAll(page, elements));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> getOne(@PathVariable("id") int id) {
        return ResponseEntity.ok(pizzaService.get(id));
    }

    @GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> getAvailable() {
        return ResponseEntity.ok(pizzaService.getAvailable());
    }

    @GetMapping("/availablePage")
    public ResponseEntity<Page<PizzaEntity>> getAvailablePageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int elements,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection
    ) {
        return ResponseEntity.ok(pizzaService.getAvailablePageable(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/pizza")
    public ResponseEntity<PizzaEntity> getByName(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(pizzaService.findByName(name));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWith(@PathVariable ("ingredient") String ingredient) {
        return ResponseEntity.ok(pizzaService.getWith(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWithOut(@PathVariable ("ingredient") String ingredient) {
        return ResponseEntity.ok(pizzaService.withOut(ingredient));
    }

    @GetMapping("/cheapest")
    public ResponseEntity<List<PizzaEntity>> getCheapest(@RequestParam (value = "price", required = false) Double price) {
        return ResponseEntity.ok(pizzaService.getCheapest(price));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity pizzaEntity) {
        if (pizzaEntity.getIdPizza() == null || !pizzaService.exists(pizzaEntity.getIdPizza())){
            return ResponseEntity.ok(pizzaService.save(pizzaEntity));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizzaEntity) {
        if (pizzaEntity.getIdPizza() != null || pizzaService.exists(pizzaEntity.getIdPizza())){
            return ResponseEntity.ok(pizzaService.save(pizzaEntity));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable ("id") int id) {
        return ResponseEntity.ok(pizzaService.delete(id));
    }
}

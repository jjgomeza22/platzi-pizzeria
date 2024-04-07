package com.platzi.pizzeria.service.dto;

import lombok.Data;

@Data
public class UpdatePizzaPriceDTO {
    private int pizzaId;
    private double newPrice;
}

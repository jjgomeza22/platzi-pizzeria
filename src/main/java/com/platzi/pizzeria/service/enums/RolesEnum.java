package com.platzi.pizzeria.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RolesEnum {
    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER"),
    CHEF("CHEF");

    private final String value;
}

package com.autoflex.inventory.dto;

import java.math.BigDecimal;

public class ProductionSuggestionDTO {
    public String productName;
    public Integer quantity;
    public BigDecimal totalValue;

    public ProductionSuggestionDTO() {}

    public ProductionSuggestionDTO(String productName, Integer quantity, BigDecimal totalValue) {
        this.productName = productName;
        this.quantity = quantity;
        this.totalValue = totalValue;
    }
}
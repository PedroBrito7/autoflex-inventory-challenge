package com.autoflex.inventory.service;

import com.autoflex.inventory.dto.ProductionSuggestionDTO;
import com.autoflex.inventory.model.Product;
import com.autoflex.inventory.model.ProductComposition;
import com.autoflex.inventory.model.RawMaterial;

import jakarta.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductionService {

    public List<ProductionSuggestionDTO> calculateProduction() {
        List<Product> products = Product.list("ORDER BY value DESC");

        Map<Long, Integer> tempStock = RawMaterial.<RawMaterial>listAll().stream()
                .collect(Collectors.toMap(rm -> rm.id, rm -> rm.stockQuantity));

        List<ProductionSuggestionDTO> suggestions = new ArrayList<>();

        for (Product product : products) {
            if (product.composition == null || product.composition.isEmpty()) continue;

            int maxProducible = Integer.MAX_VALUE;

            for (ProductComposition comp : product.composition) {
                int currentStock = tempStock.getOrDefault(comp.rawMaterial.id, 0);
                if (comp.requiredQuantity > 0) {
                    int possible = currentStock / comp.requiredQuantity;
                    maxProducible = Math.min(maxProducible, possible);
                }
            }

            if (maxProducible > 0) {
                suggestions.add(new ProductionSuggestionDTO(
                        product.name,
                        maxProducible,
                        product.value.multiply(new BigDecimal(maxProducible))
                ));

                for (ProductComposition comp : product.composition) {
                    long rmId = comp.rawMaterial.id;
                    int used = comp.requiredQuantity * maxProducible;
                    tempStock.put(rmId, tempStock.get(rmId) - used);
                }
            }
        }
        return suggestions;
    }
}
package com.autoflex.inventory.resource;

import com.autoflex.inventory.model.Product;
import com.autoflex.inventory.model.RawMaterial;
import com.autoflex.inventory.service.ProductionService;
import com.autoflex.inventory.dto.ProductionSuggestionDTO;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventoryResource {

    @Inject
    ProductionService productionService;

    @GET @Path("/raw-materials")
    public List<RawMaterial> getAllRawMaterials() {
        return RawMaterial.listAll();
    }

    @POST @Path("/raw-materials") @Transactional
    public Response createRawMaterial(RawMaterial rm) {
        rm.persist();
        return Response.status(201).entity(rm).build();
    }

    @GET @Path("/products")
    public List<Product> getAllProducts() {
        return Product.listAll();
    }

    @POST @Path("/products") @Transactional
    public Response createProduct(Product product) {
        if(product.composition != null) {
            product.composition.forEach(c -> c.product = product);
        }
        product.persist();
        return Response.status(201).entity(product).build();
    }

    @GET @Path("/production-plan")
    public List<ProductionSuggestionDTO> getProductionPlan() {
        return productionService.calculateProduction();
    }
}
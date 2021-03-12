package ch.noser.uek223.domain.product.dto;

import ch.noser.uek223.domain.user.dto.UserDTOMinimal;

import java.util.UUID;

public class ProductDTOWithoutPrice {

    private UUID id;
    private float retailPrice;
    private UserDTOMinimal suppliers;

    public ProductDTOWithoutPrice(UUID id, float retailPrice, UserDTOMinimal suppliers) {
        this.id = id;
        this.retailPrice = retailPrice;
        this.suppliers = suppliers;
    }

    public ProductDTOWithoutPrice() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public float getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(float retailPrice) {
        this.retailPrice = retailPrice;
    }
}

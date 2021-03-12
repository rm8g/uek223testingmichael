package ch.noser.uek223.domain.product.dto;

import ch.noser.uek223.domain.user.dto.UserDTOMinimal;

import java.util.UUID;

public class ProductDTORetailPrice {

    private UUID id;
    private float retailPrice;
    private float price;
    private UserDTOMinimal supplier;

    public ProductDTORetailPrice(UUID id, float retailPrice, float price, UserDTOMinimal supplier) {
        this.id = id;
        this.retailPrice = retailPrice;
        this.price = price;
        this.supplier = supplier;
    }

    public ProductDTORetailPrice() {
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public UserDTOMinimal getSupplier() {
        return supplier;
    }

    public void setSupplier(UserDTOMinimal supplier) {
        this.supplier = supplier;
    }
}

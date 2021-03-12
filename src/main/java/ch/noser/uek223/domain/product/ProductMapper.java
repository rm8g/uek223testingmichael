package ch.noser.uek223.domain.product;

import ch.noser.uek223.domain.product.dto.ProductDTORetailPrice;
import ch.noser.uek223.domain.product.dto.ProductDTOWithoutPrice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTORetailPrice productToProductDTORetailPrice(Product product);
    ProductDTOWithoutPrice productToProductDTOWithoutPrice(Product product);

}

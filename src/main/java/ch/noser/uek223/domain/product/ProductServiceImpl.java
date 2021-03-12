package ch.noser.uek223.domain.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll(Optional<Boolean> showArchived, Optional<Boolean> showOutOfStock) {
        List<Product> products = findAllAvailable();
        if (showArchived.isPresent() && showArchived.get()) {
            products.addAll(findAllArchived());
        }
        if (showOutOfStock.isPresent() && showOutOfStock.get()) {
            products.addAll(findAllOutOfStock());
        }
        return products;
    }

    @Override
    public List<Product> findAllAvailable() {
        return productRepository.findAllByAvailability(Availability.AVAILABLE);
    }

    @Override
    public List<Product> findAllArchived() {
        return productRepository.findAllByAvailability(Availability.ARCHIVED);
    }

    @Override
    public List<Product> findAllOutOfStock() {
        return productRepository.findAllByAvailability(Availability.OUT_OF_STOCK);
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(UUID.fromString(id)).get();
    }

    @Override
    public Product findByIdWithoutPrice(String id) {
        return productRepository.findById(UUID.fromString(id)).get();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(String id, Product changes) {
        if (changes.getId() == UUID.fromString(id)) {
            return productRepository.save(changes);
        }
        return null;
    }

    @Override
    public List<Product> findAllBetweenPrice(double lower, double upper,
                                             Optional<Boolean> showArchived,
                                             Optional<Boolean> showOutOfStock) {
        return findAllAvailable().stream().filter((product) -> product.getPrice() >= lower && product.getPrice() <= upper).collect(Collectors.toList());
    }

//    @Override
//    public Product archiveById(String id) {
//        Optional<Product> optionalProduct = findById(id);
//        return optionalProduct.map(product -> productRepository.save(product.setAvailability(Availability.ARCHIVED))).orElse(null);
//    }
}

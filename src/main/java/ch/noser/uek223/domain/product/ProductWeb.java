package ch.noser.uek223.domain.product;

import ch.noser.uek223.domain.product.dto.ProductDTORetailPrice;
import ch.noser.uek223.domain.product.dto.ProductDTOWithoutPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductWeb {

    private ProductMapper productMapper;
    private final ProductService productService;
    @Autowired
    public ProductWeb(ProductMapper productMapper,
                      ProductService productService){ this.productMapper = productMapper; this.productService = productService;}

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTORetailPrice> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(productMapper.productToProductDTORetailPrice(productService.findById(id)));
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<ProductDTOWithoutPrice> findByIdWithoutPrice(@PathVariable("id") String id){
        return ResponseEntity.ok().body(productMapper.productToProductDTOWithoutPrice(productService.findByIdWithoutPrice(id)));
    }


    /*@Autowired
    public ProductWeb(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> findAll(@RequestParam("archived") Optional<Boolean> archived, @RequestParam("outOfStock") Optional<Boolean> showOutOfStock) {
        return ResponseEntity.ok().body(productService.findAll(archived, showOutOfStock));
    }

*//*    @GetMapping("/{productId}")
    public ResponseEntity<Product> findById(@PathVariable("productId") String id) {
        return ResponseEntity.ok().body(productService.findById(id).get());
    }*//*

    @PostMapping("/")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return ResponseEntity.ok().body(productService.save(product));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> update(@PathVariable("productId") String id,
                                          @RequestBody Product product) {
        return ResponseEntity.ok().body(productService.update(id, product));
    }

    @GetMapping("/{lowerBound}/{upperBound}")
    public ResponseEntity<List<Product>> findAllBetween(@PathVariable("lowerBound") double lowerBound, @PathVariable("upperBound") double upperBound, @RequestParam("archived") Optional<Boolean> archived, @RequestParam("outOfStock") Optional<Boolean> showOutOfStock) {
        return ResponseEntity.ok().body(productService.findAllBetweenPrice(lowerBound, upperBound
                , archived, showOutOfStock));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> archiveById(@PathVariable("productId") String id) {
        return ResponseEntity.ok().body(productService.archiveById(id));
    }*/
}

package ch.noser.uek223.domain.purchase;

import ch.noser.uek223.domain.product_purchase.ProductPurchase;
import ch.noser.uek223.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "purchase")
    private Set<ProductPurchase> productPurchases;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private User customer;

    public UUID getId() {
        return id;
    }

    public Purchase setId(UUID id) {
        this.id = id;
        return this;
    }

    public Set<ProductPurchase> getProductPurchases() {
        return productPurchases;
    }

    public Purchase setProductPurchases(Set<ProductPurchase> productPurchases) {
        this.productPurchases = productPurchases;
        return this;
    }

    public User getCustomer() {
        return customer;
    }

    public Purchase setCustomer(User user) {
        this.customer = user;
        return this;
    }
}

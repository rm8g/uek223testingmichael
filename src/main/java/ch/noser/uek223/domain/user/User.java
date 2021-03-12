package ch.noser.uek223.domain.user;

import ch.noser.uek223.domain.product.Product;
import ch.noser.uek223.domain.purchase.Purchase;
import ch.noser.uek223.domain.role.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "users")
public class User {
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
    @Column(unique = true, nullable = false, length = 320)
    private String email;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String surname;
    @Column(length = 60)
    private String passwordHash;
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "supplier")
    private Set<Product> products;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private Set<Purchase> purchases;

    public <E> User(UUID id, String email, String password, String firstName, String lastName, Set<E> roles, Set<E> products, Set<E> purchases) {
    }

    public User() {

    }

    public UUID getId() {
        return id;
    }

    public User setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public User setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public User setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public User setProducts(Set<Product> products) {
        this.products = products;
        return this;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public User setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
        return this;
    }
}

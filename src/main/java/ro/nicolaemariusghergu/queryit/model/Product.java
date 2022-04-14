package ro.nicolaemariusghergu.queryit.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "icon_path")
    private String iconPath;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Category category;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.MERGE)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "minimarket_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private MiniMarket miniMarket;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Manufacturer manufacturer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

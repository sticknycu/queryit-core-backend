package ro.nicolaemariusghergu.queryit.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "promotions")
public class Promotion implements Serializable {

    @Id
    @Column(name = "promotion_id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @Column(name = "expire_date")
    private Long expireDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Promotion promotion = (Promotion) o;

        return id.equals(promotion.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

package ro.nicolaemariusghergu.queryit.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "minimarkets")
public class MiniMarket implements Serializable {

    @Id
    @Column(name = "minimarket_id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    /*@ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MiniMarket shelf = (MiniMarket) o;
        return id.equals(shelf.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


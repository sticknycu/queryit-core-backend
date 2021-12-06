package ro.nicolaemariusghergu.queryit.model;

import lombok.*;
import ro.nicolaemariusghergu.queryit.model.data.Product;
import ro.nicolaemariusghergu.queryit.model.data.ShelfCategory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shelves")
public class Shelf implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ShelfCategory shelfCategory;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelf shelf = (Shelf) o;
        return id.equals(shelf.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

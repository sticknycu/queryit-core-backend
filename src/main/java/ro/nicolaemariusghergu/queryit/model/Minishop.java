package ro.nicolaemariusghergu.queryit.model;

import lombok.*;
import ro.nicolaemariusghergu.queryit.model.data.MinishopCategory;
import ro.nicolaemariusghergu.queryit.model.data.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "minishops")
public class Minishop implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private MinishopCategory minishopCategory;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Minishop shelf = (Minishop) o;
        return id.equals(shelf.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


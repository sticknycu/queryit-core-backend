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
@Table(name = "deposits")
public class Deposit implements Serializable {

    @Id
    @Column(name = "deposit_id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deposit deposit = (Deposit) o;
        return id.equals(deposit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

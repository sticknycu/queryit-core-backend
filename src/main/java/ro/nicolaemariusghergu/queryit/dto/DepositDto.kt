package ro.nicolaemariusghergu.queryit.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DepositDto {

    private Long id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepositDto deposit = (DepositDto) o;
        return id.equals(deposit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


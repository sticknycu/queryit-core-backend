package ro.nicolaemariusghergu.queryit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDto {

    private Long id;

    private String name;

    private String description;

    private Long expireDate;

    private Integer quantityNeeded;
}

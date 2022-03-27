package ro.nicolaemariusghergu.queryit.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PromotionDto {

    private Long id;

    private String name;

    private String description;

    private Long expireDate;

    private Integer quantityNeeded;

    private ProductDto productId;
}

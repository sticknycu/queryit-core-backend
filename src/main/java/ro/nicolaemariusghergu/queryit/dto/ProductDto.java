package ro.nicolaemariusghergu.queryit.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductDto {

    private Long id;

    private String name;

    private Double price;

    private Integer quantity;

    private String iconUrl;

    private CategoryDto category;

    private PromotionDto promotion;
}

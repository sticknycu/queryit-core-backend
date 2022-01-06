package ro.nicolaemariusghergu.queryit.dto;

import lombok.*;
import ro.nicolaemariusghergu.queryit.model.data.Product;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto extends Product {

    private Double price;

    private LocalDateTime deliveryData;

    private Integer quantity;

    private String iconUrl;

    private String manufacturerName;
}

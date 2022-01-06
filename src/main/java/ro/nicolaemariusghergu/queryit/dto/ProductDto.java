package ro.nicolaemariusghergu.queryit.dto;

import lombok.*;
import ro.nicolaemariusghergu.queryit.model.Product;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto extends Product {

    private String iconUrl;
}

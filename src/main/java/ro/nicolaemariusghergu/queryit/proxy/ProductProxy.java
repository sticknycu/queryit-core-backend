package ro.nicolaemariusghergu.queryit.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.nicolaemariusghergu.queryit.model.Product;

import java.util.List;

@FeignClient
@RequestMapping("/api/products")
public interface ProductProxy {

    @GetMapping("/v1/{categoryId}")
    List<Product> getProducts(@PathVariable Long categoryId);

}

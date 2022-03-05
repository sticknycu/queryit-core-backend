package ro.nicolaemariusghergu.queryit.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import ro.nicolaemariusghergu.queryit.model.Product;

import static ro.nicolaemariusghergu.queryit.BackEndApplication.LOCALHOST;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductServiceImplTest {

    private static final String PRODUCTS_URL = "/v1/products";

    private static final Long PRODUCT_ID = 1L;

    @Autowired
    WebClient.Builder webClientBuilder;

    @Test
    void whenGetDataThenShowResult() {
        // give
        Product product = webClientBuilder
                .build()
                .get()
                .uri(LOCALHOST + PRODUCTS_URL + "/" + PRODUCT_ID)
                .retrieve()
                .bodyToMono(Product.class)
                .block();

        // then
        Assertions.assertNotNull(product);
    }
}

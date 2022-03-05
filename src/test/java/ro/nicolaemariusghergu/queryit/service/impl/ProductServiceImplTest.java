package ro.nicolaemariusghergu.queryit.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import ro.nicolaemariusghergu.queryit.config.WebClientConfig;
import ro.nicolaemariusghergu.queryit.model.Product;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
record ProductServiceImplTest(WebClientConfig webClientConfig) {

    private static final String PRODUCTS_URL = "/v1/products";

    private static final Long PRODUCT_ID = 1L;

    @Test
    void whenGetDataThenShowResult() {
        // give
        Product product = webClientConfig.getWebClientBuilder()
                .build()
                .get()
                .uri(PRODUCTS_URL + "/" + PRODUCT_ID)
                .retrieve()
                .bodyToMono(Product.class)
                .block();

        // then
        Assertions.assertNotNull(product);
    }
}

/*package ro.nicolaemariusghergu.queryit.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import ro.nicolaemariusghergu.queryit.model.Product;
import ro.nicolaemariusghergu.queryit.repository.ProductRepository;
import ro.nicolaemariusghergu.queryit.service.ProductService;

import java.io.IOException;
import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductServiceImplTest {

    private static final Long ID = 1L;

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void whenGetDataThenShowResult() throws JSONException, IOException {
        // give
        Product product = new Product();
        product.setId(ID);

        Optional<Product> searchedProd = productRepository.findById(ID);

        // when
        Mockito.when(productRepository.findById(ID)).thenReturn(Optional.of(product));

        // then
        Assertions.assertEquals(product, searchedProd.get());
    }

    @TestConfiguration
    class ProductServiceImplTestContextConfiguration {

        @Bean
        public ProductServiceImpl productService() {
            return new ProductServiceImpl(productRepository);
        }
    }
}*/

package ro.nicolaemariusghergu.queryit.service.impl;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import ro.nicolaemariusghergu.queryit.model.Product;
import ro.nicolaemariusghergu.queryit.repository.ProductRepository;

import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductServiceImplTest {

    private static final Long ID = 1L;

    @MockBean
    private ProductRepository productRepository;

    @SneakyThrows
    @Test
    void whenGetDataThenShowResult() {
        // give
        Product product = new Product();
        product.setId(ID);
        product.setManufacturer(null);

        // when
        Mockito.when(productRepository.findById(ID)).thenReturn(Optional.of(product));

        Optional<Product> searchedProd = productRepository.findById(ID);
        searchedProd.get().setManufacturer(null);

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
}

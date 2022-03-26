package ro.nicolaemariusghergu.queryit.service.impl;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ro.nicolaemariusghergu.queryit.model.Product;
import ro.nicolaemariusghergu.queryit.repository.ProductRepository;
import ro.nicolaemariusghergu.queryit.service.ProductService;

import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductServiceImplTest {

    private static final Long ID = 1L;

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository);
    }

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
}

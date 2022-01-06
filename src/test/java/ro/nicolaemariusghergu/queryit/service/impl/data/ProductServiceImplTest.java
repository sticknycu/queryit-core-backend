package ro.nicolaemariusghergu.queryit.service.impl.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import ro.nicolaemariusghergu.queryit.repository.ProductRepository;
import ro.nicolaemariusghergu.queryit.service.data.ProductService;

import java.io.IOException;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void whenGetDataFromWebsiteThenShowResult() throws JSONException, IOException {
        productService.handleDataFromWeb();
    }

    @TestConfiguration
    class CashRegisterServiceImplTestContextConfiguration {

        @Bean
        public ProductServiceImpl productService() {
            return new ProductServiceImpl(productRepository);
        }
    }
}

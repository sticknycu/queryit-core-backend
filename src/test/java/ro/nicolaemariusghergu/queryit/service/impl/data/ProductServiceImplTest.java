package ro.nicolaemariusghergu.queryit.service.impl.data;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import ro.nicolaemariusghergu.queryit.exceptions.ResourceNotFoundException;
import ro.nicolaemariusghergu.queryit.model.CashRegister;
import ro.nicolaemariusghergu.queryit.model.data.Product;
import ro.nicolaemariusghergu.queryit.model.data.TypePay;
import ro.nicolaemariusghergu.queryit.model.data.TypeStatus;
import ro.nicolaemariusghergu.queryit.repository.CashRegisterRepository;
import ro.nicolaemariusghergu.queryit.repository.ProductRepository;
import ro.nicolaemariusghergu.queryit.service.CashRegisterService;
import ro.nicolaemariusghergu.queryit.service.data.ProductService;

import java.io.IOException;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @TestConfiguration
    class CashRegisterServiceImplTestContextConfiguration {

        @Bean
        public ProductServiceImpl productService() {
            return new ProductServiceImpl(productRepository);
        }
    }

    @Test
    void whenGetDataFromWebsiteThenShowResult() throws JSONException, IOException {
        productService.handleDataFromWeb();
    }
}

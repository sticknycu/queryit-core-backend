package ro.nicolaemariusghergu.queryit.service.impl

import lombok.SneakyThrows
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import ro.nicolaemariusghergu.queryit.model.Product
import ro.nicolaemariusghergu.queryit.proxy.ProductProxy
import ro.nicolaemariusghergu.queryit.repository.ProductRepository
import ro.nicolaemariusghergu.queryit.service.ProductService
import java.util.*

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ProductServiceImplTest {
    private var productService: ProductService? = null

    @Mock
    private val productRepository: ProductRepository? = null

    @Mock
    private val productProxy: ProductProxy? = null
    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        productService = ProductServiceImpl(productRepository, productProxy)
    }

    @SneakyThrows
    @Test
    fun whenGetDataThenShowResult() {
        // give
        val product = Product()
        product.id = ID
        product.manufacturer = null

        // when
        Mockito.`when`(productRepository.findById(ID)).thenReturn(Optional.of(product))
        val searchedProd = productRepository.findById(ID)
        searchedProd.get().manufacturer = null

        // then
        Assertions.assertEquals(product, searchedProd.get())
    }

    companion object {
        private val ID: Long? = 1L
    }
}
package com.exyfi.reactive.shop.service;

import com.exyfi.reactive.shop.dao.ProductRepository;
import com.exyfi.reactive.shop.exception.ProductNotFoundException;
import com.exyfi.reactive.shop.model.BaseResponse;
import com.exyfi.reactive.shop.model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl service;

    PodamFactory podamFactory;

    @BeforeEach
    void setup() {
        podamFactory = new PodamFactoryImpl();
    }

    @Test
    void insertProduct() {
        Product expected = podamFactory.manufacturePojo(Product.class);
        when(productRepository.insert(expected)).thenReturn(Mono.just(expected));

        Mono<Product> actual = service.insertProduct(expected);

        assertNotNull(actual);
        assertEquals(expected, actual.block());

        verify(productRepository, times(1)).insert(expected);
    }

    @Test
    void getProductByIdError() {
        long id = 1L;
        when(productRepository.findById(id)).thenReturn(Mono.empty());

        Mono<Product> userById = service.getProductById(id);

        assertThrows(ProductNotFoundException.class, () -> userById.block());
    }


    @Test
    void getProductByIdSuccess() {
        Product expected = podamFactory.manufacturePojo(Product.class);
        when(productRepository.findById(expected.getId())).thenReturn(Mono.just(expected));

        Mono<Product> actual = service.getProductById(expected.getId());


        assertNotNull(actual);
        assertEquals(expected, actual.block());
        verify(productRepository, times(1)).findById(expected.getId());
    }

    @Test
    void deleteProduct() {
        long id = 1L;
        when(productRepository.deleteById(id)).thenReturn(Mono.empty());

        Mono<BaseResponse> actual = service.deleteProduct(id);

        assertNotNull(actual);
        assertTrue(actual.block().isSuccess());
    }
}
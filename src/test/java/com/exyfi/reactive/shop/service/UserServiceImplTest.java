package com.exyfi.reactive.shop.service;

import com.exyfi.reactive.shop.converter.CurrencyConverter;
import com.exyfi.reactive.shop.dao.ProductRepository;
import com.exyfi.reactive.shop.dao.UserRepository;
import com.exyfi.reactive.shop.exception.UserNotFoundException;
import com.exyfi.reactive.shop.model.BaseResponse;
import com.exyfi.reactive.shop.model.Currency;
import com.exyfi.reactive.shop.model.product.Product;
import com.exyfi.reactive.shop.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;
    @Mock
    ProductService productService;
    @Mock
    CurrencyConverter currencyConverter;
    @InjectMocks
    UserServiceImpl service;

    PodamFactory podamFactory;

    @BeforeEach
    void setup() {
        podamFactory = new PodamFactoryImpl();
    }

    @Test
    void registerUserSuccess() {
        User expected = podamFactory.manufacturePojo(User.class);
        when(userRepository.insert(expected)).thenReturn(Mono.just(expected));

        Mono<User> actual = service.registerUser(expected);

        assertNotNull(actual);
        assertEquals(expected, actual.block());
        verify(userRepository, times(1)).insert(expected);
    }

    @Test
    void getUsers() {
        User expected = podamFactory.manufacturePojo(User.class);
        when(userRepository.findAll()).thenReturn(Flux.just(expected));

        Flux<User> actual = service.getUsers();

        assertNotNull(actual);
        assertEquals(expected, actual.blockFirst());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void deleteUsers() {
        when(userRepository.deleteAll()).thenReturn(Mono.empty().then());

        Mono<BaseResponse> actual = service.deleteUsers();

        assertNotNull(actual);
        assertNotNull(actual.block());
        assertTrue(actual.block().isSuccess());
    }

    @Test
    void getUserByIdReturnException() {
        long id = 1L;
        when(userRepository.findById(id)).thenReturn(Mono.empty());

        Mono<User> userById = service.getUserById(id);

        assertThrows(UserNotFoundException.class, () -> userById.block());
    }

    @Test
    void getUserByIdSuccess() {
        User expected = podamFactory.manufacturePojo(User.class);
        when(userRepository.findById(expected.getId())).thenReturn(Mono.just(expected));

        Mono<User> actual = service.getUserById(expected.getId());


        assertNotNull(actual);
        assertEquals(expected, actual.block());
        verify(userRepository, times(1)).findById(expected.getId());
    }

    @Test
    void switchCurrencyForUser() {
        User expected = podamFactory.manufacturePojo(User.class);
        expected.setPreferredCurrency(Currency.EUR);
        Currency expectedCurrency = Currency.USD;
        when(userRepository.findById(expected.getId())).thenReturn(Mono.just(expected));
        expected.setPreferredCurrency(expectedCurrency);
        when(userRepository.save(any())).thenReturn(Mono.just(expected));

        Mono<User> actual = service.switchCurrencyForUser(expected.getId(), Currency.USD);

        assertNotNull(actual.block());
        assertEquals(expected, actual.block());
    }

    @Test
    void getProductsForUser() {
        User expected = podamFactory.manufacturePojo(User.class);
        Product expectedProduct = podamFactory.manufacturePojo(Product.class);
        when(userRepository.findById(expected.getId())).thenReturn(Mono.just(expected));
        when(productService.getAllProducts()).thenReturn(Flux.just(expectedProduct));
        when(currencyConverter.convert(any(), any())).thenReturn(
                Product.builder()
                        .id(expectedProduct.getId())
                        .currency(expected.getPreferredCurrency())
                        .name(expectedProduct.getName())
                        .price(100)
                        .build());

        Flux<Product> actual = service.getProductsForUser(expected.getId());

        assertNotNull(actual);
        assertNotNull(actual.blockFirst());
        assertEquals(actual.blockFirst().getCurrency(), expected.getPreferredCurrency());
    }
}
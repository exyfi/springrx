package com.exyfi.reactive.shop.converter;

import com.exyfi.reactive.shop.model.Currency;
import com.exyfi.reactive.shop.model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyConverterTest {

    PodamFactory podamFactory;

    @Mock
    ConverterProviderIban provider;
    @InjectMocks
    CurrencyConverter converter;

    @BeforeEach
    void setup() {
        podamFactory = new PodamFactoryImpl();
    }

    @Test
    void convert() {
        Product product = podamFactory.manufacturePojo(Product.class);
        Currency to = Currency.RUB;
        when(provider.getConvertedPrice(product.getCurrency(), to, product.getPrice())).thenReturn(10d);

        Product convert = converter.convert(to, product);

        assertNotNull(convert);
        assertEquals(convert.getCurrency(), to);
    }
}
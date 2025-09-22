package com.omsai.productservice.service;

import com.omsai.productservice.dto.ProductRequest;
import com.omsai.productservice.dto.ProductResponse;
import com.omsai.productservice.model.Product;
import com.omsai.productservice.repository.ProductRepository;
import com.omsai.productservice.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProduct() {
        ProductRequest request = new ProductRequest();
        Product product = new Product();
        when(productMapper.toEntity(request)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        productService.save(request);

        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testGetProductByName() {
        String name = "TestProduct";
        Product product = new Product();
        ProductResponse response = new ProductResponse();
        when(productRepository.findByName(name)).thenReturn(Optional.of(product));
        when(productMapper.toResponse(product)).thenReturn(response);

        ProductResponse result = productService.getProductByName(name);

        assertEquals(response, result);
    }

    @Test
    void testGetAllProducts() {
        List<Product> productList = Arrays.asList(new Product(), new Product());
        List<ProductResponse> responseList = Arrays.asList(new ProductResponse(), new ProductResponse());
        when(productRepository.findAll()).thenReturn(productList);
        when(productMapper.toResponse(any(Product.class))).thenReturn(responseList.get(0), responseList.get(1));

        List<ProductResponse> result = productService.getAllProducts();

        assertEquals(2, result.size());
    }
}
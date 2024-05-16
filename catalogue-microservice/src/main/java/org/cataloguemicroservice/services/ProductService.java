package org.cataloguemicroservice.services;

import lombok.AllArgsConstructor;
import org.cataloguemicroservice.dtos.PageRequestDTO;
import org.cataloguemicroservice.dtos.ProductDTO;
import org.cataloguemicroservice.entities.Category;
import org.cataloguemicroservice.entities.Product;
import org.cataloguemicroservice.enums.CustomerMessageError;
import org.cataloguemicroservice.exceptions.*;
import org.cataloguemicroservice.mappers.ProductMapper;
import org.cataloguemicroservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public Product save(Product product) {
        product.setSlug(this.slugify(product.getLabel()));
        product.setCreatedDate(new Date());
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Product product =productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        CustomerMessageError.PRODUCT_NOT_FOUND_WITH_ID_EQUALS.getMessage() + id));
        productRepository.delete(product);
    }


    @Override
    public void add(Product product) {
        if(product.getProductId() == null)
            throw new ProductEmptyException(CustomerMessageError.PRODUCT_INPUT_IS_EMPTY.getMessage());
        if( productRepository.existsByProductId(product.getProductId()))
            throw new ProductAlreadyExistException(CustomerMessageError.PRODUCT_ALREADY_EXISTS_WITH_ID_EQUALS.getMessage()+product.getProductId());
        this.save(product);
    }

    @Override
    public void update(Long id, Product p) {
        Product product =productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        CustomerMessageError.PRODUCT_NOT_FOUND_WITH_ID_EQUALS.getMessage() + id));
        product.setProductId(p.getProductId());
        product.setProductTitle(p.getProductTitle());
        product.setImageUrl(p.getImageUrl());
        product.setIdCategory(p.getIdCategory());
        product.setDescription(p.getDescription());
        product.setValid(p.isValid());
        product.setLabel(p.getLabel());
        product.setPrice(p.getPrice());
        this.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                ()->new CategoryNotFoundException(CustomerMessageError.PRODUCT_NOT_FOUND_WITH_ID_EQUALS.getMessage()+id)
        );
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public List<Product> getProductById(Long id) {
        List<Product> products = productRepository.findProductsByIdCategory(id);
        if (products.isEmpty()) {
            throw new ProductNotFoundException(CustomerMessageError.PRODUCT_NOT_FOUND.getMessage());
        }
        return products;
    }

    @Override
    public Product getProductBySlug(String slug) {
        return productRepository.findBySlug(slug)
                .orElseThrow(() -> new
                        CategoryNotFoundException(
                                CustomerMessageError.CATEGORY_NOT_FOUND_SLUG_EQUALS.getMessage()+slug));
    }

    @Override
    public Product getProductByLabel(String label) {
        return productRepository.findByLabel(label)
                .orElseThrow(() -> new
                        CategoryNotFoundException(
                                CustomerMessageError.CATEGORY_NOT_FOUND_WITH_LABEL_EQUALS.getMessage()+label));
    }

    @Override
    public Page<Product> getProductPagination(Integer pageNumber, Integer pageSize, String sort) {
        Pageable pageable = null;
        if (sort != null) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        return productRepository.findAll(pageable);
    }

    @Override
    public PageRequestDTO<Product> getCategoryPagination(Integer pageNumber, Integer pageSize, String sort) {
        Pageable pageable;
        if (sort != null) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        Page<Product> productPage = productRepository.findAll(pageable);
        return new PageRequestDTO<>(
                productPage.getContent(),
                productPage.getNumber(),
                productPage.getTotalPages(),
                (int) productPage.getTotalElements()
        );
    }
}

package com.sid.catalogservice.Service;

import com.nomadnest.clients.User.User;
import com.nomadnest.clients.User.UserServiceClient;
import com.sid.catalogservice.Dtos.ProductRequestDto;
import com.sid.catalogservice.Dtos.ProductResponseDto;
import com.sid.catalogservice.Entity.Product;
import com.sid.catalogservice.Exception.NotFoundException;
import com.sid.catalogservice.Exception.UnauthorizedException;
import com.sid.catalogservice.Utility.ValidationEntity;
import com.sid.catalogservice.Repository.ProductRepository;
import com.sid.catalogservice.Utility.QueryParams;
import com.sid.catalogservice.mappers.MappingProfiles;
import com.sid.catalogservice.Entity.SubCategory;
import com.sid.catalogservice.Repository.SubCategoryRepository;

import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final UserServiceClient userServiceClient;


    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws ValidationException,UnauthorizedException {
        //User user=userServiceClient.getUserById(productRequestDto.getUser_id());
       // System.out.println(user.getUsername()+" "+user.getRole());
        //if(!(Objects.equals(user.getRole(), "ADMIN")))
           // throw new UnauthorizedException("You are not authorized to add product");
       List<ErrorMessage>validationErrors= ValidationEntity.validateProduct(productRequestDto);
       if (!validationErrors.isEmpty()){
           throw new ValidationException(String.valueOf(validationErrors));
       }
        SubCategory subCategory = subCategoryRepository.findById(productRequestDto.getSubCategory())
                .orElseThrow(() -> new NotFoundException("SubCategory not found"));
        Product product = MappingProfiles.mapToEntity(productRequestDto);
        product.setSubCategory(subCategory);
        return MappingProfiles.mapToDto(productRepository.save(product));
    }

    @Override
    public ProductResponseDto getProductById(Long id) throws NotFoundException {
        Product product=productRepository.findById(id).orElseThrow( () -> new  NotFoundException("Product not found"));
        return MappingProfiles.mapToDto(product);
    }

    @Override
    public Page<ProductResponseDto> getAllProducts(int pageNumber, int pageSize, String field, String order) {

        PageRequest pageRequest  = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by(order.equalsIgnoreCase("desc")?
                                Sort.Direction.DESC:
                                Sort.Direction.ASC,
                        field)
        );

        return productRepository.findAll(pageRequest).map(MappingProfiles::mapToDto);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto)   {
        Product product=productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        SubCategory subCategory = subCategoryRepository.findById(productRequestDto.getSubCategory())
                .orElseThrow(() -> new NotFoundException("SubCategory not found"));
        product.setProductName(productRequestDto.getProductName());
        product.setImagePath(productRequestDto.getImagePath());
        product.setLongDescription(productRequestDto.getLongDescription());
        product.setShortDescription(productRequestDto.getShortDescription());
        product.setInStock(productRequestDto.getInStock());
        product.setOriginalPrice(productRequestDto.getOriginalPrice());
        product.setSubCategory(subCategory);
        return MappingProfiles.mapToDto(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) throws  NotFoundException {
        Product product=productRepository.findById(id).orElseThrow(() -> new  NotFoundException("Product not found"));
        productRepository.delete(product);

    }




  @Override
    public List<ProductResponseDto> findProductBySubCategory(SubCategory subcategory_id) throws  NotFoundException {
        List<Product> productList=  productRepository.findProductBySubCategory(subcategory_id).orElseThrow(() -> new NotFoundException("Product not found"));
     return productList.stream().map(MappingProfiles::mapToDto).collect(Collectors.toList());
    }

   @Override
   public List<ProductResponseDto> findProductByProductName(String name) throws  NotFoundException {
       List<Product> productList = productRepository.findProductByProductName(name)
               .orElseThrow(() -> new NotFoundException("Product not found"));
       return productList.stream()
               .map(MappingProfiles::mapToDto)
               .collect(Collectors.toList());
   }


    @Override
   public List< ProductResponseDto> findProductByShortDescription(String shortDescription) throws NotFoundException {
      List<Product>productList=productRepository.findProductByShortDescription(shortDescription)
            .orElseThrow(() -> new NotFoundException("Product not found"));
      return productList.stream()
             .map(MappingProfiles::mapToDto)
              .collect(Collectors.toList());

    }
    @Override
    public Integer getProductsCount()  {
        return Math.toIntExact(this.productRepository.count());
    }


}



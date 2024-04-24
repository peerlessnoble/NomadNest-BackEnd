package com.sid.msproduct.Exception;

import com.sid.msproduct.Dtos.CategoryRequestDTO;
import com.sid.msproduct.Dtos.ProductRequestDto;
import com.sid.msproduct.Dtos.SubCategoryRequestDTO;
import org.modelmapper.spi.ErrorMessage;

import java.util.ArrayList;
import java.util.List;


public class ValidationEntity {


    public static List<ErrorMessage> validateCategory(CategoryRequestDTO categoryRequestDTO) throws EmptyValueException, AlreadyExistException {
        List<ErrorMessage> errorMessageList = new ArrayList<>();

        if (categoryRequestDTO.getDescription().isEmpty()) {
            errorMessageList.add(new ErrorMessage("Category description cannot be empty."));
        }
        if (categoryRequestDTO.getImagePath().isEmpty()) {
            errorMessageList.add(new ErrorMessage("Category image path cannot be empty."));
        }
        return errorMessageList;
    }

    public static List<ErrorMessage> validateSubcategory(SubCategoryRequestDTO subCategoryRequestDTO) throws EmptyValueException {
        List<ErrorMessage> errors = new ArrayList<>();
        if (subCategoryRequestDTO.getSubCategoryName().isEmpty()) {
            errors.add(new ErrorMessage("Subcategory name cannot be empty."));
        }
        if (subCategoryRequestDTO.getDescription().isEmpty()) {
            errors.add(new ErrorMessage("Subcategory description cannot be empty."));
        }
        if (subCategoryRequestDTO.getImagePath().isEmpty()) {
            errors.add(new ErrorMessage("Subcategory image path cannot be empty."));
        }


        return errors;
    }

    public static List<ErrorMessage> validateProduct(ProductRequestDto productRequestDto) throws EmptyValueException {
        List<ErrorMessage> errors = new ArrayList<>();

        if (productRequestDto.getProductName().isEmpty()) {
            errors.add(new ErrorMessage("Product name cannot be empty"));
        }
        if (productRequestDto.getLongDescription().isEmpty()) {
            errors.add(new ErrorMessage("Long description cannot be empty"));
        }
        if (productRequestDto.getShortDescription().isEmpty()) {
            errors.add(new ErrorMessage("Short description cannot be empty"));
        }
        if (productRequestDto.getOriginalPrice() == null) {
            errors.add(new ErrorMessage("Original price cannot be null"));
        }
        if (productRequestDto.getInStock() == null) {
            errors.add(new ErrorMessage("Stock availability cannot be null"));
        }
        if (productRequestDto.getImagePath().isEmpty()) {
            errors.add(new ErrorMessage("Image path cannot be empty"));
        }
        return errors;
    }
}


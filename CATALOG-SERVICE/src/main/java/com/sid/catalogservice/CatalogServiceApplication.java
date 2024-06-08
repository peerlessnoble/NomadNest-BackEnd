package com.sid.catalogservice;

import com.sid.catalogservice.Repository.SubCategoryRepository;
import com.sid.catalogservice.Entity.Category;
import com.sid.catalogservice.Entity.Product;
import com.sid.catalogservice.Entity.SubCategory;
import com.sid.catalogservice.Repository.CategoryRepository;
import com.sid.catalogservice.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@AllArgsConstructor
@EnableFeignClients(basePackages = "com.nomadnest.clients")
public class CatalogServiceApplication implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;



    public static void main(String[] args) {

        SpringApplication.run(CatalogServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        List<Category> categories = Arrays.asList(
                Category.builder()
                        .categoryName("Travel Packs & Bags")
                        .description("Travel packs and bags for all your adventures.")
                        .imagePath("https://cdn11.bigcommerce.com/s-45mun2obzo/product_images/uploaded_images/h-cat-img3.jpg")
                        .createdAt(new Date())
                        .build(),

                Category.builder()
                        .categoryName("Climbing")
                        .description("Climbing gear for all your climbing needs.")
                        .imagePath("https://cdn11.bigcommerce.com/s-45mun2obzo/product_images/uploaded_images/h-cat-img2.jpg")
                        .createdAt(new Date())
                        .build(),

                Category.builder()
                        .categoryName("Electronics")
                        .description("Electronics for all your outdoor adventures.")
                        .imagePath("https://cdn11.bigcommerce.com/s-45mun2obzo/images/stencil/1280w/carousel/996/2024-06-clearance-50.jpg?c=1")
                        .createdAt(new Date())
                        .build()
        );

        categories.get(0).addSubCategory(
                SubCategory.builder()
                        .subCategoryName("Large Travel Packs")
                        .description("Large travel packs for long trips.")
                        .imagePath("https://cdn11.bigcommerce.com/s-45mun2obzo/product_images/uploaded_images/h-cat-img3.jpg")
                        .createdAt(new Date())
                        .build()
        );

        categories.get(0).addSubCategory(
                SubCategory.builder()
                        .subCategoryName("Travel Daypacks")
                        .description("Travel daypacks for short trips.")
                        .imagePath("https://cdn11.bigcommerce.com/s-45mun2obzo/product_images/uploaded_images/h-cat-img3.jpg")
                        .createdAt(new Date())
                        .build()
        );

        categories.get(1).addSubCategory(
                SubCategory.builder()
                        .subCategoryName("Ropes & Cordage")
                        .description("Ropes and cordage for climbing.")
                        .imagePath("https://cdn11.bigcommerce.com/s-45mun2obzo/product_images/uploaded_images/h-cat-img2.jpg")
                        .createdAt(new Date())
                        .build()
        );

        categories.get(1).addSubCategory(
                SubCategory.builder()
                        .subCategoryName("Protection & Hardware")
                        .description("Protection and hardware for climbing.")
                        .imagePath("/images/sacs_couchage_camping.jpg")
                        .createdAt(new Date())
                        .build()
        );

        categories.get(2).addSubCategory(
                SubCategory.builder()
                        .subCategoryName("Solar & Portable Power")
                        .description("Useful accessories to make traveling easier.")
                        .imagePath("https://cdn11.bigcommerce.com/s-45mun2obzo/images/stencil/1280w/carousel/996/2024-06-clearance-50.jpg?c=1")
                        .createdAt(new Date())
                        .build()
        );

        categories.get(2).addSubCategory(
                SubCategory.builder()
                        .subCategoryName("Protective Cases")
                        .description("Protective cases for your electronics.")
                        .imagePath("https://cdn11.bigcommerce.com/s-45mun2obzo/images/stencil/1280w/carousel/996/2024-06-clearance-50.jpg?c=1")
                        .createdAt(new Date())
                        .build()
        );

        categoryRepository.saveAll(categories);
        // Retrieve the SubCategory from the database
        Optional<SubCategory> SubCategory1 = subCategoryRepository.findById(1L);
        Optional<SubCategory> SubCategory2 = subCategoryRepository.findById(2L);
        Optional<SubCategory> SubCategory3 = subCategoryRepository.findById(3L);
        Optional<SubCategory> SubCategory4 = subCategoryRepository.findById(4L);
        Optional<SubCategory> SubCategory5 = subCategoryRepository.findById(5L);
        Optional<SubCategory> SubCategory6 = subCategoryRepository.findById(6L);



        List<Product> products = Arrays.asList(
                Product.builder()
                        .productName("Global Travel Bag 30L")
                        .shortDescription("Compact enough to pass as a carry-on.")
                        .longDescription("Built for breezing through the concourse .")
                        .originalPrice(155.22)
                        .imagePath("https://cdn11.bigcommerce.com/s-45mun2obzo/images/stencil/500x659/products/17613/111929/F21-Global-Travel-Bag-30L-Product-8_rsz__18907.1710962829.jpg?c=1")
                        .inStock(7)
                        .createdAt(new Date())
                        .user_id(1L)
                        .subCategory(SubCategory1.get())
                        .build(),

                Product.builder()
                        .productName("Sojourn Porter Travel Pack 65L")
                        .shortDescription("Ideal for longer excursions and adventures of all kinds.")
                        .longDescription("Ideal for longer excursions and adventures of all kinds, the Osprey Sojourn Porter 65L travel pack offers increased storage capacity and convenient organization for on-the-go access to your belongings.")
                        .originalPrice(225.00)
                        .imagePath("https://cdn11.bigcommerce.com/s-45mun2obzo/images/stencil/original/products/16311/95429/sojournportertrvlpk65_side_koseretgreen_RSZ__67258.1691433146.jpg?c=1")
                        .inStock(10)
                        .user_id(1L)
                        .subCategory(SubCategory1.get())
                        .createdAt(new Date())
                        .build(),

                Product.builder()
                        .productName("Ultralight Stuff Pack")
                        .shortDescription("Simple, ultralight and packable 18-liter day pack.")
                        .longDescription("Expand your luggage for day trips at your destination, or have an extra bag for souvenirs on the return journey, with this simple, ultralight and packable 18-liter day pack that provides capacity on demand.")
                        .originalPrice(45.00)
                        .imagePath("https://cdn11.bigcommerce.com/s-45mun2obzo/images/stencil/original/products/15311/88814/ulstuffpack20l_s23_side_limongreen_rsz__30928.1680285393.jpg?c=1")
                        .inStock(5)
                        .subCategory(SubCategory2.get())
                        .createdAt(new Date())
                        .build(),

                Product.builder()
                        .productName("Pintail 10.0mm Rope")
                        .shortDescription("High abrasion resistance without any handling restrictions.")
                        .longDescription("The polyester sheath and construction of the Edelrid Pintail 10.0mm ensure high abrasion resistance without any handling restrictions, as the rope remains supple even after intensive use and frequent rappelling maneuvers. Due to the slightly larger diameter and the easy-to-grip sheath structure.")
                        .originalPrice(104.96)
                        .imagePath("https://cdn11.bigcommerce.com/s-45mun2obzo/images/stencil/original/products/16145/93135/71349_047a-pintail-10.omm_RSZ__10137.1689795685.jpg?c=1")
                        .inStock(5)
                        .subCategory(SubCategory3.get())
                        .createdAt(new Date())
                        .build(),

                Product.builder()
                        .productName("Pinch Belay Device")
                        .shortDescription("Experience a new belaying experience with the Edelrid PINCH.")
                        .longDescription("Experience a new belaying experience with the Edelrid PINCH, a versatile assisted-braking belay device for use in sport climbing, multi-pitch climbing and rope access techniques. The Pinch is the first device on the market that can be attached directly to the central ring of the climbing harness.")
                        .originalPrice(119.95)
                        .imagePath("https://cdn11.bigcommerce.com/s-45mun2obzo/images/stencil/original/products/16876/106873/73838_815a_rsz__69301.1707780182.jpg?c=1")
                        .inStock(3)
                        .subCategory(SubCategory4.get())
                        .createdAt(new Date())
                        .build(),

                Product.builder()
                        .productName("SolarPanel 10+")
                        .shortDescription("Ensemble complet d'articles essentiels pour la survie en plein air.")
                        .longDescription("Get smart power from the sun with the portable BioLite SolarPanel 10+. Power tablets, phones, and other gear with 10 watts of usable electricity. .")
                        .originalPrice(119.96)
                        .imagePath("https://cdn11.bigcommerce.com/s-45mun2obzo/images/stencil/original/products/5588/26721/solarpanel10__1_1024x1024__20360.1626822982.jpg?c=1")
                        .inStock(6)
                        .subCategory(SubCategory5.get())
                        .createdAt(new Date())
                        .build(),



                Product.builder()
                        .productName("TPU Waterproof Guide Map Case")
                        .shortDescription("Resistant to UV and extreme cold.")
                        .longDescription("The TPU Waterproof Guide Map Case from Sea To Summit features TPU (thermoplastic polyurethane) with welded construction and a super-strong Ziploc® closure. It's resistant to UV and extreme cold. Designed with a detachable neck strap and corner anchor points for versatility.")
                        .originalPrice(29.71)
                        .imagePath("https://cdn11.bigcommerce.com/s-45mun2obzo/images/stencil/original/products/10891/4629/384-386-tpu_guidemapcase__08839.1626821973.jpg?c=1")
                        .inStock(4)
                        .subCategory(SubCategory6.get())
                        .createdAt(new Date())
                        .build()
        );

        productRepository.saveAll(products);
    }
}



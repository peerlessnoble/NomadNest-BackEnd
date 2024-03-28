package com.sid.msproduct;

import com.sid.msproduct.Dtos.CategoryRequestDTO;
import com.sid.msproduct.Dtos.ProductRequestDto;
import com.sid.msproduct.Dtos.SubCategoryRequestDTO;
import com.sid.msproduct.Entity.Category;
import com.sid.msproduct.Entity.Product;
import com.sid.msproduct.Entity.SubCategory;
import com.sid.msproduct.Repository.CategoryRepository;
import com.sid.msproduct.Repository.ProductRepository;
import com.sid.msproduct.Repository.SubCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class MsproductApplication implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;


    public static void main(String[] args) {

        SpringApplication.run(MsproductApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<Category> categories = Arrays.asList(
                Category.builder()
                        .categoryName("Randonnée")
                        .description("Équipements pour la randonnée en plein air.")
                        .imagePath("/images/randonnee.jpg")
                        .createdAt(new Date())
                        .build(),

                Category.builder()
                        .categoryName("Camping")
                        .description("Matériel essentiel pour le camping en pleine nature.")
                        .imagePath("/images/camping.jpg")
                        .createdAt(new Date())
                        .build(),

                Category.builder()
                        .categoryName("Voyages")
                        .description("Articles pratiques pour les voyages.")
                        .imagePath("/images/voyages.jpg")
                        .createdAt(new Date())
                        .build()


        );
        categoryRepository.saveAll(categories);



        //----------------------------------------------------------------------------------------------------------
        // Randonnée - Chaussures de randonnée
        List<SubCategory> subCategories=Arrays.asList(
        SubCategory.builder()
                .subCategoryName("Chaussures de randonnée")
                .description("Chaussures conçues pour la randonnée en plein air.")
                .imagePath("/images/chaussures_randonnee.jpg")
                .createdAt(new Date())
                .build(),

        // Randonnée - Vêtements de randonnée
         SubCategory.builder()
                .subCategoryName("Vêtements de randonnée")
                .description("Vêtements adaptés aux conditions de la randonnée en plein air.")
                .imagePath("/images/vetements_randonnee.jpg")
                .createdAt(new Date())
                .build(),

        // Camping - Tentes
        SubCategory.builder()
                .subCategoryName("Tentes")
                .description("Différents types de tentes pour le camping en plein air.")
                .imagePath("/images/tentes_camping.jpg")
                .createdAt(new Date())
                .build(),

        // Camping - Sacs de couchage
       SubCategory.builder()
                .subCategoryName("Sacs de couchage")
                .description("Sacs de couchage pour dormir confortablement en camping.")
                .imagePath("/images/sacs_couchage_camping.jpg")
                .createdAt(new Date())
                .build(),

        // Voyages - Bagages
       SubCategory.builder()
                .subCategoryName("Bagages")
                .description("Différents types de bagages pour les voyages.")
                .imagePath("/images/bagages_voyages.jpg")
                .createdAt(new Date())
                .build(),

        // Voyages - Accessoires de voyage
        SubCategory.builder()
                .subCategoryName("Accessoires de voyage")
                .description("Accessoires utiles pour faciliter les voyages.")
                .imagePath("/images/accessoires_voyages.jpg")
                .createdAt(new Date())
                .build()
        );
        subCategoryRepository.saveAll(subCategories);


       // ----------------------------------------------------------------------------------------------------------------
        // Randonnée - Chaussures de randonnée
        List<Product>products=Arrays.asList(
        Product.builder()
                .productName("Chaussures de randonnée")
                .shortDescription("Chaussures robustes pour une randonnée confortable.")
                .longDescription("Ces chaussures sont conçues pour offrir un soutien optimal lors de vos randonnées en montagne.")
                .originalPrice(89.99)
                .imagePath("/images/chaussures_randonnee.jpg")
                .inStock(50)
                .createdAt(new Date())
                .build(),

        // Camping - Tentes
        Product.builder()
                .productName("Tente familiale")
                .shortDescription("Tente spacieuse pour toute la famille.")
                .longDescription("Cette tente offre suffisamment d'espace pour accueillir une famille lors de vos escapades en camping.")
                .originalPrice(199.99)
                .imagePath("/images/tente_familiale.jpg")
                .inStock(20)
                .createdAt(new Date())
                .build(),

        // Voyages - Bagages
        Product.builder()
                .productName("Valise à roulettes")
                .shortDescription("Valise robuste pour voyager en toute simplicité.")
                .longDescription("Cette valise à roulettes offre une grande capacité de stockage et une durabilité exceptionnelle.")
                .originalPrice(149.99)
                .imagePath("/images/valise_roulettes.jpg")
                .inStock(10)
                .createdAt(new Date())
                .build(),

        // Survie et sécurité - Kits de survie
         Product.builder()
                .productName("Kit de survie complet")
                .shortDescription("Ensemble complet d'articles essentiels pour la survie en plein air.")
                .longDescription("Ce kit comprend une variété d'outils et de fournitures pour vous aider dans les situations d'urgence en plein air.")
                .originalPrice(129.99)
                .imagePath("/images/kit_survie_complet.jpg")
                .inStock(15)
                .createdAt(new Date())
                .build()
        );
        productRepository.saveAll(products);


    }
}



package org.cataloguemicroservice;

import lombok.AllArgsConstructor;
import org.cataloguemicroservice.entities.Category;
import org.cataloguemicroservice.entities.Product;
import org.cataloguemicroservice.repositories.ProductRepository;
import org.cataloguemicroservice.services.ICategoryService;
import org.cataloguemicroservice.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@AllArgsConstructor
public class CatalogueMicroserviceApplication {
    @Qualifier("categoryService")
    private final ICategoryService iCategoryService;
    private final IProductService iProductService;
    private final ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(CatalogueMicroserviceApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Category category = Category.builder()
                    .categoryId(1L)
                    .idParent(0L)
                    .label("MATÉRIEL DE MUSCULATION")
                    .categoryTitle("MATÉRIEL DE MUSCULATION")
                    .imageUrl("https://sport-equipement.shop/product-category/muscu/")
                    .valid(true)
                    .build();
            iCategoryService.save(category);

            Category category1 = Category.builder()
                    .categoryId(3L)
                    .idParent(1L)
                    .label("EQUIPEMENTS")
                    .categoryTitle("EQUIPEMENTS")
                    .imageUrl("https://sport-equipement.shop/product-category/muscu/equipements/")
                    .valid(true)
                    .build();
            iCategoryService.save(category1);

            Category category2 = Category.builder()
                    .categoryId(4L)
                    .idParent(1L)
                    .label("ACCESSOIRES DE MUSCULATION")
                    .categoryTitle("ACCESSOIRES DE MUSCULATION")
                    .imageUrl("https://sport-equipement.shop/product-category/muscu/freeweight/")
                    .valid(true)
                    .build();
            iCategoryService.save(category2);

            Category category4 = Category.builder()
                    .categoryId(5L)
                    .idParent(1L)
                    .label("ACCESSOIRES")
                    .categoryTitle("ACCESSOIRES")
                    .imageUrl("https://sport-equipement.shop/product-category/muscu/accessoires/")
                    .valid(true)
                    .build();
            iCategoryService.save(category4);

            Product product1 = Product.builder()
                    .productId(1L)
                    .label("PLYO SOFT BOX")
                    .productTitle("PLYO SOFT BOX")
                    .imageUrl("https://sport-equipement.shop/product-category/muscu/equipements/")
                    .idCategory(3L)
                    .valid(true)
                    .description("Le Set plyobox mousse permet des entraînements pour tous les niveaux et toutes les puissances. Pour augmenter les difficultés des exercices, il suffit d'empiler des plyobox supplémentaires et les connecter entre elles avec leurs bandes velcro.")
                    .price((long) 811.19)
                    .build();
            Product product2 = Product.builder()
                    .productId(2L)
                    .label("PLYO BOX")
                    .productTitle("PLYO BOX")
                    .imageUrl("https://sport-equipement.shop/product/plyo-box/")
                    .idCategory(3L)
                    .valid(true)
                    .description("Box en bois qui permet d'effectuer un travail de pliométrie pendant vos séances de cross training. Un usage régulier vous permettra d'améliorer facilement votre condition physique.")
                    .price((long) 147.59)
                    .build();
            Product product3 = Product.builder()
                    .productId(3L)
                    .label("ADJUSTABLE BENCH")
                    .productTitle("ADJUSTABLE BENCH")
                    .imageUrl("https://sport-equipement.shop/product/ajustable-bench/")
                    .idCategory(3L)
                    .valid(true)
                    .description("Notre Adjustable Bench est équipé d’un dossier matelassé réglable en 10 positions (de 0 à 85 degrés) et d’un siège réglable en 3 positions (0, 15 et 30 degrés).\n\nIl existe un très petit espace entre le coussin du siège et celui du dossier, qui permet aux athlètes de réaliser des exercices de mouvements fonctionnels ainsi que de développé  couché, en positions neutre ou inclinée, sans devoir faire face aux gènes causées par un banc disjoint classique.\n\nLe modèle standard de ce banc est livré avec des pieds en caoutchouc de qualité.")
                    .price((long) 781.19)
                    .build();
            Product product4 = Product.builder()
                    .productId(4L)
                    .label("TRAP BAR")
                    .productTitle("TRAP BAR")
                    .imageUrl("https://sport-equipement.shop/product/trap-bar/")
                    .idCategory(3L)
                    .valid(true)
                    .description("La Trap Bar est le plus souvent utilisée pour les shrugs et le soulevé de terre (deadlift) afin de réduire le stress sur les lombaires.")
                    .price((long) 297.59)
                    .build();
            Product product5 = Product.builder()
                    .productId(5L)
                    .label("BARRE COMPÉTITION HOMME")
                    .productTitle("BARRE COMPÉTITION HOMME")
                    .imageUrl("https://sport-equipement.shop/product/bar-competition-homme/")
                    .idCategory(3L)
                    .valid(true)
                    .description("La barre se caractérise par une adaptation aux tolérances de poids très strictes et un moletage prononcé adapté aux tentatives de type 1 répétition max.")
                    .price((long) 319.19)
                    .build();
            Product product6 = Product.builder()
                    .productId(6L)
                    .label("BARRE COMPÉTITION FEMME")
                    .productTitle("BARRE COMPÉTITION FEMME")
                    .imageUrl("https://sport-equipement.shop/product/barre-competition-femme/")
                    .idCategory(3L)
                    .valid(true)
                    .description("La barre se caractérise par une adaptation aux tolérances de poids très strictes et un moletage prononcé adapté aux tentatives de type 1 répétition max.")
                    .price((long) 259.19)
                    .build();
            Product product7 = Product.builder()
                    .productId(7L)
                    .label("BARRE JUNIOR")
                    .productTitle("BARRE JUNIOR")
                    .imageUrl("https://sport-equipement.shop/product/bar-junior/")
                    .idCategory(3L)
                    .valid(true)
                    .description("La barre se caractérise par une adaptation aux tolérances de poids très strictes et un moletage prononcé adapté aux tentatives de type 1 répétition max.")
                    .price((long) 223.1)
                    .build();
            Product product8 = Product.builder()
                    .productId(8L)
                    .label("POWER BAG")
                    .productTitle("POWER BAG")
                    .imageUrl("https://sport-equipement.shop/product/power-bag/")
                    .idCategory(3L)
                    .valid(true)
                    .description("La barre se caractérise par une adaptation aux tolérances de poids très strictes et un moletage prononcé adapté aux tentatives de type 1 répétition max.")
                    .price((long) 45.59)
                    .build();
            Product product9 = Product.builder()
                    .productId(9L)
                    .label("ADJUSTABLE DUMBBELL")
                    .productTitle("ADJUSTABLE DUMBBELL")
                    .imageUrl("https://sport-equipement.shop/product/adjustable-dumbbell/")
                    .idCategory(3L)
                    .valid(true)
                    .description("Plus besoin d'acheter une quantité impressionnante d'haltères différents, fini : les haltères qui traînent partout... Avec leur design épuré et personnalisable sur demande associé à leur technologie novatrice, nos adjustable dumbbell offrent une alternative compacte aux sets d'haltères encombrants et peu pratiques. Notre adjustable dumbbell vous évite donc d'acquérir jusqu'à 24 paires d'haltères. Chaque haltère vous offre la possibilité de vous entraîner avec des poids allant de 2 à 24 kg ce qui vous fait de 15 niveaux d'ajustement. Ils sont également disponibles en 17 niveaux d'ajustement de 5 à 40 kg. Vous avez la possibilité d'acheter un seul haltère ou un set incluant deux haltères.")
                    .price((long) 199.19)
                    .build();
            Product product10 = Product.builder()
                    .productId(10L)
                    .label("HEX RUBBER DUMBBELL")
                    .productTitle("HEX RUBBER DUMBBELL")
                    .imageUrl("https://sport-equipement.shop/product/hex-rubber-dumbbell/")
                    .idCategory(3L)
                    .valid(true)
                    .description("Nos Hex rubber Dumbbell sont vendus par paires ainsi qu'à l'unité.\n\nCe modèle hexagonal en caoutchouc standard est une excellente option abordable qui continue d’offrir qualité, performance et longévité.\n\nLes extrémités robustes recouvertes de caoutchouc minimisent le bruit et limitent l’usure (à la fois des haltères et de votre sol), tandis que la poignée chromée est conçue de manière ergonomique pour offrir des sensations fermes, mais confortables quel que soit le type de prise.\n\n- Tête en caoutchouc ultra-résistante\n- Réduction du bruit, des dégâts au sol et de l’usure globale des dumbbells.\n- Poignée chromée ergonomique, facile à prendre en main\n\n1 kg : 7.19€ P.U/TTC\n2 kg : 13.19€ P.U/TTC\n2.5 kg : 16.79€ P.U/TTC\n3 kg : 20.39€ P.U/TTC\n4 kg : 26.39€ P.U/TTC\n5 kg : 32.39€ P.U/TTC\n6 kg : 38.39€ P.U/TTC\n7 kg : 45.39€ P.U/TTC\n7.5 kg : 49.19€ P.U/TTC\n8 kg : 51.59€ P.U/TTC\n9 kg : 58.79€ P.U/TTC\n10 kg : 64.79€ P.U/TTC\n12.5 kg : 80.39€ P.U/TTC\n15 kg : 97.19€ P.U/TTC\n17.5 kg : 112.79€ P.U/TTC\n20 kg : 128.39€ P.U/TTC\n22.5 kg : 145.19€ P.U/TTC\n25 kg :160.79€ P.U/TTC\n27.5 kg : 177.59€ P.U/TTC\n30 kg : 193.19€ P.U/TTC\n32.5 kg : 208.79€ P.U/TTC")
                    .price((long) 7.19)
                    .build();
            Product product11 = Product.builder()
                    .productId(11L)
                    .label("TRICEPS BAR")
                    .productTitle("TRICEPS BAR")
                    .imageUrl("https://sport-equipement.shop/product/182/")
                    .idCategory(3L)
                    .valid(true)
                    .description("Cette barre de musculation permet de développer la masse des biceps et triceps.\n\nGrip des poignées pour plus de stabilité et de sécurité.")
                    .price((long) 152.39)
                    .build();
            Product product12 = Product.builder()
                    .productId(12L)
                    .label("FITNESS BAR 1.5M")
                    .productTitle("FITNESS BAR 1.5M")
                    .imageUrl("https://sport-equipement.shop/product/fitness-bar-1-5m/")
                    .idCategory(3L)
                    .valid(true)
                    .description("Description à compléter.")
                    .price((long) 122.39)
                    .build();
            Product product13 = Product.builder()
                    .productId(13L)
                    .price((long) 9.59)
                    .description("Simples et efficaces, voici des colliers de serrage qui ont conquis la confiance de la plupart des athlètes de salle depuis des décennies. Nous nous appuyons sur de l'acier de haute qualité pour créer ces colliers de serrage qui s'agripperont fermement à la barre et ne risqueront pas de glisser pendant votre entraînement. Leur longévité en fait le meilleur rapport qualité/prix du marché.")
                    .valid(true)
                    .idCategory(4L)
                    .label("COLLIER DE SERRAGE EN MÉTAL")
                    .productTitle("Collier de serrage en métal")
                    .imageUrl("https://sport-equipement.shop/product/collier-en-metal/")
                    .build();
            Product product14 = Product.builder()
                    .productId(14L)
                    .price((long) 37.19)
                    .description("Chaque collier de serrage Aluminium comprend un levier en nylon avec un mécanisme de blocage doté d'un levier d'ouverture, qui permet à l'utilisateur d'ouvrir le collier en intégralité pour plus facilement l'installer sur une barre.")
                    .valid(true)
                    .idCategory(4L)
                    .label("COLLIER DE SERRAGE ALUMINIUM")
                    .productTitle("Collier de serrage aluminium")
                    .imageUrl("https://sport-equipement.shop/product/collier-aluminium/")
                    .build();
            Product product15 = Product.builder()
                    .productId(15L)
                    .price((long) 125.99)
                    .description("Os disques \"techniques plates\" présentent, avec 450 mm, le même diamètre extérieur que les disques olympiques standards, mais proposent une gamme de poids beaucoup plus légère et gérable : 2.5 et 5 kgs. Cela permet aux débutants ou aux athlètes en période de récupération de s'entraîner en effectuant leurs mouvements de départ sans forcer ce qui réduit grandement le risque de sur blessure (que ce soit pour des épaulé-jeté ou même des soulevé de terre ) avant de passer à de véritables disques.")
                    .valid(true)
                    .idCategory(4L)
                    .label("TECHNIQUE PLATES RED")
                    .productTitle("Technique Plates Red")
                    .imageUrl("https://sport-equipement.shop/product/technique-plates/")
                    .build();
            Product product16 = Product.builder()
                    .productId(16L)
                    .price((long) 175.19)
                    .description("Nos disques \"techniques plates\" présentent, avec 450 mm, le même diamètre extérieur que les disques olympiques standards, mais proposent une gamme de poids beaucoup plus légère et gérable : 2.5 et 5 kgs. Cela permet aux débutants ou aux athlètes en période de récupération de s'entraîner en effectuant leurs mouvements de départ sans forcer ce qui réduit grandement le risque de sur blessure (que ce soit pour des épaulé-jeté ou même des soulevé de terre ) avant de passer à de véritables disques.")
                    .valid(true)
                    .idCategory(4L)
                    .label("TECHNIQUE PLATE WHITE")
                    .productTitle("Technique Plate White")
                    .imageUrl("https://sport-equipement.shop/product/technique-plate/")
                    .build();
            Product product17 = Product.builder()
                    .productId(17L)
                    .price((long) 76.79)
                    .description("Grâce à cette version colorée et abordable des Elite bumper plates, il est facile d'organiser vos disques et de trouver, en un coup d’œil le disque qu'il vous faut, surtout dans les grandes salles où de nombreuses personnes utilisent le même équipement. Peut être vendue seul ou a l'unité. - Tolérance de poids : +/- 1 % du poids indiqué - Inserts acier inoxydable - Diamètre : 450 mm (+/- 3 mm) - conforme aux normes IWF. 5 kg : 76.69€ P.U/TTC 10 kg : 107.99€ P.U/TTC 15 kg : 131.99€ P.U/TTC 20 kg : 170.39 P.U/TTC 25 kg : 202.79€ P.U/TTC")
                    .valid(true)
                    .idCategory(4L)
                    .label("COLORED BUMPER PLATE")
                    .productTitle("Colored Bumper Plate")
                    .imageUrl("https://sport-equipement.shop/product/colored-bumper-plate/")
                    .build();
            Product product18 = Product.builder()
                    .productId(18L)
                    .price((long) 67.19)
                    .description("Nos bumper plates font 450 mm de diamètre, leurs poids sont à plus ou moins 1 % de leur poids cible. Faits avec des inserts en acier inoxydable et du caoutchouc. Dispo par pair ou a l'unité. - conforme aux normes de l’IWF - tolérance de poids +/- 1% - Inserts acier inoxydable - Diamètre : 450 mm (+/- 3 mm). 5 kg : 67.19€ P.U/TTC 10 kg : 95.99€ P.U/TTC 15 kg : 119.99€ P.U/TTC 20 kg : 155.99€ P.U/TTC 25 kg : 173.99€ P.U/TTC")
                    .valid(true)
                    .idCategory(4L)
                    .label("BUMPER PLATE")
                    .productTitle("Bumper Plate")
                    .imageUrl("https://sport-equipement.shop/product/bumper-plate/")
                    .build();
            Product product19 = Product.builder()
                    .productId(19L)
                    .price((long) 9.59)
                    .description("Nos Fractional plate arborent un nouveau design et son vendu par lot de 0.50 kg a 5kg permettent d’augmenter votre charge au grammes près. Les disques fractionnels peuvent être légèrement difficiles à enfiler sur l’extrémité de votre barre, à leur sortie, mais après quelques utilisations, le caoutchouc s’use et facilite la mise en place. - Revêtement en caoutchouc - Finition mat - Lettrage en relief. 0.5 kg : 9.59€ P.U/TTC 1 kg : 16.79€ P.U/TTC 1.5 kg : 23.99€ P.U/TTC 2 kg : 28.79€ P.U/TTC 2.5 kg : 33.59€ P.U/TTC 5 kg : 69.59€ P.U/TTC")
                    .valid(true)
                    .idCategory(4L)
                    .label("FRACTIONAL PLATE")
                    .productTitle("Fractional Plate")
                    .imageUrl("https://sport-equipement.shop/product/fractional-plate/")
                    .build();
            Product product20 = Product.builder()
                    .productId(20L)
                    .price((long) 5.99)
                    .description("Nos Tri-Grip tri grip plates en caoutchouc sont conçus pour un changement de disques sans problèmes et ultra rapide grâce aux poignées. - Adapté pour les barres Olympiques 50 mm. - Prévu de poignées utiles pour plusieurs exercices. - Le revêtement en caoutchouc protège le sol et permet de couper le son lors de disques en collision. - Le revêtement en caoutchouc protège contre l'oxydation. 1.25 kg : 5.99 € P.U/TTC 2.5 kg : 11.99 € P.U/TTC 5 kg : 23.99 € P.U/TTC 10 kg : 46.99 € P.U/TTC 15 kg : 70.79 € P.U/TTC 20 kg : 93.59 € P.U/TTC 25 kg : 116.39 € P.U/TTC")
                    .valid(true)
                    .idCategory(4L)
                    .label("TRI GRIP PLATES")
                    .productTitle("Tri Grip Plates")
                    .imageUrl("https://sport-equipement.shop/product/tri-grip-plates/")
                    .build();
            Product product21 = Product.builder()
                    .productId(21L)
                    .price((long) 104.39)
                    .description("Cette corde de conditionnement est un accessoire universel, capable de développer votre puissance abdominale et d’améliorer la résistance de vos bras.")
                    .valid(true)
                    .idCategory(4L)
                    .label("BATTLING ROPES")
                    .productTitle("Battling Ropes")
                    .imageUrl("https://sport-equipement.shop/product/battling-ropes/")
                    .build();
            Product product22 = Product.builder()
                    .productId(22L)
                    .price((long) 16.79)
                    .description("Les booty band sont des bandes élastiques à résistance variable. Elles vous permettent de vous entraîner sans équipement ou d’augmenter la difficulté de votre prochain entraînement en salle. Fourni par lot de 5.")
                    .valid(true)
                    .idCategory(5L)
                    .label("BOOTY BAND PACK")
                    .productTitle("Booty Band Pack")
                    .imageUrl("https://sport-equipement.shop/product/booty-band/")
                    .build();
            Product product23 = Product.builder()
                    .productId(23L)
                    .price((long) 4.79)
                    .description("Bandes de Résistance disponibles sur 6 niveaux différents, allant de très faible à très forte. Faciles à utiliser, ne prennent pas de place, ces bandes vous permettent d'intensifier vos exercices de renforcement musculaire ou alors de vous aider à passer vos exercices à poids de corps (tractions/dips).")
                    .valid(true)
                    .idCategory(5L)
                    .label("POWER BAND")
                    .productTitle("Power Band")
                    .imageUrl("https://sport-equipement.shop/product/power-band/")
                    .build();
            Product product24 = Product.builder()
                    .productId(24L)
                    .price((long) 22.79)
                    .description("Accessoire indispensable pour cibler les triceps, biceps et trapèzes. Accessoire idéal pour machine a câble ou a poulie. S'adapte sur tout type d'appareils a charge guidée.")
                    .valid(true)
                    .idCategory(5L)
                    .label("CORDE TRICEPS")
                    .productTitle("Corde Triceps")
                    .imageUrl("https://sport-equipement.shop/product/corde-triceps/")
                    .build();
            Product product25 = Product.builder()
                    .productId(25L)
                    .price((long) 17.99)
                    .description("Accessoire indispensable pour cibler les triceps, biceps et trapèzes et permet d'équilibrer totalement la prise de muscle sur chaque bras. S'adapte sur tout type d'appareils a charge guidée.")
                    .valid(true)
                    .idCategory(5L)
                    .label("CORDE TRICEPS SIMPLE")
                    .productTitle("Corde Triceps Simple")
                    .imageUrl("https://sport-equipement.shop/product/corde-triceps-simple/")
                    .build();
            Product product26 = Product.builder()
                    .productId(26L)
                    .price((long) 40.79)
                    .description("Notre corde légère en acier inoxydable et nos poignet en aluminium permettent de réaliser des rotations rapides et fluides.")
                    .valid(true)
                    .idCategory(5L)
                    .label("ELITE JUMP ROPE")
                    .productTitle("Elite Jump Rope")
                    .imageUrl("https://sport-equipement.shop/product/elite-jump-rope/")
                    .build();
            Product product27 = Product.builder()
                    .productId(27L)
                    .price((long) 15.59)
                    .description("Notre agility ladder constitue un moyen testé et éprouvé d’effectuer une grande variété d’exercices, idéal pour travailler son jeu de jambes sa vitesse ainsi que son agilité.")
                    .valid(true)
                    .idCategory(5L)
                    .label("AGILITY LADDER")
                    .productTitle("Agility Ladder")
                    .imageUrl("https://sport-equipement.shop/product/agility-ladder/")
                    .build();
            iProductService.save(product1);
            iProductService.save(product2);
            iProductService.save(product3);
            iProductService.save(product4);
            iProductService.save(product5);
            iProductService.save(product6);
            iProductService.save(product7);
            iProductService.save(product8);
            iProductService.save(product9);
            iProductService.save(product10);
            iProductService.save(product11);
            iProductService.save(product12);
            iProductService.save(product13);
            iProductService.save(product14);
            iProductService.save(product15);
            iProductService.save(product16);
            iProductService.save(product17);
            iProductService.save(product18);
            iProductService.save(product19);
            iProductService.save(product20);
            iProductService.save(product21);
            iProductService.save(product22);
            iProductService.save(product23);
            iProductService.save(product24);
            iProductService.save(product25);
            iProductService.save(product26);
            iProductService.save(product27);

        };
    }


}

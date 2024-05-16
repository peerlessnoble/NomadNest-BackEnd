package org.gatewaymicroservice.constants;

public class MSConstant {

    public static final String LOCAL_DATE_FORMAT = "dd-MM-yyyy";
    public static final String LOCAL_DATE_TIME_FORMAT = "dd-MM-yyyy__HH:mm:ss:SSSSSS";
    public static final String ZONED_DATE_TIME_FORMAT = "dd-MM-yyyy__HH:mm:ss:SSSSSS";
    public static final String INSTANT_FORMAT = "dd-MM-yyyy__HH:mm:ss:SSSSSS";

    public static final String CATALOGUE_MICROSERVICE = "CATALOG-SERVICE";
    public static final String CONFIG_MICROSERVICE  = "config-microservice";
    public static final String AUTH_MICROSERVICE  = "auth-microservice";
    public static final String ORDER_MICROSERVICE  = "order-microservice";
    public static final String PAYMENT_MICROSERVICE  = "payment-microservice";
    public static final String USER_MICROSERVICE  = "user-microservice";

    public static final String CATALOGUE_MICROSERVICE_URI = "lb://CATALOG-SERVICE";
    public static final String CONFIG_MICROSERVICE_URI  = "lb://config-microservice";
    public static final String AUTH_MICROSERVICE_URI  = "lb://auth-microservice";
    public static final String ORDER_MICROSERVICE_URI = "lb://MSORDER";
    public static final String PAYMENT_MICROSERVICE_URI  = "lb://MSPAYMENT";
    public static final String USER_MICROSERVICE_URI  = "lb://user-microservice";

    public static final String CATALOGUE_CATEGORIES_PATH = "/catalog/categories/**";

    public static final String CATALOGUE_SUBCATEGORIES_PATH = "/catalog/subcategories/**";

    public static final String CATALOGUE_PRODUCTS_PATH = "/catalog/products/**";
    public static final String CONFIG_PATH  = "";
    public static final String AUTH_PATH  = "/auth/**";
    public static final String ORDER_PATH = "/orders/**";
    public static final String PAYMENT_PATH  = "/payment/**";
    public static final String USER_PATH  = "/users/**";
}

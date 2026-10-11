package in.coderarmy.orderit.controller;


import in.coderarmy.orderit.model.Product;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JsonNode;

import java.math.BigDecimal;
import java.util.List;

public class JsonPractice {

    public static void main(String[] args) throws Exception {
        tools.jackson.databind.ObjectMapper mapper = new tools.jackson.databind.ObjectMapper();

        // 1. Serialization: Java object -> JSON
        Product laptop = new Product(
                1L, "Laptop", new BigDecimal("50000")
        );

        String json = mapper.writeValueAsString(laptop);
        System.out.println("1. JSON:");
        System.out.println(json);

        // 2. Pretty printing
        System.out.println("\n2. Pretty JSON:");
        System.out.println(
                mapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(laptop)
        );

        // 3. Deserialization: JSON -> Java object
        String mouseJson =
                "{\"id\":2,\"name\":\"Mouse\",\"price\":500}";

        Product mouse = mapper.readValue(mouseJson, Product.class);
        System.out.println("\n3. Java object:");
        System.out.println(mouse);

        // 4. Deserialize a JSON array into List<Product>
        String productsJson = """
                [
                  {"id":1,"name":"Laptop","price":50000},
                  {"id":2,"name":"Mouse","price":500},
                  {"id":3,"name":"Monitor","price":12000}
                ]
                """;

        // Without TypeReference: inspect the runtime element type
        List<?> rawList = mapper.readValue(productsJson, List.class);
        System.out.println("\n4. Without TypeReference:");
        System.out.println(rawList.getFirst().getClass());

        // With TypeReference: deserialize to Product objects
        List<Product> products = mapper.readValue(
                productsJson,
                new TypeReference<List<Product>>() {}
        );
        System.out.println("With TypeReference:");
        products.forEach(System.out::println);

        // 5. Read JSON without a Product class
        JsonNode node = mapper.readTree(productsJson);
        System.out.println("\n5. First product name:");
        System.out.println(node.get(0).get("name").asText());

        // 6. Experiment with problematic JSON
        System.out.println("\n6. Missing id:");
        Product missingId = mapper.readValue(
                "{\"name\":\"Pen\",\"price\":10}", Product.class
        );
        System.out.println(missingId);

        System.out.println("\nExtra field:");
        try {
            mapper.readValue(
                    "{\"id\":4,\"name\":\"Pen\",\"price\":10,\"color\":\"red\"}",
                    Product.class
            );
            System.out.println("Accepted");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
        }

        System.out.println("\nWrong type:");
        try {
            mapper.readValue(
                    "{\"id\":5,\"name\":\"Pen\",\"price\":\"cheap\"}",
                    Product.class
            );
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
        }

        System.out.println("\nBroken JSON:");
        try {
            mapper.readValue("{\"id\":6,", Product.class);
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
        }

        // 7. Jackson does not automatically run Bean Validation
        Product invalid = mapper.readValue(
                "{\"id\":7,\"name\":\"\",\"price\":-5}",
                Product.class
        );
        System.out.println("\n7. Invalid values still deserialize:");
        System.out.println(invalid);
    }
}
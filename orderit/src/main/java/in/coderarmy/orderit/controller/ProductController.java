package in.coderarmy.orderit.controller;

import in.coderarmy.orderit.model.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final Map<Long, Product> store = new HashMap<>();

    @GetMapping("/all")
    public Collection<Product> getAll(){
        return store.values();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@Valid @RequestBody Product product){
        store.put(product.id(), product);
        return product;
    }

}

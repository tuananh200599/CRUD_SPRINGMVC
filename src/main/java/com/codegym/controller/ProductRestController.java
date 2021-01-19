package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "products", method = RequestMethod.GET)
    public Iterable<Product> getProducts(){
        return productService.findAll();
    }
    @RequestMapping(value = "products", method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void create(@RequestBody Product product){
        productService.save(product);
    }
    @RequestMapping(value = "products", method = RequestMethod.PUT
            , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void update(@RequestBody Product product){
        productService.update(product);
    }
    @RequestMapping(value = "products/{id}", method = RequestMethod.DELETE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id){
        productService.remove(id);
    }
}

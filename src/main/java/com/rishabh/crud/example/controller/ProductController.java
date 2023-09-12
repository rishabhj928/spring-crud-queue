package com.rishabh.crud.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rishabh.crud.example.entity.Product;
import com.rishabh.crud.example.kafka.produce.KafkaProducer;
import com.rishabh.crud.example.rabbit.RabbitProduce;
import com.rishabh.crud.example.service.ProductService;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private RabbitProduce rabbitProduce;

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return service.saveProducts(products);
    }

    @GetMapping("/products")
    public List<Product> findAllProducts() {
        return service.getProducts();
    }

    @GetMapping("/productById/{id}")
    public Product findProductById(@PathVariable int id) {
        return service.getProductById(id);
    }

    @GetMapping("/product/{name}")
    public Product findProductByName(@PathVariable String name) {
        return service.getProductByName(name);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
        return service.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }

    @PostMapping("/rabbitmq/produce")
    public String rabbitProduce(@RequestBody String message) {
        String[] msgs = {"abc", "def", "ghi", "abc", "def", "ghi", "abc", "def", "ghi", "abc", "def", "ghi"};
        for (String msg : msgs) {
            rabbitProduce.send(msg);
        }
        return "RabbitMQ Producer - " + message;
    }

    @PostMapping("/kafka/produce")
    public String kafkaProduce(@RequestBody String message) {
        kafkaProducer.produce(message);
        String[] msgs = {"abc", "def", "ghi", "abc", "def", "ghi", "abc", "def", "ghi", "abc", "def", "ghi"};
        for (String msg : msgs) {
            kafkaProducer.produce(msg);
        }
        return "Kafka Producer - " + message;
    }

    @GetMapping("/redis")
    public String redis() {
        return service.testCache();
    }

}

package ro.nicolaemariusghergu.queryit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ro.nicolaemariusghergu.queryit.service.data.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
}

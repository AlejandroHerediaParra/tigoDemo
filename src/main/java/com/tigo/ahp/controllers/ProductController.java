package com.tigo.ahp.controllers;

import java.lang.reflect.Array;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tigo.ahp.dtos.ProductDTO;
import com.tigo.ahp.models.Product;
import com.tigo.ahp.models.User;
import com.tigo.ahp.services.ProductService;
import com.tigo.ahp.services.UserService;
import com.tigo.ahp.utils.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @CrossOrigin(origins = "*")
    @GetMapping("/products")
    public ResponseEntity<Object> getAllProducts(HttpServletRequest request) {
      
      try {
        String jwt = request.getHeader("Authorization").replace("Bearer ", ""); 
        String userEmail = jwtUtil.extractUsername(jwt); 

        userService.getUserByEmail(userEmail); 
      } catch (Exception e) {
        return ResponseEntity.badRequest().body("invalid user token");
      }

      List<Product> products = productService.getAllProducts();
      List<ProductDTO> productDTOs = products.stream()
              .map(product -> modelMapper.map(product, ProductDTO.class))
              .collect(Collectors.toList());
      return ResponseEntity.ok(productDTOs);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/products/search")
    public ResponseEntity<Object> searchProducts(HttpServletRequest request, @RequestParam("title") String title) {
      try {
        String jwt = request.getHeader("Authorization").replace("Bearer ", ""); 
        String userEmail = jwtUtil.extractUsername(jwt); 

        userService.getUserByEmail(userEmail);
      } catch (Exception e) {
        return ResponseEntity.badRequest().body("invalid user token");
      } 

        List<Product> products = productService.searchProductsByTitle(title); 
        List<ProductDTO> productDTOs = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDTOs);
    }
}

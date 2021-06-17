package com.example.demo.services;

import com.example.demo.domain.Product;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    //@Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> productDb = this.productRepository.findById(product.getId());
        if (productDb.isPresent()) {
            Product productUpdate = productDb.get();
            productUpdate.setId(product.getId());
            productUpdate.setName(product.getName());
            productUpdate.setDescription(product.getDescription());
            return productRepository.save(product);
            //return productUpdate;
        }
        else{
            throw new ResourceNotFoundException("Product not found with id: "+ product.getId());
        }

    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(long productId) {
        Optional<Product> product =  this.productRepository.findById(productId);
        if(product.isPresent()){
            return product.get();
        }else{
            throw new ResourceNotFoundException("Product not found with id: "+ productId);
        }

    }

    @Override
    public void deleteProduct(long id) {
        Optional<Product> productDb = this.productRepository.findById(id);
        if(productDb.isPresent()){
            productRepository.delete(productDb.get());
        }else{
            throw new ResourceAccessException("Product not found with id: "+ id);
        }

    }
}

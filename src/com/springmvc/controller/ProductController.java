package com.springmvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.domain.Product;
import com.springmvc.form.ProductForm;

@Controller
public class ProductController {

	
	/**
	 * One of the benefits of using the Spring Framework is that you get dependency injection for free. 
	 * After all, Spring started as a dependency injection container. 
	 * The easiest way to get a dependency injected to a Spring MVC controller is by annotating a field or a
	 *  method with @Autowired. 
	 */
    private static final Log logger = LogFactory.getLog(ProductController.class);
    
    @RequestMapping(value="/product_input")
    public String inputProduct() {
        logger.info("inputProduct called");
        return "ProductForm";
    }

    @RequestMapping(value="/product_save")
    public String saveProduct(ProductForm productForm, Model model) {
    	//Spring MVC creates a Model instance every time a request-handling method is invoked
        logger.info("saveProduct called");
        // no need to create and instantiate a ProductForm
        // create Product
        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        try {
            product.setPrice(Float.parseFloat(
                    productForm.getPrice()));
        } catch (NumberFormatException e) {
        }

        // add product

        model.addAttribute("product", product);
        return "ProductDetails";
    }


}

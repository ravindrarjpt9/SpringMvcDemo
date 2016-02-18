package com.springmvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springmvc.domain.Product;
import com.springmvc.form.ProductForm;
import com.springmvc.services.ProductService;

@Controller
public class ProductControllerWithAutoWired {

private static final Log logger = LogFactory.getLog(ProductController.class);
    
@Autowired
ProductService productService;

    @RequestMapping(value="/product_input1")
    public String inputProduct() {
        logger.info("inputProduct called");
        return "ProductForm";
    }
    
    @RequestMapping(value = "/product_save1", method = RequestMethod.POST)
    public String saveProduct1(ProductForm productForm, 
            RedirectAttributes redirectAttributes) {
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

       // model.addAttribute("product", product);
       Product saveProduct =  productService.add(product);
       redirectAttributes.addFlashAttribute("message", 
               "The product was successfully added.");
       return "redirect:/product_view/" + saveProduct.getId();

    }
    
    
    /**
     * 
     * Both request parameters and path variables are used to send values to the server. Both are also part of a URL
     * The request parameter takes the form of key=value pairs separated by an ampersand.
     * A path variable is similar to a request parameter, except that there is no key part, just a value.
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/product_view/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "ProductView";
    }

}

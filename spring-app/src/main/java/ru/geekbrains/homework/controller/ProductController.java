package ru.geekbrains.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.geekbrains.homework.dao.ProductDao;
import ru.geekbrains.homework.entity.Product;
import ru.geekbrains.homework.repo.ProductRepositrory;

import javax.persistence.OrderBy;
import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private  ProductRepositrory productRepositrory;

    /**
     * Получение списка продуктов
     * @param model
     * @return
     */

    @GetMapping
    public String ProductList(Model model){
        List<Product> productList = productRepositrory.findAll();
        model.addAttribute("products",productList);
        return "product";
    }

    /**
     * Создание продукта
     * @param price
     * @param title
     * @return
     */
    @PostMapping(value = "/create")
    public ModelAndView createProduct(
            @RequestParam(name = "price") int price,
            @RequestParam(name = "title") String title
    ) {
        Product product = new Product(title,price);
        productRepositrory.save(product);
        productRepositrory.flush();
        return new ModelAndView("redirect:/products");
    }

    /**
     * Показ формы на обновление продукта
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Product product = productRepositrory.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

        model.addAttribute("product", product);
        return "update-product";
    }

    /**
     * Обновление продукта
     * @param id
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, Product product,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            product.setId(id);
            return "update-user";
        }

        productRepositrory.save(product);
        return "redirect:/products";
    }


    /**
     * Удаление продукта
     */
    @DeleteMapping(value = "/delete")
    public String deleteProduct(@RequestParam(name = "id") Long id){

        productRepositrory.deleteById(id);
        return "redirect:/products";
    }

    /**
     * Переход на форму создания продукта
     * @param product
     * @return
     */
    @GetMapping("/add")
    public String showSignUpForm(Product product) {
        return "create-product";
    }



    @GetMapping(value = "/getProductBy/{id}")
    public Product findById(@PathVariable long id) {
        return productRepositrory.findById(id).get();
    }


    @GetMapping(value = "/getProductList", produces = {"application/json"})
    public List<Product> findAll() {
        return (List<Product>) productRepositrory.findAll();
    }

    @PostMapping(value = "/createProduct", produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product) {
        productRepositrory.save(product);
    }

    @GetMapping(value = "/deleteProductBy/{id}")
    public void deleteProductById(@PathVariable long id) {
        productRepositrory.deleteById(id);
    }

}

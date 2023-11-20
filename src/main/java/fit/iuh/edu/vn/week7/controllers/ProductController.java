package fit.iuh.edu.vn.week7.controllers;
import fit.iuh.edu.vn.week7.enums.ProductStatus;
import fit.iuh.edu.vn.week7.models.Product;
import fit.iuh.edu.vn.week7.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    private  String  showCustomerList(Model model){
        model.addAttribute("productList",productRepository.findAll());
        //tra ve trang web
        return "product/list";
    }

    @GetMapping ("/products/show-add-form")
    public String showFormAdd(Model model) {
        Product product = new Product();
        model.addAttribute("productAdd",product);
        model.addAttribute("statuses", ProductStatus.values());
        return "product/add";
    }
    //add Product
    @PostMapping("/products/add")
    public String addProduct(
            @ModelAttribute("productAdd")    Product Product,
            BindingResult result , Model model) {
        productRepository.save(Product);
        return "redirect:/products";
    }


    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id){
        Product product = productRepository.findById(id).orElse(new Product());
        productRepository.delete(product);
        return "redirect:/products";
    }

        @GetMapping("/products/show-edit-form/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("productUpdate", product);
        model.addAttribute("statuses", ProductStatus.values());
        return "product/update";
    }


    @PostMapping("/products/update/{id}")
    public String updateCustomer(@PathVariable("id") long id,
                                 @ModelAttribute("productUpdate")  Product updateProduct) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setName(updateProduct.getName());
            product.setDescription(updateProduct.getDescription());
            product.setUnit(updateProduct.getUnit());
            product.setManufacturer(updateProduct.getManufacturer());
            product.setStatus(updateProduct.getStatus());


            productRepository.save(product);
        }
        return "redirect:/products";
    }

}

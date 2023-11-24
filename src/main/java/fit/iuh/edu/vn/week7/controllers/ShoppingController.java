package fit.iuh.edu.vn.week7.controllers;

import fit.iuh.edu.vn.week7.models.CartItem;
import fit.iuh.edu.vn.week7.models.Product;
import fit.iuh.edu.vn.week7.repositories.ProductRepository;
import fit.iuh.edu.vn.week7.services.ProductServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ShoppingController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductServices productServices;
    @GetMapping("/shopping")
    private  String  showProductList(
            HttpSession session,
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size")Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Product> productPage = productServices.findPaginate(currentPage-1,
                pageSize,"name","asc");

        model.addAttribute("productPage",productPage);

        int totalPages = productPage.getTotalPages();
        if (totalPages >0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);
        }
        //tra ve trang web
        return "client/index";
    }

    @GetMapping("/shopping/add2cart/{id}")
    private  String  add2Cart(HttpSession session, Model model, @PathVariable("id") long id){
        Object obj = session.getAttribute("items");
        Product product = productRepository.findById(id).get();
        HashMap<Long, CartItem> cart = null;
        if (obj == null)
            cart = new HashMap<>() ;
        else
            cart = (HashMap<Long, CartItem>) obj;
        CartItem item = new CartItem(product,1);
        if (cart.get(product.getProduct_id() )!=null);
        item.setAmount(item.getAmount()+1);
        cart.put(product.getProduct_id(),item);

        session.setAttribute("items",cart);
        session.setAttribute("itemsOnCart",cart.size());
        return "redirect:/shopping";
    }

}
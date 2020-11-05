package com.example.Fashionista;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductRepository {

    protected List<Product> products = new ArrayList<>();

    public ProductRepository() {
        products.add(new Product(1L, "Puff-sleeved sequined dress", Category.DRESSES, 29.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2F97%2Ff2%2F97f2794870328c37794671f720d46f6b45984077.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5B%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Short dress in sequined mesh with a small stand-up collar, a concealed zip at the back and a hook-and-eye fastener at the back of the neck. Short, draped puff sleeves, a seam at the waist and a gently flared skirt. Lined in jersey made from recycled polyester."));
        products.add(new Product(2L, "T-shirt Long Fit", Category.TSHIRTS, 6.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2F11%2F27%2F1127c1c0c94616e819660927e437a540e84b4db0.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bmen_tshirtstanks_shortsleeve%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Long, round-necked T-shirt in soft jersey with a curved hem."));
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct(Long id) {
        for (Product product : products) {
            if (product.id == id)
                return product;
        }
        return null;
    }
}

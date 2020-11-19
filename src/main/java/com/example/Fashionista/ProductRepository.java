package com.example.Fashionista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {

    @Autowired
    private DataSource dataSource;

    protected Map<Long, String> categories = new HashMap<Long, String>();

    public ProductRepository() {
        getCategories();
/*
        products.add(new Product(1L, "Puff-sleeved sequined dress", Category.DRESSES, 29.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2F97%2Ff2%2F97f2794870328c37794671f720d46f6b45984077.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5B%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Short dress in sequined mesh with a small stand-up collar, a concealed zip at the back and a hook-and-eye fastener at the back of the neck. Short, draped puff sleeves, a seam at the waist and a gently flared skirt. Lined in jersey made from recycled polyester."));
        products.add(new Product(2L, "T-shirt Long Fit", Category.TSHIRTS, 6.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2F11%2F27%2F1127c1c0c94616e819660927e437a540e84b4db0.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bmen_tshirtstanks_shortsleeve%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Long, round-necked T-shirt in soft jersey with a curved hem."));
//        for (Long i = 3L; i < 12; i++) {
//            products.add(new Product(i, "Garment" + i, Category.TSHIRTS, 6.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2F11%2F27%2F1127c1c0c94616e819660927e437a540e84b4db0.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bmen_tshirtstanks_shortsleeve%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Long, round-necked T-shirt in soft jersey with a curved hem."));
//        }
        products.add(new Product(3L, "Satin jacket dress", Category.DRESSES, 49.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2F64%2Fbc%2F64bcd79819ac2f0b6faaa688acba0bb2d967de5c.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5BLadies_dresses_wrap%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Short dress in satin with notch lapels and long sleeves. Wrapover front with a hook-and-eye fastener at the front, a concealed, adjustable fastening at one side and wide ties at the other, which define the waist. Unlined."));
        products.add(new Product(4L, "Twill shirt jacket", Category.JACKET, 24.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2Fda%2F63%2Fda63367cff420065c8403595f7def93f8bd9cd5e.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bmen_jacketscoats_jackets%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Shirt jacket in cotton twill with a collar, zip down the front and yoke at the back. Flap chest pockets with a concealed press-stud, sleeves with a slit and button at the cuffs, and a straight-cut hem. Unlined."));
        products.add(new Product(5L, "Velvet jacket Skinny Fit", Category.JACKET, 59.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2Fa1%2Fd1%2Fa1d18d779f3dd339ebdd72dcb2845f8cd1b5ad93.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bmen_blazerssuits_blazers%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Single-breasted jacket in soft cotton velvet with peak lapels, a buttonhole on one lapel, a chest pocket, jetted front pockets and three inner pockets. One button at the front, decorative buttons at the cuffs and a double back vent. Lined. Skinny Fit – a slightly shorter style that shapes in at the chest and tapers sharply at the waist. This, combined with narrow shoulders and sleeves, creates a completely fitted silhouette."));
        products.add(new Product(6L, "Crinkled blouse", Category.BLOUSE, 34.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2Fae%2F5f%2Fae5f709cdfef86727457ee41406fcc5936e095f9.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bladies_shirtsblouses_blouses%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Blouse in crinkled satin with laser-cut edges. Double-layered, frilled collar and an opening at the back with narrow ties at the back of the neck. Voluminous, extra-long sleeves with a double flounce at the cuffs and narrow ties around the wrists."));
        products.add(new Product(7L, "V-neck blouse", Category.BLOUSE, 12.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2Fc5%2Ff1%2Fc5f188efb952f27ccced067075ea1b087861acdd.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bladies_shirtsblouses_blouses%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Long-sleeved blouse in woven fabric with a collar, V-neck, buttons down the front, buttoned cuffs and a rounded hem."));
        products.add(new Product(8L, "Chiffon dress", Category.DRESSES, 19.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2Fc8%2F8f%2Fc88fb442ba7d1ddcd8bdbe4421ec30bb0c0c82fc.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bladies_dresses_aline%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Short, A-line dress in crinkled chiffon with a grandad collar and covered buttons at the top. Long puff sleeves and narrow cuffs with a covered button. Seam at the waist and a tiered skirt. V-neck underdress in jersey made from recycled polyester with narrow shoulder straps."));
        products.add(new Product(9L, "T-shirt Long Fit", Category.TSHIRTS, 6.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2Fc1%2Fb1%2Fc1b1bbc63a23984e66f3e32f1ce5b45ad8b0956f.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bmen_tshirtstanks_shortsleeve%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Long, round-necked T-shirt in soft jersey with a curved hem."));
        products.add(new Product(10L, "Wide-skirt dress", Category.DRESSES, 39.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2Fdb%2F74%2Fdb746ea793796711efb871bb5896f260214991cb.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bladies_dresses_aline%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Short dress in woven fabric made from a cotton blend with a square neckline and seam under the bust. Long-puff sleeves with elastication over the shoulders. Gathered, A-line skirt and a concealed zip in one side. Lined."));
        products.add(new Product(11L, "Wool-blend coat", Category.JACKET, 119.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2F3f%2F80%2F3f8071856f836db887b675d9a05a529d9a03c9db.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bladies_jacketscoats_coats%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Fitted coat in a soft, felted wool blend with notch lapels and fastenings at the front with one visible and one concealed button. Pockets in the side seams and a single back vent. Lined."));
        products.add(new Product(12L, "Long balloon-sleeved dress", Category.DRESSES, 39.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2F95%2F92%2F959276461950038dc43cc2294cea44da4efbbf3c.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bladies_dresses_aline%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Long dress in an airy weave with an A-line silhouette. Double-layered stand-up collar, an opening at the front with a drawstring at the neck, gently dropped shoulders and long balloon sleeves with an opening and drawstring at the cuffs. Asymmetric seam at the hips with drawstrings, and a voluminous, softly draping skirt. Lined."));
        products.add(new Product(13L, "Gathered satin dress", Category.DRESSES, 24.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2Fda%2F71%2Fda714e4f60c25c444af8145ad125fb7e7160d1d9.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bladies_dresses_party%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Short, fitted dress in shiny satin with vertical elastication down the front and back to create a gathered effect. Sweetheart neckline and long, puff sleeves with narrow, covered elastication at the cuffs. Zip at the back and a small frill trim at the hem. Unlined."));
        products.add(new Product(14L, "Puff-sleeved sequined dress", Category.DRESSES, 34.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2Ffa%2F04%2Ffa0405f6f058f8ac38cab09b9aaf32a5ca9f7df7.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bladies_dresses_mididresses%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Fitted, calf-length dress in sequined mesh. Stand-up collar with hook-and-eye fasteners at the back of the neck, and short, gathered puff sleeves with a slit. Concealed zip and a slit at the back, and a seam at the waist. Lined in jersey made from recycled polyester."));
        products.add(new Product(15L, "Wool-blend coat", Category.JACKET, 69.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2F27%2F91%2F2791f204b7337c8de044f6057504dba9ebcaf68c.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bmen_jacketscoats_coats%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Coat in woven fabric made from a felted wool blend with a stand-up collar and buttons down the front. Diagonal, welt front pockets, two inner pockets, decorative buttons at the cuffs and a single back vent. Lined."));
        products.add(new Product(16L, "Wide blouse", Category.BLOUSE, 12.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2Fed%2Fc6%2Fedc6addc9de84bba33ae57cca552de84683132c3.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bladies_shirtsblouses_blouses%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Blouse in an airy weave with a stand-up collar and gathers at the top to create soft draping front and back. Opening with covered buttons at the back of the neck, long puff sleeves, cuffs with covered buttons, and a gently rounded hem."));
        products.add(new Product(17L, "Cable-knit cardigan", Category.BLOUSE, 19.99, "https://lp2.hm.com/hmgoepprod?set=quality%5B79%5D%2Csource%5B%2F92%2F74%2F927438a49a63c009e3d1aff21e77ec076415cf31.jpg%5D%2Corigin%5Bdam%5D%2Ccategory%5Bladies_cardigansjumpers_cardigans%5D%2Ctype%5BLOOKBOOK%5D%2Cres%5Bm%5D%2Chmver%5B1%5D&call=url[file:/product/main]", "Long cardigan in a soft cable knit containing some wool. V-neck, dropped shoulders and long, wide sleeves. Concealed buttons down the front, tapered waist and ribbing at the cuffs and hem. The polyester content of the cardigan is recycled."));
*/
    }

    public void getCategories() {
        categories = new HashMap<Long, String>();
        categories.put(1L, "Dresses");
        categories.put(2L, "T-shirts");
        categories.put(3L, "Jackets");
        categories.put(4L, "Blouses");

/*
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name FROM Category")) {

            while (rs.next()) {
                categories.put(rs.getLong("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name, categoryId, price, imageUrl, description FROM Product")) {

            while (rs.next()) {
                products.add(rsProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public Product getProduct(Long id) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT id, name, categoryId, price, imageUrl, description FROM Product WHERE id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rsProduct(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        List<Product> productsInCategory = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT id, name, categoryId, price, imageUrl, description FROM Product WHERE categoryId=?");
            ps.setLong(1, categoryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                productsInCategory.add(rsProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productsInCategory;
    }

    private Product rsProduct(ResultSet rs) throws SQLException {
        return new Product(rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("categoryId"),
                rs.getDouble("price"),
                rs.getString("imageUrl"),
                rs.getString("description")
        );
    }

}

package com.example.Fashionista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ProductRepository productRepository;

    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, customerId, orderDate, totalAmount FROM Orders")) {

            while (rs.next()) {
                orders.add(rsOrder(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public Order getOrder(Long id) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT id, customerId, orderDate, totalAmount FROM Orders WHERE id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rsOrder(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<OrderRow> getOrderRows(Long orderId) {
        List<OrderRow> orderRows = new ArrayList<>();

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT id, productId, quantity FROM OrderRow WHERE orderId=?");
            ps.setLong(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orderRows.add(rsOrderRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderRows;
    }

    public Customer getCustomer(Long id) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT id, firstName, lastName, email, address, address2, country, state, zip FROM Customer WHERE id=?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rsCustomer(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Long saveOrder(Customer customer, Cart cart) {
        Long generatedId = -1L;

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);

            // Customer
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Customer (firstName, lastName, email, address, address2, country, state, zip) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getAddress2());
            ps.setString(6, customer.getCountry());
            ps.setString(7, customer.getState());
            ps.setString(8, customer.getZip());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getLong(1);
            }

            // Order
            ps = conn.prepareStatement("INSERT INTO Orders (customerId, totalAmount) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, generatedId);
            ps.setDouble(2, cart.totalAmount());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getLong(1);
            }

            // OrderItems
            for (CartItem item : cart.getCartItems()) {
                ps = conn.prepareStatement("INSERT INTO OrderRow (orderId, productId, quantity) VALUES (?, ?, ?)");
                ps.setLong(1, generatedId);
                ps.setLong(2, item.product.id);
                ps.setDouble(3, item.quantity);
                ps.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    private Customer rsCustomer(ResultSet rs) throws SQLException {
        return new Customer(rs.getLong("id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("email"),
                rs.getString("address"),
                rs.getString("address2"),
                rs.getString("country"),
                rs.getString("state"),
                rs.getString("zip")
        );
    }

    private Order rsOrder(ResultSet rs) throws SQLException {
        return new Order(rs.getLong("id"),
                getCustomer(rs.getLong("customerId")),
                rs.getDate("orderDate"),
                getOrderRows(rs.getLong("id")),
                rs.getDouble("totalAmount")
        );
    }

    private OrderRow rsOrderRow(ResultSet rs) throws SQLException {
        return new OrderRow(productRepository.getProduct(rs.getLong("productId")),
                rs.getInt("quantity")
        );
    }

}

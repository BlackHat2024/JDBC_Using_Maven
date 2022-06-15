package repository.impl;

import mapper.OrderMapper;
import model.entity.Order;
import util.JdbcConnection;
import util.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends BaseRepository<Order, Integer> {

    public OrderRepository() {
        super(new OrderMapper());
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_ALL_ORDERS)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Order order = getMapper().map(result);
                orders.add(order);
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return orders;
    }

    @Override
    public Order findById(Integer id) {
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(Queries.FIND_ORDER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return getMapper().map(result);
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }
        return null;
    }

    @Override
    public Boolean exists(Integer id) {
        // TODO: Implement a method which checks if an order with the given id exists in the orders table
        Order order = findById(id) ;
        if(order==null)
            return  false;
        else
            return  true ;
    }

    @Override
    public Boolean save(Order order) {
        /*
         * TODO: Implement a method which adds an order to the orders table
         *  If the order exists then the method should instead update the employee
         *
         */
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement(Queries.INSERT_NEW_ORDER)) {
            boolean saves=false;
            if (exists(order.getOrderNumber())==true) {
                System.out.println("ORDER exist Update it");
            }
            else {
                pstmt.setInt(1, order.getOrderNumber());
                pstmt.setDate(2, (Date) order.getOrderDate());
                pstmt.setDate(3, (Date) order.getRequiredDate());
                pstmt.setDate(4, (Date) order.getShippedDate());
                pstmt.setString(5, order.getStatus());
                pstmt.setString(6, order.getComments());
                pstmt.setInt(7, order.getCustomerNumber());

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) saves=true;
                else saves=false;
            }
            if(saves==true){
                System.out.println("ORDER added sucessfully");
            }

            return saves;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public Integer update(Order order) {
        /*
         * TODO: Implement a method which updates an order with the given Order instance
         *  The method should then return the number of updated records
         */
        int id = 0;

        try (Connection connection = JdbcConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement(Queries.UPDATE_ORDER)) {


            if (exists(order.getOrderNumber())==true) {
                pstmt.setInt(1, order.getOrderNumber());
                pstmt.setDate(2, (Date) order.getOrderDate());
                pstmt.setDate(3, (Date) order.getRequiredDate());
                pstmt.setDate(4, (Date) order.getShippedDate());
                pstmt.setString(5, order.getStatus());
                pstmt.setString(6, order.getComments());
                pstmt.setInt(7, order.getCustomerNumber());
                id = pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(id>0){
            System.out.println("USER UPDATED");
        }

        return id;
    }

}

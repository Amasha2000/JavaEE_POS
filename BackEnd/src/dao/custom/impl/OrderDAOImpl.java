package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDAO;
import entity.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean add(Order order, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `Order` VALUES(?,?,?,?)",connection,order.getOrderId(),order.getCusId(),order.getDate(),order.getCost());
    }

    @Override
    public boolean update(Order order, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Order> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getOrderId() {
        return null;
    }
}

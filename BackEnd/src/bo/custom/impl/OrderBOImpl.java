package bo.custom.impl;

import bo.custom.OrderBO;
import dto.ItemDTO;
import dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    @Override
    public boolean addOrder(OrderDTO orderDTO, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateOrder(OrderDTO orderDTO, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteOrder(String id, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDTO search(String id, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getOrderId() {
        return null;
    }
}

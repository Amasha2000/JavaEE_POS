package dao.custom.impl;

import dao.CrudUtil;
import dao.DAOFactory;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailDAO;
import dto.OrderDetailDTO;
import entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public boolean add(OrderDetail orderDetail, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `Order Detail` VALUES (?,?,?,?)",connection,orderDetail.getOrderId(),orderDetail.getItemCode(),orderDetail.getDiscount(),orderDetail.getCost());
    }

    @Override
    public boolean update(OrderDetail orderDetail, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetail search(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDetail> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDetail> getAllOrderDetails(String id){
        return null;
    }
}

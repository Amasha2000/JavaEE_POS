package bo.custom.impl;

import bo.custom.OrderBO;
import dao.DAOFactory;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailDAO;
import dto.ItemDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import entity.Order;
import entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    private final OrderDAO orderDAO=(OrderDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailDAO orderDetailDAO=(OrderDetailDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOTypes.ORDERDETAIL);

    @Override
    public boolean addOrder(OrderDTO orderDTO, Connection connection) throws SQLException, ClassNotFoundException {
        connection.setAutoCommit(false);
        Order order=new Order(orderDTO.getOrderId(),orderDTO.getCusId(),orderDTO.getDate(),orderDTO.getCost());
        boolean added=orderDAO.add(order,connection);
        if(!added){
            connection.rollback();
            connection.setAutoCommit(true);
            return false;

        }else{
            for(OrderDetailDTO orderDetailDTO:orderDTO.getDetailList()){
                OrderDetail orderDetail=new OrderDetail(orderDetailDTO.getItemCode(),orderDetailDTO.getOrderId(),orderDetailDTO.getDiscount(),orderDetailDTO.getCost());
                boolean add = orderDetailDAO.add(orderDetail, connection);
                if (!add){
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
        }
        return true;
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
    public String getOrderId(Connection connection) {
        return null;
    }
}

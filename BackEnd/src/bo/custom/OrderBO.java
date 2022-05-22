package bo.custom;

import bo.SuperBO;
import dto.ItemDTO;
import dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    boolean addOrder(OrderDTO orderDTO, Connection connection) throws SQLException, ClassNotFoundException;
    boolean updateOrder(OrderDTO orderDTO, Connection connection) throws SQLException, ClassNotFoundException;
    boolean deleteOrder(String id,Connection connection) throws SQLException, ClassNotFoundException;
    OrderDTO search(String id, Connection connection) throws SQLException, ClassNotFoundException;
    ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException;
    String getOrderId(Connection connection);
}

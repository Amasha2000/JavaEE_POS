package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    boolean addItem(ItemDTO itemDTO, Connection connection) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDTO itemDTO, Connection connection) throws SQLException, ClassNotFoundException;
    boolean deleteItem(String id,Connection connection) throws SQLException, ClassNotFoundException;
    ItemDTO search(String id, Connection connection) throws SQLException, ClassNotFoundException;
    ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllItemCode();
}

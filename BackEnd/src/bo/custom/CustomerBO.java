package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    boolean addCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id,Connection connection) throws SQLException, ClassNotFoundException;
    CustomerDTO search(String id, Connection connection) throws SQLException, ClassNotFoundException;
    ArrayList<CustomerDTO> getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllCustomerId();
}

package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    boolean addCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDTO customerDTO);
    boolean deleteCustomer(CustomerDTO customerDTO);
    CustomerDTO search(String id);
    ArrayList<CustomerDTO> getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException;
}

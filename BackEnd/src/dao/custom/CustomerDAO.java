package dao.custom;

import dao.CrudDAO;
import entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    ArrayList<String> getAllCustomerId(Connection connection) throws SQLException, ClassNotFoundException;
}

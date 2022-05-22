package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {


    @Override
    public  boolean add(Customer customer, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO customer VALUES(?,?,?,?)",connection,customer.getCustomerID(),customer.getCustomerName(),customer.getCustomerAddress(),customer.getCustomerTeleNumber());
    }

    @Override
    public boolean update(Customer customer, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE customer SET name=?,address=?,teleNumber=? WHERE id=?",connection,customer.getCustomerName(),customer.getCustomerAddress(),customer.getCustomerTeleNumber(),customer.getCustomerID());
    }


    @Override
    public boolean delete(String id, Connection connection) throws SQLException, ClassNotFoundException {
       return CrudUtil.executeUpdate("DELETE FROM customer WHERE id=?",connection,id);
    }

    @Override
    public Customer search(String id, Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM customer WHERE id=?", connection,id);
        resultSet.next();
        return new Customer(id,resultSet.getString("name"),resultSet.getString("address"), resultSet.getString("teleNumber"));
    }

    @Override
    public ArrayList<Customer> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers=new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM customer",connection);
        while (resultSet.next()){
            allCustomers.add(new Customer(resultSet.getString("id"),resultSet.getString("name"),resultSet.getString("address"),resultSet.getString("teleNumber")));
        }
        return allCustomers;

    }

    @Override
    public String getCustomerId() {
        return null;
    }
}

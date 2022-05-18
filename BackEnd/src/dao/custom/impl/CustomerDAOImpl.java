package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {


    @Override
    public  boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO customer VALUES(?,?,?,?)",customer.getCustomerID(),customer.getCustomerName(),customer.getCustomerAddress(),customer.getCustomerTeleNumber());
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE customer SET name=?,address=?,teleNumber=? WHERE id=?",customer.getCustomerName(),customer.getCustomerAddress(),customer.getCustomerTeleNumber(),customer.getCustomerID());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return CrudUtil.executeUpdate("DELETE FROM customer WHERE id=?",id);
    }

    @Override
    public Customer search(String s) {
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() {
        return null;
    }
}

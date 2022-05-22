package dao.custom;

import dao.CrudDAO;
import entity.Customer;

import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    ArrayList<String> getAllCustomerId();
}

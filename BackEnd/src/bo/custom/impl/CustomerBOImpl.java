package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    private final CustomerDAO customerDAO=(CustomerDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public boolean addCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(customerDTO.getCustomerID(),customerDTO.getCustomerName(),customerDTO.getCustomerAddress(),customerDTO.getCustomerTeleNumber()),connection);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) {
        return false;
    }

    @Override
    public boolean deleteCustomer(CustomerDTO customerDTO) {
        return false;
    }

    @Override
    public CustomerDTO search(String id) {
        return null;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers=new ArrayList<>();
        ArrayList<Customer> all=customerDAO.getAll(connection);
        for (Customer customer:all){
            allCustomers.add(new CustomerDTO(customer.getCustomerID(),customer.getCustomerName(),customer.getCustomerAddress(),customer.getCustomerTeleNumber()));
        }
        return allCustomers;

    }
}

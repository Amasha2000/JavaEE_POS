package dao.custom;

import dao.CrudDAO;
import entity.OrderDetail;

import java.util.ArrayList;

public interface OrderDetailDAO extends CrudDAO<OrderDetail,String> {
    ArrayList<OrderDetail> getAllOrderDetails(String id);
}

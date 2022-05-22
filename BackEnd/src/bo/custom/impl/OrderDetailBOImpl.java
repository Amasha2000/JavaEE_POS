package bo.custom.impl;

import bo.custom.OrderDetailBO;
import dao.DAOFactory;
import dao.custom.OrderDetailDAO;
import dto.OrderDetailDTO;
import entity.OrderDetail;

import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailBO {
    private final OrderDetailDAO orderDetailDAO=(OrderDetailDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOTypes.ORDERDETAIL);


    @Override
    public ArrayList<OrderDetailDTO> getOrderDetail(String id) {
//        ArrayList<OrderDetailDTO> allOrders=new ArrayList<>();
//        ArrayList<OrderDetail> all=orderDetailDAO.getAllOrderDetails(id);
//        for(OrderDetail orderDetail:all){
//            allOrders.add(new OrderDetailDTO(orderDetail.getItemCode(),orderDetail.getOrderId(),orderDetail.getDiscount(),orderDetail.getCost()));
//        }
//        return allOrders;
        return null;
    }
}

package bo.custom;

import dto.OrderDetailDTO;

import java.util.ArrayList;

public interface OrderDetailBO {
    ArrayList<OrderDetailDTO> getOrderDetail(String id);
}

package bo;

import bo.custom.impl.CustomerBOImpl;
import bo.custom.impl.ItemBOImpl;
import bo.custom.impl.OrderBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}
    public static BOFactory getBoFactory(){
        return boFactory==null? boFactory=new BOFactory():boFactory;
    }
    public enum BOTypes{
        CUSTOMER,ITEM,ORDER,ORDERDETAIL;
    }
    public SuperBO getBOType(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:return new CustomerBOImpl();
            case ITEM:return new ItemBOImpl();
            case ORDER:return new OrderBOImpl();
            default:return null;
        }
    }
}

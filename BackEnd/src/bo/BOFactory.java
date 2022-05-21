package bo;

import bo.custom.impl.CustomerBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}
    public static BOFactory getBoFactory(){
        return boFactory==null? boFactory=new BOFactory():boFactory;
    }
    public enum BOTypes{
        CUSTOMER;
    }
    public SuperBO getBOType(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:return new CustomerBOImpl();
            default:return null;
        }
    }
}
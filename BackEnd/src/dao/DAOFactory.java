package dao;

import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.ItemDAOImpl;
import dao.custom.impl.OrderDetailImpl;
import dao.custom.impl.OrderImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}
    public static DAOFactory getDaoFactory(){
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,ITEM,ORDER,ORDERDETAIL
    }
    public SuperDAO getDAOType(DAOTypes types){
        switch (types){
            case CUSTOMER:return new CustomerDAOImpl();
            case ITEM:return new ItemDAOImpl();
            case ORDER:return new OrderImpl();
            case ORDERDETAIL:return new OrderDetailImpl();
            default:return null;
        }
    }

}

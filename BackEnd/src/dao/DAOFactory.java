package dao;

import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.ItemDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}
    public static DAOFactory getDaoFactory(){
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,ITEM,ORDER
    }
    public SuperDAO getDAOType(DAOTypes types){
        switch (types){
            case CUSTOMER:return new CustomerDAOImpl();
            case ITEM:return new ItemDAOImpl();
            default:return null;
        }
    }

}

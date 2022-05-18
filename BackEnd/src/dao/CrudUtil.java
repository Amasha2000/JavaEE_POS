package dao;

import bo.CustomerServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {


    private static PreparedStatement getPreparedStatement(String sql,Object... args) throws SQLException {
        Connection connection = CustomerServlet.ds.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for(int i=0;i< args.length;i++){
            preparedStatement.setObject(i+1,args[i]);
        }
        connection.close();
        return preparedStatement;
    }


    public static boolean executeUpdate(String sql,Object... args) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql,args).executeUpdate()>0;
    }

    public static ResultSet executeQuery(String sql, Object... args) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql,args).executeQuery();
    }
}

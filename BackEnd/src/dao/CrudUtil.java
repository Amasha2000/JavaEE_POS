package dao;

import servlets.CustomerServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {

    private static PreparedStatement getPreparedStatement(String sql,Connection connection,Object... args) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for(int i=0;i< args.length;i++){
            preparedStatement.setObject(i+1,args[i]);
        }
        return preparedStatement;
    }


    public static boolean executeUpdate(String sql,Connection connection,Object... args) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql,connection,args).executeUpdate()>0;
    }

    public static ResultSet executeQuery(String sql,Connection connection, Object... args) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql,connection,args).executeQuery();
    }
}

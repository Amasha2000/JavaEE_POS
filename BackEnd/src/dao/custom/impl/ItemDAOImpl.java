package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import entity.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item item, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO item VALUES(?,?,?,?)",connection,item.getCode(),item.getItemName(),item.getQuantity(),item.getPrice());
    }

    @Override
    public boolean update(Item item, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE item SET itemName=?,quantity=?,price=? WHERE code=?",connection,item.getItemName(),item.getQuantity(),item.getPrice(),item.getCode());
    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM item WHERE code=?",connection,id);
    }

    @Override
    public Item search(String id, Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item WHERE code=?", connection,id);
        resultSet.next();
        return new Item(id,resultSet.getString("itemName"),resultSet.getInt("quantity"), resultSet.getDouble("price"));
    }

    @Override
    public ArrayList<Item> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItems=new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item",connection);
        while (resultSet.next()){
            allItems.add(new Item(resultSet.getString("code"),resultSet.getString("itemName"),resultSet.getInt("quantity"),resultSet.getDouble("price")));
        }
        return allItems;
    }


    @Override
    public ArrayList<String> getAllItemCode(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<String> allIds=new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT code FROM item", connection);
        while (resultSet.next()){
            allIds.add(resultSet.getString("code"));
        }
        return allIds;
    }
}

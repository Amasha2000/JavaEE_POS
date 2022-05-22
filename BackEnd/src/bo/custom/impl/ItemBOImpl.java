package bo.custom.impl;

import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dto.ItemDTO;
import entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    private final ItemDAO itemDAO=(ItemDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean addItem(ItemDTO itemDTO, Connection connection) throws SQLException, ClassNotFoundException {
        return itemDAO.add(new Item(itemDTO.getCode(),itemDTO.getItemName(),itemDTO.getQuantity(),itemDTO.getPrice()),connection);
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO, Connection connection) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(itemDTO.getCode(),itemDTO.getItemName(),itemDTO.getQuantity(),itemDTO.getPrice()),connection);
    }

    @Override
    public boolean deleteItem(String id, Connection connection) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id,connection);
    }

    @Override
    public ItemDTO search(String id, Connection connection) throws SQLException, ClassNotFoundException {
        Item search = itemDAO.search(id, connection);
        return new ItemDTO(search.getCode(), search.getItemName(),search.getQuantity(),search.getPrice());

    }

    @Override
    public ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItems=new ArrayList<>();
        ArrayList<Item> all=itemDAO.getAll(connection);
        for (Item item:all){
            allItems.add(new ItemDTO(item.getCode(),item.getItemName(),item.getQuantity(),item.getPrice()));
        }
        return allItems;
    }

    @Override
    public ArrayList<String> getAllItemCode(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<String> allItemCode = itemDAO.getAllItemCode(connection);
        return allItemCode;
    }
}

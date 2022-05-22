package dao.custom;

import dao.CrudDAO;
import entity.Item;

import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item,String> {
    ArrayList<String> getAllItemCode();
}

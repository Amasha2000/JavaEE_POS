package servlets;

import bo.BOFactory;
import bo.custom.ItemBO;
import dto.ItemDTO;

import javax.annotation.Resource;
import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {

    @Resource(name="java:comp/env/jdbc/pool")
    DataSource ds;

    private final ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBOType(BOFactory.BOTypes.ITEM);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            Connection connection = ds.getConnection();
            String option=req.getParameter("option");
            resp.setContentType("application/json");
            PrintWriter writer=resp.getWriter();

            switch (option){
                case "SEARCH":
                    String itemCode = req.getParameter("ItemCode");
                    ItemDTO item=itemBO.search(itemCode,connection);

                    JsonArrayBuilder arrayBuilder1 = Json.createArrayBuilder();

                    String code = item.getCode();
                    String itemName = item.getItemName();
                    int quantity = item.getQuantity();
                    double price = item.getPrice();

                    JsonObjectBuilder objectBuilder1 = Json.createObjectBuilder();
                    objectBuilder1.add("code",code);
                    objectBuilder1.add("itemName",itemName);
                    objectBuilder1.add("quantity",quantity);
                    objectBuilder1.add("price",price);
                    arrayBuilder1.add(objectBuilder1.build());

                    JsonObjectBuilder response1 = Json.createObjectBuilder();
                    response1.add("status", 200);
                    response1.add("message", "Done");
                    response1.add("data", arrayBuilder1.build());
                    writer.print(response1.build());

                    break;
                case  "GETALL":

                    ArrayList<ItemDTO> allItems = itemBO.getAllItems(connection);

                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                    for (ItemDTO itemDTO:allItems){
                        String code1 = itemDTO.getCode();
                        String itemName1 = itemDTO.getItemName();
                        int quantity1 = itemDTO.getQuantity();
                        double price1 = itemDTO.getPrice();

                        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                        objectBuilder.add("code",code1);
                        objectBuilder.add("itemName",itemName1);
                        objectBuilder.add("quantity",quantity1);
                        objectBuilder.add("price",price1);
                        arrayBuilder.add(objectBuilder.build());
                    }
                    JsonObjectBuilder response = Json.createObjectBuilder();
                    response.add("status", 200);
                    response.add("message", "Done");
                    response.add("data", arrayBuilder.build());
                    writer.print(response.build());
                    break;

            }
            connection.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemCode = req.getParameter("ItemCode");
        String itemName = req.getParameter("ItemName");
        int quantity = Integer.parseInt(req.getParameter("Quantity"));
        double price = Double.parseDouble(req.getParameter("Price"));


        ItemDTO itemDTO = new ItemDTO(itemCode, itemName, quantity, price);

        PrintWriter writer = resp.getWriter();

        resp.setContentType("application/json");

        try {
            Connection connection = ds.getConnection();
            boolean b = itemBO.addItem(itemDTO,connection);

            if (b) {
                JsonObjectBuilder response = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_OK);
                response.add("status", 200);
                response.add("message", "Successfully Added");
                response.add("data", "");
                writer.print(response.build());

            }
            connection.close();
        } catch (ClassNotFoundException e) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", e.getLocalizedMessage());
            writer.print(response.build());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        } catch (SQLException throwables) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", throwables.getLocalizedMessage());
            writer.print(response.build());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String code = jsonObject.getString("code");
        String name = jsonObject.getString("itemName");
        int quantity = Integer.parseInt(jsonObject.getString("quantity"));
        double price = Double.parseDouble(jsonObject.getString("price"));

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        ItemDTO itemDTO = new ItemDTO(code, name, quantity, price);


        try {
            Connection connection = ds.getConnection();
            boolean b = itemBO.updateItem(itemDTO,connection);

            if (b) {
                JsonObjectBuilder response = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_OK);
                response.add("status", 200);
                response.add("message", "Success");
                response.add("data", "");
                writer.print(response.build());

            }else{
                JsonObjectBuilder response = Json.createObjectBuilder();
                response.add("status", 400);
                response.add("message", "Update Failed");
                response.add("data", "");
                writer.print(response.build());

            }
            connection.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", throwables.getLocalizedMessage());
            writer.print(response.build());
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemCode = req.getParameter("ItemCode");
        PrintWriter writer = resp.getWriter();

        resp.setContentType("application/json");

        try{
            Connection connection = ds.getConnection();
            boolean b = itemBO.deleteItem(itemCode,connection);

            if (b) {
                JsonObjectBuilder response = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_OK);
                response.add("status", 200);
                response.add("message", "Success");
                response.add("data", "");
                writer.print(response.build());

            }else{
                JsonObjectBuilder response = Json.createObjectBuilder();
                response.add("status", 400);
                response.add("message", "Delete Failed");
                response.add("data", "");
                writer.print(response.build());
            }
            connection.close();
        } catch (SQLException throwables) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", throwables.getLocalizedMessage());
            writer.print(response.build());
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", e.getLocalizedMessage());
            writer.print(response.build());
            e.printStackTrace();
        }


    }
}

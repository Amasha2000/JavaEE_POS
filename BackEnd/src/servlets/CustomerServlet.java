package servlets;

import bo.BOFactory;
import bo.custom.CustomerBO;
import dto.CustomerDTO;

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

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    @Resource(name="java:comp/env/jdbc/pool")
    DataSource ds;

    private final CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBOType(BOFactory.BOTypes.CUSTOMER);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String customerID = req.getParameter("CustomerID");
        String customerName = req.getParameter("CustomerName");
        String customerAddress = req.getParameter("CustomerAddress");
        String customerTeleNumber = req.getParameter("CustomerTeleNumber");


        CustomerDTO customerDTO = new CustomerDTO(customerID, customerName, customerAddress, customerTeleNumber);

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            Connection connection = ds.getConnection();
            boolean b = customerBO.addCustomer(customerDTO,connection);
//            PreparedStatement preparedStatement = ds.getConnection().prepareStatement("INSERT INTO customer VALUES(?,?,?,?)");
//            preparedStatement.setObject(1,customerID);
//            preparedStatement.setObject(2,customerName);
//            preparedStatement.setObject(3,customerAddress);
//            preparedStatement.setObject(4,customerTeleNumber);

            //boolean b= preparedStatement.executeUpdate()>0;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try{
//        String option=req.getParameter("option");
//        resp.setContentType("application/json");
//        PrintWriter writer=resp.getWriter();
//
//        switch (option){
//            case "SEARCH":
//                break;
//            case  "GETALL":
//                // resultSet = ds.getConnection().prepareStatement("SELECT * FROM customer").executeQuery();
//                ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();
//
//                JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
//                for (CustomerDTO customerDTO:allCustomers){
//                    String id = customerDTO.getCustomerID();
//                    String name = customerDTO.getCustomerName();
//                    String address = customerDTO.getCustomerAddress();
//                    String teleNumber = customerDTO.getCustomerTeleNumber();
//
//                    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
//                    objectBuilder.add("id",id);
//                    objectBuilder.add("name",name);
//                    objectBuilder.add("address",address);
//                    objectBuilder.add("teleNumber",teleNumber);
//                    arrayBuilder.add(objectBuilder.build());
//                }
//                JsonObjectBuilder response = Json.createObjectBuilder();
//                response.add("status", 200);
//                response.add("message", "Done");
//                response.add("data", arrayBuilder.build());
//                writer.print(response.build());
//                break;
//
//        }
//           // CrudUtil.connection.close();
//        } catch (SQLException | ClassNotFoundException throwables) {
//            throwables.printStackTrace();
//
//        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        JsonReader reader = Json.createReader(req.getReader());
//        JsonObject jsonObject = reader.readObject();
//        String id = jsonObject.getString("id");
//        String name = jsonObject.getString("name");
//        String address = jsonObject.getString("address");
//        String teleNumber = jsonObject.getString("teleNumber");
//
//        PrintWriter writer = resp.getWriter();
//        resp.setContentType("application/json");
//
//        Customer customer = new Customer(id, name, address, teleNumber);
//
//
//        try {
//           boolean b = customerDAO.update(customer);
//
//        if (b) {
//                JsonObjectBuilder response = Json.createObjectBuilder();
//                resp.setStatus(HttpServletResponse.SC_OK);
//                response.add("status", 200);
//                response.add("message", "Success");
//                response.add("data", "");
//                writer.print(response.build());
//
//            }else{
//            JsonObjectBuilder response = Json.createObjectBuilder();
//            response.add("status", 400);
//            response.add("message", "Update Failed");
//            response.add("data", "");
//            writer.print(response.build());
//
//        }
//        } catch (SQLException throwables) {
//            JsonObjectBuilder response = Json.createObjectBuilder();
//            response.add("status", 400);
//            response.add("message", "Error");
//            response.add("data", throwables.getLocalizedMessage());
//            writer.print(response.build());
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            JsonObjectBuilder response = Json.createObjectBuilder();
//            response.add("status", 400);
//            response.add("message", "Error");
//            response.add("data", e.getLocalizedMessage());
//            writer.print(response.build());
//            e.printStackTrace();
//        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String customerID = req.getParameter("CustomerID");
//        PrintWriter writer = resp.getWriter();
//
//        resp.setContentType("application/json");
//
//        try{
//
//        boolean b = customerDAO.delete(customerID);
//
//        if (b) {
//            JsonObjectBuilder response = Json.createObjectBuilder();
//            resp.setStatus(HttpServletResponse.SC_OK);
//            response.add("status", 200);
//            response.add("message", "Success");
//            response.add("data", "");
//            writer.print(response.build());
//
//        }else{
//            JsonObjectBuilder response = Json.createObjectBuilder();
//            response.add("status", 400);
//            response.add("message", "Delete Failed");
//            response.add("data", "");
//            writer.print(response.build());
//
//        }
//    } catch (SQLException throwables) {
//        JsonObjectBuilder response = Json.createObjectBuilder();
//        response.add("status", 400);
//        response.add("message", "Error");
//        response.add("data", throwables.getLocalizedMessage());
//        writer.print(response.build());
//        throwables.printStackTrace();
//    } catch (ClassNotFoundException e) {
//        JsonObjectBuilder response = Json.createObjectBuilder();
//        response.add("status", 400);
//        response.add("message", "Error");
//        response.add("data", e.getLocalizedMessage());
//        writer.print(response.build());
//        e.printStackTrace();
//    }
//

}
}

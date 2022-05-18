package bo;

import dao.DAOFactory;
import dao.custom.CustomerDAO;
import entity.Customer;

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
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    @Resource(name="java:comp/env/jdbc/pool")
    public static DataSource ds;

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String customerID = req.getParameter("CustomerID");
        String customerName = req.getParameter("CustomerName");
        String customerAddress = req.getParameter("CustomerAddress");
        String customerTeleNumber = req.getParameter("CustomerTeleNumber");

        Customer customer = new Customer(customerID, customerName, customerAddress, customerTeleNumber);

        PrintWriter writer = resp.getWriter();
        try {
             boolean b = customerDAO.add(customer);

            if (b) {
                JsonObjectBuilder response = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_OK);
                response.add("status", 200);
                response.add("message", "Success");
                response.add("data", "");
                writer.print(response.build());

            }
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
        try{
        String option=req.getParameter("option");
        resp.setContentType("application/json");
        PrintWriter writer=resp.getWriter();

        switch (option){
            case "SEARCH":
                break;
            case  "GETALL":
                ResultSet resultSet = ds.getConnection().prepareStatement("SELECT * FROM customer").executeQuery();

                JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                while (resultSet.next()){
                    String id = resultSet.getString(1);
                    String name = resultSet.getString(2);
                    String address = resultSet.getString(3);
                    String teleNumber = resultSet.getString(4);

                    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                    objectBuilder.add("id",id);
                    objectBuilder.add("name",name);
                    objectBuilder.add("address",address);
                    objectBuilder.add("teleNumber",teleNumber);
                    arrayBuilder.add(objectBuilder.build());
                }
                JsonObjectBuilder response = Json.createObjectBuilder();
                response.add("status", 200);
                response.add("message", "Done");
                response.add("data", arrayBuilder.build());
                writer.print(response.build());
                break;

        }
        ds.getConnection().close();;
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        String teleNumber = jsonObject.getString("teleNumber");

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        Customer customer = new Customer(id, name, address, teleNumber);


        try {
           boolean b = customerDAO.update(customer);

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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerID = req.getParameter("CustomerID");
        PrintWriter writer = resp.getWriter();

        resp.setContentType("application/json");

        try{

        boolean b = customerDAO.delete(customerID);

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

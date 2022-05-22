package servlets;

import bo.BOFactory;
import bo.custom.ItemBO;
import bo.custom.OrderBO;
import dto.ItemDTO;
import dto.OrderDTO;
import entity.OrderDetail;

import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
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

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    @Resource(name="java:comp/env/jdbc/pool")
    DataSource ds;

    private final OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBOType(BOFactory.BOTypes.ORDER);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            Connection connection = ds.getConnection();
            String option=req.getParameter("option");
            resp.setContentType("application/json");
            PrintWriter writer=resp.getWriter();

            switch (option){
                case "SEARCH":

                    break;

                case "GETID":
                    String oId = orderBO.getOrderId(connection);


                    JsonObjectBuilder responseId = Json.createObjectBuilder();
                    responseId.add("status", 200);
                    responseId.add("message", "Done");
                    responseId.add("data", oId);
                    writer.print(responseId.build());
                    break;

            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double cost = Double.parseDouble( req.getParameter("Cost"));
        String cusId = "C00-001";//As an example
        String orderId = req.getParameter("OId");
        String date = "2022-05-22";//As an example
        ArrayList<OrderDetail> details=new ArrayList<>();

        OrderDTO orderDTO = new OrderDTO(orderId,cusId, date, cost,details);

        PrintWriter writer = resp.getWriter();

        resp.setContentType("application/json");

        try {
            Connection connection = ds.getConnection();
            boolean b = orderBO.addOrder(orderDTO,connection);

            if (b) {
                JsonObjectBuilder response = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_OK);
                response.add("status", 200);
                response.add("message", "Purchase Successful");
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

}

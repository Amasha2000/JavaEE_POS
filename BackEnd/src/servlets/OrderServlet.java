package servlets;

import bo.BOFactory;
import bo.custom.ItemBO;
import bo.custom.OrderBO;
import dto.ItemDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import entity.OrderDetail;

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
        JsonReader reader = Json.createReader(req.getReader());

        JsonObject jsonObject = reader.readObject();

        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();

        String oId = jsonObject.getString("oId");
        String cusId = jsonObject.getString("cusId");
        String date = jsonObject.getString("date");
        String price = jsonObject.getString("total");
        String discount = jsonObject.getString("discount");
        JsonArray cartDetail=jsonObject.getJsonArray("cart");

        ArrayList<OrderDetailDTO> dtos=new ArrayList<>();

        for(int i=0;i<cartDetail.size();i++){
            JsonObject detailJsonObject = cartDetail.getJsonObject(i);

            String code = detailJsonObject.getString("code");
//            String name = detailJsonObject.getString("name");
//            String unitPrice = detailJsonObject.getString("unitPrice");
//            String quantity = detailJsonObject.getString("qty");
            String total = detailJsonObject.getString("tot");

            dtos.add(new OrderDetailDTO(code,oId,Double.parseDouble( discount),Double.parseDouble(total)));
        }

        OrderDTO orderDTO = new OrderDTO(oId, cusId, date, Double.parseDouble(price), dtos);
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

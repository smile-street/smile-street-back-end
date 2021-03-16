package com.smilestreet;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smilestreet.model.Volunteer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SaveVolunteerHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Logger LOG = LogManager.getLogger(SaveVolunteerHandler.class);

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        LOG.info("received the request");

        String volunteerId = request.getPathParameters().get("volunteer_id");
        String requestBody = request.getBody();
        ObjectMapper objMapper = new ObjectMapper();
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setStatusCode(200);
        Map<String, String> headers = new HashMap<>();
        headers.put("Access-Control-Allow-Origin", "*");
        response.setHeaders(headers);

        try {
            Volunteer v = objMapper.readValue(requestBody, Volunteer.class);

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s",
                    System.getenv("DB_HOST"),
                    System.getenv("DB_NAME"),
                    System.getenv("DB_USER"),
                    System.getenv("DB_PASSWORD")));

            preparedStatement = connection.prepareStatement("INSERT INTO volunteer VALUES ( ?, ?, ?,?,?,?,?,?,?)");
          //  preparedStatement.setString(1, UUID.randomUUID().toString());
            preparedStatement.setInt(2, Integer.parseInt(volunteerId));
            preparedStatement.setString(3, v.getFirstname());
            preparedStatement.setString(4, v.getLastname());
            preparedStatement.setString(5, v.getContactnumber());
            preparedStatement.setString(6, v.getUsername());
            preparedStatement.setString(7, v.getEmployername());
            preparedStatement.setString(8, v.getPrimarylocation());
            preparedStatement.setInt(9, v.getNumberofdays());
            preparedStatement.setString(10, v.getStartdate());
            preparedStatement.setString(11, v.getEnddate());


            preparedStatement.execute();

            connection.close();
        } catch (IOException e) {
            LOG.error("Unable to unmarshal JSON for adding a task", e);
        } catch (ClassNotFoundException e) {
            LOG.error("ClassNotFoundException", e);
        } catch (SQLException throwables) {
            LOG.error("SQL Exception", throwables);
        }
        finally {
            closeConnection();
        }

        return response;
    }

    private void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
        catch (Exception e) {
            LOG.error("Unable to close connections to MySQL - {}", e.getMessage());
        }
    }
}


package com.smilestreet;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smilestreet.model.SaveVolunteerIntrests;
import com.smilestreet.model.VolunteerAvailability;
import jdk.incubator.jpackage.internal.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SaveVolunteerIntrestsHandaler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Logger LOG = LogManager.getLogger(SaveVolunteerIntrestsHandaler.class);

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        LOG.info("received the request");

        String volunteerId = request.getPathParameters().get("volunteer_id");
        // Log.info(volunteerId);
        String requestBody = request.getBody();
        ObjectMapper objMapper = new ObjectMapper();
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setStatusCode(200);
        Map<String, String> headers = new HashMap<>();
        headers.put("Access-Control-Allow-Origin", "*");
        response.setHeaders(headers);
        response.setBody(volunteerId);

        try {

            LOG.debug("trying");
            SaveVolunteerIntrests i = objMapper.readValue(requestBody, SaveVolunteerIntrests.class);

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s",
                    System.getenv("DB_HOST"),
                    System.getenv("DB_NAME"),
                    System.getenv("DB_USER"),
                    System.getenv("DB_PASSWORD")));

            preparedStatement  = connection.prepareStatement("UPDATE volunteer SET Web_Design = ? , SEO = ? , Graphic_Design= ? , Teaching = ? , Public_Health = ?  ,Empowerment = ? ,Sports= ?,Construction= ? ,Cooking= ? , Accessibility= ? ,Mental_Health= ? ,Event_Planning= ? ,Gardening= ? , Music = ? ,Dance= ?  WHERE volunteer_id = ? ");

            preparedStatement.setBoolean(1,i.isWeb_Design());
            preparedStatement.setBoolean(2,i.isSEO());
            preparedStatement.setBoolean(3,i.isGraphic_Design());
            preparedStatement.setBoolean(4,i.isTeaching());
            preparedStatement.setBoolean(5,i.isPublic_Health());
            preparedStatement.setBoolean(6,i.isEmpowerment());
            preparedStatement.setBoolean(7,i.isSports());
            preparedStatement.setBoolean(8,i.isConstruction());
            preparedStatement.setBoolean(9,i.isCooking());
            preparedStatement.setBoolean(10,i.isAccessibility());
            preparedStatement.setBoolean(11,i.isMental_Health());
            preparedStatement.setBoolean(12,i.isEvent_Planning());
            preparedStatement.setBoolean(13,i.isGardening());
            preparedStatement.setBoolean(14,i.isMusic());
            preparedStatement.setBoolean(15,i.isDance());
            preparedStatement.setString(16, volunteerId);



            LOG.debug("this is the prepared statement object");

            LOG.debug(preparedStatement);
            preparedStatement.executeUpdate();

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


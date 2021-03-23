package com.smilestreet;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smilestreet.model.SaveGoodCauseOpportunity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SaveGoodCauseOpportunityHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Logger LOG = LogManager.getLogger(SaveGoodCauseOpportunity.class);

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        LOG.info("received the request");

        //its gets the id from the previous page or via add opportunity
        String good_cause_uid = request.getPathParameters().get("good_cause_id");
        String requestBody = request.getBody();
        ObjectMapper objMapper = new ObjectMapper();
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setStatusCode(200);
        Map<String, String> headers = new HashMap<>();
        headers.put("Access-Control-Allow-Origin", "*");
        response.setHeaders(headers);

        try {

            LOG.debug("trying");
            SaveGoodCauseOpportunity v = objMapper.readValue(requestBody, SaveGoodCauseOpportunity.class);

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s",
                    System.getenv("DB_HOST"),
                    System.getenv("DB_NAME"),
                    System.getenv("DB_USER"),
                    System.getenv("DB_PASSWORD")));




            preparedStatement  = connection.prepareStatement(
                    "INSERT INTO good_cause_opportunity (good_cause_opportunity_id, opportunityname, opportunitydate, opportunitydescription, good_cause_uid) VALUES (?,?,?,?,?)");

            preparedStatement.setString(1, UUID.randomUUID().toString());
            preparedStatement.setString(2, v.getOpportunityname());
            preparedStatement.setDate(3, v.getOpportunitydate());
            preparedStatement.setString(4, v.getOpportunitydescription());
            preparedStatement.setString(5, good_cause_uid);
            LOG.debug("this is the prepared statement object");

            LOG.debug(preparedStatement);
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

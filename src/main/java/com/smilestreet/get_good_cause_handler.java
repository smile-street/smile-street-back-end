package com.smilestreet;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.smilestreet.model.Good_cause;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class get_good_cause_handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static final Logger LOG= LogManager.getLogger(get_good_cause_handler.class);
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet=null;

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        List<Good_cause> good_causes = null;
        try {
            good_causes = new ArrayList<>();
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s",
                    System.getenv("DB_HOST"),
                    System.getenv("DB_NAME"),
                    System.getenv("DB_USER"),
                    System.getenv("DB_PASSWORD")
            ));
            preparedStatement = connection.prepareStatement("SELECT * FROM  good_cause");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Good_cause good_cause = new Good_cause(resultSet.getInt("good_cause_id"),
                        resultSet.getString("descriptionofgoodcause"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("emailaddress"),
                        resultSet.getString("contactnumber"));

                good_causes.add(good_cause);

            }
        } catch (Exception e) {
            LOG.error(String.format("unable to query database"));
        } finally {
            closeConnection();
        }
        return null;
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
        } catch (Exception e) {
            LOG.error("unable to close connection", e.getMessage());
        }
    }
}


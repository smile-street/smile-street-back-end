package com.smilestreet;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.smilestreet.model.Skill;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SaveSkillsHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
    private static final Logger LOG= LogManager.getLogger(getVolunteerHandler.class);
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet=null;

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        LOG.debug("saving skills");
        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s",
                    System.getenv("DB_HOST"),
                    System.getenv("DB_NAME"),
                    System.getenv("DB_USER"),
                    System.getenv("DB_PASSWORD")
            ));

            int skill_id = 52;
            String skillname = "dave";

            PreparedStatement pst = connection.prepareStatement("INSERT INTO skill(skill_id, skillname) VALUES(?,?)");

            pst.setInt(1, skill_id);
            pst.setString(2,skillname);
            pst.executeUpdate();




        } catch (Exception e) {
            LOG.error(String.format("unable to query database"));
        }
        finally {
            closeConnection();
        }


        return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody("saved")
                .build();
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

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


public class getSkillsHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
    private static final Logger LOG= LogManager.getLogger(getSkillsHandler.class);
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet=null;

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        List<Skill> skills = null;
        try {
            skills = new ArrayList<>();
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s",
                    System.getenv("DB_HOST"),
                    System.getenv("DB_NAME"),
                    System.getenv("DB_USER"),
                    System.getenv("DB_PASSWORD")
            ));
            preparedStatement = connection.prepareStatement("SELECT * FROM  skill");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Skill skill=new Skill(resultSet.getInt("skill_id"),
                                    resultSet.getString("skillname"));

                skills.add(skill);

            }
        } catch (Exception e) {
            LOG.error(String.format("unable to query database"));
        }
        finally {
            closeConnection();
        }


        return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(skills)
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

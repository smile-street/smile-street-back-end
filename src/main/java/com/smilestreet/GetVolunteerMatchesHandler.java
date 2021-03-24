package com.smilestreet;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.smilestreet.model.GetVolunteerMatchSingle;
import com.smilestreet.model.GetVolunteerMatches;
import com.smilestreet.model.GetVolunteerMatchesOpportunityObject;
import com.smilestreet.model.GetVolunteerMatchSingle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class GetVolunteerMatchesHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
    //private static final Logger LOG= LogManager.getLogger(GetVolunteerMatchesHandler.class);
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        List<GetVolunteerMatchesOpportunityObject> locDates = null;
        try {
            locDates = new ArrayList<>();
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s",
                    System.getenv("DB_HOST"),
                    System.getenv("DB_NAME"),
                    System.getenv("DB_USER"),
                    System.getenv("DB_PASSWORD")
            ));
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM good_cause_opportunity" +
                            "WHERE good_cause_opportunity.good_cause_opportunity_id NOT IN" +
                            "( SELECT matching_list.join_id FROM matching_list WHERE matching_list.voln_id = 1234 )" +
                            "AND good_cause_opportunity.opportunitydate BETWEEN" +
                            "( SELECT volunteer.startdate FROM volunteer WHERE volunteer.vol_id = 1234 )" +
                            "AND" +
                            "( SELECT volunteer.enddate FROM volunteer WHERE volunteer.vol_id = 1234 );");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GetVolunteerMatchesOpportunityObject MatchedLocationAndData = new GetVolunteerMatchesOpportunityObject(resultSet.getString("good_cause_opportunity_id"),
                        resultSet.getString("opportunityname"),
                        resultSet.getDate("opportunitydate"),
                        resultSet.getString("opportunitydescription"),
                        resultSet.getString("good_cause_uid"),
                        resultSet.getInt("joining_id"),
                        resultSet.getBoolean("Web_Design"),
                        resultSet.getBoolean("SEO"),
                        resultSet.getBoolean("Graphic_Design"),
                        resultSet.getBoolean("Teaching"),
                        resultSet.getBoolean("Public_Health"),
                        resultSet.getBoolean("Empowerment"),
                        resultSet.getBoolean("Sports"),
                        resultSet.getBoolean("Construction"),
                        resultSet.getBoolean("Cooking"),
                        resultSet.getBoolean("Accessibility"),
                        resultSet.getBoolean("Mental_Health"),
                        resultSet.getBoolean("Event_Planning"),
                        resultSet.getBoolean("Gardening"),
                        resultSet.getBoolean("Music"),
                        resultSet.getBoolean("Dance"),
                        resultSet.getString("Location"));

                //all opportunites that match the volunteers location and dates are in the array
                locDates.add(MatchedLocationAndData);
            }

            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM volunteer");

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                GetVolunteerMatchSingle v1 = new GetVolunteerMatchSingle(resultSet.getInt("vol_id"),
                        resultSet.getString("volunteer_id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("contactnumber"),
                        resultSet.getString("username"),
                        resultSet.getString("employername"),
                        resultSet.getString("primarylocation"),
                        resultSet.getInt("numberofdays"),

                        resultSet.getDate("datetime"),
                        resultSet.getDate("enddate"),
                        resultSet.getBoolean("Web_Design"),
                        resultSet.getBoolean("SEO"),
                        resultSet.getBoolean("Graphic_Design"),
                        resultSet.getBoolean("Teaching"),
                        resultSet.getBoolean("Public_Health"),
                        resultSet.getBoolean("Empowerment"),
                        resultSet.getBoolean("Sports"),
                        resultSet.getBoolean("Construction"),
                        resultSet.getBoolean("Cooking"),
                        resultSet.getBoolean("Accessibility"),
                        resultSet.getBoolean("Mental_Health"),
                        resultSet.getBoolean("Event_Planning"),
                        resultSet.getBoolean("Gardening"),
                        resultSet.getBoolean("Music"),
                        resultSet.getBoolean("Dance"));


            }


        } catch (Exception e) {
            // LOG.error(String.format("unable to query database"));
        } finally {
            closeConnection();
        }


        return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(locDates)
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
            //LOG.error("unable to close connection", e.getMessage());
        }
    }

    public ArrayList<GetVolunteerMatchesOpportunityObject> MatchFunc (GetVolunteerMatchSingle V, ArrayList<GetVolunteerMatchesOpportunityObject>locDates) {

        int count = 0;
        for (GetVolunteerMatchesOpportunityObject A : locDates) {
            if (A.isAccessibility() ==) {

        }
    }

        





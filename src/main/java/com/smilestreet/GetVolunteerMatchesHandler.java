package com.smilestreet;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.smilestreet.model.GetVolunteerMatchSingle;
import com.smilestreet.model.GetVolunteerMatchesOpportunityObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GetVolunteerMatchesHandler implements RequestHandler<APIGatewayProxyRequestEvent, ApiGatewayResponse> {
    private static final Logger LOG= LogManager.getLogger(GetVolunteerMatchesHandler.class);
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public ApiGatewayResponse handleRequest(APIGatewayProxyRequestEvent request, Context context) {

        List<GetVolunteerMatchesOpportunityObject> locDates = null;
        String volunteer_id = request.getPathParameters().get("volunteer_id");


        ArrayList<GetVolunteerMatchesOpportunityObject> finalMatch = null;
        try {
            LOG.debug("try 1");
            locDates = new ArrayList<>();
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s",
                    System.getenv("DB_HOST"),
                    System.getenv("DB_NAME"),
                    System.getenv("DB_USER"),
                    System.getenv("DB_PASSWORD")
            ));

            //we get the volunteer_id
            preparedStatement = connection.prepareStatement("SELECT vol_id FROM volunteer WHERE volunteer_id=?");

            LOG.debug("try get vol_id, input volunteer ");

            preparedStatement.setString(1, volunteer_id);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                GetVolunteerMatchSingle v2 = new GetVolunteerMatchSingle(resultSet.getInt("vol_id"));
                LOG.debug(v2);
                LOG.debug("this is the vol_id "  + v2.getVol_id());


                        preparedStatement = connection.prepareStatement(
                        "SELECT * FROM good_cause_opportunity " +
                                "WHERE good_cause_opportunity.good_cause_opportunity_id NOT IN " +
                                "( SELECT matching_list.join_id FROM matching_list WHERE matching_list.voln_id = ? ) " +
                                "AND good_cause_opportunity.opportunitydate BETWEEN " +
                                "( SELECT volunteer.startdate FROM volunteer WHERE volunteer.vol_id = ?) " +
                                "AND " +
                                "( SELECT volunteer.enddate FROM volunteer WHERE volunteer.vol_id = ? ); ");

                LOG.debug("match on location and dates");

                preparedStatement.setInt(1, v2.getVol_id());
                preparedStatement.setInt(2, v2.getVol_id());
                preparedStatement.setInt(3, v2.getVol_id());

                resultSet = preparedStatement.executeQuery();
                LOG.debug(" locdates object query");
            }
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

                LOG.debug("opportunity date for matchedLocation and data", MatchedLocationAndData.getOpportunitydate());
                //all opportunites that match the volunteers location and dates are in the array
                locDates.add(MatchedLocationAndData);
            }

            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM volunteer WHERE volunteer_id=?");

            LOG.debug("volunteer object query ");

            preparedStatement.setString(1, volunteer_id);

            resultSet = preparedStatement.executeQuery();

            LOG.debug(resultSet + " select * from volunteer");
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

                        resultSet.getDate("startdate"),
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

                finalMatch = MatchFunc(v1, (ArrayList<GetVolunteerMatchesOpportunityObject>) locDates);
            }


            

        } catch (Exception e) {
           LOG.error(String.format("unable to query database"),e);
        } finally {
            closeConnection();
        }


        return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(finalMatch)
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

    // function matches the array ist containing the matching location and dates with the skills of our volunteer and saves that in a arraylist of
    //Matched opportunities
    public static ArrayList<GetVolunteerMatchesOpportunityObject> MatchFunc(GetVolunteerMatchSingle V, ArrayList<GetVolunteerMatchesOpportunityObject> locDates) {
        List<GetVolunteerMatchesOpportunityObject> finalMatched = null;
        finalMatched = new ArrayList<>();

        int count = 0;
        for (GetVolunteerMatchesOpportunityObject A : locDates) {

            if (A.isWeb_Design() == true && A.isWeb_Design() == V.isWeb_Design())
                count += 1;
            if (A.isSEO() == true && A.isSEO() == V.isSEO())
                count += 1;
            if (A.isGraphic_Design() == true && A.isGraphic_Design() == V.isGraphic_Design())
                count += 1;
            if (A.isTeaching() == true && A.isGraphic_Design() == V.isGraphic_Design())
                count += 1;
            if (A.isPublic_Health() == true && A.isPublic_Health() == V.isPublic_Health())
                count += 1;
            if (A.isEmpowerment() == true && A.isEmpowerment() == V.isEmpowerment())
                count += 1;
            if (A.isSports() == true && A.isSports() == V.isSports())
                count += 1;
            if (A.isConstruction() == true && A.isConstruction() == V.isConstruction())
                count += 1;
            if (A.isCooking() == true && A.isCooking() == V.isCooking())
                count += 1;
            if (A.isAccessibility() == true && A.isAccessibility() == V.isAccessibility())
                count += 1;
            if (A.isMental_Health() == true && A.isMental_Health() == V.isMental_Health())
                count += 1;
            if (A.isEvent_Planning() == true && A.isEvent_Planning() == V.isEvent_Planning())
                count += 1;
            if (A.isGardening() == true && A.isGardening() == V.isGardening())
                count += 1;
            if (A.isMusic() == true && A.isMusic() == V.isMusic())
                count += 1;
            if (A.isDance() == true && A.isDance() == V.isDance())
                count += 1;

            if (count >= 3) {

                A.setVol_id(V.getVol_id());

                finalMatched.add(A);



            }

        }
        return (ArrayList<GetVolunteerMatchesOpportunityObject>) finalMatched;
    }
}





package com.smilestreet;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.smilestreet.model.GetVolunteerMatchSingle;
import com.smilestreet.model.GetVolunteerMatchesOpportunityObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GetVolunteerMatchesHandler implements RequestHandler<APIGatewayProxyRequestEvent, ApiGatewayResponse> {
    private static final Logger LOG = LogManager.getLogger(GetVolunteerMatchesHandler.class);
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public ApiGatewayResponse handleRequest(APIGatewayProxyRequestEvent request, Context context) {

        List<GetVolunteerMatchesOpportunityObject> locDates = null;
        String volunteer_id = request.getPathParameters().get("volunteer_id");

        GetVolunteerMatchSingle v2 = new GetVolunteerMatchSingle();
        GetVolunteerMatchesOpportunityObject MatchedLocationAndData = new GetVolunteerMatchesOpportunityObject();
        ArrayList finalMatch = null;
        try {

            locDates = new ArrayList<>();
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s",
                    System.getenv("DB_HOST"),
                    System.getenv("DB_NAME"),
                    System.getenv("DB_USER"),
                    System.getenv("DB_PASSWORD")
            ));

            //we get the volunteer_id
            preparedStatement = connection.prepareStatement("SELECT * FROM volunteer WHERE volunteer_id=?");


            preparedStatement.setString(1, volunteer_id);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                v2.setVol_id(resultSet.getInt("vol_id"));
                v2.setFirstname(resultSet.getString("firstname"));
                v2.setLastname(resultSet.getString("lastname"));
                v2.setContactnumber(resultSet.getString("contactnumber"));
                v2.setUsername(resultSet.getString("username"));
                v2.setEmployername(resultSet.getString("employername"));
                v2.setPrimarylocation(resultSet.getString("primarylocation"));
                v2.setNumberofdays(resultSet.getInt("numberofdays"));

                v2.setWeb_Design(resultSet.getBoolean("Web_Design"));
                v2.setSEO(resultSet.getBoolean("SEO"));
                v2.setGraphic_Design(resultSet.getBoolean("Graphic_Design"));
                v2.setTeaching(resultSet.getBoolean("Teaching"));
                v2.setPublic_Health(resultSet.getBoolean("Public_Health"));
                v2.setEmpowerment(resultSet.getBoolean("Empowerment"));
                v2.setSports(resultSet.getBoolean("Sports"));
                v2.setConstruction(resultSet.getBoolean("Construction"));
                v2.setCooking(resultSet.getBoolean("Cooking"));
                v2.setAccessibility(resultSet.getBoolean("Accessibility"));
                v2.setMental_Health(resultSet.getBoolean("Mental_Health"));
                v2.setEvent_Planning(resultSet.getBoolean("Event_Planning"));
                v2.setGardening(resultSet.getBoolean("Gardening"));
                v2.setMusic(resultSet.getBoolean("Music"));
                v2.setDance(resultSet.getBoolean("Dance"));


            }

            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM good_cause_opportunity " +
                            "WHERE good_cause_opportunity.good_cause_opportunity_id NOT IN " +
                            "( SELECT matching_list.join_id FROM matching_list WHERE matching_list.voln_id = ? ) " +
                            "AND good_cause_opportunity.opportunitydate BETWEEN " +
                            "( SELECT volunteer.startdate FROM volunteer WHERE volunteer.vol_id = ?) " +
                            "AND " +
                            "( SELECT volunteer.enddate FROM volunteer WHERE volunteer.vol_id = ? ); ");


            preparedStatement.setInt(1, v2.getVol_id());
            preparedStatement.setInt(2, v2.getVol_id());
            preparedStatement.setInt(3, v2.getVol_id());

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {


                GetVolunteerMatchesOpportunityObject A = new GetVolunteerMatchesOpportunityObject();
                A.setGood_cause_opportunity_id(resultSet.getString("good_cause_opportunity_id"));
                A.setOpportunityname(resultSet.getString("opportunityname"));
                A.setOpportunitydate(resultSet.getDate("opportunitydate"));
                A.setOpportunitydescription(resultSet.getString("opportunitydescription"));
                A.setGood_cause_uid(resultSet.getString("good_cause_uid"));
                A.setJoining_id(resultSet.getInt("joining_id"));
                A.setWeb_Design(resultSet.getBoolean("Web_Design"));
                A.setSEO(resultSet.getBoolean("SEO"));
                A.setGraphic_Design(resultSet.getBoolean("Graphic_Design"));
                A.setTeaching(resultSet.getBoolean("Teaching"));
                A.setPublic_Health(resultSet.getBoolean("Public_Health"));
                A.setEmpowerment(resultSet.getBoolean("Empowerment"));
                A.setSports(resultSet.getBoolean("Sports"));
                A.setConstruction(resultSet.getBoolean("Construction"));
                A.setCooking(resultSet.getBoolean("Cooking"));
                A.setAccessibility(resultSet.getBoolean("Accessibility"));
                A.setMental_Health(resultSet.getBoolean("Mental_Health"));
                A.setEvent_Planning(resultSet.getBoolean("Event_Planning"));
                A.setGardening(resultSet.getBoolean("Gardening"));
                A.setMusic(resultSet.getBoolean("Music"));
                A.setDance(resultSet.getBoolean("Dance"));
                A.setLocation(resultSet.getString("Location"));
                A.setGc_id1(resultSet.getInt("gc_id1"));


                locDates.add(A);

            }


            finalMatch = MatchFunc(v2, (ArrayList<GetVolunteerMatchesOpportunityObject>) locDates);


        } catch (Exception e) {
            LOG.error(String.format("unable to query database"), e);
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
        ArrayList<GetVolunteerMatchesOpportunityObject> finalMatch = null;
        finalMatch = new ArrayList<>();
        int n = 3;

        for (int i = 0; i < locDates.size(); i++) {
            int count = 0;



                if (locDates.get(i).isWeb_Design() == true && V.isWeb_Design() == true) {

                    count++;
                    LOG.debug(("count " + count));
                }
                if (locDates.get(i).isSEO() == true && V.isSEO() == true) {

                    count++;
                    LOG.debug(("count " + count));
                }
                if (locDates.get(i).isGraphic_Design() == true && V.isGraphic_Design() == true) {
                    count++;
                    LOG.debug(("count " + count));
                }

                if (locDates.get(i).isTeaching() == true && V.isTeaching() == true) {
                    count++;
                    LOG.debug(("count " + count));
                }


                if (locDates.get(i).isPublic_Health() == true && V.isPublic_Health() == true) {
                    count++;
                    LOG.debug(("count " + count));
                }
                if (locDates.get(i).isEmpowerment() == true && V.isEmpowerment() == true) {
                    count++;
                    LOG.debug(("count " + count));
                }
                if (locDates.get(i).isSports() == true && V.isSports() == true) {
                    count++;
                    LOG.debug(("count " + count));
                }

                if (locDates.get(i).isConstruction() == true && V.isConstruction() == true) {
                    count++;
                    LOG.debug(("count " + count));
                }
                if (locDates.get(i).isCooking() == true && V.isCooking() == true) {
                    count++;
                    LOG.debug(("count " + count));
                }
                if (locDates.get(i).isAccessibility() == true && V.isAccessibility() == true) {
                    count++;
                    LOG.debug(("count " + count));
                }
                if (locDates.get(i).isMental_Health() == true && V.isMental_Health() == true) {
                    count++;
                    LOG.debug(("count " + count));
                }
                if (locDates.get(i).isEvent_Planning() == true && V.isEvent_Planning() == true) {

                    count++;
                    LOG.debug(("count " + count));
                }
                if (locDates.get(i).isGardening() == true && V.isGardening() == true) {
                    count++;
                    LOG.debug(("count " + count));
                }
                if (locDates.get(i).isMusic() == true && V.isMusic() == true) {

                    count++;
                    LOG.debug(("count " + count));
                }
                if (locDates.get(i).isDance() == true && V.isDance() == true) {
                    count++;
                    LOG.debug(("count " + count));


                }
                if (count >= n) {
                    LOG.debug(("count " + count));
                    finalMatch.add(locDates.get(i));


                }


                // if (count >= 1) {
//LOG.debug("ths is the count " + count);
                //A.setVol_id(V.getVol_id());



            }

        return finalMatch;
    }

}




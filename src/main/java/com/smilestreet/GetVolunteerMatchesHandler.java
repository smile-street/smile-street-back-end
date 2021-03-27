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
    private static final Logger LOG= LogManager.getLogger(GetVolunteerMatchesHandler.class);
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
            preparedStatement = connection.prepareStatement("SELECT * FROM volunteer WHERE volunteer_id=?");

            LOG.debug("try get vol_id, input volunteer ");

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


                LOG.debug(v2);
                LOG.debug("this is the vol_id " + v2.getVol_id());
                LOG.debug("this is the first name " + v2.getFirstname());
                LOG.debug("this is the web design " + v2.isWeb_Design());
                LOG.debug("this is the primary locatio " + v2.getPrimarylocation());

            }

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


            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    LOG.debug("james debug " + columnValue + " " + rsmd.getColumnName(i));
                }
                LOG.debug("second log for rsmd" +  " ");
            }

            while (resultSet.next()) {
                locDates.add(new GetVolunteerMatchesOpportunityObject(
                resultSet.getString("good_cause_opportunity_id"),
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
                resultSet.getString("Location")));


            }

            LOG.debug ("===========>>>>>>size of loc dates " + locDates.size());

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
        int n = 0;
        for (int i= 0; i< locDates.size(); i++) {
            int count = 0;

            locDates.get(i);

            if (locDates.get(i).isWeb_Design() == true && V.isWeb_Design() == true)
                LOG.debug("web design " + locDates.get(i).isWeb_Design());
                count = count + 1;
            if (locDates.get(i).isSEO() == true && V.isSEO() == true)
                LOG.debug("is SEO ", locDates.get(i).isSEO());
                count = count + 1;
            if (locDates.get(i).isGraphic_Design() == true && V.isGraphic_Design() == true)
                count = count + 1;
            LOG.debug("graphic deign " + locDates.get(i).isGraphic_Design());
            if (locDates.get(i).isTeaching() == true && V.isTeaching() == true)
                count = count + 1;

            LOG.debug("teaching " + locDates.get(i));
            if (locDates.get(i).isPublic_Health() == true && V.isPublic_Health() == true)
                count = count + 1;
            if (locDates.get(i).isEmpowerment() == true && V.isEmpowerment() == true)
                count = count + 1;
            if (locDates.get(i).isSports() == true && V.isSports() == true)
            count = count + 1;
            LOG.debug("code hit  spots " + count);
            if (locDates.get(i).isConstruction() == true && V.isConstruction() == true)
                count = count + 1;
            if (locDates.get(i).isCooking() == true && V.isCooking() == true)
                LOG.debug("code hit cooking " + count);
            count = count + 1;
            if (locDates.get(i).isAccessibility() == true && V.isAccessibility() == true)
                LOG.debug("code hit access " + count);
            count = count + 1;
            if (locDates.get(i).isMental_Health() == true && V.isMental_Health() == true)
                LOG.debug("code hit mental heath " + count);
            count = count + 1;
            if (locDates.get(i).isEvent_Planning() == true && V.isEvent_Planning() == true)
                LOG.debug("code hit event planning" + count);
            count = count + 1;
            if (locDates.get(i).isGardening() == true && V.isGardening() == true)
                count = count + 1;
            LOG.debug("code hit  gardening " + count);
            if (locDates.get(i).isMusic() == true && V.isMusic() == true)
                LOG.debug("code hit  music " + count);
            count = count + 1;
            if (locDates.get(i).isDance() == true && V.isDance() == true)
                count = count + 1;
            LOG.debug("danc e " + locDates.get(i));
            LOG.debug("code hit dance " + count);
            if (count >= n) {
                finalMatch.add(locDates.get(i));
            }

        }


           // if (count >= 1) {
//LOG.debug("ths is the count " + count);
                //A.setVol_id(V.getVol_id());








        return finalMatch;
    }
}





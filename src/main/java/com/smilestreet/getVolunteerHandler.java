package com.smilestreet;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.smilestreet.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class getVolunteerHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
	//private static final Logger LOG=new LogManager.getLogger(getVolunteerHandler.class);
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet=null;

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		List<User> users = null;
		try {
			users = new ArrayList<>();
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s",
					"streetsahead.cxoqaafagpfv.eu-west-2.rds.amazonaws.com",
					"smilestreet",
					"root",
					"smilestreet123"));
			preparedStatement = connection.prepareStatement("SELECT * FROM  volunteer");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = new User(resultSet.getInt("volunteer_id"),
									resultSet.getString("firstname"),
									resultSet.getString("lastname"),
									resultSet.getString("contactnumber"),
									resultSet.getString("username"),
									resultSet.getString("employername"),
									resultSet.getString("primarylocation"),
									resultSet.getInt("numberofdays"),
									resultSet.getString("startdate"),
									resultSet.getString("enddate")
									);
						users.add(user);

			}
		} catch (Exception e) {
			//LOG.error(String.format("unable to query database"));
		}
		finally {
		closeConnection();
			}


		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody(users)
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
}

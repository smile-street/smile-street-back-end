package com.smilestreet;

import java.util.Collections;
import java.util.Map;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

		User user1 = new User(0001, "ariel", "pillemer", 016404151, "pillemer@gmail.com", "unemployed", "Wales", "2", "12/03/2021", "31/12/2022");
		User user2 = new User(0001, "Taslima", "Patel", 3207213, "Taslima@gmail.com", "Greeat!", "Manchester", "1", "18/03/2021", "22/10/2021");
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);

		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody(users)
				.build();
	}
}

package com.smilestreet;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


import com.smilestreet.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class Handler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

		User user1 =new User(1,"Taslima","Patel","666","tas","Tas","ii","2","23/05/2022","22/03/2022");
		List<User> users=new ArrayList<>();
		users.add(user1);


		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody(users)
				.build();
	}
}

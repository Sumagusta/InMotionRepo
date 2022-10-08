package com.app.inovaztest.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.inovaztest.model.User;
import com.app.inovaztest.service.UserServiceRetrofit;
import com.app.inovaztest.service.impl.UserServiceImpl;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@RestController
@RequestMapping("/api/inmotion")
public class UserController {

	@GetMapping("/get/users")
	public List<User> getUsers() throws IOException {

		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com")
				.addConverterFactory(JacksonConverterFactory.create()).build();

		UserServiceRetrofit service = retrofit.create(UserServiceRetrofit.class);

		List<User> users = service.get().execute().body();
		
		
		return users;
	}

	@GetMapping("/users/export")
	public void exportPdf(HttpServletResponse response) throws IOException {	
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_"+currentDateTime+".pdf";
		response.setHeader(headerKey, headerValue);

		UserServiceImpl export = new UserServiceImpl(getUsers());
		export.export(response);
		
		Map<String, Object> result = new HashMap<>();
		result.put("Result", Boolean.TRUE);
	}
}

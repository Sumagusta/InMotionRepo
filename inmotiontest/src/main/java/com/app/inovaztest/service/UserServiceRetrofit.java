package com.app.inovaztest.service;

import java.util.List;

import com.app.inovaztest.model.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserServiceRetrofit {
	
	@GET("/users")
	Call<List<User>> get();
}

package com.example.demo.service;

import com.example.demo.model.WeatherInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ApiService {
	WeatherInfo getweatherInfoByCity(String city) throws JsonMappingException, JsonProcessingException;
}

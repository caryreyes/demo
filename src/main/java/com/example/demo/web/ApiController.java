package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.WeatherInfo;
import com.example.demo.service.ApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class ApiController {

	@Autowired
	private ApiService service;

	@GetMapping(path = "/api/weather", produces = MediaType.APPLICATION_JSON_VALUE)
	public WeatherInfo getWeatherInfoByCity(@RequestParam String city) throws JsonMappingException, JsonProcessingException {
		return service.getweatherInfoByCity(city);
	}
}

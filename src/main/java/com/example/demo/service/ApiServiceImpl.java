package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.model.Weather;
import com.example.demo.model.Weather.WeatherBuilder;
import com.example.demo.model.WeatherInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApiServiceImpl implements ApiService {
	
	private static final String WEATHER_NODE = "weather";
	private static final String WEATHER_ICON = "icon";
	private static final String WEATHER_DESCRIPTION = "description";
	private static final String WEATHER_MAIN = "main";
	private static final String WEATHER_ID = "id";

	private static final String WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather";
	
	@Autowired
	private Environment env;

	private final RestTemplate restTemplate = new RestTemplate();
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public WeatherInfo getweatherInfoByCity(String city) throws JsonMappingException, JsonProcessingException {
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(WEATHER_API_URL)
		        .queryParam("q", city)
		        .queryParam("appid", env.getProperty("openweather.api.key"));
		
		ResponseEntity<String> responseText = restTemplate.getForEntity(builder.build().encode().toUri() , String.class);
		
		String body = responseText.getBody();
		System.out.println(body);
		
		return getWeatherInfo(mapper.readTree(body));
	}

	private WeatherInfo getWeatherInfo(JsonNode root) {

		WeatherInfo weatherInfo = new WeatherInfo();
		
		if (!root.isNull()) {
			JsonNode weatherNode = root.get(WEATHER_NODE);
			
			if (weatherNode.isArray()) {
				JsonNode objNode = weatherNode.get(0);

				WeatherBuilder weather = Weather.builder()
						.id(objNode.get(WEATHER_ID).asText(""))
						.main(objNode.get(WEATHER_MAIN).asText(""))
						.description(objNode.get(WEATHER_DESCRIPTION).asText(""))
						.icon(objNode.get(WEATHER_ICON).asText(""));

				weatherInfo.setWeather(weather.build());
			}
		}
		
		return weatherInfo;
	}

}

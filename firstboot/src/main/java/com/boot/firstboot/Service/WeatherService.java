package com.boot.firstboot.Service;

import com.boot.firstboot.entity.User;
import com.boot.firstboot.entity.WeatherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class WeatherService {

    private static final String ApiKey = "1b9bce4f4ceece81ca0f411c45852996";
//   @Value("{weather.api.key}")
//    private static String ApiKey;
//    private static final String API = "http://api.weatherstack.com/current?access_key=API_key&query=CITY";
@Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    public WeatherEntity getWeather(String city) {
        WeatherEntity weatherEntity = redisService.get("weather_of_" + city, WeatherEntity.class);
        if (weatherEntity != null) {
            System.out.println(weatherEntity);
            return weatherEntity;
        }
      else {
            String finalAPI = appCache.APP_CACHE.get("weather_api").replace("<city>", city).replace("<api_key>", ApiKey);

            try {
                ResponseEntity<WeatherEntity> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherEntity.class);

                WeatherEntity body = response.getBody();
                redisService.set("weather_of_" + city,body,300l);
                if (response.getStatusCode().is2xxSuccessful()) {


                    return body;
                }

                else {
                    System.err.println("API call failed with status code: " + response.getStatusCode());

                }
            } catch (Exception ex) { // Correctly referencing the exception
                ex.printStackTrace(); // Printing the stack trace of the exception
                System.err.println("Error occurred while fetching weather data: " + ex.getMessage());
            }
        }

        return null; // Returning null if there's an error
    }

    public WeatherEntity postWeather(String city) {
        String finalAPI = appCache.APP_CACHE.get("weather_api").replace("<city>", city).replace("<api_key>", ApiKey);

        String requestBody = "{ \"userName\": \"new user\", \"password\": \"new\" }";
        try {

        User user =     User.builder().userName("DK Universe").password("dk").build();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("header name","header value");
            HttpEntity<User> httpEntity  = new HttpEntity<>(user);
//            HttpEntity<String> httpEntity = new HttpEntity<>(requestBody);
            ResponseEntity<WeatherEntity> response = restTemplate.exchange(finalAPI, HttpMethod.POST, httpEntity, WeatherEntity.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                System.err.println("API call failed with status code: " + response.getStatusCode());
            }
        } catch (Exception ex) { // Correctly referencing the exception
            ex.printStackTrace(); // Printing the stack trace of the exception
            System.err.println("Error occurred while fetching weather data: " + ex.getMessage());
        }

        return null; // Returning null if there's an error
    }
}
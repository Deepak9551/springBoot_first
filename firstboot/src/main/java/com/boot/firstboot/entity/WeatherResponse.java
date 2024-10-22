package com.boot.firstboot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
public class WeatherResponse {
    private Current current;

    @Data
    @NoArgsConstructor
    public class Current {


        private int temperature;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;
        private int feelslike;
//    public class Location{
//        public String name;
//        public String country;
//        public String region;
//
//        public String timezone_id;
//        public String localtime;
//        public int localtime_epoch;
//        public String utc_offset;
//    }

//    public class Request{
//        public String type;
//        public String query;
//        public String language;
//        public String unit;
//    }

//    public class Root{
//        public Request request;
//        public Location location;
//        public Current current;
//    }


    }
}
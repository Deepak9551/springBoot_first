package com.boot.firstboot.Service;

import com.boot.firstboot.Repository.ConfigDataRepo;
import com.boot.firstboot.entity.ConfigData;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    enum keys{
        WEATHER_API
    }

    @Autowired
    private ConfigDataRepo configDataRepo ;

    public Map<String ,String> APP_CACHE ;

    @PostConstruct
    public void init(){
       APP_CACHE =  new HashMap<>();
        List<ConfigData> all = configDataRepo.findAll();
    for(ConfigData configData : all){
        APP_CACHE.put(configData.getKey(),configData.getValue());
    }

    }
}

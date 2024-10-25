package com.boot.firstboot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("config_cache")
public class ConfigData {
private String key;
private String value;
}

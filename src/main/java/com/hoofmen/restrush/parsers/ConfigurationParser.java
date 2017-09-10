package com.hoofmen.restrush.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoofmen.restrush.config.model.Configuration;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("configurationParser")
public class ConfigurationParser {

    public Configuration getConfiguration(String jsonString) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        Configuration configuration = mapper.readValue(jsonString, Configuration.class);

        JsonNode rootNode = mapper.readTree(jsonString);
        JsonNode entityNode = rootNode.path("entity");
        configuration.setEntity(entityNode.toString());

        return configuration;
    }
}

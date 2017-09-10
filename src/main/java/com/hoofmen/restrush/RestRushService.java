package com.hoofmen.restrush;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Osman H. on 9/8/17.
 */

@Service ("rrService")
public class RestRushService {

    public void printJSON(String path) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get(path));
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(jsonData);
        JsonNode projectNameNode = rootNode.path("project-name");
        JsonNode descriptionNode = rootNode.path("description");
        System.out.println("project-name: " + projectNameNode.asText());
        System.out.println("description: " + descriptionNode.asText());
    }

    public String getValue(String val){
        return "property: " + val;
    }


    public void printUsage(){
        System.out.println("");
        System.out.println(" Welcome to REST RUSH :()");
        System.out.println(" ------------------------");
        System.out.println(" Usage:");
        System.out.println("\tjava -jar restrush.jar <json file with the configuration>");
        System.out.println("");
    }
}

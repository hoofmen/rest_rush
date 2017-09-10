package com.hoofmen.restrush;

import com.hoofmen.restrush.config.model.Configuration;
import com.hoofmen.restrush.config.model.Entity;
import com.hoofmen.restrush.parsers.ConfigurationParser;
import com.hoofmen.restrush.parsers.EntityParser;
import com.hoofmen.restrush.writers.JavaPojoWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Osman H. on 9/8/17.
 */

@Service ("rrService")
public class RestRushService {

    @Autowired
    private ConfigurationParser configurationParser;
    @Autowired
    private EntityParser entityParser;

    @Autowired
    private JavaPojoWriter javaPojoWriter;

    public void readConfiguration(String path) throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get(path));
        Configuration configuration = configurationParser.getConfiguration(new String(jsonData));
        Entity entity = entityParser.parseEntity(configuration.getEntity());

        // Send the details of the entity to be written into a Java file.
        javaPojoWriter.writeToFile(entity);
    }

    public void printUsage(){
        System.out.println("");
        System.out.println(" Welcome to REST RUSH :()");
        System.out.println(" ------------------------");
        System.out.println(" Usage:");
        System.out.println("\tjava -jar rest-rush.jar <json file with the configuration>");
        System.out.println("");
    }
}

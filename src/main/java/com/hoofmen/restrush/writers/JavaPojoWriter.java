package com.hoofmen.restrush.writers;

import com.hoofmen.restrush.config.model.Entity;
import com.hoofmen.restrush.utils.Constants;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Service("javaPojoWriter")
public class JavaPojoWriter {


    public void writeToFile(Entity entity) throws IOException {
        FileWriter fileWriter = new FileWriter(entity.getClassName() + Constants.JAVA_FILE_EXTENSION);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.printf(Constants.PACKAGE_LINE,entity.getPackageName());
        printWriter.printf(Constants.CLASS_LINE,entity.getClassName());

        // print the attribute section of the class
        for (Map.Entry<String,Entity> attribute : entity.getAttributesMap().entrySet()){
            printWriter.printf(Constants.ATTRIBUTE_LINE,attribute.getValue().getClassName(), attribute.getKey());
        }
        printWriter.print(Constants.NEW_LINE);

        // print the setter/getter of the class
        for (Map.Entry<String,Entity> attribute : entity.getAttributesMap().entrySet()){
            printWriter.printf(Constants.SETTER_METHOD_LINE, attribute.getKey(), attribute.getValue().getClassName(), attribute.getKey());
            printWriter.printf(Constants.SET_ACTION_LINE, attribute.getKey(), attribute.getKey());
            printWriter.print(Constants.TAB + Constants.CLOSE_CURLY_BRACKET);

            printWriter.printf(Constants.GETTER_METHOD_LINE,attribute.getValue().getClassName(), attribute.getKey());
            printWriter.printf(Constants.GET_ACTION_LINE, attribute.getKey());
            printWriter.print(Constants.TAB + Constants.CLOSE_CURLY_BRACKET);
        }
        printWriter.print(Constants.CLOSE_CURLY_BRACKET);
        printWriter.close();
    }
}

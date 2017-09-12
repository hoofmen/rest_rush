package com.hoofmen.restrush.parsers;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.hoofmen.restrush.config.model.Entity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service("entityParser")
public class EntityParser {

    public Entity parseEntity(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(jsonString);
        Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.fields();

        // We only care about the first node, from which we get the 'entity name' and 'entity body'
        Map.Entry<String,JsonNode> field = fieldsIterator.next();
        String entityName = field.getKey();
        JsonNode entityBodyNode = field.getValue();
        // Parse the rest of the entity body to get the attributes in a Map
        Map<String, Entity> entityAttributesMap = this.getEntityAttributes(entityName, entityBodyNode);

        Entity entity = new Entity();
        entity.setClassName(entityName);
        entity.setAttributesMap(entityAttributesMap);

        return entity;
    }

    private Map<String, Entity> getEntityAttributes(String entityName, JsonNode entityBodyNode){
        Map<String, Entity> attributesMap = new HashMap<>();
        Iterator<Map.Entry<String,JsonNode>> fieldsIterator = entityBodyNode.fields();
        while (fieldsIterator.hasNext()) {
            Map.Entry<String,JsonNode> field = fieldsIterator.next();

            String attributeKey = field.getKey();
            Entity entity = this.getNodeType(entityName, field.getValue());
            attributesMap.put(attributeKey,entity);
        }

        return attributesMap;
    }

    private Entity getNodeType(String entityName, JsonNode node){
        Entity entity = new Entity();
        if (node.getNodeType() == JsonNodeType.STRING) {
            entity.setClassName("String");
        }else if (node.getNodeType() == JsonNodeType.NUMBER){
            entity.setClassName(this.getNumberType(node));
        }else if (node.getNodeType() == JsonNodeType.ARRAY){
            entity.setClassName(this.getArrayType(entityName, node));
        }else if (node.getNodeType() == JsonNodeType.OBJECT){
            entity.setClassName(entityName);
        }else if (node.getNodeType() == JsonNodeType.BOOLEAN){
            entity.setClassName("boolean");
        }else if (node.getNodeType() == JsonNodeType.BINARY){

        }else if (node.getNodeType() == JsonNodeType.MISSING){

        }else if (node.getNodeType() == JsonNodeType.NULL){

        }else if (node.getNodeType() == JsonNodeType.POJO){

        }
        return entity;
    }

    private String getNumberType(JsonNode node){
        try{
            Integer.valueOf(node.asText());
            return "Integer";
        }catch(NumberFormatException ex){}
        try{
            Double.valueOf(node.asText());
            return "Double";
        }catch(NumberFormatException ex){}
        return "NUMBER";
    }

    private String getArrayType(String entityName, JsonNode node){
        String type = "List<" + entityName +">";


        return type;
    }
}

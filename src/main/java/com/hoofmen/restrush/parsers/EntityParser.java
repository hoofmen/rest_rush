package com.hoofmen.restrush.parsers;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        Map<String, String> entityAttributesMap = this.getEntityAttributes(entityBodyNode);

        Entity entity = new Entity();
        entity.setClassName(entityName);
        entity.setAttributesMap(entityAttributesMap);

        return entity;
    }

    private Map<String, String> getEntityAttributes(JsonNode entityBodyNode){
        Map<String, String> attributesMap = new HashMap<>();
        Iterator<Map.Entry<String,JsonNode>> fieldsIterator = entityBodyNode.fields();
        while (fieldsIterator.hasNext()) {
            Map.Entry<String,JsonNode> field = fieldsIterator.next();
            String attributeKey = field.getKey();
            String attributeValue = field.getValue().getNodeType().name();
            attributesMap.put(attributeKey,attributeValue);
        }

        return attributesMap;
    }
}

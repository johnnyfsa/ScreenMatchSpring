package br.com.biscoithor.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverter implements IDataConversible{
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getDados(String json, Class<T> dataClass) {
        try {
            return mapper.readValue(json, dataClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

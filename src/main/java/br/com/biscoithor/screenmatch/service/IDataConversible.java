package br.com.biscoithor.screenmatch.service;

public interface IDataConversible {
    <T> T getDados(String json, Class<T> dataClass);
}

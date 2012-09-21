package de.javamagazin.activiti.spring.persistence.converter;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class BeanConverter implements Converter<Object> {

    private static BeanConverter INSTANCE = new BeanConverter();

    private JSONSerializer jsonSerializer = new JSONSerializer();

    private JSONDeserializer<Object> jsonDeserializer = new JSONDeserializer<Object>();

    public static BeanConverter getInstance() {
        return INSTANCE;
    }

    private BeanConverter() {
    }

    @Override
    public String serialize(Object o) {
        return o == null ? null : jsonSerializer.serialize(o);
    }

    @Override
    public Object deserialize(String s) {
        return s == null ? null : jsonDeserializer.deserialize(s);
    }

}

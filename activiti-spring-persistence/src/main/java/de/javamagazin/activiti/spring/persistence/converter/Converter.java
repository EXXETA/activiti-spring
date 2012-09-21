package de.javamagazin.activiti.spring.persistence.converter;

public interface Converter<T> {

    String serialize(T t);

    T deserialize(String s);

}

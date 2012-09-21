package de.javamagazin.activiti.spring.persistence.converter;

public class LongConverter implements Converter<Long> {

    private static LongConverter INSTANCE = new LongConverter();

    public static LongConverter getInstance() {
        return INSTANCE;
    }

    private LongConverter() {
    }

    @Override
    public String serialize(Long l) {
        return l == null ? null : Long.toString(l);
    }

    @Override
    public Long deserialize(String s) {
        return s == null ? null : Long.parseLong(s);
    }
}

package utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();
    public static <T> T readJsonFile(String filePath, Class<T> clazz) throws IOException {

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


          return mapper.readValue(new File(filePath), clazz);
    }

}


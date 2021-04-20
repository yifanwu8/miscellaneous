package com.yifanwu.algo.coinbase;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * {
 * 	"name": "yifan wu",
 * 	"year": 2021,
 * 	"jobs": [
 *                {
 * 			"name": "1st",
 * 			"years": 4
 *        },
 *        {
 * 			"name": "2nd",
 * 			"year": 2
 *        },
 *        {
 * 			"name": "3rd",
 * 			"year": 3
 *        }
 * 	]
 * }
 *
 * @author Yifan.Wu on 4/7/2021
 */
public class JsonTest {

    @Test
    public void deserializeJson() throws IOException {
        String json = "" +
                "{" +
                "\"name\": \"yifan wu\"," +
                "\"year\": 2021," +
                "\"jobs\": [" +
                "{" +
                "\"name\": \"1st\"," +
                "\"years\": 4" +
                "}," +
                "{" +
                "\"name\": \"2nd\"," +
                "\"years\": 2" +
                "}," +
                "{" +
                "\"name\": \"3rd\"," +
                "\"years\": 3" +
                "}" +
                "]" +
                "}";

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = mapper.readValue(json, Map.class);
        System.out.println(map);
    }
}

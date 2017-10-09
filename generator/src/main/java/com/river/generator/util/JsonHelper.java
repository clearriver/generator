/**
 * Project Name:generator
 * File Name:JsonHelper.java
 * Package Name:com.river.generator.util
 * Date:2017年10月06日下午3:44:17
 *
*/

package com.river.generator.util;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * ClassName:JsonHelper <br/>
 * Function: Json工具类. <br/>
 * Date: 2017年10月06日 下午3:44:17 <br/>
 * 
 * @author wuqh
 * @version
 * @since JDK 1.6
 * @see
 */
public class JsonHelper {

    private static Logger       logger = LogManager.getLogger(JsonHelper.class);

    private static ObjectMapper mapper = new ObjectMapper();

    public static String getJSONString(Object obj) {
        String result = "";
        try {
            result = mapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            logger.error("", e);
        } catch (JsonMappingException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.error("", e);
        }
        return result;
    }

    public static Object getObjectByJSON(String jsonStr) {
        Object obj = null;
        try {
            obj = mapper.readValue(jsonStr, Object.class);
        } catch (JsonParseException e) {
            logger.error("", e);
        } catch (JsonMappingException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.error("", e);
        }
        return obj;
    }
}

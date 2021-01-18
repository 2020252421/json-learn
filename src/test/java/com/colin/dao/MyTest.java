package com.colin.dao;


import com.alibaba.fastjson.JSON;
import com.colin.util.JSONUtil;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;


public class MyTest {


    @Test
    public void test() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");

        String json = JSON.toJSONString(map);//map转json
        System.out.println(json);
    }

    @Test
    public void test2() {
        String strData = "{\"name\":\"Annie\",\"age\":\"18\"}";
        Map<String, Object> result = JSONUtil.parseJSON2Map(strData);//json转map

        System.out.println(result);
    }

}

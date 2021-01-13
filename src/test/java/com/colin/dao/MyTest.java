package com.colin.dao;


import com.colin.mapper.ScoreMapper;
import com.colin.util.JSONUtil;
import com.colin.util.MybatisUtil;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MyTest {




    @Test
    public void test() {
        String strData = "{\"name\":\"Annie\"},{\"age\":\"18\"}";
        Map<String, Object> score = JSONUtil.parseJSON2Map(strData);
        System.out.println(score);
    }

    @Test
    public void insertScore() {
        String strData = "[{\"name\":\"Annie\"},{\"age\":\"18\"}]";
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
        Map<String, Object> score = JSONUtil.parseJSON2Map(strData);
        mapper.insertScore(score);
        sqlSession.close();
    }

}

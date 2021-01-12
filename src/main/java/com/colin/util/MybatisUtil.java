package com.colin.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        String Resource = "mybatis-config.xml";
        try {
            //1.读取配置文件
            InputStream in = Resources.getResourceAsStream(Resource);
            //2.创建SqlSessionFactory工厂
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            //通过该文件流创建SQL sessionfactory 以便之后获得SQL session对象可以操作数据库
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}

package org.solvd.company.persistence.impl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MybatisUtil {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            Properties properties = new Properties();
            InputStream propStream = Resources.getResourceAsStream("db.properties");
            properties.load(propStream);
            InputStream configStream = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configStream, properties);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }

}

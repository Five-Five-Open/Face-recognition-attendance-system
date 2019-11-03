package com.example.test.dao;

import com.example.test.form.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository("helloDao")
public class HelloDao implements IHelloDao {

    @Resource
    private JdbcTemplate jdbcTemplate;
    public List<Test> list(){
        String sql="SELECT*FROM test";
        List<Test> testList=jdbcTemplate.query(sql, new RowMapper<Test>() {
            Test test=null;
            @Override
            public Test mapRow(ResultSet rs, int i) throws SQLException {
                test=new Test();

                test.setName(rs.getString("name"));
                test.setAge(rs.getInt("age"));
                return test;
            }
        });

        return testList;
    }
}

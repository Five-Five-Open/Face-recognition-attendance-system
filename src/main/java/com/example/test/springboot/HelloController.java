package com.example.test.springboot;
import com.example.test.form.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import com.example.test.service.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/home")
public class HelloController {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IHelloService helloService;
    @RequestMapping("hello")
    public String hello(HttpServletRequest request, HttpServletResponse response){
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
        request.setAttribute("now",testList);

        String str=helloService.hello();
        return str;
    }

}

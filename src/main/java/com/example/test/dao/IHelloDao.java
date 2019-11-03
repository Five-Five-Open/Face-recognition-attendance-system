package com.example.test.dao;

import com.example.test.form.Test;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IHelloDao {
    List<Test> list();
}

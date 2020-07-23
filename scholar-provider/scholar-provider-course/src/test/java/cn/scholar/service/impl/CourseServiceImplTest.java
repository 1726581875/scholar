package cn.scholar.service.impl;

import cn.scholar.service.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CourseServiceImplTest{

    @Autowired
    private CourseService courseService;


    @Test
    public void testFindById() {
    //  Course course = courseService.findById(1);
      //  System.out.println(course);
    }

    @Test
    public void testFindByName() {
        courseService.findByName("计算机组成原理").forEach(System.out::println);

    }

    @Test
    public void testFindAll() {
      //  courseService.findByPage(4).forEach(System.out::println);
    }

    public void testUpdateSelection() {
    }

    public void testDeleteById() {
    }

    @Test
    public void testInsert() {



    }





}
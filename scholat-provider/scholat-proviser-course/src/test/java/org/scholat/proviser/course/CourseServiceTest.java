package org.scholat.proviser.course;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.scholat.proviser.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CourseServiceTest {

	@Autowired
	private CourseService courseService;
	
	@Test
	public void findAll(){
		courseService.findAll().forEach(System.out::println);
	}
	
	
	
}

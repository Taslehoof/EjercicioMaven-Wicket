package cservicio;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public interface IServicio {

	ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class);
	
}

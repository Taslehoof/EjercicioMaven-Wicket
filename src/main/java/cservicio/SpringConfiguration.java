package cservicio;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan({ "crepositorio" })
@Configuration
public class SpringConfiguration {
	@Bean(name = "pmf")
	public PersistenceManagerFactory pmf() {
		return JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		// return JDOHelper.getPersistenceManagerFactory("PostgreSQL");
	}

}


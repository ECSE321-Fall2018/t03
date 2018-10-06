package ca.mcgill.ecse321.ridesharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



//@EnableAutoConfiguration
//@SpringBootApplication

@EnableAutoConfiguration(exclude = {JndiConnectionFactoryAutoConfiguration.class,DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})

@RestController
public class IntegratedSpringApplication extends SpringBootServletInitializer{
 
     @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(IntegratedSpringApplication.class);
        }
 
     
    public static void main(String[] args) {
    	
        SpringApplication.run(IntegratedSpringApplication.class, args);
      
       
    	}
        @RequestMapping("/")
    	public String greeting() {
    		return "<h1>Welcome to the ride sharing app!</h1>";
    }
}
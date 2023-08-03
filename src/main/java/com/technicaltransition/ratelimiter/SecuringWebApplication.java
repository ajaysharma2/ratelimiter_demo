package com.technicaltransition.ratelimiter;

import com.technicaltransition.ratelimiter.modal.Customer;
import com.technicaltransition.ratelimiter.modal.MessageData;
import com.technicaltransition.ratelimiter.repository.CustomerRepository;
import com.technicaltransition.ratelimiter.repository.MessageRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class SecuringWebApplication {

    private static final Logger log = LoggerFactory.getLogger(SecuringWebApplication.class);

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(SecuringWebApplication.class, args);
    }

    @Bean
    public RateLimitterDemo rateLimitterDemo() {
        return new RateLimitterDemo();
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp_views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository, MessageRepo messageRepo) {
        return (args) -> {
            // save a few customers
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Customer customer = repository.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            //  log.info(bauer.toString());
            // }
            log.info("");

            log.info("Adding dummy messages...");
            messageRepo.save(new MessageData("dummy message... 1"));
            messageRepo.save(new MessageData("dummy message... 2"));
            messageRepo.save(new MessageData("dummy message... 3"));
            messageRepo.save(new MessageData("dummy message... 4"));
            messageRepo.save(new MessageData("dummy message... 5"));

            log.info("All dummy messages saved...");
            messageRepo.findAll().forEach(messageData -> log.info(messageData.toString()));
        };
    }

}

package com.dvhlspringboot.testspringboot.Database;



import com.dvhlspringboot.testspringboot.Repositories.UserRepository;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Db {
    //private static final Logger logger=LoggerFactory.getLogger(Db.class);
    @Bean
    CommandLineRunner initDB(UserRepository userRepository){
        
        return new CommandLineRunner() {

            @Override
            public void run(String... args) throws Exception {
                // User u1=new User("dvhl", "123");
                // User u2=new User("admin", "admin123");
                // System.out.print("add test data:" +userRepository.save(u1));
                // System.out.print("add test data:" +userRepository.save(u2));
            }
            
        };
        
    }
}

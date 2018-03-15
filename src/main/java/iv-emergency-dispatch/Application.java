package ivemergencydispatch;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.social.TwitterAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
@ComponentScan
@EnableAutoConfiguration(exclude={TwitterAutoConfiguration.class})
public class Application {
  @Bean
  public ThreadPoolTaskExecutor taskExecutor(){
      ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
      pool.setCorePoolSize(1);
      pool.setMaxPoolSize(10);
      pool.setWaitForTasksToCompleteOnShutdown(true);
      return pool;
  }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
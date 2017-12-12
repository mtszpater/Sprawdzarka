package uwr.onlinejudge.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ServerApplication  extends SpringBootServletInitializer {


    //todo maybe can remove it?
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServerApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);

    }
}

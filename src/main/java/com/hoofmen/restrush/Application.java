package com.hoofmen.restrush;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;

/**
 * Created by Osman H. on 9/7/17.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private RestRushService restRushService;

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0) {
            restRushService.printJSON(args[0].toString());
        } else {
            restRushService.printUsage();
        }

        exit(0);
    }
}

package org.assignment.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    @Override
    public void run(String... args) {

        System.out.println("Started in Bootstrap");
        Thread thread = new Thread(new DataLoader());
        thread.start();


    }
}

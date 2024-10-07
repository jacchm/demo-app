package org.aszjch.demoapp.extendwith;

import org.aszjch.demoapp.DemoAppApplication;
import org.springframework.boot.SpringApplication;

public class TestDemoAppApplication {

    public static void main(final String[] args) {
        SpringApplication.from(DemoAppApplication::main)
                .run(args);
    }

}

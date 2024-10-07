package org.aszjch.demoapp.extendwith;

import org.aszjch.demoapp.DemoAppApplication;
import org.aszjch.demoapp.extendwith.tcconfig.KafkaContainerExtension;
import org.aszjch.demoapp.extendwith.tcconfig.MinIOContainerExtension;
import org.aszjch.demoapp.extendwith.tcconfig.PostgresContainerExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith({KafkaContainerExtension.class, MinIOContainerExtension.class, PostgresContainerExtension.class})
@SpringBootTest(classes = DemoAppApplication.class)
class DemoAppApplicationTests {

    @Test
    void contextLoads() {
    }

}

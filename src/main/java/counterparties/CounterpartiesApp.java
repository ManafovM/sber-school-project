package counterparties;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class CounterpartiesApp {
    public static void main(String[] args) {
        SpringApplication.run(CounterpartiesApp.class, args);
    }
}

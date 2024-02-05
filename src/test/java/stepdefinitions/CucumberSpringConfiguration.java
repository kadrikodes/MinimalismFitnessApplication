package stepdefinitions;

import com.health.minimalismfitnessapp.backend.MinimalismFitnessAppApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest
@ContextConfiguration(classes = MinimalismFitnessAppApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CucumberSpringConfiguration {
}

package org.canadian.tire.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.canadian.tire.spring.SpringConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = SpringConfig.class)
@CucumberContextConfiguration
@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"org.canadian.tire.cucumber"},
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})
public class RunSpringCucumberTest extends AbstractTestNGCucumberTests {
}

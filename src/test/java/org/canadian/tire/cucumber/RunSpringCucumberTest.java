package org.canadian.tire.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@CucumberContextConfiguration
@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"org.canadian.tire.cucumber"},
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})
public class RunSpringCucumberTest extends AbstractTestNGCucumberTests {
}

package org.canadian.tire.util.listener;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;

import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

@Slf4j
public class StepListener implements StepLifecycleListener {

    @Override
    public void afterStepStart(StepResult result) {
        log.info("Step started: {}", result.getName());
    }

    @Override
    public void afterStepUpdate(StepResult result) {
        if (hasWebDriverStarted()) {
            takeScreenshot(result.getName());
        }
    }

    @Attachment(value = "{screenshotName}", type = "image/png")
    private byte[] takeScreenshot(final String screenshotName) {
        try {
            return Selenide.screenshot(OutputType.BYTES);
        } catch (NoSuchWindowException e) {
            log.warn("Screenshot was aborted due to current tab is closed");
            return new byte[0];
        }
    }
}

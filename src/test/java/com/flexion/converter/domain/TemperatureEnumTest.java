package com.flexion.converter.domain;

import com.flexion.converter.usecase.TemperatureService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
public class TemperatureEnumTest {
    @Autowired
    private TemperatureService temperatureService;
    @Test
    public void correctTemperatureConversion() {
        var result = Temperatures.FAHRENHEIT.convert(84.2, Temperatures.RANKINE);
        Assertions.assertNotEquals(result, 543.8);
    }
    @Test
    public void getTempFromString() {
        var getTempFromString = Temperatures.getTemperatures("Fahrenheit");
        Assertions.assertEquals(getTempFromString, Temperatures.FAHRENHEIT);
    }
    @Test
    public void roundedToTenth() {
        var getTempFromString = Temperatures.getTemperatures("Fahrenheit");
        var authoritativeAnswer = getTempFromString.convert(84.2, Temperatures.RANKINE);
        var roundedToTenthResult = temperatureService.roundToTenths(authoritativeAnswer);

        Assertions.assertEquals(roundedToTenthResult, 543.9);

    }



}

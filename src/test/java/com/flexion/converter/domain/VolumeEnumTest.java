package com.flexion.converter.domain;

import com.flexion.converter.usecase.TemperatureService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


    @SpringBootTest
    @Slf4j
    public class VolumeEnumTest  {
        @Autowired
        private TemperatureService temperatureService;
        @Test
        public void correctVolumeConversion() {
            var result = Volumes.CUPS.convert(25.6, Volumes.LITERS);
            Assertions.assertEquals(result, 6.0566528);
        }
        @Test
        public void getVolumeFromString() {
            var getVolumeFromString = Volumes.getVolumes("cubic-inches");
            Assertions.assertNotEquals(getVolumeFromString, Volumes.CUPS);
        }
        @Test
        public void roundedToTenth() {
            var authoritativeAnswer = Volumes.CUPS.convert(25.6, Volumes.LITERS);
            var roundedToTenthResult = temperatureService.roundToTenths(authoritativeAnswer);

            Assertions.assertEquals(roundedToTenthResult, 6.1);

        }
}

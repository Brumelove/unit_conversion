package com.flexion.converter.usecase;

import com.flexion.converter.domain.GenericResponseBody;
import com.flexion.converter.domain.TeacherRequest;
import com.flexion.converter.domain.Volumes;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class VolumeServiceTest {
    @Autowired
    private VolumeService volumeService;

    @Test
    public void result_should_be_invalid() {

        var teacherRequest = new TeacherRequest();
        teacherRequest.setInputNumericalValue(20.3);
        teacherRequest.setInputUnitOfMeasure("gallons");
        teacherRequest.setTargetUnitOfMeasure("kelvin");
        teacherRequest.setStudentResponse("40.6");

        var response = volumeService.effectVolumeConversions(teacherRequest);
        var invalid = GenericResponseBody.builder().response("invalid").build();
        Assertions.assertEquals(response, invalid);
    }

    @Test
    public void result_should_be_incorrect() {

        var teacherRequest = new TeacherRequest();
        teacherRequest.setInputNumericalValue(6.5);
        teacherRequest.setInputUnitOfMeasure("gallons");
        teacherRequest.setTargetUnitOfMeasure("cups");
        teacherRequest.setStudentResponse("dog");

        var response = volumeService.effectVolumeConversions(teacherRequest);
        var incorrect = GenericResponseBody.builder().response("incorrect").build();
        Assertions.assertEquals(response, incorrect);
    }

    @Test
    public void result_should_be_correct() {

        var teacherRequest = new TeacherRequest();
        teacherRequest.setInputNumericalValue(25.6);
        teacherRequest.setInputUnitOfMeasure("cups");
        teacherRequest.setTargetUnitOfMeasure("liters");
        teacherRequest.setStudentResponse("6.1");

        var response = volumeService.effectVolumeConversions(teacherRequest);
        var correct = GenericResponseBody.builder().response("correct").build();
        Assertions.assertEquals(response, correct);
    }

    @Test()
    public void numberFormatException() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Volumes.TABLESPOONS.convert(Double.parseDouble("boy"), Volumes.GALLONS);
        });

    }

}

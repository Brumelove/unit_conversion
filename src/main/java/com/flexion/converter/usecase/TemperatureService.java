package com.flexion.converter.usecase;

import com.flexion.converter.domain.GenericResponseBody;
import com.flexion.converter.domain.TeacherRequest;
import com.flexion.converter.domain.Temperatures;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TemperatureService {

    public GenericResponseBody effectTemperatureConversion(TeacherRequest teacherRequest) {
        Temperatures targetTemp;
        Temperatures getTemp;

        try {
            getTemp = Temperatures.getTemperatures(teacherRequest.getInputUnitOfMeasure());


            targetTemp = Temperatures.getTemperatures(teacherRequest.getTargetUnitOfMeasure());
        } catch (NoSuchElementException exception) {
            return GenericResponseBody.builder().response("invalid").build();
        }
        try {
            val result = getTemp.convert(teacherRequest.getInputNumericalValue(), targetTemp);
            val authoritativeAnswer = roundToTenths(result);
            val studentResponse = roundToTenths(Double.parseDouble(teacherRequest.getStudentResponse()));

            log.info("Teacher's temperature result ==> " + authoritativeAnswer);
            log.info("Student temperature result ==> " + studentResponse);


            if (!(authoritativeAnswer == (studentResponse))) {
                return GenericResponseBody.builder().response("incorrect").build();
            }

        }
        catch (NumberFormatException ne) {
            return GenericResponseBody.builder().response("incorrect").build();

        }

        return GenericResponseBody.builder().response("correct").build();

    }
    public  List<String> getAllTemperature() {
        return EnumSet.allOf(Temperatures.class).stream().map(Enum::name).collect(Collectors.toList());
    }
    public double roundToTenths(double value) {
        String str = String.format("%1.1f", value);
        return Double.parseDouble(str);
    }

}

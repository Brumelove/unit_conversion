package com.flexion.converter.usecase;

import com.flexion.converter.domain.GenericResponseBody;
import com.flexion.converter.domain.TeacherRequest;
import com.flexion.converter.domain.Temperatures;
import com.flexion.converter.domain.Volumes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class VolumeService {
    private TemperatureService temperatureService;
    public GenericResponseBody effectVolumeConversions(TeacherRequest teacherRequest) {
        Volumes targetVolume;
        Volumes getVolumeFromString;


        try {
            getVolumeFromString = Volumes.getVolumes(teacherRequest.getInputUnitOfMeasure());


            targetVolume = Volumes.getVolumes(teacherRequest.getTargetUnitOfMeasure());
        } catch (NoSuchElementException exception) {
            return GenericResponseBody.builder().response("invalid").build();
        }
        try {
            val result = getVolumeFromString.convert(teacherRequest.getInputNumericalValue(), targetVolume);
            val authoritativeAnswer = temperatureService.roundToTenths(result);
            val studentResponse = temperatureService.roundToTenths(Double.parseDouble(teacherRequest.getStudentResponse()));

            log.info("Teacher's volume result ==> " + authoritativeAnswer);
            log.info("Student volume result ==> " + studentResponse);



            if (!(authoritativeAnswer == (studentResponse))) {
                return GenericResponseBody.builder().response("incorrect").build();
            }

        }
        catch (NumberFormatException ne) {
            return GenericResponseBody.builder().response("incorrect").build();

        }

        return GenericResponseBody.builder().response("correct").build();

    }
    public  List<String> getAllVolumes() {
        return EnumSet.allOf(Volumes.class).stream().map(Enum::name).collect(Collectors.toList());
    }

}

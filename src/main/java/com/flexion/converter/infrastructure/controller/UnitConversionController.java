package com.flexion.converter.infrastructure.controller;

import com.flexion.converter.domain.GenericResponseBody;
import com.flexion.converter.domain.TeacherRequest;
import com.flexion.converter.usecase.TemperatureService;
import com.flexion.converter.usecase.VolumeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UnitConversionController {
    private TemperatureService temperatureService;
    private VolumeService volumeService;

    @PostMapping("/convert/temperature")
    public ResponseEntity<GenericResponseBody> temperatureWorksheet(@Valid @RequestBody TeacherRequest teacherRequest){
        return new ResponseEntity<>(temperatureService.effectTemperatureConversion(teacherRequest) , HttpStatus.CREATED);
    }
    @PostMapping("/get/temperature/list")
    public ResponseEntity<?> temperatureList(){
        return new ResponseEntity<>(temperatureService.getAllTemperature() , HttpStatus.CREATED);
    }
    @PostMapping("/convert/volume")
    public ResponseEntity<GenericResponseBody> volumeWorksheet(@Valid @RequestBody TeacherRequest teacherRequest){
        return new ResponseEntity<>(volumeService.effectVolumeConversions(teacherRequest) , HttpStatus.CREATED);
    }
    @PostMapping("/get/volume/list")
    public ResponseEntity<?> volumeList(){
        return new ResponseEntity<>(volumeService.getAllVolumes() , HttpStatus.CREATED);
    }
}

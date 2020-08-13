package com.flexion.converter.domain;

import lombok.Data;

@Data
public class TeacherRequest {
private Double inputNumericalValue;
private String inputUnitOfMeasure;
private String targetUnitOfMeasure;
private String studentResponse;

}

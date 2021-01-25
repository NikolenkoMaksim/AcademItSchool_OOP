package ru.oop.nikolenko.temperature_converter.model;

import java.util.function.UnaryOperator;

public class TwoUnitsConverter {
    private final String nameOfFirstUnit;
    private final String nameOfSecondUnit;
    private final UnaryOperator<Double> operatorOfConversionFromFirstToSecondUnit;
    private final UnaryOperator<Double> operatorOfConversionFromSecondToFirstUnit;

    public TwoUnitsConverter(String nameOfFirstUnit, String nameOfSecondUnit, UnaryOperator<Double> operatorOfConversionFromFirstToSecondUnit, UnaryOperator<Double> convertDataFromFirstToSecondUnit1) {
        this.nameOfFirstUnit = nameOfFirstUnit;
        this.nameOfSecondUnit = nameOfSecondUnit;
        this.operatorOfConversionFromFirstToSecondUnit = operatorOfConversionFromFirstToSecondUnit;
        this.operatorOfConversionFromSecondToFirstUnit = convertDataFromFirstToSecondUnit1;
    }

    public String getNameOfFirstUnit() {
        return nameOfFirstUnit;
    }

    public String getNameOfSecondUnit() {
        return nameOfSecondUnit;
    }

    public Double convertDataFromFirstToSecondUnit(Double data) {
        return operatorOfConversionFromFirstToSecondUnit.apply(data);
    }

    public Double convertDataFromSecondToFirstUnit(Double data) {
        return operatorOfConversionFromSecondToFirstUnit.apply(data);
    }
}

package lapr.project.model;

import lapr.project.utils.Pair;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    /**
     * To test the constructor.
     */
    Calculator calculator;
    @BeforeEach
    void setUp() {
        calculator=new Calculator();
    }

    /**
     * Testing the calculateDistance method.
     */
    @Test
    void calculateDistance() {
        DecimalFormat df = new DecimalFormat("#");

        double expected=16.965440417018367;
        double result = Calculator.calculateDistance(42.7698,-66.9759,42.92236,-66.97243);
        assertEquals(df.format(expected),df.format(result));
    }

    @Test
    void calculatePressure() {
        Ship ship1=new Ship(123456789, "IMO1234567", "ABCD");
        double mass=201384.0;
        ship1.setWidth(32);
        ship1.setLength(294);
        ship1.setGeometricCode(4);
        double result = Calculator.calculatePressure(ship1.calculateShipArea(),mass);
        double expected=209.91734732142857;
        assertEquals(result,expected);
    }

    @Test
    void calculateAreaGeometric() {
        double x=30;
        double z=10;
        double result1=Calculator.calculateAreaGeometric(x,z,1);
        double expected1=(x*z)/2;
        double result2=Calculator.calculateAreaGeometric(z,z,2);
        double expected2=(Math.pow((z/2),2)*Math.PI)/2;
        double result3=Calculator.calculateAreaGeometric(x,z,3);
        double expected3=(((x*z)/2)/2)*3;
        double result4=Calculator.calculateAreaGeometric(x,z,4);
        double expected4=x*z;

        Exception exception= Assertions.assertThrows(NotImplementedException.class,()->Calculator.calculateAreaGeometric(z,z,0));
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
        assertEquals(expected3,result3);
        assertEquals(expected4,result4);
        assertEquals(exception.getMessage(),"The geometric number introduced is not implemented");
    }

    @Test
    void calculateGeometricCM() {
        double x=30;
        double z=10;
        Pair<Double,Double> result1=Calculator.calculateGeometricCM(x,z,1,0,0);
        Pair<Double,Double> expected1=new Pair<>(x/3,z/2);
        Pair<Double,Double> result2=Calculator.calculateGeometricCM(0,x,2,x/2,0);
        Pair<Double,Double> expected2=new Pair<>(21.366197723675814,x/2);
        Pair<Double,Double> result3=Calculator.calculateGeometricCM(x,z,3,x/2,0);
        Pair<Double,Double> expected3=new Pair<>(28.333333333333336,z/2);
        Pair<Double,Double> result4=Calculator.calculateGeometricCM(x,z,4,0,0);
        Pair<Double,Double> expected4=new Pair<>(x/2,z/2);

        Exception exception= Assertions.assertThrows(NotImplementedException.class,()->Calculator.calculateGeometricCM(x,z,0,0.0,0.0));

        assertEquals(expected1.getFirst(),result1.getFirst());
        assertEquals(expected1.getSecond(),result1.getSecond());
        assertEquals(expected2.getFirst(),result2.getFirst());
        assertEquals(expected2.getSecond(),result2.getSecond());
        assertEquals(expected3.getFirst(),result3.getFirst());
        assertEquals(expected3.getSecond(),result3.getSecond());
        assertEquals(expected4.getFirst(),result4.getFirst());
        assertEquals(expected4.getSecond(),result4.getSecond());
        assertEquals(exception.getMessage(),"The geometric number introduced is not implemented");

    }
}
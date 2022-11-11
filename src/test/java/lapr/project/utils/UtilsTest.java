package lapr.project.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    List<String> list= new ArrayList<>();
    List<Double> doubleList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        list.add("This");
        list.add("is");
        list.add("a");
        list.add("test");
        list.add("to");
        list.add("the");
        list.add("Utils");
        list.add("class");
        doubleList.add(0.0);
        doubleList.add(1.0);
        doubleList.add(2.0);
        doubleList.add(3.0);
        doubleList.add(4.0);
        doubleList.add(5.0);
    }
    @Test
    void showList() {
        Utils.showList(list,"New list");
    }

    @Test
    void getDateWithoutTime() {
        Date date = new Date(2021,12,25);
        Date result = Utils.getDateWithoutTime(date);
        assertEquals(date,result);
    }

    @Test
    void listToArray() {
        double expectedArray[]= new double[]{0.0,1.0,2.0,3.0,4.0,5.0};
        double doubleArray[]=Utils.listToArray(doubleList);
        for (int i=0;i<expectedArray.length;i++)
            assertEquals(expectedArray[i],doubleArray[i]);
    }

    @Test
    void setTimeToZero() {
        Calendar calendar = Utils.setTimeToZero(new GregorianCalendar());
    }

}
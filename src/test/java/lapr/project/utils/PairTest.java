package lapr.project.utils;

import lapr.project.model.Ship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PairTest {

    private Ship ship1;
    private Ship ship2;
    private Pair<Ship, Ship> pairShips;

    @BeforeEach
    void setUp() {
        Integer mmsi1 = 123456789;
        Integer mmsi2 = 123456781;
        String imo1 = "IMO1234567";
        String imo2 = "IMO1234566";
        String callSign1 = "ABCD";
        String callSign2 = "ABDD";
        ship1 = new Ship(mmsi1, imo1, callSign1);
        ship2 = new Ship(mmsi2, imo2, callSign2);
        pairShips=new Pair<>(ship1,ship2);
    }

    @Test
    void testPair(){
        Ship shipResult1=pairShips.getFirst();
        Ship shipResult2=pairShips.getSecond();

        assertEquals(ship1,shipResult1);
        assertEquals(ship2,shipResult2);
    }

    @Test
    void add() {
       Exception exception= assertThrows(UnsupportedOperationException.class,() -> {pairShips.add(pairShips);});
       String expectedMessage="Not implemented yet.";
       String message=exception.getMessage();
       assertEquals(expectedMessage,message);
    }
}
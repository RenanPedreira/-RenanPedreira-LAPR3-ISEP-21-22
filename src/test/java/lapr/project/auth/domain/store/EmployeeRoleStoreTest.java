package lapr.project.auth.domain.store;

import lapr.project.auth.domain.model.EmployeeRole;
import lapr.project.shared.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRoleStoreTest {
    EmployeeRoleStore store = new EmployeeRoleStore();
    @BeforeEach
    void setUp() {
        store.add(store.create(Constants.ROLE_SHIP_CHIEF_ELETRICAL_ENGINEER));
        store.add(store.create(Constants.ROLE_TRUCK_DRIVER));
        store.add(store.create(Constants.ROLE_WAREHOUSE_MANAGER));
    }

    /**
     * Test that verifies if a certain role was created
     */
    @Test
    void testIfRoleWasCreated1() {
        //Arrange
        EmployeeRole expected= new EmployeeRole(Constants.ROLE_ADMIN);

        //Act
        EmployeeRole result= store.create(Constants.ROLE_ADMIN);

        //Assert
        assertEquals(expected.getId(),result.getId(),"The id are different.,");
    }

    /**
     * Test that verifies if a certain role was created
     */
    @Test
    void testIfRoleWasCreated2() {
        //Arrange
        EmployeeRole expected= new EmployeeRole(Constants.ROLE_TRAFFIC_MANAGER);

        //Act
        EmployeeRole result= store.create(Constants.ROLE_CLIENT);

        //Assert
        assertNotEquals(expected.getId(),result.getId(),"The id are equals.");
    }

    /**
     * Test that verifies if a certain role was created
     */
    @Test
    void testIfRoleWasCreated3() {
        //Arrange
        EmployeeRole expected= new EmployeeRole(Constants.ROLE_CLIENT);

        //Act
        EmployeeRole result= store.create(Constants.ROLE_CLIENT);

        //Assert
        assertEquals(expected.getId(),result.getId(),"The id are different.,");
    }

    /**
     * Test that verifies if a certain role was added
     */
    @Test
    void testIfRoleWasAdded() {
        //Act
        boolean result1=store.add(store.create(Constants.ROLE_CLIENT));
        boolean result2=store.add(store.create(Constants.ROLE_ADMIN));
        boolean result3=store.add(store.create(Constants.ROLE_TRAFFIC_MANAGER));

        //Assert
        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
    }

    /**
     * Test that verifies if a certain list is shown
     */
    @Test
    void showList() {
        //Act
        store.showList();
    }

    @Test
    void exist() {
        //Arrange
        EmployeeRole empR1 = store.create(Constants.ROLE_FLEET_MANAGER);
        EmployeeRole empR2 = store.create(Constants.ROLE_PORT_STAFF);
        EmployeeRole empR3 = store.create(Constants.ROLE_PORT_MANAGER);
        EmployeeRole empR4 = store.create(Constants.ROLE_SHIP_CAPTAIN);
        store.add(empR1);
        store.add(empR2);
        store.add(empR3);

        //Act
        boolean result1=store.exist(empR1);
        boolean result2=store.exist(empR2);
        boolean result3=store.exist(empR3);
        boolean result4=store.exist(empR4);

        //Assert
        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
        assertFalse(result4);
    }
}
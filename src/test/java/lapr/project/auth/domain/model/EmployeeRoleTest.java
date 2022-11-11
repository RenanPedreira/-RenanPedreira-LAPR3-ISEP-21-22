package lapr.project.auth.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luís Araújo
 */
class EmployeeRoleTest {
    EmployeeRole employeeRole;

    @BeforeEach
    public void setUp() throws Exception {
        employeeRole = new EmployeeRole("1");
    }

    /**
     * Test that gets the id
     */
    @Test
    void getId() {
        //Arrange
        String expectedResult = "1";

        //Act
        String result = employeeRole.getId();

        //Assert
        assertEquals(expectedResult, result);
    }
}
package lapr.project.auth.mappers.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luís Araújo
 */
class UserRoleDTOTest {
    UserRoleDTO userRoleDTO;
    @BeforeEach
    void setUp() {
        userRoleDTO=new UserRoleDTO("1", "Description");
    }

    /**
     * Test that verifies if the method getId returns the id
     */
    @Test
    void getId() {
        //Arrange
        String expectedResult = "1";

        //Act
        String result = userRoleDTO.getId();

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that verifies if the method getDescription returns the description
     */
    @Test
    void getDescription() {
        //Arrange
        String expectedResult = "Description";

        //Act
        String result = userRoleDTO.getDescription();

        //Assert
        assertEquals(expectedResult, result);
    }
}
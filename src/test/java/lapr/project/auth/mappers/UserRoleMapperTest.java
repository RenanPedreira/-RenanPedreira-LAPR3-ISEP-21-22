package lapr.project.auth.mappers;

import lapr.project.auth.domain.model.UserRole;
import lapr.project.auth.mappers.dto.UserRoleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author
 */
class UserRoleMapperTest {
    UserRoleMapper userRoleMapper;

    UserRoleDTO userRoleDTO;

    @BeforeEach
    void setUp() {
        userRoleMapper = new UserRoleMapper();
        userRoleDTO = new UserRoleDTO("1", "Description");
    }

    /**
     * Test that tests the method toDto
     */
    @Test
    void toDTO() {
        //Arrange
        UserRole userRole = new UserRole("1", "Description");

        //Act
        UserRoleDTO userRoleDTO2 = userRoleMapper.toDTO(userRole);

        //Assert
        assertNotEquals(userRoleDTO, userRoleDTO2);
    }

    /**
     * Test that tests the method toDto
     */
    @Test
    void testToDTO() {
        //Arrange
        List<UserRoleDTO> rolesDTO = new ArrayList<>();
        List<UserRole> userRoles = new ArrayList<>();

        //Act
        rolesDTO=userRoleMapper.toDTO(userRoles);

        //Assert
        assertNotNull(rolesDTO);
    }
}
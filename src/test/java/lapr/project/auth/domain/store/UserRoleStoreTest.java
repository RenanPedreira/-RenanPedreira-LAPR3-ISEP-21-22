package lapr.project.auth.domain.store;

import lapr.project.auth.domain.model.UserRole;
import lapr.project.shared.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserRoleStoreTest {

    UserRoleStore store = new UserRoleStore();

    @BeforeEach
    void setUp() {
        store.add(store.create(Constants.ROLE_ADMIN, Constants.ROLE_ADMIN));
        store.add(store.create(Constants.ROLE_TRAFFIC_MANAGER, Constants.ROLE_TRAFFIC_MANAGER));
        store.add(store.create(Constants.ROLE_CLIENT, Constants.ROLE_CLIENT));
    }

    /**
     * Test that verifies if a certain role was created
     */
    @Test
    void testIfRoleWasCreated() {
        //Arrange
        UserRole role1 = new UserRole(Constants.ROLE_CLIENT, Constants.ROLE_CLIENT);

        //Act
        UserRole role2 = store.create(Constants.ROLE_CLIENT, Constants.ROLE_CLIENT);

        //Assert
        assertEquals(role1.getId(), role2.getId());
        assertEquals(role1.getDescription(), role2.getDescription());

        //Arrange
        UserRole role3 = new UserRole(Constants.ROLE_ADMIN, Constants.ROLE_ADMIN);

        //Act
        UserRole role4 = store.create(Constants.ROLE_ADMIN, Constants.ROLE_ADMIN);

        //Assert
        assertEquals(role3.getId(), role4.getId());
        assertEquals(role3.getDescription(), role4.getDescription());
    }

    /**
     * Test that verifies if a certain role was added
     */
    @Test
    void testIfRoleWasAdded() {
        //Arrange
        store.add(store.create(Constants.ROLE_WAREHOUSE_MANAGER, Constants.ROLE_WAREHOUSE_MANAGER));
        boolean result1 = store.exists(Constants.ROLE_ADMIN);
        boolean result2 = store.exists(Constants.ROLE_WAREHOUSE_MANAGER);

        //Act
        boolean result3 = store.add(null);
        boolean result4 = store.add(store.create(Constants.ROLE_WAREHOUSE_MANAGER, Constants.ROLE_WAREHOUSE_MANAGER));

        //Assert
        assertTrue(result1);
        assertTrue(result2);
        assertFalse(result3);
        assertFalse(result4);
    }

    /**
     * Test that verifies if a certain role was removed
     */
    @Test
    void testIfRoleWasRemoved() {
        //Arrange
        UserRole role1 = new UserRole(Constants.ROLE_TRUCK_DRIVER, Constants.ROLE_TRUCK_DRIVER);
        UserRole role2 = store.create(Constants.ROLE_SHIP_CHIEF_ELETRICAL_ENGINEER, Constants.ROLE_SHIP_CHIEF_ELETRICAL_ENGINEER);
        store.add(role1);

        //Act
        boolean result1 = store.remove(role1);
        boolean result2 = store.remove(role2);
        boolean result3 = store.remove(null);

        //Assert
        assertTrue(result1);
        assertFalse(result2);
        assertFalse(result3);
    }

    /**
     * Test that tests the method that returns a role by its id
     */
    @Test
    void getById() {
        //Act
        Optional<UserRole> userRole = store.getById(Constants.ROLE_WAREHOUSE_STAFF);
        Optional<UserRole> expected = Optional.empty();

        //Assert
        assertEquals(expected, userRole);
    }

    /**
     * Test that verifies if the method that returns the store is correct
     */
    @Test
    void getStore() {
        //Act
        Set<UserRole> result = store.getStore();
        Set<UserRole> expected = new HashSet<>();

        //Assert
        assertNotEquals(expected, result);
    }

    /**
     * Test that verifies if a role exists
     */
    @Test
    void testExists() {
        //Arrange
        UserRole role1 = new UserRole(Constants.ROLE_TRUCK_DRIVER, Constants.ROLE_TRUCK_DRIVER);
        UserRole role2 = store.create(Constants.ROLE_SHIP_CHIEF_ELETRICAL_ENGINEER, Constants.ROLE_SHIP_CHIEF_ELETRICAL_ENGINEER);
        store.add(role1);

        //Act
        boolean result1 = store.exists(role1);
        boolean result2 = store.exists(role2);

        //Assert
        assertTrue(result1);
        assertFalse(result2);
    }

    /**
     * Test that verifies if a certain list is shown
     */
    @Test
    void showRoleslist() {
        //Act
        store.showRoleslist();
    }
}
package lapr.project.model;

import lapr.project.auth.AuthFacade;
import lapr.project.auth.domain.model.UserRole;
import lapr.project.auth.domain.store.UserRoleStore;
import lapr.project.network.FreightNetwork;
import lapr.project.store.CountryStore;
import lapr.project.tree.AVL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author
 */
class CompanyTest {
    private AuthFacade authFacade;
    private UserRoleStore userRole;
    private Company company;
    private AVL<Country> countryList = new AVL<>();
    private CountryStore countryStore;
    private FreightNetwork freightNetwork;

    @BeforeEach
    public void setUp() throws Exception {
        authFacade = new AuthFacade();
        userRole = new UserRoleStore();
        company = new Company("Sem3PI");
        countryStore = new CountryStore(countryList);
        freightNetwork = new FreightNetwork();
    }

    @Test
    public void getDesignation() {
        //Arrange
        String expectedResult = "Sem3PI";

        //Act
        String result = company.getDesignation();

        //Assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void testConstrutor() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Company(""));
        String expectedMessage = "Designation cannot be blank.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void getAuthFacade() {
        assertNotNull(company.getAuthFacade());
    }

    @Test
    public void getUserRoles1() {
        assertTrue(company.getUserRoles().getStore().isEmpty());
    }

    @Test
    public void getUserRoles2() {
        UserRoleStore userRoleStore = company.getUserRoles();
        UserRole ur = userRoleStore.create("ID", "Description");
        company.getUserRoles().add(ur);
        assertFalse(company.getUserRoles().getStore().isEmpty());
    }

    @Test
    public void getCountryStore() {
        assertNotNull(company.getCountryStore());
    }

    @Test
    public void getFreightNetwork() {
        assertNotNull(company.getFreightNetwork());
    }

}
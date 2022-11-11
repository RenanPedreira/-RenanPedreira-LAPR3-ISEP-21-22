package lapr.project.auth.domain.store;

import lapr.project.auth.domain.model.Email;
import lapr.project.auth.domain.model.Password;
import lapr.project.auth.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserStoreTest {
    UserStore store = new UserStore();
    @BeforeEach
    void setUp() {
        String name3="Diogo";
        String name4="Renan";
        String email3="diogo@gmail.com";
        String email4="renan@gmail.com";
        String password="123456";
        store.add(store.create(name3,email3,password));
        store.add(store.create(name4,email4,password));
    }

    /**
     * Test that tests the method that verifies if a user was created
     */
    @Test
    void testIfUserWasCreated() {
        //Arrange
        String name2="Luís";
        String email2="luis@gmail.com";
        String password="123456";

        //Act
        User user1=store.create(name2,email2,password);
        User expected=new User(new Email(email2),new Password(password),name2);

        //Assert
        assertEquals(expected,user1);
    }

    /**
     * Test that tests the method that verifies if a user was added
     */
    @Test
    void testIfUserWasAdded() {
        //Arrange
        String name2="Luís";
        String email2="luis@gmail.com";
        String password="123456";
        User user1=store.create(name2,email2,password);

        //Act
        boolean result1 = store.add(user1);
        boolean result2 = store.add(null);
        boolean result3 = store.add(user1);

        //Assert
        assertTrue(result1);
        assertFalse(result2);
        assertFalse(result3);
    }

    /**
     * Test that tests the method that verifies if a user was removed
     */
    @Test
    void testIfUserWasRemoved() {
        //Arrange
        String name1="Danilton";
        String email1="danilton@gmail.com";
        String password="123456";
        String name2="Luís";
        String email2="luis@gmail.com";
        User user1=store.create(name1,email1,password);
        User user2=store.create(name2,email2,password);
        store.add(user1);

        //Act
        boolean result1= store.remove(null);
        boolean result2= store.remove(user1);
        boolean result3= store.remove(user2);

        //Assert
        assertFalse(result1);
        assertTrue(result2);
        assertFalse(result3);
    }

    /**
     * Test that tests the method that returns the id of a user
     */
    @Test
    void getById() {
        //Arrange
        String name3="Diogo";
        String email3="diogo@gmail.com";
        String password="123456";
        String email1="danilton@gmail.com";

        //Act
        User user1 = store.getById(email3).get();
        User expected1 = store.create(name3,email3,password);
        Optional<User> expected2= Optional.empty();
        Optional<User> user2=store.getById(email1);

        //Assert
        assertEquals(expected1,user1);
        assertEquals(expected2,user2);
    }

    /**
     * Test that tests the method that verifies if a user exists
     */
    @Test
    void testIfUserExists() {
        //Arrange
        String email3="diogo@gmail.com";
        String email1="danilton@gmail.com";
        Email email4= new Email("renan@gmail.com");

        //Act
        boolean result1=store.exists(email3);
        boolean result2=store.exists(email1);
        boolean result3=store.exists(email4);

        //Assert
        assertTrue(result1);
        assertFalse(result2);
        assertTrue(result3);
    }
}
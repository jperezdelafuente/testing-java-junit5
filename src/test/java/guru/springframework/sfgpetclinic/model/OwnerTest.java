package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {

    @Test
    void testDependentAssertions() {
        Owner owner = new Owner(1l, "Joe", "Buck");
        owner.setCity("Key West");
        owner.setTelephone("1231231234");

        assertAll("Properties Test",
                () -> assertAll("Person Properties",
                        () -> assertEquals("Joe", owner.getFirstName(), "First Name Did not Match"),
                        () -> assertEquals("Buck", owner.getLastName(), "Last Name Did not Match"))
                ,
                () -> assertAll("Owner Properties",
                        () -> assertEquals("Key West", owner.getCity(), "City Did Not Match"),
                        () -> assertEquals("1231231234", owner.getTelephone(), "Telephone Did Not Match")
                ));

        assertThat(owner.getCity(), is("Key West"));
    }

}
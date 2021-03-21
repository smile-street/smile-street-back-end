package com.smilestreet.model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VolunteerTest {


    @Test
    @DisplayName("Test volunteer firstname GET")
    public void testTaskDescription() {
        Volunteer v =new Volunteer("Taslima","Banglawala","07948513237","taslima26");

        assertEquals("Taslima", v.getFirstname(), "Volunteer name was incorrect");
    }

    @Test
    @DisplayName("Test voluneer contactnumber GET")
    public void testTaskId() {
        Volunteer v = new Volunteer("Taslima", "Banglawala","123456","Tassu26");

        assertEquals("123456", v.getContactnumber(), "Phone name was incorrect");
    }
}




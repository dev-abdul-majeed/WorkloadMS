package application;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class WorkloadTest {

    @BeforeEach
    void setup() {
        // Reset workloads before each test
        Workload.getWorkloads().clear();
    }

    @Test
    void testWorkloadCreation() {
        Workload w = new Workload(1, "ATSR", "Teaching", "Lecture hours", "2025", 10.5, 2);
        assertEquals(1, w.getTeacherId());
        assertEquals("ATSR", w.getType());
        assertEquals("Teaching", w.getActivity());
        assertEquals("Lecture hours", w.getDescription());
        assertEquals("2025", w.getYear());
        assertEquals(11, w.getActivityDuration()); // Rounded up
        assertEquals(2, w.getInstances());
    }

    @Test
    void testSetHoursForATSR() {
        Workload w = new Workload(1, "ATSR", "Teaching", "Lecture hours", "2025", 5, 3);
        assertEquals(15, w.getAtsr()); // 5 * 3 rounded
        assertEquals(18, w.getTs());   // 15 * 1.2
        assertEquals(0, w.getTlr());
        assertEquals(0, w.getSa());
        assertEquals(0, w.getOther());
    }

    @Test
    void testSetHoursForOtherTypes() {
        Workload w = new Workload(2, "TLR", "Management", "Admin work", "2025", 3.7, 4);
        assertEquals(15, w.getTlr()); // 3.7 * 4 rounded
        assertEquals(0, w.getAtsr());
        assertEquals(0, w.getTs());
        assertEquals(0, w.getSa());
        assertEquals(0, w.getOther());
    }


    @Test
    void testWorkloadList() {
        Workload w1 = new Workload(1, "ATSR", "Teaching", "Lecture hours", "2025", 10.5, 2);
        Workload w2 = new Workload(2, "SA", "Research", "Field study", "2025", 7.2, 1);
        ArrayList<Workload> workloads = Workload.getWorkloads();
        assertEquals(2, workloads.size());
        assertEquals(w1, workloads.get(0));
        assertEquals(w2, workloads.get(1));
    }

    @Test
    void testValidateWorkloadMissingFields() {
        String error1 = Workload.validateWorkload("", "ATSR", "Teaching", "Lecture hours", "2025", "10.5", "2");
        assertTrue(error1.contains("Please select a teacher"));

        String error2 = Workload.validateWorkload("John Doe", null, "Teaching", "Lecture hours", "2025", "10.5", "2");
        assertTrue(error2.contains("Please select a type"));

        String error3 = Workload.validateWorkload("John Doe", "ATSR", null, "Lecture hours", "2025", "10.5", "2");
        assertTrue(error3.contains("Please provide an activity"));

        String error4 = Workload.validateWorkload("John Doe", "ATSR", "Teaching", "", "2025", "10.5", "2");
        assertTrue(error4.contains("Please provide a description"));

        String error5 = Workload.validateWorkload("John Doe", "ATSR", "Teaching", "Lecture hours", null, "10.5", "2");
        assertTrue(error5.contains("Please provide a valid year time"));
    }

    @Test
    void testValidateWorkloadInvalidNumbers() {
        String error1 = Workload.validateWorkload("John Doe", "ATSR", "Teaching", "Lecture hours", "2025", "abc", "2");
        assertTrue(error1.contains("Please provide a numeric activity duration"));

        String error2 = Workload.validateWorkload("John Doe", "ATSR", "Teaching", "Lecture hours", "2025", "10.5", "xyz");
        assertTrue(error2.contains("Please provide a numeric instance count"));
    }

    @Test
    void testValidateWorkloadValidInput() {
        String error = Workload.validateWorkload("John Doe", "ATSR", "Teaching", "Lecture hours", "2025", "10.5", "2");
        assertEquals("", error); // No errors for valid input
    }
}

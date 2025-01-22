package application;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TeacherTest {

    @BeforeEach
    void setup() {
        // Clear the static list of teachers before each test to ensure isolation
        Teacher.teacherList().clear();
    }

    @Test
    void testTeacherCreation() {
        Teacher t1 = new Teacher("John Doe", "Math", "Full Time");
        assertEquals("John Doe", t1.getName());
        assertEquals("Math", t1.getDepartment());
        assertEquals("Full Time", t1.getStatus());
        assertEquals(Teacher.teacherList().get(0).getId(), t1.getId());
    }

    @Test
    void testPartTimeCount() {
        new Teacher("John Doe", "Math", "Full Time");
        new Teacher("Jane Smith", "Science", "Part Time");
        new Teacher("Alice Brown", "History", "Part Time");
        assertEquals(2, Teacher.partTimeCount());
    }

    @Test
    void testFullTimeCount() {
        new Teacher("John Doe", "Math", "Full Time");
        new Teacher("Jane Smith", "Science", "Part Time");
        assertEquals(1, Teacher.fullTimeCount());
    }

    @Test
    void testAddTeacher() {
        Teacher.teacherList().clear();;
        Teacher t1 = new Teacher("John Doe", "Math", "Full Time");
        Teacher.addTeacher(t1);
        assertEquals(true, Teacher.teacherList().contains(t1));
    }

    @Test
    void testGetNameById() {
        Teacher t1 = new Teacher("John Doe", "Math", "Full Time");
        Teacher t2 = new Teacher("Jane Smith", "Science", "Part Time");
        assertEquals("John Doe", Teacher.getNameById(t1.getId()));
        assertEquals("Jane Smith", Teacher.getNameById(t2.getId()));
    }

    @Test
    void testTeacherNameList() {
        Teacher t1 = new Teacher("John Doe", "Math", "Full Time");
        Teacher t2 = new Teacher("Jane Smith", "Science", "Part Time");
        ArrayList<String> expectedNames = new ArrayList<>();
        expectedNames.add(t1.getId()+": John Doe");
        expectedNames.add(t2.getId()+": Jane Smith");
        assertEquals(expectedNames, Teacher.teacherNameList());
    }

    @Test
    void testValidateTeacher() {
        // Test with missing name
        String error1 = Teacher.validateTeacher("", "Math", "Full Time");
        assertTrue(error1.contains("Please provide a Name"));

        // Test with null department
        String error2 = Teacher.validateTeacher("John Doe", null, "Full Time");
        assertTrue(error2.contains("Please select a Department"));

        // Test with null status
        String error3 = Teacher.validateTeacher("John Doe", "Math", null);
        assertTrue(error3.contains("Please select a Status"));

        // Test with all fields valid
        String error4 = Teacher.validateTeacher("John Doe", "Math", "Full Time");
        assertEquals("", error4);
    }

    @Test
    void testSetters() {
        Teacher t = new Teacher("John Doe", "Math", "Full Time");
        t.setName("Jane Doe");
        t.setDepartment("Science");
        t.setStatus("Part Time");

        assertEquals("Jane Doe", t.getName());
        assertEquals("Science", t.getDepartment());
        assertEquals("Part Time", t.getStatus());
    }
}

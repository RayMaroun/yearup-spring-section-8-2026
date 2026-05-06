package com.pluralsight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    public void punchIn_StartTimeNotSet_StartTimeSetToGivenTime() {
        // Arrange
        Employee employee = new Employee(1, "John Doe", "IT", 20.0);
        double startTime = 9.0;
        double expectedStartTime = 9.0;

        // Act
        employee.punchIn(startTime);

        // Assert
        double actualStartTime = employee.getStartTime();
        assertEquals(expectedStartTime, actualStartTime);
    }

    @Test
    public void punchOut_HoursNotSet_HoursWorkedSetCorrectly() {
        // Arrange
        Employee employee = new Employee(1, "John Doe", "IT", 20.0);
        double endTime = 17.5;
        employee.punchIn(9.0);
        double expectedHoursWorked = 8.5;

        // Act
        employee.punchOut(endTime);

        // Assert
        double actualHoursWorked = employee.getHoursWorked();
        assertEquals(expectedHoursWorked, actualHoursWorked);
    }


}
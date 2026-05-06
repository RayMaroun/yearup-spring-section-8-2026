package com.pluralsight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    public void accelerate_NormalSpeedChange_SpeedIncreased() {
        // Arrange
        Car remoCar = new Car("Bugatti", "Chevron");
        int speedChange = 15;
        int expectedSpeed = 15;

        // Act
        remoCar.accelerate(speedChange);

        // Assert
        int actualSpeed = remoCar.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }

    @Test
    public void brake_NormalSpeedChange_SpeedDecreased() {
        // Arrange
        Car adamCar = new Car("Toyota", "Prius");
        int speedUpBy = 15;
        int slowDownBy = 10;
        int expectedSpeed = 5;
        adamCar.accelerate(speedUpBy);

        // Act
        adamCar.brake(slowDownBy);

        // Assert
        int actualSpeed = adamCar.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }


    @Test
    public void brake_SpeedChangeGreaterThanActualSpeed_SpeedIsZero() {
        // Arrange
        Car bogdanCar = new Car("Toyota", "Rav4");
        int speedUpBy = 15;
        int slowDownBy = 20;
        int expectedSpeed = 0;
        bogdanCar.accelerate(speedUpBy);

        // Act
        bogdanCar.brake(slowDownBy);

        // Assert
        int actualSpeed = bogdanCar.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }


}
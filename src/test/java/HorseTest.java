import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mockStatic;

class HorseTest {

    @Test
    void checkConstructorWhenFirstParameterIsNullTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Horse(null, 1, 2);
                }
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    void checkConstructorWhenFirstParameterIsEmptyStringTest(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Horse(name, 3, 2);
                }
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void checkConstructorWhenSecondParameterIsNegativeNumberTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Horse("name", -1, 2);
                }
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void checkConstructorWhenThirdParameterIsPositiveNumberTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Horse("name", 1, -2);
                }
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameTest() {
        Horse horse = new Horse("name", 1, 2);
        assertEquals("name", horse.getName());
    }

    @Test
    void getSpeedTest() {
        Horse horse = new Horse("name", 1, 2);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    void getDistanceTest() {
        Horse horse = new Horse("name", 1, 2);
        assertEquals(2, horse.getDistance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 1.2})
    void moveUsersRandomTest(double doubles) {
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(doubles);
            Horse horse = new  Horse("name", 1, 2);
            horse.move();

            assertEquals(2 + 1 * doubles, horse.getDistance());
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}
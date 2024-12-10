import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {
    @Test
    void constructor_check_when_passing_null() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));

        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void CheckWhenPassingAnEmptyListToConstructor() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));

        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("" + i, random.nextInt(10), random.nextInt(10)));
        }

        assertEquals(horses, new Hippodrome(horses).getHorses());
    }

    @Test
    void moveTest() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        new Hippodrome(horses).move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    void getWinnerTest() {
        Horse horse1 = new Horse("Horse1", 10, 10);
        Horse horse2 = new Horse("Horse2", 20, 20);
        Horse horse3 = new Horse("Horse3", 30, 30);
        List<Horse> horses = List.of(horse1,horse2,horse3);
        Hippodrome hippodrome = new Hippodrome(horses);

        Horse horseWinner = hippodrome.getWinner();

        assertEquals(horse3, horseWinner);

    }
}
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private Main.CoffeeMachine coffeeMachine;

    @BeforeEach
    public void setUp() {
        coffeeMachine = new Main.CoffeeMachine();
    }

    @Test
    public void testPowerOn() {
        coffeeMachine.powerOn();
        assertEquals(Main.CoffeeMachine.State.IDLE, coffeeMachine.getCurrentState());
    }


}
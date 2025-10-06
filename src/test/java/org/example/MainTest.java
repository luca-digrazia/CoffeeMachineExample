package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//mvn test-compile org.pitest:pitest-maven:mutationCoverage

class MainTest {
    private Main.CoffeeMachine coffeeMachine;

    @BeforeEach
    public void setUp() {
        coffeeMachine = new Main.CoffeeMachine();
    }

    @Test
    public void testStartBrewing_OffState() {
        coffeeMachine.powerOff();
        coffeeMachine.startBrewing();
        assertEquals(Main.CoffeeMachine.State.ERROR, coffeeMachine.getCurrentState());

    }

    @Test
    public void testStartGetNumberCoffee0() {
        Main.CoffeeMachine.setNumberCoffees(0);
        coffeeMachine.powerOn();
        assertEquals(Main.CoffeeMachine.State.IDLE, coffeeMachine.getCurrentState());

        int c = Main.CoffeeMachine.getNumberCoffees();
        assertEquals(0, c);

        Main.CoffeeMachine.setNumberCoffees(2);
        c = Main.CoffeeMachine.getNumberCoffees();
        assertEquals(2, c);

        // increase coverage of setnumber
        Main.CoffeeMachine.setNumberCoffees(3);
        c = Main.CoffeeMachine.getNumberCoffees();
        assertEquals(3, c);


    }





}
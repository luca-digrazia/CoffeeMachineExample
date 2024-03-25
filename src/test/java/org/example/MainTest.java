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

    }

    @Test
    public void testStartBrewing_ErrorState() {
        coffeeMachine.coffeeMachineError();
        coffeeMachine.startBrewing();
        assertEquals(Main.CoffeeMachine.State.ERROR, coffeeMachine.getCurrentState());

    }
    @Test
    public void testStartBrewing_IdleState() {
        coffeeMachine.powerOn();
        assertEquals(Main.CoffeeMachine.State.IDLE, coffeeMachine.getCurrentState());

        Main.CoffeeMachine.getNumberCoffees();
        coffeeMachine.startBrewing();

        assertEquals(Main.CoffeeMachine.State.ERROR, coffeeMachine.getCurrentState());

    }
    @Test
    public void testStartBrewing_Maintenance() {
        coffeeMachine.powerOn();
        assertEquals(Main.CoffeeMachine.State.IDLE, coffeeMachine.getCurrentState());

        coffeeMachine.selectCoffee();

        Main.CoffeeMachine.setNumberCoffees(4);
        coffeeMachine.startBrewing();

        assertEquals(Main.CoffeeMachine.State.IDLE, coffeeMachine.getCurrentState());

    }
    @Test
    public void testStartBrewing_4Coffees() {
        coffeeMachine.powerOn();
        assertEquals(Main.CoffeeMachine.State.IDLE, coffeeMachine.getCurrentState());
        coffeeMachine.selectCoffee();
        coffeeMachine.startBrewing();

        coffeeMachine.selectCoffee();
        coffeeMachine.startBrewing();

        coffeeMachine.selectCoffee();
        coffeeMachine.startBrewing();

        coffeeMachine.selectCoffee();
        coffeeMachine.startBrewing();

        assertEquals(Main.CoffeeMachine.State.IDLE, coffeeMachine.getCurrentState());

    }
    @Test
    public void testPowerOn_4Coffees() {
        coffeeMachine.setNumberCoffees(4);
        coffeeMachine.powerOn();
        assertEquals(Main.CoffeeMachine.State.IDLE, coffeeMachine.getCurrentState());


    }
    @Test
    public void testStartBrewing_HeatingWaterState() {
        coffeeMachine.heatingWater();
        coffeeMachine.startBrewing();

        assertEquals(Main.CoffeeMachine.State.ERROR, coffeeMachine.getCurrentState());


    }
    @Test
    public void testStartBrewing_GrindingBeansState() {
        coffeeMachine.grindingBeans();
        coffeeMachine.startBrewing();

        assertEquals(Main.CoffeeMachine.State.ERROR, coffeeMachine.getCurrentState());


    }
    @Test
    public void testStartBrewing_ReadyState_RequiresMaintenance() {
        coffeeMachine.powerOn();
        coffeeMachine.selectCoffee();
        assertEquals(Main.CoffeeMachine.State.READY, coffeeMachine.getCurrentState());
        coffeeMachine.startBrewing();

        assertEquals(Main.CoffeeMachine.State.IDLE, coffeeMachine.getCurrentState());

    }



    /*@Test
    public void testStartGetNumberCoffee4() {
        coffeeMachine.powerOn();
        assertEquals(Main.CoffeeMachine.State.IDLE, coffeeMachine.getCurrentState());

        //Set number of coffees to 4
        Main.CoffeeMachine.setNumberCoffees(4);
        int c = Main.CoffeeMachine.getNumberCoffees();
        assertEquals(4, c);

    }*/







    // I ran mutation analysis with PIT and I got the following warning:
    // 1. replaced int return with 0 for org/example/Main$CoffeeMachine::getNumberCoffees → SURVIVED.
    // Please write a test case to KILL this mutant with numberCoffee = 4



}
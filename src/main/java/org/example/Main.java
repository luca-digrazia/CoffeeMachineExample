package org.example;

public class Main {
    public static class CoffeeMachine {


        public enum State {
            OFF, IDLE, READY, BREWING, HEATING_WATER, GRINDING_BEANS, CLEANING, MAINTENANCE_REQUIRED, ERROR        }

        protected State currentState;
        protected static int numberCoffees = 0;

        public State getCurrentState() {
            return currentState;
        }

        public static int getNumberCoffees() {
            return numberCoffees;
        }

        public static void setNumberCoffees(int n) {
            numberCoffees = n;
        }

        public CoffeeMachine() {
            this.currentState = State.OFF;
        }

        public void powerOn() {
            if( numberCoffees >= 3) {
                currentState = State.MAINTENANCE_REQUIRED;
                performMaintenance();
            }

            currentState = State.IDLE;
            System.out.println("Coffee machine powered on.");
        }

        public void powerOff() {

            currentState = State.READY;
            System.out.println("Coffee machine powered off.");
        }

        public void selectCoffee() {
            if( numberCoffees >= 3) {
                currentState = State.MAINTENANCE_REQUIRED;
                performMaintenance();
            }

            if (currentState == State.IDLE) {
                currentState = State.READY;
                System.out.println("Coffee selected. Machine ready.");
            } else {
                System.out.println("Cannot select coffee in current state.");
                coffeeMachineError();

            }
        }

        public void startBrewing() {

            switch (currentState) {
                case OFF, ERROR, IDLE, CLEANING,
                        MAINTENANCE_REQUIRED, HEATING_WATER, GRINDING_BEANS:
                    System.out.println("Cannot start brewing in current state.");
                    coffeeMachineError();
                    break;

                case READY:
                    if (numberCoffees >= 3) {
                        currentState = State.MAINTENANCE_REQUIRED;
                        performMaintenance();
                    } else {
                        currentState = State.BREWING;
                        System.out.println("Brewing coffee...");
                        grindingBeans();
                        heatingWater();

                        // Simulate brewing process
                        currentState = State.IDLE;
                        System.out.println("Coffee brewed successfully.");
                        numberCoffees += 1;
                    }
                    break;
            }
        }


        public void heatingWater() {
            if (currentState == State.BREWING) {
                currentState = State.HEATING_WATER;
                System.out.println("Heating water...");
            } else {
                System.out.println("Cannot heat water in current state.");
                coffeeMachineError();

            }
        }

        public void grindingBeans() {
            if (currentState == State.HEATING_WATER) {
                currentState = State.GRINDING_BEANS;
                System.out.println("Grinding beans...");
            } else {
                System.out.println("Cannot grind beans in current state.");
                coffeeMachineError();
            }
        }

        public void performMaintenance() {
            if (currentState == State.MAINTENANCE_REQUIRED) {
                currentState = State.CLEANING;
                System.out.println("Performing maintenance...");
                // Simulate maintenance process
                currentState = State.IDLE;
                System.out.println("Maintenance completed.");
                numberCoffees = 0;
            } else {
                System.out.println("No maintenance required.");
            }
        }

        public void coffeeMachineError() {
            currentState = State.ERROR;
            System.out.println("Error encountered. Machine in ERROR state.");
        }

        // Other methods for handling different states and actions

    }

    // Add main
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        coffeeMachine.powerOn();


        coffeeMachine.powerOff();
    }
}

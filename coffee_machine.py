from enum import Enum


class CoffeeMachine:

    class State(Enum):
        OFF = "OFF"
        IDLE = "IDLE"
        READY = "READY"
        BREWING = "BREWING"
        HEATING_WATER = "HEATING_WATER"
        GRINDING_BEANS = "GRINDING_BEANS"
        CLEANING = "CLEANING"
        MAINTENANCE_REQUIRED = "MAINTENANCE_REQUIRED"
        ERROR = "ERROR"

    number_coffees = 0

    def __init__(self):
        self.current_state = CoffeeMachine.State.OFF

    def get_current_state(self):
        return self.current_state

    @staticmethod
    def get_number_coffees():
        return CoffeeMachine.number_coffees

    @staticmethod
    def set_number_coffees(n):
        CoffeeMachine.number_coffees = n

    def power_on(self):
        if CoffeeMachine.number_coffees >= 3:
            self.current_state = CoffeeMachine.State.MAINTENANCE_REQUIRED
            self.perform_maintenance()
            return

        self.current_state = CoffeeMachine.State.IDLE
        print("Coffee machine powered on.")

    def power_off(self):
        self.current_state = CoffeeMachine.State.OFF
        print("Coffee machine powered off.")

    def select_coffee(self):
        if CoffeeMachine.number_coffees >= 3:
            self.current_state = CoffeeMachine.State.MAINTENANCE_REQUIRED
            self.perform_maintenance()

        if self.current_state == CoffeeMachine.State.IDLE:
            self.current_state = CoffeeMachine.State.READY
            print("Coffee selected. Machine ready.")
        else:
            print("Cannot select coffee in current state.")
            self.coffee_machine_error()

    def start_brewing(self):
        if self.current_state in (
            CoffeeMachine.State.OFF,
            CoffeeMachine.State.ERROR,
            CoffeeMachine.State.CLEANING,
            CoffeeMachine.State.MAINTENANCE_REQUIRED,
            CoffeeMachine.State.HEATING_WATER,
            CoffeeMachine.State.GRINDING_BEANS,
        ):
            print("Cannot start brewing in current state.")
            self.coffee_machine_error()
        elif self.current_state == CoffeeMachine.State.READY:
            if CoffeeMachine.number_coffees >= 3:
                self.current_state = CoffeeMachine.State.MAINTENANCE_REQUIRED
                self.perform_maintenance()
            else:
                self.current_state = CoffeeMachine.State.BREWING
                print("Brewing coffee...")
                self.grinding_beans()
                self.heating_water()

                self.current_state = CoffeeMachine.State.IDLE
                print("Coffee brewed successfully.")
                CoffeeMachine.number_coffees += 1

    def heating_water(self):
        if self.current_state == CoffeeMachine.State.BREWING:
            self.current_state = CoffeeMachine.State.HEATING_WATER
            print("Heating water...")
        else:
            print("Cannot heat water in current state.")
            self.coffee_machine_error()

    def grinding_beans(self):
        if self.current_state == CoffeeMachine.State.HEATING_WATER:
            self.current_state = CoffeeMachine.State.GRINDING_BEANS
            print("Grinding beans...")
        else:
            print("Cannot grind beans in current state.")
            self.coffee_machine_error()

    def perform_maintenance(self):
        if self.current_state == CoffeeMachine.State.MAINTENANCE_REQUIRED:
            self.current_state = CoffeeMachine.State.CLEANING
            print("Performing maintenance...")
            print("Maintenance completed.")
            CoffeeMachine.number_coffees = 0
        else:
            print("No maintenance required.")

    def coffee_machine_error(self):
        self.current_state = CoffeeMachine.State.ERROR
        print("Error encountered. Machine in ERROR state.")


def main():
    coffee_machine = CoffeeMachine()
    coffee_machine.power_on()
    coffee_machine.power_off()


if __name__ == "__main__":
    main()

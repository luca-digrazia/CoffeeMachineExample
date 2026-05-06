import unittest

from coffee_machine import CoffeeMachine


class MainTest(unittest.TestCase):

    def setUp(self):
        self.coffee_machine = CoffeeMachine()

    def test_start_brewing_off_state(self):
        self.coffee_machine.power_off()
        self.coffee_machine.start_brewing()
        self.assertEqual(
            CoffeeMachine.State.ERROR, self.coffee_machine.get_current_state()
        )

    def test_start_get_number_coffee_0(self):
        CoffeeMachine.set_number_coffees(0)
        self.coffee_machine.power_on()
        self.assertEqual(
            CoffeeMachine.State.IDLE, self.coffee_machine.get_current_state()
        )

        c = CoffeeMachine.get_number_coffees()
        self.assertEqual(0, c)

        CoffeeMachine.set_number_coffees(2)
        c = CoffeeMachine.get_number_coffees()
        self.assertEqual(2, c)

        # increase coverage of setnumber
        CoffeeMachine.set_number_coffees(3)
        c = CoffeeMachine.get_number_coffees()
        self.assertEqual(3, c)


if __name__ == "__main__":
    unittest.main()

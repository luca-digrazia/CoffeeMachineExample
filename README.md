# ☕ Coffee Machine Project: Setup and Quality Testing

This project simulates a digital coffee machine. More importantly, it serves as a laboratory for learning how to ensure software is reliable and "bug-free" using professional testing tools.

---

## 1. Setting Up Your Workspace (The "Virtual Toolbox")
Before running the code, we create a **Virtual Environment**. Think of this as a dedicated toolbox just for this project. It ensures that the tools we install here don't interfere with other projects on your computer.

1. **Open your terminal** in VS Code.
2. **Create the environment**:
   ```bash
   python -m venv venv
   ```
3. **Activate the environment**:
   * **Mac/Linux:** `source venv/bin/activate`
   * **Windows:** `.\venv\Scripts\activate`

*You will know it worked if you see `(venv)` appear at the start of your command line.*

---

## 2. Installing the Tools
Now we need to install the specific libraries (like `coverage` and `mutmut`) required for this project.

Run this command:
```bash
pip install -r requirements.txt
```

---

## 3. Running the Coffee Machine
To see the machine in action and interact with it, run the main program:

```bash
python coffee_machine.py
```

---

## 4. Basic Testing: Does it work?
We have a file called `test_coffee_machine.py`. This is a "robot" that runs a series of checks to make sure the coffee machine behaves correctly (e.g., it shouldn't make coffee if there isn't enough water).

To run these checks:
```bash
python -m unittest test_coffee_machine.py
```
* **OK**: All tests passed!
* **FAIL**: The robot found a mistake in the logic.

---

## 5. Code Coverage: Did we check everything?
"Coverage" tells us which lines of our code were actually tested by our robot. If our code is 100 lines long and our tests only check 80 lines, we have 80% coverage.

1. **Run the tests with the coverage tool**:
   ```bash
   coverage run -m unittest test_coffee_machine.py
   ```
2. **View the report**:
   ```bash
   coverage report
   ```
*Look at the `Stmts` (Statements) and `Miss` (Lines missed) columns to see which parts of the machine were ignored.*

---

## 6. Mutation Testing: The Ultimate Stress Test
Coverage only tells us if a line was *executed*. It doesn't tell us if our test was actually *smart* enough to catch a mistake. 

**Mutation Testing** (using a tool called `mutmut`) intentionally "breaks" your code—for example, changing a `>` to a `<`—and then runs your tests. If your tests still pass despite the code being broken, your tests "failed" to catch the bug.

### How to run it:
1. **Run the mutation cycle**:
   ```bash
   mutmut run
   ```
2. **See the summary**:
   ```bash
   mutmut results
   ```

### How to read the results:
If you see **"Survived"** mutants, it means `mutmut` broke your code, but your tests were too weak to notice! 

To see exactly what `mutmut` changed to trick your tests, pick a number from the results list (e.g., `5`) and type:
```bash
mutmut show 5
```
This will show a "Diff" (a comparison). The lines with `-` are your original code, and the lines with `+` are the "mutant" (broken) code that your tests missed.



---

## Summary of Commands
| Action | Command |
| :--- | :--- |
| **Run Program** | `python coffee_machine.py` |
| **Run Tests** | `python -m unittest test_coffee_machine.py` |
| **Check Coverage** | `coverage report` |
| **Mutation Test** | `mutmut run` |
| **View Mistakes** | `mutmut show <number>` |

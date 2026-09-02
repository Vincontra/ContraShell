# ContraShell

ContraShell is a command-line shell application developed in Java.  
The project is built from scratch to understand how a basic shell works internally and how Java can be used to interact with the operating system.

---

## Project Objective

The main objective of ContraShell is to build a basic command-line shell in Java while understanding the concepts involved in:

- Taking input from the user
- Parsing commands
- Executing commands
- Handling command arguments
- Managing the shell execution flow
- Validating user input
- Handling invalid commands and errors
- Using Java OOP concepts in a practical project

The project is intentionally kept simple and focused on the core functionality required for a basic shell.

---

## Technologies Used

- Java
- Java Standard Library
- Command Line / Terminal
- Git & GitHub

---

## Requirements

Before running ContraShell, make sure Java is installed.

Check Java installation:

```bash
java -version
```

Check Java compiler:

```bash
javac -version
```

---

## How ContraShell Works

The shell follows a simple execution cycle:

```
Start Shell
    ↓
Display Prompt
    ↓
Take User Input
    ↓
Parse Input
    ↓
Validate Command
    ↓
Execute Command
    ↓
Display Result
    ↓
Repeat
```

The shell continuously waits for the user to enter a command and processes it.

---

## Basic Shell Flow

When ContraShell starts, it enters an infinite command-processing loop.

For every iteration:

1. The shell displays a prompt.
2. The user enters a command.
3. The input is read by the program.
4. The input is divided into the command and its arguments.
5. The command is validated.
6. The corresponding operation is performed.
7. The result or error message is displayed.
8. The shell waits for the next command.

---

## Commands

The shell supports the commands implemented in the project.

### Exit

The `exit` command terminates the shell.

Example:

```
> exit
```

After receiving the exit command, the shell stops its execution loop.

---

## Command Arguments

A command can contain additional values called arguments.

For example:

```
command argument1 argument2
```

Here:

- `command` is the main command
- `argument1` and `argument2` are command arguments

ContraShell separates the user input so that the command and its arguments can be processed independently.

---

## Input Parsing

Input parsing is the process of converting the raw string entered by the user into meaningful parts.

For example:

```
echo hello
```

The shell identifies:

- Command  → `echo`
- Argument → `hello`

This allows the program to decide which operation should be executed.

---

## Command Validation

Before executing a command, ContraShell checks whether the entered command is valid.

If the command is not supported, the shell does not terminate immediately. Instead, it displays an appropriate error message and continues running.

Example:

```
> abc
Unknown command
```

The shell then waits for another command.

---

## Error Handling

ContraShell handles invalid user input and command-related errors so that a single invalid command does not terminate the entire shell.

The basic approach is:

```
User Input
    ↓
Validation
    ↓
Valid → Execute
Invalid → Display Error
    ↓
Continue Shell
```

This keeps the shell running even when the user enters an unsupported command.

---

## Object-Oriented Programming Concepts Used

The project is implemented using Java and applies important Object-Oriented Programming concepts.

### Encapsulation

Encapsulation means keeping data and the operations that work on that data together inside a class while controlling access to the internal details.

In ContraShell, classes are responsible for their own functionality instead of putting all logic into one place.

### Abstraction

Abstraction means hiding unnecessary implementation details and exposing only what is required.

The user only interacts with the shell through commands. The internal processing required to interpret and execute those commands remains hidden.

### Inheritance

Inheritance allows one class to acquire properties and behavior from another class.

Where applicable, common functionality can be placed in a parent class and specialized behavior can be implemented by child classes.

### Polymorphism

Polymorphism allows the same interface or method structure to represent different behaviors.

For a command-based shell, different commands can provide different implementations while following a common structure.

---

## Why Java?

Java was selected because:

- It provides strong Object-Oriented Programming support.
- It provides APIs for interacting with the operating system.
- It has built-in support for input/output operations.
- It provides exception handling.
- It is platform independent through the JVM.
- It is suitable for building command-line applications.

---

## Important Java Concepts Used

The project also provides practical understanding of:

- Classes and Objects
- Constructors
- Methods
- Access Modifiers
- Encapsulation
- Abstraction
- Inheritance
- Polymorphism
- Interfaces
- Method Overriding
- Static and Instance Members
- Exception Handling
- String Handling
- Input/Output
- Loops
- Conditional Statements
- Collections where required

---

## Exception Handling

Exception handling is used to prevent unexpected runtime problems from unnecessarily terminating the application.

The general idea is:

```
try
    Perform operation
catch
    Handle problem
```

This allows the shell to display an error and continue execution wherever appropriate.

---

## Shell Execution Loop

The shell continues running until the user explicitly exits.

Conceptually:

```java
while (running) {

    // Display prompt

    // Read input

    // Parse command

    // Validate command

    // Execute command

}
```

The loop is the core of the interactive shell because it allows multiple commands to be executed during one program execution.

---

## Single File Project

ContraShell is intentionally maintained as a single-file Java project for simplicity.

The complete implementation is contained in one Java source file rather than being distributed across multiple Java files.

This makes the project easier to:

- Understand
- Compile
- Run
- Demonstrate
- Explain during project reviews

The complete project documentation is also maintained in this single README.md file.

---

## Running the Project

Compile the Java file using:

```bash
javac ContraShell.java
```

Run the program using:

```bash
java ContraShell
```

The shell will start and wait for user commands.

### Example

```
ContraShell started.

> command
Output

> invalidcommand
Unknown command

> exit

ContraShell terminated.
```

---

## Project Learning Outcomes

Through this project, the following concepts are understood practically:

- How a command-line shell works.
- How user input is continuously received.
- How commands are parsed.
- How command arguments are processed.
- How commands are validated.
- How Java interacts with the operating system.
- How errors are handled without unnecessarily terminating the application.
- How OOP concepts can be applied to a practical Java project.
- How an interactive application maintains an execution loop.

---

## Future Scope

The shell can be extended in the future with additional shell functionality such as:

- More built-in commands
- Better command parsing
- Command history
- Input redirection
- Output redirection
- Pipes
- Environment variables
- Background process execution

These features are outside the current implementation and can be added later if required.

---

## Conclusion

ContraShell is a Java-based command-line shell developed from scratch to understand the fundamentals of shell execution and practical Java programming.

The project focuses on keeping the implementation simple while demonstrating important concepts such as command parsing, validation, execution flow, error handling, and Object-Oriented Programming.

The project provides a practical way to understand how commands entered through a terminal can be processed and executed by a Java application.
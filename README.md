# ContraShell

A lightweight command-line shell built in Java.

ContraShell is a learning-oriented shell implementation that demonstrates how a command-line shell receives user input, identifies commands, executes built-in commands, resolves external executables through the system `PATH`, and manages the execution flow.

The project is based on a CodeCrafters-style shell implementation, with additional compatibility adjustments for running on Windows with Java 21.

---

## Features

Currently implemented and tested:

- Built-in `echo` command
- Built-in `pwd` command
- Built-in `cd` command
- Built-in `exit` command
- Built-in `type` command
- External command execution
- Executable lookup through the system `PATH`
- Detection of whether a command is built-in or external
- Unknown command handling
- Windows executable (`.exe`) resolution
- Cross-platform `PATH` separator handling

---

## Tech Stack

- **Language:** Java
- **Java Version:** 21
- **Build Tool:** Maven
- **IDE:** IntelliJ IDEA
- **Platform Tested:** Windows
- **Version Control:** Git / GitHub

---

## Project Structure

```text
ContraShell/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── Main.java
│   │       │
│   │       ├── commands/
│   │       │   ├── Command.java
│   │       │   ├── CdCommand.java
│   │       │   ├── EchoCommand.java
│   │       │   ├── ExitCommand.java
│   │       │   ├── PwdCommand.java
│   │       │   └── TypeCommand.java
│   │       │
│   │       ├── server/
│   │       │   ├── ShellServer.java
│   │       │   └── CommandHandler.java
│   │       │
│   │       ├── parser/
│   │       │   └── ParsedCommand.java
│   │       │
│   │       └── utils/
│   │           └── CommandUtils.java
│   │
│   └── pom.xml
│
└── README.md
# ContraShell

A POSIX-style command-line shell built from scratch in Java. ContraShell reads a command line, parses it, and either dispatches it to a built-in command or resolves it against the system `PATH` and runs it as an external process — the same way `bash` handles the split between built-ins and external binaries.

Originally started as a CodeCrafters "Build Your Own Shell" challenge and extended with a proper package structure and additional built-ins.

---

## Why this project

Shells look simple from the outside but touch a lot of real systems programming: process management, environment variables, the filesystem, and I/O redirection. Building one from scratch — instead of just using one — forces you to actually understand how a terminal turns text into a running process.

---

## Architecture

```
Main
  └─ builds the command registry (Map<String, Command>)
  └─ starts ShellServer

ShellServer               (read-eval loop)
  └─ ParsedCommand         parses raw input into {command, args}
  └─ CommandHandler         dispatches the parsed command
       ├─ found in registry  → Command.execute()
       └─ not found           → CommandUtils resolves it on PATH
                                → ProcessBuilder runs it as a subprocess
```

**Package layout:**

| Package | Responsibility |
|---|---|
| `parser` | Turns a raw input line into a `ParsedCommand` (command + args) |
| `server` | `ShellServer` (the read loop) and `CommandHandler` (dispatch + external process execution) |
| `commands` | The `Command` interface and one class per built-in (`cd`, `pwd`, `echo`, `exit`, `type`) |
| `utils` | `CommandUtils` — PATH resolution and path canonicalization |

Each package has one job. `ShellServer` doesn't know how parsing works; `CommandHandler` doesn't know how any individual command executes; no command class knows about the read loop. That separation is deliberate — it's what makes the project extensible instead of a single tangled `main()`.

---

## Built-in commands

| Command | Behavior |
|---|---|
| `cd [path]` | Changes the working directory. Supports `~` for home, relative and absolute paths. Returns proper POSIX-style errors (`No such file or directory`, `Not a directory`) instead of crashing. |
| `pwd` | Prints the current working directory. |
| `echo [args...]` | Prints its arguments, space-joined. |
| `exit [code]` | Exits the shell. Validates argument count and that the exit code is numeric before exiting. |
| `type [command]` | Reports whether a name is a shell builtin or resolves to an executable on `PATH`. |

Anything else is looked up on `$PATH` and, if found, executed as a subprocess with its stdout/stderr wired directly to the terminal.

---

## Why these design choices

**Why an interface (`Command`) instead of an if/else chain on the command string?**
An if/else ladder means every new command requires editing one growing method, risking regressions in existing commands. With `Command` as an interface, adding a builtin means writing one new class and registering it in `Main` — nothing else changes. This follows the Open/Closed Principle.

**Why a `Map<String, Command>` instead of a list?**
O(1) dispatch lookup, and the same map is reused by `TypeCommand` to answer "is this a builtin?" — no duplicated registry logic.

**Why fall back to `ProcessBuilder` for unknown commands instead of just erroring out?**
Because a real shell resolves anything on `PATH` (`ls`, `cat`, `grep`, …), not just a fixed command set. `CommandUtils.checkCommandInPaths` walks every directory in `PATH`; `CommandHandler.runExecutable` spawns the match as a subprocess with `Redirect.INHERIT` so its output appears directly in the terminal. This is what makes ContraShell behave like an actual shell rather than a command dispatcher with five options.

**Why a separate `ParsedCommand` class instead of parsing inline?**
Single Responsibility: `ShellServer` owns the read loop, `ParsedCommand` owns turning a string into `{command, args}`. If parsing later needs to support quoting or pipes, only this class changes.

**Known limitation:** `cd` uses `System.setProperty("user.dir", ...)` rather than a true OS-level `chdir`, because the JVM has no API to change its process working directory after start. This is enough to make the shell's own commands (like `pwd`) behave correctly, but it won't affect subprocesses spawned via `ProcessBuilder`.

---

## OOP concepts in practice

- **Abstraction / Polymorphism** — the `Command` interface lets `CommandHandler` call `.execute()` without knowing which builtin it's calling.
- **Encapsulation** — `ParsedCommand` bundles the command and its arguments together instead of passing loose strings around.
- **Single Responsibility** — each package (`parser`, `server`, `commands`, `utils`) owns exactly one concern.

---

## Why Java

- Strong static typing and OOP support for an interface-driven command registry
- Built-in `ProcessBuilder` API for spawning and managing external processes
- Platform independence via the JVM
- Mature exception handling for a program that must never crash on bad input

---

## Requirements

- Java 21
- Maven

```bash
java -version
mvn -version
```

---


### Example session

```
$ pwd
/home/user/ContraShell

$ cd ..
$ pwd
/home/user

$ echo hello world
hello world

$ type cd
cd is a shell builtin

$ type ls
ls is /usr/bin/ls

$ ls
README.md  src

$ exit
```

---

## Future scope

- Command history
- Input/output redirection (`>`, `>>`, `<`)
- Pipes (`|`)
- Environment variable expansion
- Background process execution (`&`)
- Quoted-argument parsing (currently splits on plain whitespace)

---

## Conclusion

ContraShell is a Java shell built to understand, hands-on, how a terminal turns typed text into running processes — command parsing, built-in dispatch, PATH resolution, subprocess execution, and the error handling that keeps a shell alive after a bad command.
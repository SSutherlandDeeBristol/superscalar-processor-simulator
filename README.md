# Superscalar Processor Simulator

A superscalar processor simulator written in Java as part of the Advanced Computer Architecture unit.

## Features

The simulator has implemented several features that allow program to be executed super-scalar out of order:

* Reservation Stations
* Reorder Buffer
* Register Renaming
* Operand forwarding/bypassing
* Multiple execution units

## How to run

Ant is used to compile and run the simulator, to compile the project:

```bash
ant compile
```

To run the project:

```bash
ant -Dptp=path/to/assembly/file.asm -Di=true
```

where `-Dptp ` is the filepath of the assembly file and `-Di` is whether to run the simulator in interactive mode.

The build can be cleaned by running:

```bash
ant clean
```

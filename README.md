# Superscalar Processor Simulator

A superscalar processor simulator written in Java as part of the Advanced Computer Architecture unit.

## Features

The simulator has implemented several features that allow programs to be executed super-scalar out of order:

* Reservation Stations
* Reorder Buffer with implicit register renaming
* Operand forwarding/bypassing
* Multiple execution units
* Various methods of branch prediction with a branch target address cache (BTAC)

## How to run

Ant is used to compile and run the simulator, to compile the project:

```bash
ant compile
```

To run the project:

```bash
ant -Dptp=path/to/assembly/file.asm -Di=true -Dwidth=4 -Dbp=2
```

where `-Dptp ` is the relative filepath of the assembly file, `-Di` is whether to run the simulator in interactive mode, `-Dwidth` is the superscalar width and `-Dbp` is the branch predictor.

`-Dbp` options:

`0` : Fixed Taken

`1` : Fixed Not Taken

`2` : Static (Backwards Taken, Forwards Not Taken)

`3` : Dynamic 1-bit History

`4` : Dynamic 3-bit History

All of the assembly files that are guaranteed to work on this simulator are in the `simulator/programs/` directory.

The build can be cleaned by running:

```bash
ant clean
```

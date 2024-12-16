# clox

clox is a bytecode interpreter for Lox. This implementation, written in C, compiles the Lox program to bytecode, which is then run on a stack machine.

## Challenges

I do a few challenges from the 'challenge' sections of the book. They can be found in the [challenges](./challenges) folder.

## How To Run

To run the interpreter, use a compiler to compile the source files. For convenience, you can use the following script `run.sh`
```sh
# run.sh

# Find all .c files in the current directory (without subdirectories)
SRC_FILES=$(find . -maxdepth 1 -name "*.c")

# Compile the C files and generate the executable
gcc -g $SRC_FILES -o clox
```
then run the generated executable:
```
./clox
```
You could also just include `./clox` as the last command in `run.sh` to run the interpreter immediately after compilation.

To disable the debug trace and logs, comment out the following lines in [`common.h`](./common.h) before compiling:
```h
#define DEBUG_PRINT_CODE
#define DEBUG_TRACE_EXECUTION

#define DEBUG_STRESS_GC
#define DEBUG_LOG_GC
```
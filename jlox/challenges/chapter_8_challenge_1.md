### (Chapter 8, Challenge 1) 

In addition to statements, the REPL now allows the user to enter an expression, which is then evaluated then printed.

For example,
```
> 1 + 1
2
```

I also made it so that the REPL evaluates and prints an expression after a statement (or statements, including blocks):
```
> 1 + 2; 2 + 3; 3 + 4
7
> {1 + 2;} {2 + 3;} 3 + 4
7
``` 

I implemented this by converting any trailing expression into an print statement before it is passed to `run()` (which executes statements) in `Lox.java`.
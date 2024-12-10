### (Chapter 24, Challenge 2) Native Function Arity Checking

In this challenge, we add a check to make sure that the number of arguments passed to a native function is correct. To do this, an `arity` field was added to the `ObjNative` struct and initialized when defining a new native function.

The interpreter reports an error if the wrong number of arguments is passed in.

**Example**: The `clock` function has no parameters.
```
> var x = clock(5);
Expected 0 arguments but got 1.
[line 1] in script
```
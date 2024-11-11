### (Chapter 10, Challenge 2) Anonymous Functions 

Add support for anonymous functions. 

How I went about implementing this feature:
1. In addition to function statements (`Stmt.Function`), I added a a function *expression* class (`Expr.Function`), which is almost the same but does not have a field for the name.

2. In order to avoid redundancy, I decided that a function expression should be an instance of the `LoxFunction` class. So, I added another constructor but with an `Expr.Function` type for the parameter. To avoid multiple checks in the class methods, I copied the individual fields from the function to the `LoxFunction` instance.

3. Implement this `anonymousFunction()` method in `Parser.java`; this method is similar to the `function()` method for named function declarations but modified to fit the semantics and grammar of the function expression. 

4. Add the corresponding `visitFunctionExpr()` method in `Interpreter.java`. 

For the precedence, I put higher than and `call` so that this kind of thing works:
```
> fun () { return 7; } ()
7
``` 

How I handled the case of an anonymous function expression occurring in an expression statement:
```
fun () {};
```
My solution to this is kind of a hack. In the `declaration()` method in `Parser.java`, once the parser matches a `fun` keyword, I check to see if the next token is a left parenthesis; if it is, then we try to parse an expression statement, and if not, we parse it as a function declaration as usual.

Note: Due to how I extended the REPL in Challenge 8.1, even though you could enter single expressions in the REPL, it will not work properly when functions are involved, so you would have to use statements with functions.
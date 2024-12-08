### (Chapter 23, Challenge 1) Switch Statement

In this challenge, we add support for the `switch` statement. Unlike the `switch` statement in C and Java, this one does not have fallthrough, so `break` statements are not required; after executing a case's statements, we jump to the end of the switch statement.

Note on scoping: each case has its own scope, so a variable declared in one case cannot be accessed in another case.

**Example**
```
var day = 3;

switch (day) {
    case 1: print "Monday"; 
    case 2: print "Tuesday"; 
    case 3: print "Wednesday"; 
    case 4: print "Thursday"; 
    case 5: print "Friday"; 
    case 6: print "Saturday"; 
    case 7: print "Sunday"; 
    default: print "Invalid day";
}
```
prints `Wednesday`.
### (Chapter 15, Challenge 4) OP_NEGATE Optimization

In this challenge, we try to optimize the negate operation by negating the value in place on the stack instead of popping and pushing.

Original implementation
```c
 case OP_NEGATE: push(-pop()); break;
```

Challenge implementation
```c
 case OP_NEGATE: {
    Value* valuePointer = vm.stackTop - 1;
    *valuePointer = -(*valuePointer);
    break;
 }
```

For testing, we do negation a million times. Code for testing:
```c
#include "common.h"
#include "chunk.h"
#include "debug.h"
#include "vm.h"
#include <time.h>
#include <stdio.h>

int main(int argc, const char* argv[]) {
    initVM();

    Chunk chunk;
    initChunk(&chunk);

    int constant = addConstant(&chunk, 1);
    writeChunk(&chunk, OP_CONSTANT, 200);
    writeChunk(&chunk, constant, 200);

    for (int i = 0; i < 1000000; i++) {
        writeChunk(&chunk, OP_NEGATE, 200);
    }

    writeChunk(&chunk, OP_RETURN, 200);

    clock_t start = clock();
    interpret(&chunk);
    clock_t end = clock();

    freeVM();
    freeChunk(&chunk);

    double duration = (double)(end - start) / CLOCKS_PER_SEC;
    printf("Time: %f\n", duration);
    return 0;
}
```

#### Results (averaged across 5 runs each)
- Text (pop/push) implementation  : `0.0046`
- In place negation implementation: `0.0022`

So the implementation suggested by the challenge is faster.
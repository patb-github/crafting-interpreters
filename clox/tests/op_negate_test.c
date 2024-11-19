#include "../common.h"
#include "../chunk.h"
#include "../debug.h"
#include "../vm.h"
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
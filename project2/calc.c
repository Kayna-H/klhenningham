#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#include "base.h"
#include "operation.h"


int main()
{
    long answer = 0;

    //call method to read value
    long a = readValue();

    //not sure if this will work properly
    char operator = readOperator(skipSpace());

    long b = readValue();

    //read operator and do operations
    if (operator == '/') {
        answer += divide(a, b);
    }

    if (operator == '*') {
        answer += times(a, b);
    }

    if (operator == '+') {
        answer += plus(a, b);
    }

    if (operator == '-') {
        answer = minus(a, b);
    }

    //print out answer to console
    writeValue(answer);

    return 0;
}

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <limits.h>

#define DIVIDE_BY_ZERO_EXIT_STATUS 101
#define OUTSIDE_OF_RANGE_EXIT_STATUS 100

long plus (long a, long b)
{
    if (a >= 0 && b >= 0 && a + b < 0) {
        //overflow has occurred
        //exit status?
        return OUTSIDE_OF_RANGE_EXIT_STATUS;
        //exit();
    }

    else if (a < 0 && b < 0 && a + b > 0) {
        //overflow has occurred, exit
        return OUTSIDE_OF_RANGE_EXIT_STATUS;
    }

    return a + b;
}

long minus(long a, long b)
{
    if (a < 0 && b >= 0 && a - b > 0) {
        //overflow has occurred, exit
        return OUTSIDE_OF_RANGE_EXIT_STATUS;
    }

    //add code to detect overflow
    return a - b;
}

long times(long a, long b)
{
    long x = LONG_MAX / b;

    if (a > 0 && b > 0 && a > x ) {
        //overflow has occurred
        return OUTSIDE_OF_RANGE_EXIT_STATUS;
    }

    return a * b;
}

long divide(long a, long b) {
    //add code to detect over flow

    if (b == 0) {
        return DIVIDE_BY_ZERO_EXIT_STATUS;
    }

    if (a / b > LONG_MAX || a / b < LONG_MIN) {
        return DIVIDE_BY_ZERO_EXIT_STATUS;
    }

    return a / b;
}
#include "base.h"

#include <stdio.h>
#include <stdbool.h>
#include <ctype.h>
#include "operation.h"

// This preprocessor syntax makes it so we can override the value of BASE with
// a compiler option.  For some desired base, n, we can compile with: -DBASE=n
// Don't change these three lines of preprocessor directives.
#ifndef BASE
/** Base this program uses for input and output. */
#define BASE 7
#endif

/*
This function reads characters from standard input.
It keeps reading characters until it reaches a non-whitespace
character or EOF. It returns the code for the first non-whitespace
character (or EOF). For this function, whitespace does not include
the newline character. Code inside base.c or elsewhere in the program
 can use this to easily skip past whitespace within an expression.
 */
int skipSpace()
{
    int ch = getchar();
    while (ch != EOF || ch == ' ') {
        //increment char
        ch = getchar();
    }
    return ch;
}

/*
This function reads the next number from the input
in the current base. If it detects errors in the input number,
it terminates the program with the correct exit status.
 */
long readValue()
{
    //call skip space before reading a number or operator
    skipSpace();

    // Value we've parsed so far.
    int value = 0;

    // Get the next input character.
    //ch = next_input_char();
    int ch = getchar();

    bool isNegative = false;
    if (ch == '-') {
        isNegative = true;
        //value is negative, multiply by -1
    }

    // Keep reading as long as we're seeing digits.
    //WHAT TO DO FOR OPERATORS? WHAT READS THE OPERATOR?
    //use is digit or just as long as we're not seeing whitespace
    //which char is in number range
    while (isdigit(ch)) {
        // Convert from ASCII code for the next digit into the value
        // of that digit.  For example 'A' -> 10 or '7' -> 7
        //  d = char_to_digit( ch );

        int d = 0;

        if (ch >= '0' && ch <= '9') {
            d = ch - '0';
        }

        if (ch >= 'A' && ch <= 'Z') {
            d = ch - 55;
        }

        if (d < 0 || d > BASE - 1) {
            //invalid input, exit with return status
            return FAIL_INPUT;
        }

        // Slide all digits we've read so far one place value to the
        // left.
        value = value * BASE;

        // Add this digit as a new, low-order digit.
        value = value + d;

        // Get the next input character.
        // ch = next_input_char();

        ch = getchar();
    }

    // ch was one character past the end of the number.  Put it back on
    // the input stream so it's there for other code to parse (see notes
    // below about ungetc()).
    ungetc( ch, stdin );

    if (isNegative == true) {
        value = value * -1;
    }

    return value;
}

int readOperator(char ch)
{
    if (ch == '+') {
        return ch;
    }

    else if (ch == '-') {
        return ch;
    }

    else if (ch == '/') {
        return ch;
    }

    else if (ch == '*') {
        return ch;
    }

    //else, input is invalid
    return FAIL_INPUT;
}

//This function prints val to standard output in the current base.
void writeValue(long val)
{
    //add logic to handle negative numbers
    //handle printing 0 as a special case
    if (val == 0) {
        printf("%ld", val);
    }
    //code prints backwards, use recursion
    // While there are more digits to print.
    while ( val != 0 ) {
        // Get the next digit on the right.
        int d = val % BASE;

        // Convert it to a character, e.g, 13 -> 'D' or 3 -> '3'
        //is this pseudocode or a method?
        // ch = digit_to_char( d );


        //convert digit
        char ch = '\0';

        //convert digit as long as it's between 0 and 9
        if (d <= BASE - 1 && d >= 0) {
            ch = '0' + d;
        }

        if (d >= 10) {
            //find a better way to handle this
            ch = 'A' + d;
        }

        //multiply by negative one for negative numbers
        //handling for negative numbers?
        if (val < 0) {
            val = val * -1;
        }

        // Print out the next digit (note, this will give us the digits
        // backward).
        // printf( ch );
        putchar(ch);

        // Slide remaining digits to the right.
        val = val / BASE;
    }

}

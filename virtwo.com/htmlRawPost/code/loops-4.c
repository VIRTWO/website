/*
    This program has a for loop from which you come out on failure of some
    condition that is not mentioned in the condition part of the for
    statement.
*/

#include <stdio.h>

int main(void) {

    int a,b;

    for(a=0,b=0 ; a<10 && b<10 ; a++,b++) {
        /*
            In the loop statement we have initialize two variables
            namely 'a' and 'b' setting them equal to zero.
            The condition/guard here fails when any of the 'a' or 'b'
            becomes equal to or greater than 10.
            a++ and b++ are equal to a=a+1 and b=b+1 and we
            will this writing style after this example.
        */

        printf("Hi! How are you?\n");

        //condition that checks if the sum of a and b is
        //more than 10
        if((a+b)>10)
            break; 

    }

    return 0;
}
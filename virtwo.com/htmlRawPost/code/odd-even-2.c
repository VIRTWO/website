#include <stdio.h>

int main(void) {

    int number=10;

    //we consider number to be checked is 10
    if( (number%2)==0 ) {
	    printf("NUMBER IS EVEN\n");
    } else 
	    printf("NUMBER IS ODD\n");

    /*
        We replaced the second 'if' by an 'else', so what happens now? well, 
        whenever 'if' becomes false then the 'else' statement block gets 
        executed & the block of 'else' is similar to 'if' statement block. 
        So in our example say number was 11 then the 'if' would have become false and
        hence the 'else' gets executed printing it to be odd.
    */

    return 0;
}
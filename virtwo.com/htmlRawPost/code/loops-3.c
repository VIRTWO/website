#include <stdio.h>

int main(void) {

    //count declared and initialized to zero
    int count=0; 

    while(count<10) {

        /*
            When the execution of the program reaches to while statement
            it checks that is the value of count is less than
            10 or not and if it is true then the program execution enters
            the while loop. Here this condition is the guard which
            fails when count equals 10 or becomes greater than 10
        */

        printf("HAPPY PROGRAMMING\n");

        count=count+1;

        /*

            We increase the value of count at the end of each iteration by one,
            so that after the loop has gone through 10 iterations
            i.e. for these values of count 0,1,2,3,4,5,6,7,8 & 9 the guard fails
            and the loop exits that is program proceeds to the code
            after the while loop. If this statement was not there then the loop
            would have run for infinite times, since the guard wouldn’t have failed.
        */

        /*
            In this example you can consider loop invariant as count is
            always les than or equal to 10 as you can see here count            
            takes the value 0~10 and when it becomes 10 guard fails and loop exits.
        */
    }

    return 0;
}
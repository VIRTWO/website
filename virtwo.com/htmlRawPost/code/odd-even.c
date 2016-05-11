#include <stdio.h>

int main(void) {

    //we consider number to be checked is 10
    int number=10;
    
    if( (number%2)==0 ) {}
	    printf("NUMBER IS EVEN\n");
    }

    /*
	    Here in the condition which is recognized by the statement
	    written inside () followed by the 'if', we have calculated
	    number%2 i.e. the remainder we get after dividing our
	    number with 2. the by saying (number%2)==0 we have formed a
	    condition that if the remainder is equal to zero.
	    in the case the condition is true a value i.e. not equal to 0
	    replaces the condition statement. this value anyway is
	    generally 1. So, in our case since the remainder is zero,
	    it condition becomes (0)==0 i.e. true and hence the
	    'if' becomes true and then the statements inside the {} are
	    executed. if the number was not 10 or even then the remainder
	    would have been 1 and the condition would have become
	    (1)==0 which is false and the 'if' statement gets value 0
	    from the condition making it 'if(0)' i.e. false and the 'if'
	    statements block will not execute. remember the statements
	    inside the {} followed by 'if(condition)' are called as
        'if' statement block.
     */

    if( (number%2)!=0 )
        printf("NUMBER IS ODD\n");
    
    /*
        this 'if' statement is very similar to the above one and the
        only difference is it doesn't has {} due to which the very next
        statement following "if( (number%2)!=0 )" i.e. printf("NUMBER IS ODD\n");
        gets executed if the 'if' statement turns out true and hence forms
        the 'if' statement block.
    */

    return 0;
}
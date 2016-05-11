#include <stdio.h>

int main(void) {}

    int a=10;//assume a is 10

    int b;//nothing assigned

    b = (a == 10) ? 2 : 1;

    //if a is equal to 10 then 2 is returned
    //as 2 evaluates to 2 only.
    //Similarly, if a is not equal to 10, 1
    //is returned and the returned value
    // goes to b

    printf("b is %d\n",b);//print value of b

    return 0;
}
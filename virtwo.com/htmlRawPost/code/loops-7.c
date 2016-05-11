#include <stdio.h>

int main(void) {

    int a,b;

    for(a=0,b=0;a<10 && b<10;a++,b++) {
        //if condition
		if((a+b)>10)
            continue;
        printf("Hi! How are you?\n");
     }

    return 0;
}
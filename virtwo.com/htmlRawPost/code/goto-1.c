#include <stdio.h>

int main(void) {

    int a=10;

    //Please note that the indentation
    //is for readability and is not mandatory

	//label name can be anything and is followed by ':'
    LABEL1: 
        a=a+1;//increase a by one and print it
        printf("a is %d\n",a);

    if(a>15)
        goto LABEL2;//if a is more than 15 goto label2
    goto LABEL1; //goto label1

    LABEL2:
        printf("So that was Goto statements example.\n");

    return 0;

}
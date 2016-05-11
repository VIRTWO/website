#include <stdio.h>

int main(void) {

    //menu assumed to be 9 can be any other number also
    int menu=9; 

    //select the variable on which conditions are made
	switch (menu) {
        case 1:
            printf("Menu is 1\n");
            break;
        case 2:
            printf("Menu is 2\n");
            break;
        case 10:
            printf("Menu is 10\n");
            break;
        case 9:
            printf("Menu is 1\n");
            break;
        default:
            printf("This case for all other values of menu\n");
    }

    return 0;
}
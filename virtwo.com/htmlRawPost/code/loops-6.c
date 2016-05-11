#include <stdio.h>

int main(void) {

    int a,i=0,j=0;

    for(a=0;a<10;a++)
        printf("%d %d\n",i++,++j);

    // print i and j

    printf("THIS IS NOT IN FOR LOOP BLOCK\n");

    return 0;
}
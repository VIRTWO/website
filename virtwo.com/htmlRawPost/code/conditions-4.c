#include <stdio.h>

int main(void) {

    int i=0;//assume a is 10

    int b;//nothing assigned

    LABEL1:

       (i==5) ?
           ( 
		       {
		           printf("i has become 5\n");
				   goto LABEL2;
			   }
			):
           (
		       {
			       printf("i is %d\n",i);
				   i=i+1;
				   goto LABEL1;
               }
			);

    //don't get confused with the statement
    //been broken into 3 line. It’s ok and
    //has been done because the whole thing
    //was not coming in one line.

    LABEL2:
        printf("EXAMPLE OVER\n");

    return 0;
}
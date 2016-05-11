#include <stdio.h>

int main(void) {}

    int math_mark=40;

    if (math_mark >= 75) {
	printf("Awesome! Well Done\n");
        printf("You can become a good programmer\n");
    } else if (math_mark >= 65) {
        printf("You performed good buddy.\n");
        printf("Why don't you try your hands on programming?\n");
    } else {
	printf("Sorry! But you Failed Like Me\n");
        printf("Some more study is required dude.\n");
    }

	/*
	    What happens here is first the 'if (mark >= 75)' gets checked,
	    if it is false then the 'else if (mark >= 65)' gets checked and
	    if it is also false then 'else' statement block gets executed.
	    That's it! Rest all remains same as before.
	*/

    return 0;
}
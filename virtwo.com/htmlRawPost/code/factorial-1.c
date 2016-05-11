/*
    PRECONDITION: @Input: N > 0

    We have simplified the form f the factorial function here, so 
    that we can focus on loop-invariants, and not deviate from our aim.
*/

int getFactorial(int N) {
    // This will hold the result at the end of the loop.
    int RESULT = 1; 
    // This variable will control the iterations.
    int i=1;        

    // We have stored the 0! = 1! initially.
    RESULT = 1;     

    for(i=2;i<N;i++)
        RESULT = RESULT * i;

    // In the last step we are calculating the factorial
    // Please note that at every step the factorial of i is calculated

	// At the end of the loop, the factorial of the number N is stored in RESULT
    RESULT = RESULT * N;

    return RESULT;
}

/*
    POSTCONDITION: @Output: RESULT = N!
*/
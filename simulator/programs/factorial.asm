# Calculate the factorial of 5
ldi r0 5
ldi r3 1
ldi r1 1
ldi r2 1
bge r2 r0 3
mul r1 r1 r2
add r2 r2 r3
jmp 4
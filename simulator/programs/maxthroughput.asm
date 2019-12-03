ldi r9 1000
ldi r15 500

loop:
    addi r1 r1 1
    sub r5 r4 r3
    ldi r6 9
    subi r10 r11 1
    ble r1 r15 random
    addi r7 r8 1
    bne r1 r9 loop

end:
    halt

random:
    addi r14 r13 1
    jmp loop
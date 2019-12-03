ldi r0 5
ldi r1 0

loop:
    bge r1 r0 end
    addi r1 r1 1
    jmp loop
end:
    halt
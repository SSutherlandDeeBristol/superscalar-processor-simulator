ldi r0 3457345 # x

# mod return codes
ldi r6 0
ldi r7 1
ldi r9 2
ldi r10 3

nlesseq3:
    ldi r1 3
    ldi r2 1
    bgt r0 r1 nmod2     # if n <= 3
    bgt r0 r2 true      # if n > 1 return true
    jmp false           # else return false

nmod2:
    mov r3 r0           # y = x
    ldi r4 2            # z = 2
    ldi r5 0            # load return code
    jmp mod             # call x mod 2

nmod2result:
    beq r14 r6 false    # if (x mod 2) == 0 return false

nmod3:
    mov r3 r0           # y = x
    ldi r4 3            # z = 3
    ldi r5 1            # load return code
    jmp mod             # call mod

nmod3result:
    beq r14 r6 false    # if (x mod 3) == 0 return false

ldi r8 5                # i = 5
mul r12 r8 r8           # r12 = i * i
bgt r12 r0 true

nmodi:
    mov r3 r0           # y = x
    mov r4 r8           # z = i
    ldi r5 2            # load return code
    jmp mod             # call mod

nmodiresult:
    beq r14 r6 false    # if (x mod i) == 0 return false

nmodi2:
    mov r3 r0           # r3 = x
    addi r4 r8 2        # r4 = i + 2
    ldi r5 3            # load return code
    jmp mod             # call mod

nmodi2result:
    beq r14 r6 false    # if (x mod (i + 2)) == 0 return false

addi r8 r8 6            # i = i + 6
mul r12 r8 r8           # r12 = i * i
ble r12 r0 nmodi        # if (i * i) <= n then loop
jmp true                # else return true

# r14 = r3 % r4
mod:
    mov r13 r3          # z = x

modloop:
    sub r13 r13 r4      # z = z - y
    bge r13 r4 modloop  # loop if z >= y
    mov r14 r13         # set result to z

    # return to correct control flow on completion
    beq r5 r6 nmod2result
    beq r5 r7 nmod3result
    beq r5 r9 nmodiresult
    beq r5 r10 nmodi2result

true:
    ldi r31 1
    jmp end

false:
    ldi r31 -1
    jmp end

end:
    halt

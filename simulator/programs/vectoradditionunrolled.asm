ldi r1 0     # x = 0

ldi r2 0     # base address of vector a
ldi r3 100   # base address of vector b
ldi r4 200   # base address of vector c

# while x < 100
# c[x] = a[x] + b[x]
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1
ldx r5 r2 r1 # r5 = a[x]
ldx r6 r3 r1 # r6 = b[x]
add r7 r5 r6 # r7 = a[x] + b[x]
stx r7 r4 r1 # c[x] = a[x] + b[x]
addi r1 r1 1 # x += 1

end:
    halt
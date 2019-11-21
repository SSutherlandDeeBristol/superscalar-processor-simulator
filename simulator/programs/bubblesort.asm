ldi r9 0 # Base address to store array

# Store the array
sti r9 0 10
sti r9 1 3
sti r9 2 7
sti r9 3 4
sti r9 4 5
sti r9 5 2
sti r9 6 1
sti r9 7 9
sti r9 8 8
sti r9 9 6

# Sort
ldi r0 10 # n = 10

# Start of pass through array
ldi r1 0 # swapped = 0
ldi r2 1 # index = 1

subi r3 r2 1 # r3 = i - 1
ld r4 r3 0 # r4 = Array[i - 1]
ld r5 r2 0 # r5 = Array[i]
ble r4 r5 4 # branch if Array[i - 1] <= Array[i]

# Swap Array[i] and Array[i-1]
st r5 r3 0 # Array[i - 1] = r5
st r4 r2 0 # Array[i] = r4
addi r1 r1 1 # swapped++

addi r2 r2 1 # i++
bne r2 r0 -8 # branch if i != n

bne r1 r9 -11 # repeat the loop if swapped > 0

# Load the array back into the registers
ld r0 r9 0
ld r1 r9 1
ld r2 r9 2
ld r3 r9 3
ld r4 r9 4
ld r5 r9 5
ld r6 r9 6
ld r7 r9 7
ld r8 r9 8
ld r9 r9 9
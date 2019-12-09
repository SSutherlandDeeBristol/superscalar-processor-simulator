# def max_subarray(numbers):
#    best_sum = 0
#    current_sum = 0
#    for x in numbers:
#        best_sum = max(best_sum, current_sum)
#        current_sum = max(0, current_sum + x)
#    return best_sum

ldi r30 0 # base address of array
ldi r29 10 # length of the array

# store the array
sti r30 0 1
sti r30 1 1
sti r30 2 -3
sti r30 3 1
sti r30 4 1
sti r30 5 2
sti r30 6 1
sti r30 7 -1
sti r30 8 -2
sti r30 9 1

ldi r0 0 # best_sum = 0
ldi r1 1 # current_sum = 0

ldi r2 0 # i = 0

# return counters
ldi r8 0
ldi r9 1

# return from max
ldi r10 0

loop:
    mov r3 r30 # x = 0
    ldx r6 r30 r2 # y = A[i]
    add r4 r1 r6 # r7 = current_sum + y
    ldi r10 0 # return code
    jmp max # call max(0, current_sum + y)

max1:
    mov r1 r5 # current_sum = max(0, current_sum + y)
    mov r3 r0 # x = best_sum
    mov r4 r1 # y = current_sum
    ldi r10 1 # return code
    jmp max # call max(best_sum, current_sum)

max2:
    mov r0 r5 # best_sum = max(current_sum, best_sum)
    addi r2 r2 1 # i = i + 1
    blt r2 r29 loop # if i < 10 then loop
    jmp end # else finish

# x = r3
# y = r4
# r5 = max(r3, r4)
max:
    bgt r3 r4 maxx # if x > y
    jmp maxy # else

# return from max function to correct point
maxres:
    beq r10 r8 max1
    beq r10 r9 max2
    jmp end

maxx:
    mov r5 r3 # r5 = x
    jmp maxres

maxy:
    mov r5 r4 # r5 = y
    jmp maxres

end:
    halt

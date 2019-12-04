# Calculate the factorial of 5
ldi r0 5          # x = 5
ldi r3 1          # y = 1
ldi r1 1          # z = 1
ldi r2 1          # w = 1

loop:
    bgt r2 r0 end # while w <= x
    mul r1 r1 r2  # z = z * w
    add r2 r2 r3  # w = w + 1
    jmp loop      # end

end:
    halt
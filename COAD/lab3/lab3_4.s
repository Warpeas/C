.data
    seperate: .asciiz "\n"
.text
    #   the input integer coulbe positive or negative
    #   and all the numbers are represented in 2's complement
    #   so first we get the first position which represents 
    #   positive or negative of the number, and then extend 
    #   the number to all the bits, then xor with the original
    #   number, and finally minus the all bits number
    #   y = x >> 31
    #   abs(x) = x ^ y - y

    #   t0: x
    #   t1: y
    #   t2: x ^ y
    li      $v0, 5
    syscall
    move $t0, $v0
    
    #abs $a0, $t0

    sra     $t1, $t0, 31
    xor     $t2, $t0, $t1
    subu    $a0, $t2, $t1
    li      $v0, 1
    syscall
    
    la $a0, seperate
    li $v0, 4
    syscall
    
    li $v0, 1
    add $t2, $t0, $t1
    xor $a0, $t2, $t1
    syscall

    li      $v0, 10
    syscall

#   recursion

.data
    instruction:    .asciiz "please input an integer "
.text
    la      $a0, instruction
    li      $v0, 4
    syscall

    li      $v0, 5  #   get integer
    syscall

    li      $s1, 0  #   stop
    li      $s2, 10 #   get bit
    addi    $a0, $v0, 0 #   the number

    jal     loop
    
    li      $v0, 10
    syscall
    
loop:   #   recursion
    addi    $sp,$sp,-8
    sw      $ra, 4($sp)
    sw      $v0,($sp)

    div     $a0, $s2    #   number/10
    mfhi    $a0 #   the mod result
    li      $v0, 1
    syscall #   print

    mflo    $a0 #   the left number
    beq     $a0, $s1, end
    jal     loop
    
    end:
    lw      $ra, 4($sp)
    lw      $v0,($sp)
    addi    $sp,$sp,8
    jr      $ra

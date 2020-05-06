#   loop

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
    addi    $t0, $v0, 0 #   the number

    j       loop
start:
    div     $t0, $s2    #   number/10
    mflo    $t0 #   the left number
    mfhi    $a0 #   the mod result
    li      $v0, 1
    syscall
loop:
    bne     $t0, $s1, start
    
    li      $v0, 10
    syscall
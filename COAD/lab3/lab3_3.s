.data
    exceptionData:  .asciiz "Detect overflow, system exit"
.text
    #   Ran on mars
    #   number * (16 - 4 - 2) or number * (8 + 2) or number * (4 + 4 ＋ ２）
    #   I choose to use number * (8 + 2)

    #   t0 has the original number value
    #   t1 has the 8 number value
    #   t2 has 2 times number value
    #   t3 has the first overflow test number value
    li      $v0, 5
    syscall
    move $t0, $v0

    sll     $t1, $t0, 3
    sll     $t3, $t1, 1
    srl     $t3, $t3, 4
    sll     $t2, $t0, 1
    bne     $t3, $t0, exception
    
    add     $a0, $t1, $t2
    li      $v0, 1
    syscall

    end:
    li      $v0, 10
    syscall

    exception:
    la      $a0, exceptionData
    li      $v0, 4
    syscall
    j       end

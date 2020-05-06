.data
    number1: .word  0x55555555
    number2: .word  0xaaaaaaaa
#    number: .word
.text
    #   Ran on mars
    #   t0: number
    #   t1: all 2nth number
    #   t2: all 2n+1th number
    #   t3: xor result

    li      $v0, 5
    syscall
    move $t0, $v0

    #   get 2nth number
    la      $t1, number1
    lw      $t1, 0($t1)
    and     $t1, $t1, $t0
    
    #   get 2n+1th number
    la      $t2, number2
    lw      $t2, 0($t2)
    and     $t2, $t2, $t0

    sll     $t1, $t1, 1
    srl     $t2, $t2, 1

    xor     $t3, $t1, $t2

    move    $a0, $t3
    li      $v0, 35
    syscall

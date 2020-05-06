.data
    number: .space 4
    str1: .asciiz "\nIf little-endian: "
    str2: .asciiz "\nIf big-endian: "
#    number: .word
.text
    #   Ran on mars
    #   The address of number space
    la      $t0, number
    #   read input integer
    li      $v0, 5
    syscall
    sw      $v0, ($t0)
    #   print little-endian result
    li      $v0, 4
    la      $a0, str1
    syscall

    lb      $t1, 0($t0)
    lb      $t2, 1($t0)
    sll     $t2, $t2, 8
    lb      $t3, 2($t0)
    sll     $t3, $t3, 16
    lb      $t4, 3($t0)
    sll     $t4, $t4, 24
    add     $a0, $t1, $t2
    add     $a0, $a0, $t3
    add     $a0, $a0, $t4
    li      $v0, 34
    syscall

    #   print big-endian result
    li      $v0, 4
    la      $a0, str2
    syscall

    lb      $t1, 3($t0)
    lb      $t2, 2($t0)
    sll     $t2, $t2, 8
    lb      $t3, 1($t0)
    sll     $t3, $t3, 16
    lb      $t4, 0($t0)
    sll     $t4, $t4, 24
    add     $a0, $t1, $t2
    add     $a0, $a0, $t3
    add     $a0, $a0, $t4
    li      $v0, 34
    syscall

    li      $v0, 10
    syscall

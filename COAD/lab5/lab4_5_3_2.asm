.data
    space:  .asciiz " "
.text
#   The code mainly from lecture 5 slide and do a lot effect to make it work
.globl sort
.globl print_array
sort:
    addi $sp, $sp, -20
    sw $ra, 16($sp)
    sw $s3, 12($sp)
    sw $s2, 8($sp)
    sw $s1, 4($sp)
    sw $s0, 0($sp)

    move $s2, $a0   #   s2: The start address of the array
    move $s3, $a1   #   s3: The size of the array

    addi    $s0, $zero, 1 #   s0: represents i, start with i = 1
loopbody1:
    bge     $s0, $s3, exit1 #   if i >= n, then exit
    addi $s1, $s0, -1 # s1: j, start with j = i - 1

loopbody2:
    blt $s1, $zero, exit2 # j < 0, then back to out loop
    sll $t1, $s1, 2
    add $t2, $a0, $t1   #   t2 has the position of array[j]
    lw $t3, 0($t2)      #   t3 has the value of array[j]
    lw $t4, 4($t2)      #   t4 has the value of array[j+1]
    bgt $t4, $t3, exit2 #   if t4 > t3, then no need to swap
    #   else
    move    $a0, $s2        #   a0 is the address of the array 
    move    $a1, $s1        #   a1 is j
    jal swap

    addi $s1, $s1, -1
    j loopbody2
exit2:
    addi $s0, $s0, 1
    j loopbody1

exit1:
    lw $ra, 16($sp)
    lw $s3, 12($sp)
    lw $s2, 8($sp)
    lw $s1, 4($sp)
    lw $s0, 0($sp)
    addi $sp, $sp, 20
    jr      $ra

swap:
    sll $t1, $a1, 2 #   j * 4 to get the position
    add $t1, $a0, $t1   #   t1 has the postion
    lw $t0, 0($t1)
    lw $t2, 4($t1)
    sw $t2, 0($t1)
    sw $t0, 4($t1)
    jr $ra

print_array:
    addi $sp, $sp, -20
    sw $ra, 16($sp)
    sw $s3, 12($sp)
    sw $s2, 8($sp)
    sw $v0, 4($sp)
    sw $s0, 0($sp)

    move $s2, $a0   #   s2: The start address of the array
    move $s3, $a1   #   s3: The size of the array, n

    addi    $s0, $zero, 0 #   s0: represents i, start with i = 0
loop:
    bge     $s0, $s3, exit3 #   if i >= n, then exit
    sll     $t1, $s0, 2 #   get 4 * i
    add     $t2, $t1, $s2   #   get the correspond position
    lw      $a0, 0($t2)
    li      $v0, 1
    syscall
    la      $a0, space
    li      $v0, 4
    syscall
    addi    $s0, $s0, 1
    j       loop

exit3:
    lw $ra, 16($sp)
    lw $s3, 12($sp)
    lw $s2, 8($sp)
    lw $v0, 4($sp)
    lw $s0, 0($sp)
    addi $sp, $sp, 20
    jr	     $ra

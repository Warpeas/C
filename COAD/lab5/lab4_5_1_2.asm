#   function

.data
time:   .asciiz " * "
equal:  .asciiz " = "
space:  .asciiz "\t"
.text
.globl print_matrix
print_matrix: 
addi $sp,$sp,-4
sw $v0,($sp)

li $v0,1
addi    $t0, $a0, 0
addi    $t1, $a1, 0
mul     $t2, $t0, $t1   #   calculate a*b
syscall #   print a
la      $a0, time
li      $v0, 4
syscall #   print *
addi    $a0, $a1, 0
li      $v0, 1
syscall #   print b
la      $a0, equal
li      $v0, 4
syscall #   print =
addi    $a0, $t2, 0
li      $v0, 1
syscall #   print result
li      $v0, 4
la      $a0, space
syscall

lw $v0,($sp)
addi $sp,$sp,4
jr $ra

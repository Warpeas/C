#   main

.data
next_line:  .asciiz "\n"
.text
.globl main
main:
li      $s0, 9
li      $s1, 0  #$s1    a
out_loop:
la      $a0, next_line
li      $v0, 4
syscall

addi    $s1, $s1, 1
li      $s2, 0  #$s2    b
in_loop:
addi    $s2, $s2, 1
addi    $a0, $s1, 0
addi    $a1, $s2, 0
jal print_matrix

bne     $s2, $s0, in_loop
bne     $s1, $s0, out_loop

li      $v0, 10
syscall

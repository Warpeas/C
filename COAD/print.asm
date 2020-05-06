#   marcos
.macro print_string(%str)
.data
pstr: .asciiz %str
.text
la $a0,pstr
li $v0,4
syscall
 .end_macro
.macro end
li $v0,10
syscall 
.end_macro

.macro print_integer(%int)
.text
addi    $sp, $sp, -8
sw      $a0, 4($sp)
sw      $v0, 0($sp)
move  $a0, %int
li  $v0, 1
syscall
lw      $a0, 4($sp)
lw      $v0, 0($sp)
addi    $sp, $sp, 8
.end_macro

.macro print_float
.text
addi $sp,$sp,-4
sw $v0,0($sp)
li $v0,2
syscall
lw $v0,0($sp)
addi $sp,$sp,4
.end_macro

.macro print_float(%fr)
.text
addi $sp,$sp,-8
swc1 $f12,4($sp)
sw $v0,0($sp)
mov.s $f12,%fr
li $v0,2
syscall
lw $v0,0($sp)
lwc1 $f12,4($sp)
addi $sp,$sp,8
.end_macro

.macro print_float_with_two_bits_behind
.text
addi $sp,$sp,-8
swc1 $f0, 4($sp)
sw $v0,0($sp)
li      $t0, 100
mtc1    $t0, $f0
cvt.s.w $f0, $f0
mul.s   $f12, $f12, $f0
round.w.s   $f12, $f12
cvt.s.w $f12, $f12
div.s   $f12, $f12, $f0
li $v0,2
syscall
lw $v0,0($sp)
lwc1 $f0,4($sp)
addi $sp,$sp,8
.end_macro

.macro print_float_with_two_bits_behind(%fr)
.text
addi $sp,$sp,-12
swc1 $f0, 8($sp)
swc1 $f12,4($sp)
sw $v0,0($sp)
mov.s $f12,%fr
li      $t0, 100
mtc1    $t0, $f0
cvt.s.w $f0, $f0
mul.s   $f12, $f12, $f0
round.w.s   $f12, $f12
cvt.s.w $f12, $f12
div.s   $f12, $f12, $f0
li $v0,2
syscall
lw $v0,0($sp)
lwc1 $f12,4($sp)
lwc1 $f0,8($sp)
addi $sp,$sp,12
.end_macro

.macro print_double(%fr)
.text
addi $sp,$sp,-12
swc1 $f13,8($sp)
swc1 $f12,4($sp)
sw $v0,0($sp)
mov.s $f12,%fr
li $v0,2
syscall
lw $v0,0($sp)
lwc1 $f13,8($sp)
lwc1 $f12,4($sp)
addi $sp,$sp,8
.end_macro

.macro read_integer(%r)
.text
addi    $sp, $sp, -4
sw      $v0, 0($sp)
li      $v0, 5
syscall
move    %r, $v0
lw      $v0, 0($sp)
addi    $sp, $sp, 4
.end_macro

.macro read_float(%r)
.text
addi    $sp, $sp, -4
sw      $v0, 0($sp)
li      $v0, 6
syscall
mov.s   %r, $f0
lw      $v0, 0($sp)
addi    $sp, $sp, 4
.end_macro

.macro read_double(%r)
.text
addi    $sp, $sp, -4
sw      $v0, 0($sp)
li      $v0, 7
syscall
mov.d   %r, $f0
lw      $v0, 0($sp)
addi    $sp, $sp, 4
.end_macro

.macro end
    li      $v0,10
    syscall
.end_macro

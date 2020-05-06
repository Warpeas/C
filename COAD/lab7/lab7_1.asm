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

.data
    c1: .float  1.0
    c2: .float  2.0
    zero:   .float 0.0
.text
    #   input integer: $f2
    #   precesion:  $f4
    #   result: $f6
    #   tmp: $f10
    #   check: $f12
    print_string("Please input an integer: ")
    read_float($f2)
    lwc1	$f16, zero
    c.lt.s	$f2, $f16
    bc1t    negative_error
    c.eq.s  $f2, $f16
    bc1t    zero_output
    print_string("Input the precision: ")
    read_float($f4)

    # mtc1    $s0, $f2
    lwc1    $f6, c1
    lwc1    $f8, c2

sqrt:
    div.s   $f10, $f2, $f6
    add.s   $f10, $f10, $f6
    div.s   $f6, $f10, $f8

    mul.s   $f12, $f6, $f6
    sub.s   $f12, $f12, $f2
    abs.s   $f12, $f12
    c.lt.s  $f4, $f12
    bc1t    sqrt

    print_string("The result is: ")
    print_float($f6)
    end
zero_output:
    print_string("The result is: ")
    print_float($f2)
    end
negative_error: 
    # lw      $a0, negative_error
    # li      $v0, 4
    # syscall
    print_string("Error. Input integer is negative")
    end

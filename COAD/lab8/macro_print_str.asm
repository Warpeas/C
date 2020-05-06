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
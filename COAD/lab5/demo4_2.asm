.data
.extern defaulte_str 20
str_callee: .asciiz "it's in print callee."
.text
.globl print_callee
print_callee: addi $sp,$sp,-4
sw $v0,($sp)
li $v0,0x0a636261
sw $v0,defaulte_str
addi $v0,$zero,4
la $a0,str_callee
syscall
la $a0,defaulte_str
syscall
lw $v0,($sp)
addi $sp,$sp,4
jr $ra

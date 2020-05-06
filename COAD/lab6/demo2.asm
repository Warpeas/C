.data
str: .asciiz "hello"
.text
print_string:
addi $sp,$sp,-4
sw $v0,($sp)
la $a0,str
li $v0,4
syscall
lw $v0,($sp)
addi $sp,$sp,4
addi $ra,$zero,0xffffffff
jr $ra
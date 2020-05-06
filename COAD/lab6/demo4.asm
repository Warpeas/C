.data
	addend1: .word 0xffffffff
	addend2: .word 0xffffffff
.text
	print_string:
	lw $t0,addend1
	li $v0,	1
	lw $a0, addend1
	syscall
	lw $t1,addend2
	add $a0,$t0,$t1
	li $v0,1
	syscall
	li $v0,10
	syscall

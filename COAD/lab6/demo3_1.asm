.include "../macro_print_str.asm"
.data
	str: .asciiz "data is:"
	.align 2
	bs: .byte 1:10
	ws: .word 2:10
.text
	print_string("data is:")
	add $t0,$zero,$zero
	addi $t1,$zero,10
loop_out:
	lw $a0,bs
	li $v0,1
	syscall
	add $t0,$t0,1
	bne $t0,$t1,loop_out
end

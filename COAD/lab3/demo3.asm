#signed vs unsigned
.include "macro_print_str.asm"
.data
tdata: .word 0x11111111
#tdata: .word 0x71111111
.text
main:
lw $t0,tdata
addu $a0,$t0,$t0
li $v0,1
syscall
print_string("\n")
add $a0,$t0,$t0
li $v0,1
syscall
end
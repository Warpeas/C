#signed-extended vs zero extended
.include "macro_print_str.asm"
.data
tdata: .byte 0x80
.text
main:
lb $a0,tdata
#lbu     $a0, tdata
li $v0,1
syscall
print_string("\n")
lb $a0,tdata
#lbu     $a0, tdata
li $v0,36
syscall
end
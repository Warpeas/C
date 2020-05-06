.include "../macro_print_str.asm"
.data
tdata: .word 0
.text
add $t1,$zero,$zero
addi $t0,$zero,0
addi $t7,$zero,10
j for
calcu:
    addi $t0,$t0,1
    add $t1,$t1,$t0
for: bgt $t7,$t0,calcu

print_string("The sum from 1 to 10 : ")
move $a0,$t1
li $v0,1
syscall
end
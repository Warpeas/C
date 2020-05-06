.include "macro_print_str.asm"
.data
min: .word 0
max: .word 0
.text
lw $t0,min
lw $t1,max
li $t7,5
li $t6,0
print_string("please input 5 integer:")
loop:
li $v0,5
syscall
bgt $v0,$t1,get_max
j get_min

get_max:
move $t1,$v0
j get_min
get_min:
bgt $v0,$t0,judge_times
move $t0,$v0
j judge_times

judge_times:
addi $t6,$t6,1
bgt $t7,$t6,loop
print_string("min : ")
move $a0,$t0
li $v0,1
syscall
print_string("max : ")
move $a0,$t1
li $v0,1
syscall
end
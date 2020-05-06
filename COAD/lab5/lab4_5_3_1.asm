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
.data

.text
.globl main
main:
    print_string("please input the number:")
    li $v0,5        #read a integer
    syscall
    move $s0,$v0    #s0 is the number of integer
    beq     $s0, $zero, fin
    sll $a0,$s0,2   #new a heap with 4*$s0
    li $v0,9
    syscall
    move $s1,$v0    #$s1 is the start of the heap
    move $s2,$v0    #$s2 is the point
    print_string("please input the array\n")
    add $t0,$0,$0
    j loop_start

    loop_read:
    li $v0,5        #read the array
    syscall
    sw $v0,($s2)
    addi $s2,$s2,4
    addi $t0,$t0,1
    loop_start: bne $t0,$s0,loop_read

    move    $a0, $s1
    move    $a1, $s0
    jal     sort
    move    $a0, $s1
    move    $a1, $s0
    jal     print_array

fin:
    end

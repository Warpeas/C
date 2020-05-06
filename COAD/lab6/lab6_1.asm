#   calculator
#   $s0:    operand1
#   $s1:    operator
#   $s2:    operand2
#   $s3:    result
#   $s4:    remainder

.data
    nextLine:   .asciiz "\n"
    division:   .asciiz "/"
    multiplication:   .asciiz "*"
    addition:   .asciiz "+"
    subtraction:    .asciiz "-"
    equal:  .asciiz " = "
    space:  .asciiz " "
.text
.globl main
main:
    li      $a1, 0
    #   read data
    li      $v0, 5
    syscall
    move    $s0, $v0

    li      $v0, 12
    syscall
    move    $s1, $v0

    la      $a0, nextLine
    li      $v0, 4
    syscall

    li      $v0, 5
    syscall
    move    $s2, $v0

    #   no use now
    # move    $a0, $s0
    # move    $a1, $s1
    # move    $a2, $s2

    jal     calculator
    j       end

calculator:   
    addi    $sp,$sp,-8
    sw      $a0, 4($sp)
    sw      $v0,($sp) 

    #	match operator
    la      $t0, division
    lb      $t1, 0($t0)
    beq     $s1, $t1, divide

    la      $t0, multiplication
    lb      $t1, 0($t0)
    beq     $s1, $t1, multiply

    la      $t0, addition
    lb      $t1, 0($t0)
    beq     $s1, $t1, plus

    la      $t0, subtraction
    lb      $t1, 0($t0)
    beq     $s1, $t1, minus
    
    #	if the operator not match
    li		$a1, -4
    break

divide:
    # teq     $s2, $zero
    li      $a1, 16
    div     $s3, $s0, $s2
    mfhi    $s4
    j       print_result

multiply:
    mulo    $s3, $s0, $s2
    j       print_result

plus:
    add     $s3, $s0, $s2
    j       print_result

minus:
    sub     $s3, $s0, $s2
    j       print_result

print_result:
    li      $v0, 1
    addi	$a0, $s0, 0
    syscall

    li		$v0, 4
    la		$a0, space
    syscall
    
    li		$v0, 11
    addi	$a0, $s1, 0
    syscall

    li		$v0, 4
    la		$a0, space
    syscall
    
    li		$v0,	1
    addi	$a0, $s2, 0
    syscall
    
    li		$v0, 4
    la		$a0, equal
    syscall
    
    li		$v0, 1
    addi    $a0, $s3, 0
    syscall

    lw      $a0, 4($sp)
    lw      $v0,($sp)
    addi    $sp,$sp,8
    jr      $ra

end:
    li      $v0,10
    syscall

.ktext 0x80000180
    
    move    $s5, $v0
    move    $s6, $a0

	mfc0    $k0, $13		# Cause register
	add		$k0, $k0, $a1
	# Print information about exception.
	li      $v0, 4		# syscall 4 (print_str)
	la      $a0, m1
	syscall

    li      $v0, 34
    mfc0      $a0, $14
    syscall

    li		$v0, 4
    la		$a0, space
    syscall

	li      $v0, 1		# syscall 1 (print_int)
	srl     $a0, $k0, 2		# Extract ExcCode Field
	andi    $a0, $a0, 0xf
	syscall

	li $v0, 4		# syscall 4 (print_str)
	andi $a0, $k0, 0x3c
    subi $a0, $a0, 32
	lw $a0 excp($a0)
	syscall

	li $v0 4		# syscall 4 (print_str)
	la $a0 m2
	syscall

	li $v0 10		# Exit on really bad PC
	syscall

.kdata	
    # Define the exception handling code.  This must go first!
    m1:	    .asciiz	"Runtime exception at 0x"
    m2:	    .asciiz	"occurred and exit\n"
    e8:	    .asciiz	": [Illegal input] "
    e9:	    .asciiz	": [Arithmetic overflow] "
    e10:    .asciiz ""
    e11:    .asciiz ""
    e12:	.asciiz	":  [Arithmetic overflow] "
    e13:	.asciiz	":  [Devide by zero] "
    excp:	.word e8, e9, e10, e11, e12, e13

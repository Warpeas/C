#   marcos
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

.macro print_integer(%int)
.text
addi    $sp, $sp, -8
sw      $a0, 4($sp)
sw      $v0, 0($sp)
move  $a0, %int
li  $v0, 1
syscall
lw      $a0, 4($sp)
lw      $v0, 0($sp)
addi    $sp, $sp, 8
.end_macro

.macro print_float
.text
addi $sp,$sp,-4
sw $v0,0($sp)
li $v0,2
syscall
lw $v0,0($sp)
addi $sp,$sp,4
.end_macro

.macro print_float(%fr)
.text
addi $sp,$sp,-8
swc1 $f12,4($sp)
sw $v0,0($sp)
mov.s $f12,%fr
li $v0,2
syscall
lw $v0,0($sp)
lwc1 $f12,4($sp)
addi $sp,$sp,8
.end_macro

.macro print_float_with_two_bits_behind
.text
addi $sp,$sp,-8
swc1 $f0, 4($sp)
sw $v0,0($sp)
li      $t0, 100
mtc1    $t0, $f0
cvt.s.w $f0, $f0
mul.s   $f12, $f12, $f0
round.w.s   $f12, $f12
cvt.s.w $f12, $f12
div.s   $f12, $f12, $f0
li $v0,2
syscall
lw $v0,0($sp)
lwc1 $f0,4($sp)
addi $sp,$sp,8
.end_macro

.macro print_float_with_two_bits_behind(%fr)
.text
addi $sp,$sp,-12
swc1 $f0, 8($sp)
swc1 $f12,4($sp)
sw $v0,0($sp)
mov.s $f12,%fr
li      $t0, 100
mtc1    $t0, $f0
cvt.s.w $f0, $f0
mul.s   $f12, $f12, $f0
round.w.s   $f12, $f12
cvt.w.s $f12, $f12
div.s   $f12, $f12, $f0
li $v0,2
syscall
lw $v0,0($sp)
lwc1 $f12,4($sp)
lwc1 $f0,8($sp)
addi $sp,$sp,12
.end_macro

.macro print_double(%fr)
.text
addi $sp,$sp,-12
swc1 $f13,8($sp)
swc1 $f12,4($sp)
sw $v0,0($sp)
mov.s $f12,%fr
li $v0,2
syscall
lw $v0,0($sp)
lwc1 $f13,8($sp)
lwc1 $f12,4($sp)
addi $sp,$sp,8
.end_macro

.macro read_integer(%r)
.text
addi    $sp, $sp, -4
sw      $v0, 0($sp)
li      $v0, 5
syscall
move    %r, $v0
lw      $v0, 0($sp)
addi    $sp, $sp, 4
.end_macro

.macro read_float(%r)
.text
addi    $sp, $sp, -4
sw      $v0, 0($sp)
li      $v0, 6
syscall
mov.s   %r, $f0
lw      $v0, 0($sp)
addi    $sp, $sp, 4
.end_macro

.macro read_double(%r)
.text
addi    $sp, $sp, -4
sw      $v0, 0($sp)
li      $v0, 7
syscall
mov.d   %r, $f0
lw      $v0, 0($sp)
addi    $sp, $sp, 4
.end_macro

.macro end
    li      $v0,10
    syscall
.end_macro

.data
    studens1:   .space  20
    studens2:   .space  20
    student3:   .space  20
    p:          .word   studens1,studens2,student3
    q1:     .space  4
    q2:     .space  4
    q3:     .space  4
    q4:     .space  4
    q5:     .space  4
    questions:  .word   q1, q2, q3, q4, q5
    zero:   .float  0.0
    three:  .float  3.0
    five:   .float  5.0
.text
    #   $t0:    integer output tmp
    #   $f12:   float output tmp
    #   $s1:    question cnt
    #   $s2:    student cnt
    #   $s3:    address of students store position
    #   $s4:    address of questions
    #   $t4:    5
    #   $t5:    2
    #   $f2:    total average
    #   $f4:    float tmp

    li      $s2, -1
    li      $t4, 4
    li      $t5, 2
    lwc1	$f16, zero
    lwc1    $f18, three
    lwc1    $f17, five
    mul.s   $f19, $f17, $f18

start:
    li      $s1, -1
    addi    $s2, $s2, 1
    sll     $t2, $s2, 2
    lw      $s3, p($t2)
inputloop:
    addi    $s1, $s1, 1
    print_string("Please input the score of student ")
    addi    $t0, $s2, 1
    print_integer($t0)
    print_string(" in question ")
    addi    $t0, $s1, 1
    print_integer($t0)
    print_string(" :")
    li      $v0, 6
    syscall

    c.lt.s	$f0, $f16
    bc1t    out_of_range_error
    c.lt.s  $f17, $f0
    bc1t    out_of_range_error

    sll     $t1, $s1, 2
    add     $t3, $t1, $s3
    swc1    $f0,0($t3)

    blt     $s1, $t4, inputloop
    blt     $s2, $t5, start

    print_string("Input over\n")

    li      $s2, -1

total_start:
    addi    $s2, $s2, 1
    li      $s1, -1
    lwc1    $f12, zero
    sll     $t2, $s2, 2
    lw      $s3, p($t2)
total_score:
    addi    $s1, $s1, 1
    addi    $t0, $s1, 1

    sll     $t1, $s1, 2
    add     $t3, $t1, $s3
    lwc1    $f0, 0($t3)
    add.s   $f12,$f12,$f0

    blt     $s1, $t4, total_score

    print_string("The total score of student ")
    addi    $t0, $s2, 1
    print_integer($t0)
    print_string(" is ")
    print_float_with_two_bits_behind
    print_string("\n")

    blt     $s2, $t5, total_start

    li      $s1, -1
question_start:
    addi    $s1, $s1, 1
    lwc1    $f12, zero
    li      $s2, -1
question_average:
    addi    $s2, $s2, 1
    sll     $t2, $s2, 2
    lw      $s3, p($t2)

    sll     $t1, $s1, 2
    add     $t3, $t1, $s3

    lwc1    $f0, 0($t3)
    add.s   $f12,$f12,$f0

    blt     $s2, $t5, question_average

    print_string("The average score of question ")
    addi    $t0, $s1, 1
    print_integer($t0)
    print_string(" is ")
    div.s   $f12, $f12, $f18
    sll     $t1, $s1, 2
    swc1    $f12, questions($t1)
    print_float_with_two_bits_behind
    print_string("\n")

    blt     $s1, $t4, question_start

    li      $s2, -1
    lwc1    $f12, zero

average_start:
    addi    $s2, $s2, 1
    li      $s1, -1
    sll     $t2, $s2, 2
    lw      $s3, p($t2)
average_score:
    addi    $s1, $s1, 1
    addi    $t0, $s1, 1

    sll     $t1, $s1, 2
    add     $t3, $t1, $s3
    lwc1    $f0, 0($t3)
    add.s   $f12,$f12,$f0

    blt     $s1, $t4, average_score
    blt     $s2, $t5, average_start
    div.s   $f12, $f12, $f19
    print_string("The total average score of the questions is ")
    print_float_with_two_bits_behind
    print_string("\n")

    li      $s1, -1
not_good:
    addi    $s1, $s1, 1
    sll     $t1, $s1, 2
    lwc1    $f12, questions($t1)
    c.lt.s  $f12, $f2
    bc1t    small
back:
    blt     $s1, $t5, not_good

    end

out_of_range_error: 
    # lw      $a0, negative_error
    # li      $v0, 4
    # syscall
    print_string("Error. Input integer is out of range\n")
    end

small:
    addi    $t0, $s1, 1
    print_string("The ")
    print_integer($t0)
    print_string("th question is not good with score ")
    print_float_with_two_bits_behind
    print_string("\n")
    j       back

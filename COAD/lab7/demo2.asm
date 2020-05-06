##piece 1/2 of code##
.include "../macro_print_str.asm"
.data
    str1: .asciiz "str1:"
    fd1: .float 1.0
    dd1: .double 2.0
.text
    lwc1    $f0, fd1
    ldc1    $f2, dd1
    mov.s    $f12, $f0
    li $v0 2
    syscall
    c.le.d	$f0, $f2
    bc1t printLe
    j printGt

##piece 2/2 of code##
printLe:
    print_string(" LessOrEqual ")
    j printSecondData
printGt:
    print_string(" Large Than")
printSecondData:
	mov.d	$f12, $f2
    li $v0,3
    syscall
end

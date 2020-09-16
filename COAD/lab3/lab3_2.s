.data 0x0000
    number1: .word  0x55555555
    number2: .word  0xaaaaaaaa
#    number: .word
.text 0x0000
start:ori $t0,$zero,6
	ori	$t1, $zero, 0
    lw      $t1, number1($t1)
    and     $t1, $t1, $t0
    
    #   get 2n+1th number
    ori	$t2, $zero, 0
    lw      $t2, number2($t2)
    and     $t2, $t2, $t0

    sll     $t1, $t1, 1
    srl     $t2, $t2, 1

    xor     $t3, $t1, $t2

	#    move    $a0, $t3
	#    li      $v0, 35
	#    syscall

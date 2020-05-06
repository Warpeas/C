.data
    dmsg: .asciiz "\ndata over"
.text
main:
    li $v0,5
    syscall
    teqi $v0,0
    la $a0,dmsg
    li $v0,4
    syscall
    li $v0,10
    syscall

.ktext 0x80000180
    move $k0,$v0
    move $k1,$a0
    la $a0,msg
    li $v0,4
    syscall
    move $v0,$k0
    move $a0,$k1
    mfc0 $k0,$14
    addi $k0,$k0,4
    mtc0 $k0,$14
    eret
.kdata
    msg: .asciiz "\nTrap generated"

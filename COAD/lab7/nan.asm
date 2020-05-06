.data
    sdata: .word 0xffff7fff
    fneg1: .float -1
    zdata: .word 0x007fffff
.text
    lw $t0,sdata
    mtc1 $t0,$f1
    mul.s $f12,$f1,$f1
    li $v0,2
    syscall
    lwc1 $f2,fneg1
    div.s $f12,$f12,$f12
    li $v0,2
    syscall
    li $v0,10
    syscall
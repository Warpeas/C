#big-endian and small-endian
.include "macro_print_str.asm"
.data
    tdata0: .byte 0x11,0x22,0x33,0x44
    tdata: .word 0x44332211
.text
main:
    lh      $a0, tdata	#ourput	0x00002211
    #lb $a0,tdata
    li $v0,34
    syscall
    end

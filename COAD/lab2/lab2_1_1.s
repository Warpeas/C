.data
    H1: .word   0
    M1: .word   37
    S:  .word   2
    R:  .word   60
    Takes:  .asciiz "Takes "
    AND:    .asciiz " hour(s) and "
    Mi: .asciiz " minute(s)"

.text
    #t0: H1
    #t1: M1
    #t2: S
    #t3: R
    #t4: Lo(Minutes)
    #t5: t4/60 (Hour)
    #t6: t4%60 (Minutes)

    lw      $t0, H1	#t0=hours
    lw      $t1, M1	#t1=miniutes
    lw      $t2, S	#t2=distance
    lw      $t3, R	#t3=speed

    mul     $t2, $t2, 60	#t2=distance*60
    
    div     $t2, $t3	#hi=0(ignore overflow), lo=minutes
    mflo    $t4	#t4=minutes
    
    li      $t5, 60	#t5=60
    div     $t4, $t5	#hi=minutes, lo=hours
    mflo    $t5 #t5=hours
    mfhi    $t6 #t6=minutes

    li      $v0, 4
    la      $a0, Takes
    syscall
    
    add     $a0, $t0, $t5   #a0=hours
    li      $v0, 1
    syscall

    li      $v0, 4
    la      $a0, AND
    syscall
    
    add     $a0, $t1, $t6   #a0=minutes
    li      $v0, 1
    syscall

    li      $v0, 4
    la      $a0, Mi
    syscall

    li      $v0, 10
    syscall

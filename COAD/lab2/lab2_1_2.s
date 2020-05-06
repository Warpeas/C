.data
    H1: .asciiz "H1: "
    M1: .asciiz "M1: "
    S:  .asciiz "S: "
    R:  .asciiz "R: "
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
    li      $v0, 4
    la      $a0, H1
    syscall
    li      $v0, 5
    syscall
    move      $t0, $v0	#t0=hours

    li      $v0, 4
    la      $a0, M1
    syscall
    li      $v0, 5
    syscall
    move      $t1, $v0	#t1=miniutes

    li      $v0, 4
    la      $a0, S
    syscall
    li      $v0, 5
    syscall
    move      $t2, $v0	#t2=distance

    li      $v0, 4
    la      $a0, R
    syscall
    li      $v0, 5
    syscall
    move      $t3, $v0	#t3=speed

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

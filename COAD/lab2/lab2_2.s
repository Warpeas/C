.data
    abc:    .asciiz "abc"
    aAbBcC: .space  6

.text
main:
    #t0: original abc
    #t1: read abc
    #t2: aAbBcC position
    la      $t0, abc
    lb		$t1,0($t0)
    la      $t2, aAbBcC

    sb      $t1, ($t2)
    sub    $t1, $t1, 32
    sb      $t1, 1($t2)

    lb      $t1, 1($t0)
    sb      $t1, 2($t2)
    sub    $t1, $t1, 32
    sb      $t1, 3($t2)
    
    lb      $t1, 2($t0)
    sb      $t1, 4($t2)
    sub    $t1, $t1, 32
    sb      $t1, 5($t2)
    
    lb      $t1, 3($t0)
    sb      $t1, 6($t2)
    
    li $v0,	4
    la $a0, aAbBcC
    syscall
    
    li $v0,10
    syscall
fib:    addi    $sp, $sp, -12   #   make room on stack
        sw      $ra, 8($sp)     #   push $ra
        sw      $s0, 4($sp)     #   push $s0
        sw      $a0, 0($sp)     #   if n>0, test if n=1
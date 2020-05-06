.include "../macro_print_str.asm"
.data
.text
    print_string("please input your score (0~100):")
    li $v0,5
    syscall
    move $t0,$v0
case1:
    bgt $t0,90,gt90_lable
case2:
    bge $t0,70,gt70lt90_lable
case3:
    print_string(" \nNOT GOOD(less than 70)")
    j case_end
gt90_lable:
    print_string(" \nEXCELLENT (exceed 90) ")
    j case_end
gt70lt90_lable:
    print_string(" \nGOOD(70~90)")
    j case_end
case_end:
end
.include "../macro_print_str.asm"
.data
    f1: .float 12.625
.text
    lwc1 $f0,f1
    floor.w.s $f1,$f0
    ceil.w.s $f2,$f0
    round.w.s $f3,$f0
    print_string("orignal float: ")
    print_float($f0)
    print_string("\nafter floor:")
    print_float($f1)
    print_string("\nafter ceil:")
    print_float($f2)
    print_string("\nafter round:")
    print_float($f3)
end
module andgate
#(parameter Port_Num = 2, // Specify the default input is 2 input ports
parameter WIDTH=8) // Specify the data width parameter, the default value is 8
(
input [(WIDTH-1):0] a,
input [(WIDTH-1):0] b,
input [(WIDTH-1):0] c,
input [(WIDTH-1):0] d,
input [(WIDTH-1):0] e,
input [(WIDTH-1):0] f,
input [(WIDTH-1):0] g,
input [(WIDTH-1):0] h,
output [(WIDTH-1):0] q
);
assign q = (a & b & c & d & e & f & g & h);
endmodule
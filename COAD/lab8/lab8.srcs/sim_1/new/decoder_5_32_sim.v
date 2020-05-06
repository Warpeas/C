//~ `New testbench
`timescale  1ns / 1ps

module tb_decoder_5_32;

// decoder_5_32 Parameters
parameter PERIOD     = 10;
parameter in_ports = 5;
parameter out_ports  = 32;

// decoder_5_32 Inputs
reg   [(in_ports-1):0]  in                 = 0 ;

// decoder_5_32 Outputs
wire  [(out_ports-1):0]  out               ;
reg clk;
reg rst_n;

initial
begin
    forever #(PERIOD/2)  clk=~clk;
end

initial
begin
    #(PERIOD*2) rst_n  =  1;
end

decoder_5_32 #(
    .in_ports ( in_ports ),
    .out_ports ( out_ports ))
 u_decoder_5_32 (
    .in                      ( in   [(in_ports-1):0]  ),

    .out                     ( out  [(out_ports-1):0] )
);

initial
begin
    clk = 0;
    in = 0;
    #10000
    $finish;
end

always @(posedge clk) begin
    in = in + 1;
end

endmodule
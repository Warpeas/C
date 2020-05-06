`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company:
// Engineer:
//
// Create Date: 04/18/2020 09:49:56 PM
// Design Name:
// Module Name: mux2x1_tb
// Project Name:
// Target Devices:
// Tool Versions:
// Description:
//
// Dependencies:
//
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
//
//////////////////////////////////////////////////////////////////////////////////


`timescale 1ns / 1ps
module mux2x1_tb();
    // input
    reg a = 0;
    reg b = 1;
    reg s = 0;
    //output
    wire c;
    mux2x1 u(.a(a),.b(b),.s(s),.c(c));
    initial begin
        # 200 s = 1;
    end
endmodule

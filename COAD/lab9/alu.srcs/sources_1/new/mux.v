`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company:
// Engineer:
//
// Create Date: 04/25/2020 02:30:49 AM
// Design Name:
// Module Name: mux
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


module mux(input aluSrc,
           input [31:0] data2,
           input [31:0] signExtend,
           output [31:0] out);
    assign out = aluSrc == 1?signExtend:data2;
endmodule

`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 04/25/2020 03:00:26 AM
// Design Name: 
// Module Name: alu_module
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


module alu_module(
    input aluSrc,
    input [3:0] aluControl,
    input [31:0] data1,
    input [31:0] data2,
    input [31:0] signExtend,
    output [31:0] aluResult,
    output zero
    );
    wire out;
    mux mux1(.aluSrc(aluSrc),.data2(data2),.signExtend(signExtend),.out(out));
    alu alu1(.aluControl(aluControl),.data1(data1),.data2(out),.aluResult(aluResult),.zero(zero));
endmodule

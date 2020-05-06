//~ `New testbench
`timescale  1ns / 1ps

module alu_tb;

// registers Inputs
    reg aluSrc;
    reg [3:0] aluControl;
    reg [31:0] data1;
    reg [31:0] data2;
    reg [31:0] signExtend;

// registers Outputs
    wire [31:0] aluResult;
    wire zero;

alu alu1(.aluSrc(aluSrc),.aluControl(aluControl),.data1(data1),.data2(data2),.signExtend(signExtend),.aluResult(aluResult),.zero(zero));

initial
begin
    data1 = 32'ha011;
    data2 = 32'ha011;
    signExtend = 32'h4321;
    #10
    aluSrc = 0;
    aluControl=4'b0_000;
    #10
    aluControl=4'b0_001;
    #10
    aluControl=4'b0_01x;
    #10
    aluControl=4'b0_100;
    #10
    aluControl=4'b0_101;
    #10
    aluControl=4'b0_11x;
    #10
    aluControl=4'b1_100;
    #10
    aluControl=4'b1_110;
    #10
    aluControl=4'b1_111;
    #10
    aluSrc = 1;
    aluControl=4'b0_000;
    #10
    aluControl=4'b0_001;
    #10
    aluControl=4'b0_01x;
    #10
    aluControl=4'b0_100;
    #10
    aluControl=4'b0_101;
    #10
    aluControl=4'b0_11x;
    #10
    aluControl=4'b1_000;
    #10
    aluControl=4'b1_010;
    #10
    aluControl=4'b1_011;
    #100
    $finish;
end

endmodule
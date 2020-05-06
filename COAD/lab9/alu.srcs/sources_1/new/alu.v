`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company:
// Engineer:
//
// Create Date: 04/25/2020 02:36:55 AM
// Design Name:
// Module Name: alu
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


module alu(input aluSrc,
           input [3:0] aluControl,
           input [31:0] data1,
           input [31:0] data2,
           input [31:0] signExtend,
           output [31:0] aluResult,
           output zero);
    wire [4:0] shift;
    wire [2:0] left;
    wire [0:0] right;
    wire[31:0] out;
    assign shift = out[4:0];
    assign left = aluControl[3:1];
    assign right = aluControl[0];
    mux mux1(.aluSrc(aluSrc),.data2(data2),.signExtend(signExtend),.out(out));
    assign zero = aluResult == 0?1:0;
    assign aluResult = aluControl == 4'b0_000 ? data1&&out:
    aluControl == 4'b0_001 ? data1||out:
    left == 3'b0_01 ? data1+out:
    aluControl == 4'b0_100 ? (!data1&&out)||(data1&&!out):
    aluControl == 4'b0_101 ? !(data1||out):
    left == 3'b0_11 ? data1 - out:
    aluControl == 4'b1_000 || aluControl == 4'b1_100 ? (data1 << shift):
    aluControl == 4'b1_010 || aluControl == 4'b1_110 ? (data1 >> shift):
    aluControl == 4'b1_011 || aluControl == 4'b1_111 ? (data1 >>> shift): 0;
    // always @(aluControl) begin
    //     case (aluControl)
    //         4'b0_000:  aluResult = data1&&out;
    //         4'b0_001:  aluResult = data1||out;
    //         4'b0_01x:  aluResult = data1+out;
    //         4'b0_100:  aluResult = (!data1&&out)||(data1&&!out);
    //         4'b0_101:  aluResult = !(data1||out);
    //         4'b0_11x:  aluResult = data1 - out;
    //         4'b1_000, 4'b1_100: begin
    //             shift     = out[4:0];
    //             aluResult = data1 << shift;
    //         end
    //         4'b1_010,4'b1_110:begin
    //             shift     = out[4:0];
    //             aluResult = data1 >> shift;
    //         end
    //         4'b1_011, 4'b1_111:begin
    //             shift     = out[4:0];
    //             aluResult = data1 >>> shift;
    //         end
    //         default: assign aluResult = 0;
    //     endcase
    // end
endmodule

module mux(input aluSrc,
           input [31:0] data2,
           input [31:0] signExtend,
           output [31:0] out);
    assign out = aluSrc == 1?signExtend:data2;
endmodule

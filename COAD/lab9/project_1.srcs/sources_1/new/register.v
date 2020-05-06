`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company:
// Engineer:
//
// Create Date: 04/24/2020 11:49:03 PM
// Design Name:
// Module Name: registers
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


module registers#(parameter Depth = 32,
                  parameter Data_width = 32)
                 (input [4:0] Read_register_1,
                  input [4:0] Read_register_2,
                  input [4:0] Write_register,
                  input [31:0] Write_Data,
                  input RegWrite,
                  input clk,
                  input rst,
                  output reg [31:0] Read_data_1,
                  output reg [31:0] Read_data_2);
    wire rst_n = ~rst;
    reg [Data_width-1:0] registers [0:Depth-1];
    always @(posedge clk or negedge rst_n) begin
        if (~rst_n) begin
            registers[0]  <= 32'b0;
            registers[1]  <= 32'b0;
            registers[2]  <= 32'b0;
            registers[3]  <= 32'b0;
            registers[4]  <= 32'b0;
            registers[5]  <= 32'b0;
            registers[6]  <= 32'b0;
            registers[7]  <= 32'b0;
            registers[8]  <= 32'b0;
            registers[9]  <= 32'b0;
            registers[10] <= 32'b0;
            registers[11] <= 32'b0;
            registers[12] <= 32'b0;
            registers[13] <= 32'b0;
            registers[14] <= 32'b0;
            registers[15] <= 32'b0;
            registers[16] <= 32'b0;
            registers[17] <= 32'b0;
            registers[18] <= 32'b0;
            registers[19] <= 32'b0;
            registers[20] <= 32'b0;
            registers[21] <= 32'b0;
            registers[22] <= 32'b0;
            registers[23] <= 32'b0;
            registers[24] <= 32'b0;
            registers[25] <= 32'b0;
            registers[26] <= 32'b0;
            registers[27] <= 32'b0;
            registers[28] <= 32'b0;
            registers[29] <= 32'b0;
            registers[30] <= 32'b0;
            registers[31] <= 32'b0;
        end
        else begin
            Read_data_1 <= registers[Read_register_1];
            Read_data_2 <= registers[Read_register_2];
            if (RegWrite&Write_register>0) begin
                registers[Write_register] <= Write_Data;
            end
        end
    end
endmodule

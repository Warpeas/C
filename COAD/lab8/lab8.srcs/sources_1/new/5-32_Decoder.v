`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company:
// Engineer:
//
// Create Date: 04/19/2020 12:44:03 AM
// Design Name:
// Module Name: 5-32_Decoder
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


module decoder_5_32 #(parameter in_ports = 5,
                      parameter out_ports = 32)
                     (in,
                      out);input [(in_ports-1):0] in;output [(out_ports-1):0] out;
    assign out = 1 << in;
    reg [1:0] mem [0:3];
endmodule

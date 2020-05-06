//Copyright 1986-2019 Xilinx, Inc. All Rights Reserved.
//--------------------------------------------------------------------------------
//Tool Version: Vivado v.2019.2 (lin64) Build 2708876 Wed Nov  6 21:39:14 MST 2019
//Date        : Sat Apr 18 21:49:22 2020
//Host        : hunter-pc running 64-bit Manjaro Linux
//Command     : generate_target mux2x1_wrapper.bd
//Design      : mux2x1_wrapper
//Purpose     : IP block netlist
//--------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

module mux2x1_wrapper
   (a,
    b,
    c,
    s);
  input a;
  input b;
  output [0:0]c;
  input s;

  wire a;
  wire b;
  wire [0:0]c;
  wire s;

  mux2x1 mux2x1_i
       (.a(a),
        .b(b),
        .c(c),
        .s(s));
endmodule

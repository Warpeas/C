// Copyright 1986-2019 Xilinx, Inc. All Rights Reserved.
// --------------------------------------------------------------------------------
// Tool Version: Vivado v.2019.2 (lin64) Build 2708876 Wed Nov  6 21:39:14 MST 2019
// Date        : Sat Apr 18 21:30:36 2020
// Host        : hunter-pc running 64-bit Manjaro Linux
// Command     : write_verilog -force -mode synth_stub -rename_top decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix -prefix
//               decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_ andgate_0_stub.v
// Design      : andgate_0
// Purpose     : Stub declaration of top-level module interface
// Device      : xc7a100tfgg484-1
// --------------------------------------------------------------------------------

// This empty module with port declaration file causes synthesis tools to infer a black box for IP.
// The synthesis directives are for Synopsys Synplify support to prevent IO buffer insertion.
// Please paste the declaration into a Verilog source file or add the file as an additional source.
(* X_CORE_INFO = "andgate,Vivado 2019.2" *)
module decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix(a, b, c, d, e, f, g, h, q)
/* synthesis syn_black_box black_box_pad_pin="a[7:0],b[7:0],c[7:0],d[7:0],e[7:0],f[7:0],g[7:0],h[7:0],q[7:0]" */;
  input [7:0]a;
  input [7:0]b;
  input [7:0]c;
  input [7:0]d;
  input [7:0]e;
  input [7:0]f;
  input [7:0]g;
  input [7:0]h;
  output [7:0]q;
endmodule

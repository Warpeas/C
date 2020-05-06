// Copyright 1986-2019 Xilinx, Inc. All Rights Reserved.
// --------------------------------------------------------------------------------
// Tool Version: Vivado v.2019.2 (lin64) Build 2708876 Wed Nov  6 21:39:14 MST 2019
// Date        : Sat Apr 18 21:30:36 2020
// Host        : hunter-pc running 64-bit Manjaro Linux
// Command     : write_verilog -force -mode funcsim -rename_top decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix -prefix
//               decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_ andgate_0_sim_netlist.v
// Design      : andgate_0
// Purpose     : This verilog netlist is a functional simulation representation of the design and should not be modified
//               or synthesized. This netlist cannot be used for SDF annotated simulation.
// Device      : xc7a100tfgg484-1
// --------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

module decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_andgate
   (q,
    g,
    f,
    a,
    h,
    d,
    e,
    b,
    c);
  output [7:0]q;
  input [7:0]g;
  input [7:0]f;
  input [7:0]a;
  input [7:0]h;
  input [7:0]d;
  input [7:0]e;
  input [7:0]b;
  input [7:0]c;

  wire [7:0]a;
  wire [7:0]b;
  wire [7:0]c;
  wire [7:0]d;
  wire [7:0]e;
  wire [7:0]f;
  wire [7:0]g;
  wire [7:0]h;
  wire [7:0]q;
  wire \q[0]_INST_0_i_1_n_0 ;
  wire \q[1]_INST_0_i_1_n_0 ;
  wire \q[2]_INST_0_i_1_n_0 ;
  wire \q[3]_INST_0_i_1_n_0 ;
  wire \q[4]_INST_0_i_1_n_0 ;
  wire \q[5]_INST_0_i_1_n_0 ;
  wire \q[6]_INST_0_i_1_n_0 ;
  wire \q[7]_INST_0_i_1_n_0 ;

  LUT5 #(
    .INIT(32'h00008000)) 
    \q[0]_INST_0 
       (.I0(g[0]),
        .I1(f[0]),
        .I2(a[0]),
        .I3(h[0]),
        .I4(\q[0]_INST_0_i_1_n_0 ),
        .O(q[0]));
  LUT4 #(
    .INIT(16'h7FFF)) 
    \q[0]_INST_0_i_1 
       (.I0(d[0]),
        .I1(e[0]),
        .I2(b[0]),
        .I3(c[0]),
        .O(\q[0]_INST_0_i_1_n_0 ));
  LUT5 #(
    .INIT(32'h00008000)) 
    \q[1]_INST_0 
       (.I0(g[1]),
        .I1(f[1]),
        .I2(a[1]),
        .I3(h[1]),
        .I4(\q[1]_INST_0_i_1_n_0 ),
        .O(q[1]));
  LUT4 #(
    .INIT(16'h7FFF)) 
    \q[1]_INST_0_i_1 
       (.I0(d[1]),
        .I1(e[1]),
        .I2(b[1]),
        .I3(c[1]),
        .O(\q[1]_INST_0_i_1_n_0 ));
  LUT5 #(
    .INIT(32'h00008000)) 
    \q[2]_INST_0 
       (.I0(g[2]),
        .I1(f[2]),
        .I2(a[2]),
        .I3(h[2]),
        .I4(\q[2]_INST_0_i_1_n_0 ),
        .O(q[2]));
  LUT4 #(
    .INIT(16'h7FFF)) 
    \q[2]_INST_0_i_1 
       (.I0(d[2]),
        .I1(e[2]),
        .I2(b[2]),
        .I3(c[2]),
        .O(\q[2]_INST_0_i_1_n_0 ));
  LUT5 #(
    .INIT(32'h00008000)) 
    \q[3]_INST_0 
       (.I0(g[3]),
        .I1(f[3]),
        .I2(a[3]),
        .I3(h[3]),
        .I4(\q[3]_INST_0_i_1_n_0 ),
        .O(q[3]));
  LUT4 #(
    .INIT(16'h7FFF)) 
    \q[3]_INST_0_i_1 
       (.I0(d[3]),
        .I1(e[3]),
        .I2(b[3]),
        .I3(c[3]),
        .O(\q[3]_INST_0_i_1_n_0 ));
  LUT5 #(
    .INIT(32'h00008000)) 
    \q[4]_INST_0 
       (.I0(g[4]),
        .I1(f[4]),
        .I2(a[4]),
        .I3(h[4]),
        .I4(\q[4]_INST_0_i_1_n_0 ),
        .O(q[4]));
  LUT4 #(
    .INIT(16'h7FFF)) 
    \q[4]_INST_0_i_1 
       (.I0(d[4]),
        .I1(e[4]),
        .I2(b[4]),
        .I3(c[4]),
        .O(\q[4]_INST_0_i_1_n_0 ));
  LUT5 #(
    .INIT(32'h00008000)) 
    \q[5]_INST_0 
       (.I0(g[5]),
        .I1(f[5]),
        .I2(a[5]),
        .I3(h[5]),
        .I4(\q[5]_INST_0_i_1_n_0 ),
        .O(q[5]));
  LUT4 #(
    .INIT(16'h7FFF)) 
    \q[5]_INST_0_i_1 
       (.I0(d[5]),
        .I1(e[5]),
        .I2(b[5]),
        .I3(c[5]),
        .O(\q[5]_INST_0_i_1_n_0 ));
  LUT5 #(
    .INIT(32'h00008000)) 
    \q[6]_INST_0 
       (.I0(g[6]),
        .I1(f[6]),
        .I2(a[6]),
        .I3(h[6]),
        .I4(\q[6]_INST_0_i_1_n_0 ),
        .O(q[6]));
  LUT4 #(
    .INIT(16'h7FFF)) 
    \q[6]_INST_0_i_1 
       (.I0(d[6]),
        .I1(e[6]),
        .I2(b[6]),
        .I3(c[6]),
        .O(\q[6]_INST_0_i_1_n_0 ));
  LUT5 #(
    .INIT(32'h00008000)) 
    \q[7]_INST_0 
       (.I0(g[7]),
        .I1(f[7]),
        .I2(a[7]),
        .I3(h[7]),
        .I4(\q[7]_INST_0_i_1_n_0 ),
        .O(q[7]));
  LUT4 #(
    .INIT(16'h7FFF)) 
    \q[7]_INST_0_i_1 
       (.I0(d[7]),
        .I1(e[7]),
        .I2(b[7]),
        .I3(c[7]),
        .O(\q[7]_INST_0_i_1_n_0 ));
endmodule

(* CHECK_LICENSE_TYPE = "andgate_0,andgate,{}" *) (* DowngradeIPIdentifiedWarnings = "yes" *) (* IP_DEFINITION_SOURCE = "package_project" *) 
(* X_CORE_INFO = "andgate,Vivado 2019.2" *) 
(* NotValidForBitStream *)
module decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix
   (a,
    b,
    c,
    d,
    e,
    f,
    g,
    h,
    q);
  input [7:0]a;
  input [7:0]b;
  input [7:0]c;
  input [7:0]d;
  input [7:0]e;
  input [7:0]f;
  input [7:0]g;
  input [7:0]h;
  output [7:0]q;

  wire [7:0]a;
  wire [7:0]b;
  wire [7:0]c;
  wire [7:0]d;
  wire [7:0]e;
  wire [7:0]f;
  wire [7:0]g;
  wire [7:0]h;
  wire [7:0]q;

  decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_andgate inst
       (.a(a),
        .b(b),
        .c(c),
        .d(d),
        .e(e),
        .f(f),
        .g(g),
        .h(h),
        .q(q));
endmodule
`ifndef GLBL
`define GLBL
`timescale  1 ps / 1 ps

module glbl ();

    parameter ROC_WIDTH = 100000;
    parameter TOC_WIDTH = 0;

//--------   STARTUP Globals --------------
    wire GSR;
    wire GTS;
    wire GWE;
    wire PRLD;
    tri1 p_up_tmp;
    tri (weak1, strong0) PLL_LOCKG = p_up_tmp;

    wire PROGB_GLBL;
    wire CCLKO_GLBL;
    wire FCSBO_GLBL;
    wire [3:0] DO_GLBL;
    wire [3:0] DI_GLBL;
   
    reg GSR_int;
    reg GTS_int;
    reg PRLD_int;

//--------   JTAG Globals --------------
    wire JTAG_TDO_GLBL;
    wire JTAG_TCK_GLBL;
    wire JTAG_TDI_GLBL;
    wire JTAG_TMS_GLBL;
    wire JTAG_TRST_GLBL;

    reg JTAG_CAPTURE_GLBL;
    reg JTAG_RESET_GLBL;
    reg JTAG_SHIFT_GLBL;
    reg JTAG_UPDATE_GLBL;
    reg JTAG_RUNTEST_GLBL;

    reg JTAG_SEL1_GLBL = 0;
    reg JTAG_SEL2_GLBL = 0 ;
    reg JTAG_SEL3_GLBL = 0;
    reg JTAG_SEL4_GLBL = 0;

    reg JTAG_USER_TDO1_GLBL = 1'bz;
    reg JTAG_USER_TDO2_GLBL = 1'bz;
    reg JTAG_USER_TDO3_GLBL = 1'bz;
    reg JTAG_USER_TDO4_GLBL = 1'bz;

    assign (strong1, weak0) GSR = GSR_int;
    assign (strong1, weak0) GTS = GTS_int;
    assign (weak1, weak0) PRLD = PRLD_int;

    initial begin
	GSR_int = 1'b1;
	PRLD_int = 1'b1;
	#(ROC_WIDTH)
	GSR_int = 1'b0;
	PRLD_int = 1'b0;
    end

    initial begin
	GTS_int = 1'b1;
	#(TOC_WIDTH)
	GTS_int = 1'b0;
    end

endmodule
`endif

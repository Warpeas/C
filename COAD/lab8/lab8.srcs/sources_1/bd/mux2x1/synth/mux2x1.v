//Copyright 1986-2019 Xilinx, Inc. All Rights Reserved.
//--------------------------------------------------------------------------------
//Tool Version: Vivado v.2019.2 (lin64) Build 2708876 Wed Nov  6 21:39:14 MST 2019
//Date        : Sat Apr 18 21:49:22 2020
//Host        : hunter-pc running 64-bit Manjaro Linux
//Command     : generate_target mux2x1.bd
//Design      : mux2x1
//Purpose     : IP block netlist
//--------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

(* CORE_GENERATION_INFO = "mux2x1,IP_Integrator,{x_ipVendor=xilinx.com,x_ipLibrary=BlockDiagram,x_ipName=mux2x1,x_ipVersion=1.00.a,x_ipLanguage=VERILOG,numBlks=4,numReposBlks=4,numNonXlnxBlks=0,numHierBlks=0,maxHierDepth=0,numSysgenBlks=0,numHlsBlks=0,numHdlrefBlks=0,numPkgbdBlks=0,bdsource=USER,synth_mode=Global}" *) (* HW_HANDOFF = "mux2x1.hwdef" *) 
module mux2x1
   (a,
    b,
    c,
    s);
  input a;
  input b;
  output [0:0]c;
  input s;

  wire a_1;
  wire b_1;
  wire s_1;
  wire [0:0]util_vector_logic_0_Res;
  wire [0:0]util_vector_logic_1_Res;
  wire [0:0]util_vector_logic_2_Res;
  wire [0:0]util_vector_logic_3_Res;

  assign a_1 = a;
  assign b_1 = b;
  assign c[0] = util_vector_logic_3_Res;
  assign s_1 = s;
  mux2x1_util_vector_logic_0_0 util_vector_logic_0
       (.Op1(a_1),
        .Op2(util_vector_logic_2_Res),
        .Res(util_vector_logic_0_Res));
  mux2x1_util_vector_logic_1_0 util_vector_logic_1
       (.Op1(b_1),
        .Op2(s_1),
        .Res(util_vector_logic_1_Res));
  mux2x1_util_vector_logic_2_0 util_vector_logic_2
       (.Op1(s_1),
        .Res(util_vector_logic_2_Res));
  mux2x1_util_vector_logic_3_0 util_vector_logic_3
       (.Op1(util_vector_logic_0_Res),
        .Op2(util_vector_logic_1_Res),
        .Res(util_vector_logic_3_Res));
endmodule

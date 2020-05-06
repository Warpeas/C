vlib work
vlib activehdl

vlib activehdl/util_vector_logic_v2_0_1
vlib activehdl/xil_defaultlib

vmap util_vector_logic_v2_0_1 activehdl/util_vector_logic_v2_0_1
vmap xil_defaultlib activehdl/xil_defaultlib

vlog -work util_vector_logic_v2_0_1  -v2k5 \
"../../../../lab8.srcs/sources_1/bd/mux2x1/ipshared/2137/hdl/util_vector_logic_v2_0_vl_rfs.v" \

vlog -work xil_defaultlib  -v2k5 \
"../../../bd/mux2x1/ip/mux2x1_util_vector_logic_0_0/sim/mux2x1_util_vector_logic_0_0.v" \
"../../../bd/mux2x1/ip/mux2x1_util_vector_logic_1_0/sim/mux2x1_util_vector_logic_1_0.v" \
"../../../bd/mux2x1/ip/mux2x1_util_vector_logic_2_0/sim/mux2x1_util_vector_logic_2_0.v" \
"../../../bd/mux2x1/ip/mux2x1_util_vector_logic_3_0/sim/mux2x1_util_vector_logic_3_0.v" \
"../../../bd/mux2x1/sim/mux2x1.v" \

vlog -work xil_defaultlib \
"glbl.v"


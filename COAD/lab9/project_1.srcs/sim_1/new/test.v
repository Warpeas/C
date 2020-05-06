//~ `New testbench
`timescale  1ns / 1ps

module registers_tb;

// registers Parameters
parameter PERIOD      = 10;
parameter Data_width  = 32;

// registers Inputs
reg   [4:0]  Read_register_1               = 0 ;
reg   [4:0]  Read_register_2               = 0 ;
reg   [4:0]  Write_register                = 0 ;
reg   [31:0]  Write_Data                   = 0 ;
reg   RegWrite                             = 0 ;
reg   clk                                  = 0 ;
reg   rst                                  = 0 ;

// registers Outputs
wire  [31:0]  Read_data_1                  ;
wire  [31:0]  Read_data_2                  ;


initial
begin
    forever #(PERIOD/2)  clk=~clk;
end

registers #(
    .Data_width ( Data_width ))
 u_registers (
    .Read_register_1         ( Read_register_1  [4:0]  ),
    .Read_register_2         ( Read_register_2  [4:0]  ),
    .Write_register          ( Write_register   [4:0]  ),
    .Write_Data              ( Write_Data       [31:0] ),
    .RegWrite                ( RegWrite                ),
    .clk                     ( clk                     ),
    .rst                     ( rst                     ),

    .Read_data_1             ( Read_data_1      [31:0] ),
    .Read_data_2             ( Read_data_2      [31:0] )
);

initial
begin
    #(PERIOD) rst    =  ~rst;
    #PERIOD begin
        rst = ~rst;
        Write_register = 5'd0;
        Write_Data = 32'h1234;
        RegWrite = 1;
    end
    #(PERIOD*2) begin
        Write_register = 5'd1;
        Write_Data = 32'h4321;
    end
    #PERIOD begin
        RegWrite = 0;
        Read_register_1 = 5'd0;
        Read_register_2 = 5'd1;
    end
    #100
    $finish;
end

endmodule
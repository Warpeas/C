-- Copyright 1986-2019 Xilinx, Inc. All Rights Reserved.
-- --------------------------------------------------------------------------------
-- Tool Version: Vivado v.2019.2 (lin64) Build 2708876 Wed Nov  6 21:39:14 MST 2019
-- Date        : Sat Apr 18 21:30:36 2020
-- Host        : hunter-pc running 64-bit Manjaro Linux
-- Command     : write_vhdl -force -mode funcsim -rename_top decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix -prefix
--               decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_ andgate_0_sim_netlist.vhdl
-- Design      : andgate_0
-- Purpose     : This VHDL netlist is a functional simulation representation of the design and should not be modified or
--               synthesized. This netlist cannot be used for SDF annotated simulation.
-- Device      : xc7a100tfgg484-1
-- --------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
library UNISIM;
use UNISIM.VCOMPONENTS.ALL;
entity decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_andgate is
  port (
    q : out STD_LOGIC_VECTOR ( 7 downto 0 );
    g : in STD_LOGIC_VECTOR ( 7 downto 0 );
    f : in STD_LOGIC_VECTOR ( 7 downto 0 );
    a : in STD_LOGIC_VECTOR ( 7 downto 0 );
    h : in STD_LOGIC_VECTOR ( 7 downto 0 );
    d : in STD_LOGIC_VECTOR ( 7 downto 0 );
    e : in STD_LOGIC_VECTOR ( 7 downto 0 );
    b : in STD_LOGIC_VECTOR ( 7 downto 0 );
    c : in STD_LOGIC_VECTOR ( 7 downto 0 )
  );
end decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_andgate;

architecture STRUCTURE of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_andgate is
  signal \q[0]_INST_0_i_1_n_0\ : STD_LOGIC;
  signal \q[1]_INST_0_i_1_n_0\ : STD_LOGIC;
  signal \q[2]_INST_0_i_1_n_0\ : STD_LOGIC;
  signal \q[3]_INST_0_i_1_n_0\ : STD_LOGIC;
  signal \q[4]_INST_0_i_1_n_0\ : STD_LOGIC;
  signal \q[5]_INST_0_i_1_n_0\ : STD_LOGIC;
  signal \q[6]_INST_0_i_1_n_0\ : STD_LOGIC;
  signal \q[7]_INST_0_i_1_n_0\ : STD_LOGIC;
begin
\q[0]_INST_0\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00008000"
    )
        port map (
      I0 => g(0),
      I1 => f(0),
      I2 => a(0),
      I3 => h(0),
      I4 => \q[0]_INST_0_i_1_n_0\,
      O => q(0)
    );
\q[0]_INST_0_i_1\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"7FFF"
    )
        port map (
      I0 => d(0),
      I1 => e(0),
      I2 => b(0),
      I3 => c(0),
      O => \q[0]_INST_0_i_1_n_0\
    );
\q[1]_INST_0\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00008000"
    )
        port map (
      I0 => g(1),
      I1 => f(1),
      I2 => a(1),
      I3 => h(1),
      I4 => \q[1]_INST_0_i_1_n_0\,
      O => q(1)
    );
\q[1]_INST_0_i_1\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"7FFF"
    )
        port map (
      I0 => d(1),
      I1 => e(1),
      I2 => b(1),
      I3 => c(1),
      O => \q[1]_INST_0_i_1_n_0\
    );
\q[2]_INST_0\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00008000"
    )
        port map (
      I0 => g(2),
      I1 => f(2),
      I2 => a(2),
      I3 => h(2),
      I4 => \q[2]_INST_0_i_1_n_0\,
      O => q(2)
    );
\q[2]_INST_0_i_1\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"7FFF"
    )
        port map (
      I0 => d(2),
      I1 => e(2),
      I2 => b(2),
      I3 => c(2),
      O => \q[2]_INST_0_i_1_n_0\
    );
\q[3]_INST_0\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00008000"
    )
        port map (
      I0 => g(3),
      I1 => f(3),
      I2 => a(3),
      I3 => h(3),
      I4 => \q[3]_INST_0_i_1_n_0\,
      O => q(3)
    );
\q[3]_INST_0_i_1\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"7FFF"
    )
        port map (
      I0 => d(3),
      I1 => e(3),
      I2 => b(3),
      I3 => c(3),
      O => \q[3]_INST_0_i_1_n_0\
    );
\q[4]_INST_0\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00008000"
    )
        port map (
      I0 => g(4),
      I1 => f(4),
      I2 => a(4),
      I3 => h(4),
      I4 => \q[4]_INST_0_i_1_n_0\,
      O => q(4)
    );
\q[4]_INST_0_i_1\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"7FFF"
    )
        port map (
      I0 => d(4),
      I1 => e(4),
      I2 => b(4),
      I3 => c(4),
      O => \q[4]_INST_0_i_1_n_0\
    );
\q[5]_INST_0\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00008000"
    )
        port map (
      I0 => g(5),
      I1 => f(5),
      I2 => a(5),
      I3 => h(5),
      I4 => \q[5]_INST_0_i_1_n_0\,
      O => q(5)
    );
\q[5]_INST_0_i_1\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"7FFF"
    )
        port map (
      I0 => d(5),
      I1 => e(5),
      I2 => b(5),
      I3 => c(5),
      O => \q[5]_INST_0_i_1_n_0\
    );
\q[6]_INST_0\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00008000"
    )
        port map (
      I0 => g(6),
      I1 => f(6),
      I2 => a(6),
      I3 => h(6),
      I4 => \q[6]_INST_0_i_1_n_0\,
      O => q(6)
    );
\q[6]_INST_0_i_1\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"7FFF"
    )
        port map (
      I0 => d(6),
      I1 => e(6),
      I2 => b(6),
      I3 => c(6),
      O => \q[6]_INST_0_i_1_n_0\
    );
\q[7]_INST_0\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00008000"
    )
        port map (
      I0 => g(7),
      I1 => f(7),
      I2 => a(7),
      I3 => h(7),
      I4 => \q[7]_INST_0_i_1_n_0\,
      O => q(7)
    );
\q[7]_INST_0_i_1\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"7FFF"
    )
        port map (
      I0 => d(7),
      I1 => e(7),
      I2 => b(7),
      I3 => c(7),
      O => \q[7]_INST_0_i_1_n_0\
    );
end STRUCTURE;
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
library UNISIM;
use UNISIM.VCOMPONENTS.ALL;
entity decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix is
  port (
    a : in STD_LOGIC_VECTOR ( 7 downto 0 );
    b : in STD_LOGIC_VECTOR ( 7 downto 0 );
    c : in STD_LOGIC_VECTOR ( 7 downto 0 );
    d : in STD_LOGIC_VECTOR ( 7 downto 0 );
    e : in STD_LOGIC_VECTOR ( 7 downto 0 );
    f : in STD_LOGIC_VECTOR ( 7 downto 0 );
    g : in STD_LOGIC_VECTOR ( 7 downto 0 );
    h : in STD_LOGIC_VECTOR ( 7 downto 0 );
    q : out STD_LOGIC_VECTOR ( 7 downto 0 )
  );
  attribute NotValidForBitStream : boolean;
  attribute NotValidForBitStream of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix : entity is true;
  attribute CHECK_LICENSE_TYPE : string;
  attribute CHECK_LICENSE_TYPE of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix : entity is "andgate_0,andgate,{}";
  attribute DowngradeIPIdentifiedWarnings : string;
  attribute DowngradeIPIdentifiedWarnings of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix : entity is "yes";
  attribute IP_DEFINITION_SOURCE : string;
  attribute IP_DEFINITION_SOURCE of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix : entity is "package_project";
  attribute X_CORE_INFO : string;
  attribute X_CORE_INFO of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix : entity is "andgate,Vivado 2019.2";
end decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix;

architecture STRUCTURE of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix is
begin
inst: entity work.decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_andgate
     port map (
      a(7 downto 0) => a(7 downto 0),
      b(7 downto 0) => b(7 downto 0),
      c(7 downto 0) => c(7 downto 0),
      d(7 downto 0) => d(7 downto 0),
      e(7 downto 0) => e(7 downto 0),
      f(7 downto 0) => f(7 downto 0),
      g(7 downto 0) => g(7 downto 0),
      h(7 downto 0) => h(7 downto 0),
      q(7 downto 0) => q(7 downto 0)
    );
end STRUCTURE;

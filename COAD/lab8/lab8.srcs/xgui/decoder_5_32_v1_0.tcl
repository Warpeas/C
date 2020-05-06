# Definitional proc to organize widgets for parameters.
proc init_gui { IPINST } {
  ipgui::add_param $IPINST -name "Component_Name"
  #Adding Page
  set Page_0 [ipgui::add_page $IPINST -name "Page 0"]
  ipgui::add_param $IPINST -name "in_ports" -parent ${Page_0}
  ipgui::add_param $IPINST -name "out_ports" -parent ${Page_0}


}

proc update_PARAM_VALUE.in_ports { PARAM_VALUE.in_ports } {
	# Procedure called to update in_ports when any of the dependent parameters in the arguments change
}

proc validate_PARAM_VALUE.in_ports { PARAM_VALUE.in_ports } {
	# Procedure called to validate in_ports
	return true
}

proc update_PARAM_VALUE.out_ports { PARAM_VALUE.out_ports } {
	# Procedure called to update out_ports when any of the dependent parameters in the arguments change
}

proc validate_PARAM_VALUE.out_ports { PARAM_VALUE.out_ports } {
	# Procedure called to validate out_ports
	return true
}


proc update_MODELPARAM_VALUE.in_ports { MODELPARAM_VALUE.in_ports PARAM_VALUE.in_ports } {
	# Procedure called to set VHDL generic/Verilog parameter value(s) based on TCL parameter value
	set_property value [get_property value ${PARAM_VALUE.in_ports}] ${MODELPARAM_VALUE.in_ports}
}

proc update_MODELPARAM_VALUE.out_ports { MODELPARAM_VALUE.out_ports PARAM_VALUE.out_ports } {
	# Procedure called to set VHDL generic/Verilog parameter value(s) based on TCL parameter value
	set_property value [get_property value ${PARAM_VALUE.out_ports}] ${MODELPARAM_VALUE.out_ports}
}


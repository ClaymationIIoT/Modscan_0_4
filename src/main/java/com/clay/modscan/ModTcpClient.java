package com.clay.modscan;
import com.intelligt.modbus.jlibmodbus.Modbus;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.*;

public class ModTcpClient extends ModbusClient {
	
	public ModTcpClient(String ipAddress, int port) {
		super(ipAddress, port);
	}
	
	public ModTcpClient() {
		super();
	}
	
	public void Connect() {
		try {
			super.Connect();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Disconnect() {
		if(this.isConnected()) {
			try {
				super.Disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Function Code 1
			public boolean[] ReadCoils(int startingAddress, int quantity) {
				boolean booleans[] = new boolean[quantity];
				if(this.isConnected()) {
					try {
						booleans = super.ReadCoils(startingAddress, quantity);
					} catch (ModbusException ME){
						ME.printStackTrace();
					} catch (SocketException SE) {
						SE.printStackTrace();
					} catch(IOException IOE) {
						IOE.printStackTrace();
					}
				}
				return booleans;
			}
	
	// Function Code 2
		public boolean[] ReadDiscreteInputs(int startingAddress, int quantity) {
			boolean booleans[] = new boolean[quantity];
			if(this.isConnected()) {
				try {
					booleans = super.ReadDiscreteInputs(startingAddress, quantity);
				} catch (ModbusException ME){
					ME.printStackTrace();
				} catch (SocketException SE) {
					SE.printStackTrace();
				} catch(IOException IOE) {
					IOE.printStackTrace();
				}
			}
			return booleans;
		}
	
	// Function Code 3
	public int[] ReadHoldingRegisters(int startingAddress, int quantity) {
		int registers[] = new int[quantity];
		if(this.isConnected()) {
			try {
				registers = super.ReadHoldingRegisters(startingAddress, quantity);
			} catch (ModbusException ME){
				ME.printStackTrace();
			} catch (SocketException SE) {
				SE.printStackTrace();
			} catch(IOException IOE) {
				IOE.printStackTrace();
			}
		}
		return registers;
	}
	
	// Function Code 4
		public int[] ReadInputRegisters(int startingAddress, int quantity) {
			int registers[] = new int[quantity];
			if(this.isConnected()) {
				try {
					registers = super.ReadInputRegisters(startingAddress, quantity);
				} catch (ModbusException ME){
					ME.printStackTrace();
				} catch (SocketException SE) {
					SE.printStackTrace();
				} catch(IOException IOE) {
					IOE.printStackTrace();
				}
			}
			return registers;
		}
	
	public String toString() {
		return "\nModbus TCP Client" + "\n" + "Server IP Address: " + super.getipAddress() + "\n" + "Server Port: " + super.getPort() + 
				"\n";
	}
	
	
	
	

}

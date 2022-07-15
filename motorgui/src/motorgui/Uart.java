package motorgui;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import com.fazecast.jSerialComm.*;

public class Uart {
	public SerialPort ubxPort;
	public SerialPort[] ports;
	public boolean connected;
	private byte[] readBuffer;
	protected int readIndex;
	private Datapackage readPackage;
	private int checksumdata;
	public int counterright;
	public int counterleft;
	public int battery;
	public int globalerror;
	public int rightcurrent;
	public int leftcurrent;
	public int temperature;
	public boolean checkdataready;
	protected boolean bufferReady;
	private Datapackage sendPackage;
	public int sendchecksumdata;
	public int rightpolecount;
	public int serialnumber;
	public int leftpolecount;
	public int rightspeedfilter;
	public int leftspeedfilter;
	public int rightcurrentfilter;
	public int leftcurrentfilter;
	public int sendrate;
	public int modes;
	public int rightkp;
	public int leftkp;
	public int rightki;
	public int leftki;
	public int rightwindup;
	public int leftwindup;
	public boolean eepromdataready;
	private InputStream comportInStream;
	public ArrayList<String> getports() {
		// TODO Auto-generated constructor stub
		ArrayList<String> portsnames = new ArrayList<String>();
		ports = SerialPort.getCommPorts();
		System.out.println("Available Ports:\n");
		for (int i = 0; i < ports.length; ++i)
		{		
			portsnames.add(ports[i].getDescriptivePortName());
			System.out.println("   [" + i + "] " + ports[i].getSystemPortName() + " (" + ports[i].getSystemPortPath() + "): " + ports[i].getDescriptivePortName() + " - " + ports[i].getPortDescription() + " @ " + ports[i].getPortLocation());
		}
		return portsnames;
	}
	public void connect(int portindex) {
		ubxPort = ports[portindex];
		ubxPort.allowElevatedPermissionsRequest();
		connected = ubxPort.openPort();
		readBuffer = new byte[64];
		readPackage = new Datapackage();
		sendPackage = new Datapackage();
		System.out.println("\nOpening " + ubxPort.getSystemPortName() + ": " + ubxPort.getDescriptivePortName() + " - " + ubxPort.getPortDescription() + ": " + connected);
		if (!connected)
		{
			System.out.println("Error code was " + ubxPort.getLastErrorCode() + " at Line " + ubxPort.getLastErrorLocation());
			return;
		}
		ubxPort.setBaudRate(115200);
		ubxPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);
		
	}
	protected void checkbuffer() {
		// TODO Auto-generated method stub
		readPackage.packageno = (char) bytearray2byte(readBuffer,2);
		readPackage.command = (char) bytearray2byte(readBuffer,3);
		System.arraycopy(readBuffer, 4, readPackage.data, 0, 52);
		readPackage.checksum = bytearray2int(readBuffer,56);
		ByteBuffer buffer = ByteBuffer.wrap(readPackage.data);
		checksumdata = STM32CRC.GenerateCrc(buffer, 52);
		if(checksumdata==readPackage.checksum)
		{
			processdata();
		}
		
	}
	private void processdata() {
		// TODO Auto-generated method stub
		switch(readPackage.command) {
		case 0:
			counterright = bytearray2int(readPackage.data,0);
			counterleft = bytearray2int(readPackage.data,4);
			battery = bytearray2short(readPackage.data,8);
			globalerror = bytearray2short(readPackage.data,10);
			rightcurrent = bytearray2int(readPackage.data,12);
			leftcurrent = bytearray2int(readPackage.data,16);
			temperature = bytearray2int(readPackage.data,20);
			checkdataready = true;
			break;
		case 1:
			break;
		case 2:
			serialnumber       =  bytearray2int(readPackage.data,0);
			rightpolecount     =  readPackage.data[4]&0xFF;
			leftpolecount      =  readPackage.data[5]&0xFF;
			rightspeedfilter   =  readPackage.data[6]&0xFF;
			leftspeedfilter    =  readPackage.data[7]&0xFF;
			rightcurrentfilter =  readPackage.data[8]&0xFF;
			leftcurrentfilter  =  readPackage.data[9]&0xFF;
			sendrate           =  readPackage.data[10]&0xFF;
			modes          	   =  readPackage.data[11]&0xFF;
			rightkp            =  bytearray2int(readPackage.data,12);
			leftkp             =  bytearray2int(readPackage.data,16);
			rightki            =  bytearray2int(readPackage.data,20);
			leftki             =  bytearray2int(readPackage.data,24);
			rightwindup        =  bytearray2int(readPackage.data,28);
			leftwindup         =  bytearray2int(readPackage.data,32);
			eepromdataready    = true;
			break;
		default:
			break;
		}
		
	}
	public void close()
	{
		ubxPort.removeDataListener();
	}
	public void checkincome() {
		// TODO Auto-generated method stub
		if(ubxPort.isOpen()) {
			comportInStream = ubxPort.getInputStream();
			byte[] buffer = new byte[128];
			try
			{
			   for (int j = 0; j < 128; ++j)
				   buffer[j] = (byte) comportInStream.read();
			   comportInStream.close();
			} 
			catch (SerialPortTimeoutException e) {  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//ubxPort.readBytes(buffer, buffer.length);
			for (int i = 0; i < 128; i++) {
				if((buffer[i]==readPackage.startchar)&&(buffer[(i+1)%128]==readPackage.syncchar)&&(buffer[(i+60)%128]==readPackage.stop1)&&(buffer[(i+61)%128]==readPackage.stop2)&&(buffer[(i+62)%128]==readPackage.stop3)&&(buffer[(i+63)%128]==readPackage.stop4)) {
					readIndex = 0;
				}
				readBuffer[readIndex] = buffer[i];
				readIndex++;
				if(readIndex>63) {
					readIndex = 0;
					checkbuffer();
				}	
			}	
		}
	}
	public void sendspeeds(int rightspeedset, int leftspeedset) {
		// TODO Auto-generated method stub
		if(ubxPort.isOpen()) {
		    byte[] sendbuffer = new byte[64];
			for (int i = 0; i < sendbuffer.length; i++) {
				sendbuffer[i] = 0;
				sendPackage.data[i%52] = 0;
			}
		    sendbuffer[0] = (byte) sendPackage.startchar;
		    sendbuffer[1] = (byte) sendPackage.syncchar;
		    sendbuffer[60] = (byte) sendPackage.stop1;
		    sendbuffer[61] = (byte) sendPackage.stop2;
		    sendbuffer[62] = (byte) sendPackage.stop3;
		    sendbuffer[63] = (byte) sendPackage.stop4;
		    sendPackage.packageno += 1;
		    byte2bytearray((byte) sendPackage.packageno,sendbuffer,2);
		    sendPackage.command = 1;
		    byte2bytearray((byte) sendPackage.command,sendbuffer,3);
		    int2bytearray(rightspeedset,sendPackage.data,0);
		    int2bytearray(leftspeedset,sendPackage.data,4);
		    int2bytearray(rightspeedset,sendbuffer,4);
		    int2bytearray(leftspeedset,sendbuffer,8);
			ByteBuffer buffer = ByteBuffer.wrap(sendPackage.data);
		    sendchecksumdata = STM32CRC.GenerateCrc(buffer, 52);
		    int2bytearray(sendchecksumdata,sendbuffer,56);
		    sendPackage.checksum = sendchecksumdata;
		    ubxPort.writeBytes(sendbuffer, 64);
		}
		
	}
	public void readprogramdata() {
		// TODO Auto-generated method stub
		if(ubxPort.isOpen()) {
		    byte[] sendbuffer = new byte[64];
			for (int i = 0; i < sendbuffer.length; i++) {
				sendbuffer[i] = 0;
				sendPackage.data[i%52] = 0;
			}
		    sendbuffer[0] = (byte) sendPackage.startchar;
		    sendbuffer[1] = (byte) sendPackage.syncchar;
		    sendbuffer[60] = (byte) sendPackage.stop1;
		    sendbuffer[61] = (byte) sendPackage.stop2;
		    sendbuffer[62] = (byte) sendPackage.stop3;
		    sendbuffer[63] = (byte) sendPackage.stop4;
		    sendPackage.packageno += 1;
		    byte2bytearray((byte) sendPackage.packageno,sendbuffer,2);
		    sendPackage.command = 2;
		    byte2bytearray((byte) sendPackage.command,sendbuffer,3);
			ByteBuffer buffer = ByteBuffer.wrap(sendPackage.data);
		    sendchecksumdata = STM32CRC.GenerateCrc(buffer, 52);
		    int2bytearray(sendchecksumdata,sendbuffer,56);
		    sendPackage.checksum = sendchecksumdata;
		    ubxPort.writeBytes(sendbuffer, 64);
		}
		
	}
	public void saveprogramdata(int serialnumberset, int rightpolecountset, int leftpolecountset,
			int rightspeedcoefset, int leftspeedcoefset, int rightcurrentcoefset,
			int leftcurrentcoefset, int communicationrateset, int modesset, int rightkpset,
			int leftkpset, int rightkiset, int leftkiset, int rightwindupset, int leftwindupset) {
		// TODO Auto-generated method stub
		if(ubxPort.isOpen()) {
		    byte[] sendbuffer = new byte[64];
			for (int i = 0; i < sendbuffer.length; i++) {
				sendbuffer[i] = 0;
				sendPackage.data[i%52] = 0;
			}
		    sendbuffer[0] = (byte) sendPackage.startchar;
		    sendbuffer[1] = (byte) sendPackage.syncchar;
		    sendbuffer[60] = (byte) sendPackage.stop1;
		    sendbuffer[61] = (byte) sendPackage.stop2;
		    sendbuffer[62] = (byte) sendPackage.stop3;
		    sendbuffer[63] = (byte) sendPackage.stop4;
		    sendPackage.packageno += 1;
		    byte2bytearray((byte) sendPackage.packageno,sendbuffer,2);
		    sendPackage.command = 3;
		    byte2bytearray((byte) sendPackage.command,sendbuffer,3);
		    int2bytearray(serialnumberset,sendPackage.data,0);
		    byte2bytearray((byte)rightpolecountset,sendPackage.data,4);
		    byte2bytearray((byte)leftpolecountset,sendPackage.data,5);
		    byte2bytearray((byte)rightspeedcoefset,sendPackage.data,6);
		    byte2bytearray((byte)leftspeedcoefset,sendPackage.data,7);
		    byte2bytearray((byte)rightcurrentcoefset,sendPackage.data,8);
		    byte2bytearray((byte)leftcurrentcoefset,sendPackage.data,9);
		    byte2bytearray((byte)communicationrateset,sendPackage.data,10);
		    byte2bytearray((byte)modesset,sendPackage.data,11);
		    int2bytearray(rightkpset,sendPackage.data,12);
		    int2bytearray(leftkpset,sendPackage.data,16);
		    int2bytearray(rightkiset,sendPackage.data,20);
		    int2bytearray(leftkiset,sendPackage.data,24);
		    int2bytearray(rightwindupset,sendPackage.data,28);
		    int2bytearray(leftwindupset,sendPackage.data,32);
			System.arraycopy(sendPackage.data, 0, sendbuffer, 4, 36);
			ByteBuffer buffer = ByteBuffer.wrap(sendPackage.data);
		    sendchecksumdata = STM32CRC.GenerateCrc(buffer, 52);
		    int2bytearray(sendchecksumdata,sendbuffer,56);
		    sendPackage.checksum = sendchecksumdata;
		    ubxPort.writeBytes(sendbuffer, 64);
		}
		
	}
	public int bytearray2int(byte[] data, int index) {
		int temp;
		temp = (data[(index)%64]&0xFF);
		temp += (data[(index+1)%64]&0xFF)<<8;
		temp += (data[(index+2)%64]&0xFF)<<16;
		temp += (data[(index+3)%64]&0xFF)<<24;
		return temp;
	}
	public int bytearray2short(byte[] data, int index) {
		int temp;
		temp = (data[(index)%64]&0xFF);
		temp += (data[(index+1)%64]&0xFF)<<8;
		return temp;
	}
	public int bytearray2byte(byte[] data, int index) {
		int temp;
		temp = (data[(index)%64]&0xFF);
		return temp;
	}
	public void int2bytearray(int data, byte[]out, int index) {
		out[index] = (byte) (data & 0xFF);
		out[index+1] = (byte) (data>>8 & 0xFF);
		out[index+2] = (byte) (data>>16 & 0xFF);
		out[index+3] = (byte) (data>>24 & 0xFF);
	}
	public void short2bytearray(short data, byte[] out, int index) {
		out[index] = (byte) (data & 0xFF);
		out[index+1] = (byte) (data>>8 & 0xFF);	
	}
	public void byte2bytearray(byte data, byte[] out, int index) {
		out[index] = (byte) (data & 0xFF);
	}

}

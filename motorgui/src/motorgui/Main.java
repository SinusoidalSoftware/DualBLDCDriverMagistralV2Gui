/**
 * 
 */
package motorgui;

import java.util.ArrayList;

/**
 * @author mustafa
 *
 */
public class Main {

	private static ArrayList<String> portnames;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gui thegui = new Gui();
		thegui.start();
		Uart thecomm = new Uart();
		
		boolean running = true;
		while(running ) {
			while(!thecomm.connected)
			{
				while(!thegui.deviceselected)
				{
					try { Thread.sleep(1000); } catch (Exception e) {}
					portnames = thecomm.getports();
					thegui.devicelist = portnames;
					thegui.refreshlist();
				}
				thecomm.connect(thegui.selecteddeviceindex);
			}
			thecomm.checkincome();
			if(thecomm.checkdataready) {
				thecomm.checkdataready = false;
				thegui.refreshcheckdata(thecomm.counterright,thecomm.counterleft,thecomm.battery,thecomm.globalerror,thecomm.rightcurrent,thecomm.leftcurrent,thecomm.temperature);
			}
			if(thegui.setspeedschanged) {
				thegui.setspeedschanged = false;
				thecomm.sendspeeds(thegui.rightspeedset,thegui.leftspeedset);
			}
			if(thegui.actionload) {
				thegui.actionload = false;	
				thecomm.readprogramdata();
			}
			if(thecomm.eepromdataready) {
				thecomm.eepromdataready = false;
				thegui.refreshprogramdata(thecomm.serialnumber,thecomm.rightpolecount,thecomm.leftpolecount,thecomm.rightspeedfilter,thecomm.leftspeedfilter,thecomm.rightcurrentfilter,thecomm.leftcurrentfilter,thecomm.sendrate,thecomm.modes,thecomm.rightkp,thecomm.leftkp,thecomm.rightki,thecomm.leftki,thecomm.rightwindup,thecomm.leftwindup);
			}
			if(thegui.actionsave) {
				thegui.actionsave = false;
				thecomm.saveprogramdata(thegui.serialnumberset,thegui.rightpolecountset,thegui.leftpolecountset,thegui.rightspeedcoefset,thegui.leftspeedcoefset,thegui.rightcurrentcoefset,thegui.leftcurrentcoefset,thegui.communicationrateset,thegui.modesset,thegui.rightkpset,thegui.leftkpset,thegui.rightkiset,thegui.leftkiset,thegui.rightwindupset,thegui.leftwindupset);
			}
		}
		try { Thread.sleep(5000); } catch (Exception e) {}
		System.out.println("Closing");
		//thecomm.close();
		//System.exit(0);
	}
}

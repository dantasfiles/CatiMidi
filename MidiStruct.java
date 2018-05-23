

 /* Created by Chee Yong Lee, Daniel Dantas
   Catimidi
   Class name: MidiStruct
   The structure used to hold the midi information. Serializable class
   for easy input/output.   Development Duration: March 2001 to May 2001
*/


/**
 * Data Structure to contain information on a midi segment
 */
// libraries used
package Catimidi;
import java.util.Vector;
import java.util.Random;
import java.io.*;
import java.util.zip.*;
				
public class MidiStruct implements java.io.Serializable
{
	
	int time_no;
	int note_no;			// number of noteuencies values
	int family_no;			// number of families values
	Vector noteVector;		// time frame of noteuencies
	Vector familyVector;	// time frame of noteuencies values
	Vector beatVector;		// beat at each time frame
	Vector classVector;		// classification for training file
	

	float beat;				// beat per second
	
	int noteNumber;
	/**
	 * Contructor for MidiStruct
	 */
	
	public MidiStruct (int note_num, int family_num){
		noteVector = new Vector();
		familyVector = new Vector();
		beatVector = new Vector();
		classVector = new Vector();
		
		note_no = note_num;
		family_no = family_num;
		time_no = 0;
	}
	
	
	public void printMidiStruct() {
		int x; int y;
		System.out.println("Note Table");
		for (x = 0; x < time_no; x++) {
			for (y = 0; y < note_no; y++) {
				System.out.print(((float []) noteVector.elementAt(x))[y] + "\t");
			}
			System.out.println();
		}
		System.out.println("Family Table");
		System.out.println("------------");
		System.out.println("Time\tPiano\tGuitar\tBass\tString\tSynth\tDrum");
		for (x = 0; x < time_no; x++) {
			//System.out.print(x + "\t");
			for (y = 0; y < family_no; y++) {
				System.out.print(((float []) familyVector.elementAt(x))[y] + "\t");
			}
			System.out.println();
		}
		System.out.println("Drum Table");
		for (x = 0; x < time_no; x++) {
			System.out.print(((Float) beatVector.elementAt(x)).floatValue() + "\t");
		}
	}
	
	
	/**
	 * Allows a new timeslot to be added with classification
	 */
	public void add_timeslot(float[] noteval, float[] familyval, float beat, int classtype)
	{
		noteVector.add((Object)noteval);
		familyVector.add((Object)familyval);
		beatVector.add(new Float(beat));
		classVector.add(new Integer(classtype));
		time_no++;
		
	}
	
	/**
	 * Allows a new timeslot to be added with classification
	 */
	public void add_timeslot(float[] noteval, float[] familyval, float beat)
	{
		noteVector.add(noteval);
		familyVector.add(familyval);
		beatVector.add(new Float(beat));
		classVector.add(new Integer(-1));
		time_no++;
	}
	
	
	/**
	 * Merge two MidiStruct together together
	 */
	public void merge_struct(MidiStruct addStruct)
	{
		for (int i = 0;i<addStruct.size(); i++)
		{
			float [] noteval = addStruct.get_note(i);
			float [] familyval = addStruct.get_family(i);
			float beatval = addStruct.get_beat(i);	
			int classval = addStruct.get_class(i);
			add_timeslot(noteval, familyval, beatval,classval);
		}
	}


	/**
	 * Accessor for noteuencies information
	 */
	public float[] get_note(int idx)
	{
		return (float [])noteVector.elementAt(idx);
	}
	
	/**
	 * Accessor for family information
	 */
	public float[] get_family(int idx)
	{
		return (float [])familyVector.elementAt(idx);
	}
	
	/**
	 * Accessor for beat information
	 */
	public float get_beat(int idx)
	{
		return ((Float)beatVector.elementAt(idx)).floatValue();
	}
	
	/**
	 * Accessor for class information
	 */
	public int get_class(int idx)
	{
		return ((Integer)classVector.elementAt(idx)).intValue();
	}
	
	/**
	 * return size of data_structure
	 */
	public int size()
	{
		return 	time_no;
	}
	
	/**
	 * Mix up the elements in the structure
	 */
	public void mixup()
	{
		for (int i=0;i<time_no;i++)
		{
			Random n = new Random();
			int swapindex= n.nextInt(time_no);
			// note
			Object temp = noteVector.elementAt(i);
			noteVector.set(i, noteVector.elementAt(swapindex)); 
			noteVector.set(swapindex, temp);
			
			// family
			temp = familyVector.elementAt(i);
			familyVector.set(i, familyVector.elementAt(swapindex)); 
			familyVector.set(swapindex, temp);
			
			// beat
			temp = beatVector.elementAt(i);
			beatVector.set(i,beatVector.elementAt(swapindex));
			beatVector.set(swapindex,temp);
			
			// class type
			temp = classVector.elementAt(i);
			classVector.set(i,classVector.elementAt(swapindex));
			classVector.set(swapindex,temp);
			
		}
	}
	
	/**
	 * Save midistruct to file
	 */
	public void save(String filename) {
    // Create a file dialog to query the user for a filename.
      try {
        // Create the necessary output streams to midistruct.
        FileOutputStream fos = new FileOutputStream(filename); 
        GZIPOutputStream gzos = new GZIPOutputStream(fos);     
        ObjectOutputStream out = new ObjectOutputStream(gzos); 
        out.writeObject(this);      	
        out.flush();            
        out.close();            
      }
      // Print out exceptions.  We should really display them in a dialog...
      catch (IOException e) { System.out.println(e); }
    }
  		
		
  /**
   * Load midistruct from file
   */
  public static MidiStruct load(String filename) {
	  MidiStruct x;
      try {
        // Create necessary input streams
        FileInputStream fis = new FileInputStream(filename); 
        GZIPInputStream gzis = new GZIPInputStream(fis);     
        ObjectInputStream in = new ObjectInputStream(gzis);  
        x = (MidiStruct)in.readObject();
        in.close();                    // Close the stream.
		return x;
      }
      // Print out exceptions.  We should really display them in a dialog...
      catch (Exception e) { System.out.println(e); }
	return null;
  }
 
}// end class MidiStruct
		
package Catimidi;

import java.util.Vector;
import java.io.File;
import java.util.Hashtable;
import javax.sound.midi.*;

public class Decoder {

	long m_timeWidth;
	int m_noteWidth;
	int m_numNotes = 128;
	//int freqblock[] = int[24];
	
	public static int m_famNumber = 6;

	
	public Decoder (long timeWidth, int noteWidth) {
		m_timeWidth = timeWidth;
		m_noteWidth = noteWidth;
		//freqblock = int[24];
		//int freqblock[24];
	}
			
	public MidiStruct cv_midi_struct(File midiFile) {
		return cv_midi_struct(midiFile, -1);
	}
	
	public MidiStruct cv_midi_struct(File midiFile, int classification) {
		Sequence midiSeq=null;
		try {
			midiSeq = MidiSystem.getSequence(midiFile);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("This midi file is improperly formatted.");
		}
		long ticksPerQuart= midiSeq.getResolution();
		long usPerQuart;
		//System.out.println("Resolution:" + ticksPerQuart);
		float timingType = midiSeq.getDivisionType();
		//System.out.println(timingType);
		if (timingType != Sequence.PPQ) {
			System.out.println("This midi file is not standard.");
		}
		float ticksPerUS=0;
		//ticksPerSecond = res * 29.97;
		float musicLength = midiSeq.getMicrosecondLength();
		
		int timeNumber = (int) Math.ceil(musicLength/ m_timeWidth);
		int noteNumber = (int) Math.ceil(m_numNotes/m_noteWidth);
		//System.out.println(timeNumber+ " and mortal " + freqNumber);
		//System.out.println("l " + musicLength + " = " + m_timeWidth+ " * " + timeNumber);
		//System.out.println("20000-20 = " + m_freqWidth + " * " +freqNumber);
		Track [] midiTracks = midiSeq.getTracks();
		float [][] noteTable = new float [timeNumber][noteNumber];
		float [][] famTable = new float [timeNumber][m_famNumber];
		float [] drumTable = new float[timeNumber];
		for (int i = 0; i < timeNumber; i++ ) {
			drumTable[i] =0;
			for (int j = 0; j < noteNumber; j++) {
				noteTable[i][j] = 0;
			}
			for (int j = 0; j < m_famNumber; j++) {
				famTable[i][j] = 0;
			}
		}
		
		//System.out.println(timeNumber + " " + freqNumber);
		Track currTrack;
		MidiEvent currEvent;
		MidiMessage currMessObj;
		long currTime;
		byte[] currMess;
		byte statusByte;
		byte currStat;
		byte currChan;
		int [] chanMap;
		long tick;
		int startBlock;
		float startPos;
		int endBlock;
		float endPos;
		int noteBlock;
		//float freqPos;
		//int freqBlock;
		int instrFam;
		Hashtable notes = new Hashtable();
		Note currNote;
		
		//System.out.println("TrackLength: " + midiTracks.length);
		for (int i = 0; i < midiTracks.length; i++) {
			currTrack = midiTracks[i];
			chanMap = new int[Midi.CHANNUMBER];
			for (int j = 0; j < currTrack.size(); j++) {
				currEvent = currTrack.get(j);
				
				tick = currEvent.getTick();
				currTime = (long) (tick / ticksPerUS);
				
				currMessObj = currEvent.getMessage();
				currMess = currMessObj.getMessage();
				currStat = (byte) ((currMess[0] & 0xF0) >>> 4);
				currChan = (byte) (currMess[0] & 0x0F);
				//System.out.println("Message("+currTime+ ":" + tick+ ":" + ticksPerUS+ ") " + currStat + " " + currChan + " " + currMess[1]);
				switch (currStat) {
					case (Midi.SYSEX):
						if ((currChan == 0xF) && (currMess[1] == 0x51) && (currMess[2] == 0x03)) {
							//System.out.println(currMess[3] + " " + currMess[4] + " " + currMess[5]);
							usPerQuart = ((((int) currMess[3]) & 0xFF) << 16) + ((((int) currMess[4]) & 0x000FF) << 8) + (((int) currMess[5]) & 0xFF);
							//System.out.println("Changing the tempo " + usPerQuart);
							ticksPerUS = (float) ticksPerQuart / (float) usPerQuart;
						}
						break;
					case (Midi.PROGRAM):
						//System.out.println("Changing channel " + currChan + " to " +currMess[1]);
						if (currChan != 0x9) {
							chanMap[currChan] = Midi.getFamily(currMess[1]);
						}
						break;
					case (Midi.NOTEON):
						instrFam = chanMap[currChan];
						if (currMess[2] != 0) {
							if (currChan == 0x9) {
								startBlock = (int) Math.floor(currTime/m_timeWidth);
								drumTable[startBlock]++;
							} else if (instrFam != Midi.IGNORE) {
								//System.out.println("On(" + currMess.length+ "): "+ currChan + ":" + currMess[1]+ ":" + currMess[2]);
									notes.put(new String(currChan + ":" + currMess[1]),
													new Note(currChan, currMess[1], currMess[2], currTime));
							}
							break;
						}
					case (Midi.NOTEOFF):
						instrFam = chanMap[currChan];
						if ((instrFam != Midi.IGNORE) && (currChan != 0x9)) {
						
							//System.out.print("Off: " + currChan + ":" + currMess[1] + ":" + currMess[2] + "\t");
							currNote = (Note) notes.remove(new String(currChan + ":" + currMess[1]));
							if (currNote != null) {
								startBlock = (int) Math.floor(currNote.getTime()/m_timeWidth);
								startPos = currNote.getTime();
								endBlock = (int) Math.floor(currTime/m_timeWidth);
								endPos = currTime;
								
								if ((startBlock < timeNumber) && (endBlock < timeNumber)) {
									System.out.println(currNote.getNumber());
									noteBlock = (int) Math.floor(currNote.getNumber()/m_noteWidth);
									//freqPos = (float) (440.0 * (float) Math.pow(2,((currNote.getNumber() - 69.0) / 12.0)));
									//freqBlock = (int) Math.floor((freqPos-20)/m_freqWidth);
									//System.out.println(freqPos);
									//System.out.println( freqPos); 
														//" fb" + freqBlock + " fw "+m_freqWidth);
									//System.out.println(" ct " + currTime + " s "+startBlock + " e " + endBlock+ " f " + freqPos+ " fb" + freqBlock + " fw "+m_freqWidth);
									for (; startBlock <= endBlock; startPos = m_timeWidth*(++startBlock)) {
										if (startBlock == endBlock) {
											//System.out.println(startBlock +" and fear " + freqBlock);
											noteTable[startBlock][noteBlock] += (endPos - startPos + 1) * currNote.getVelocity();
											famTable[startBlock][instrFam] += (endPos - startPos + 1) * currNote.getVelocity();
										} else {
											//System.out.println(startBlock +" and fear " + freqBlock);
											noteTable[startBlock][noteBlock] += (((startBlock+1)*m_timeWidth) - startPos) * currNote.getVelocity();
											famTable[startBlock][instrFam] += (((startBlock+1)*m_timeWidth) - startPos) * currNote.getVelocity();
										}
									}
								}
							}
						}
					default:
						break;
				}
			}
		}
		//System.out.println("Done with parsing");
		int x;
		int y;
		/*
		System.out.println("Frequency Table");
		for (x = 0; x < timeNumber; x++) {
			for (y = 0; y < freqNumber; y++) {
				System.out.print(freqTable[x][y] + "\t");
			}
			System.out.println();
		}
		System.out.println("Family Table");
		for (x = 0; x < timeNumber; x++) {
			for (y = 0; y < m_famNumber; y++) {
				System.out.print(famTable[x][y] + "\t");
			}
			System.out.println();
		}
		System.out.println("Drum Table");
		for (x = 0; x < timeNumber; x++) {
			System.out.print(drumTable[x] + "\t");
		}
		*/
			
		float noteTotal;
		float famTotal;
		//int x;
		//int y;
		for (x = 0; x < timeNumber; x++) {	
			noteTotal = 0;
			for (y = 0; y < noteNumber; y++) {
				noteTotal += noteTable[x][y];
			}
			for (y = 0; y < noteNumber; y++) {
				if (noteTotal !=0) {
					noteTable[x][y] = noteTable[x][y]/noteTotal;
				}
			}
			famTotal=0;
			for (y = 0; y < m_famNumber; y++) {
				famTotal += famTable[x][y];
			}
			for (y = 0; y < m_famNumber; y++) {
				if (famTotal !=0) {
					famTable[x][y] = famTable[x][y]/famTotal;
				}
			}
		}			
		//System.out.println("Done with normalizing");
		
		MidiStruct ms = new MidiStruct(noteNumber, m_famNumber);
		if (classification == -1) {
			for (x = 0; x < timeNumber; x++) {	
				ms.add_timeslot(noteTable[x], famTable[x], drumTable[x]);
			}
		} else {
			for (x = 0; x < timeNumber; x++) {	
				ms.add_timeslot(noteTable[x], famTable[x], drumTable[x], classification);
			}
		}
		
		return ms;
		/*
		System.out.println("Frequency Table");
		for (x = 0; x < timeNumber; x++) {
			for (y = 0; y < 5; y++) {
				System.out.print(freqTable[x][y] + "\t");
			}
			System.out.println();
		}
		System.out.println("Family Table");
		System.out.println("------------");
		System.out.println("Time\tPiano\tGuitar\tBass\tString\tSynth\tDrum");
		for (x = 0; x < timeNumber; x++) {
			System.out.print(x*m_timeWidth/1000000.0 + "\t");
			for (y = 0; y < m_famNumber; y++) {
				System.out.print(famTable[x][y] + "\t");
			}
			System.out.println();
		}
		System.out.println("Drum Table");
		for (x = 0; x < timeNumber; x++) {
			System.out.print(drumTable[x] + "\t");
		}
		*/
		
			
		
	}
}

class Note {
	Note(int channel, int number, int velocity, long time) {
		m_channel = channel;
		m_number = number;
		m_velocity = velocity;
		m_time = time;
	}
	private int m_channel;
	private int m_number;
	private int m_velocity;
	private long m_time;
	int getChannel() {
		return m_channel;
	}
	int getNumber() {
		return m_number;
	}
	int getVelocity() {
		return m_velocity;
	}
	long getTime() {
		return m_time;
	}
}


class Midi {
	static int getFamily(int instrument) {
		switch (instrument) {
			case (0):
			case (1):
			case (2):
			case (3):
			case (4):
			case (5):
			case (6):
			case (7):
			case (16):
			case (17):
			case (18):
			case (19):
			case (20):
			case (21):
			case (22):
			case (23):
				return PIANOORGAN;
			case (24):
			case (25):
			case (26):
			case (27):
			case (28):
			case (29):
			case (30):
			case (31):
				return GUITAR;
			case (32):
			case (33):
			case (34):
			case (35):
			case (36):
			case (37):
			case (38):
			case (39):
				return BASS;
			case (40):
			case (41):
			case (42):
			case (43):
			case (44):
			case (45):
			case (46):
			case (47):
			case (48):
			case (49):
			case (50):
			case (51):
			case (52):
			case (53):
			case (54):
			case (55):
				return STRINGSENSEMBLE;
			case (56):	
			case (57):
			case (58):
			case (59):
			case (60):
			case (61):
			case (62):
			case (63):
			case (64):
			case (65):
			case (66):
			case (67):
			case (68):
			case (69):
			case (70):
			case (71):
			case (72):
			case (73):
			case (74):
			case (75):
			case (76):
			case (77):
			case (78):
			case (79):
				return BRASSREEDPIPE;
			case (80):
			case (81):
			case (82):
			case (83):
			case (84):
			case (85):
			case (86):
			case (87):
			case (88):
			case (89):
			case (90):
			case (91):
			case (92):
			case (93):
			case (94):
			case (95):
			case (96):
			case (97):
			case (98):
			case (99):
			case (100):
			case (101):
			case (102):
			case (103):
				return SYNTH;
			case (112):
			case (113):
			case (114):
			case (115):
			case (116):
			case (117):
			case (118):
			case (119):
				return PERCUSSIVE;
			case (104):
			case (105):
			case (106):
			case (107):
			case (108):
			case (109):
			case (110):
			case (111):
			case (8):
			case (9):
			case (10):
			case (11):
			case (12):
			case (13):
			case (14):
			case (15):
			case (120):
			case (121):
			case (122):
			case (123):
			case (124):
			case (125):
			case (126):
			case (127):
				return IGNORE;
			default:
				System.err.println("Invalid General MIDI Instrument");
				return 16;
		}
	}
	/*
	public static boolean comparebyte(byte a, byte b)
	{
		System.out.println("Orig:"+Integer.toHexString(a));
		a = (byte)(~a+1);
		System.out.println("Final:"+Integer.toHexString(a));
		return (a==b);
	}*/
	
	public static final int PIANOORGAN = 0;
	//public static final int PIANO = 0;
	//public static final int ORGAN = 2;
	public static final int GUITAR = 1;
	public static final int BASS = 2;
	public static final int STRINGSENSEMBLE = 3;
	//public static final int STRINGS  = 5;
	//public static final int ENSEMBLE = 6;
	public static final int BRASSREEDPIPE = 4;
	//public static final int BRASS = 7;
	//public static final int REED = 8;
	//public static final int PIPE = 9;
	public static final int SYNTH = 4;
	//public static final int SYNTHLEAD = 10;
	//public static final int SYNTHPAD = 11;
	//public static final int SYNTHFX = 12;
	public static final int PERCUSSIVE = 5;
	public static final int IGNORE = 6;
	//public static final int CHROMATICPERCUSSION = 1;
	//public static final int ETHNIC = 13;
	//public static final int SOUNDFX = 15;
	public static final int FAMNUMBER = 5;
	public static final int CHANNUMBER = 16;
	
	public static final byte NOTEOFF = 0x8;
	public static final byte NOTEON = 0x9;
	public static final byte PROGRAM = 0xC;
	public static final byte SYSEX = 0xF;
	
}
		
		
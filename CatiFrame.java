/*
 * CatiFrame.java
 *
 * Created on March 16, 2001, 3:26 AM
 */


/**
 *
 * @author  Administrator
 * @version
 */
package Catimidi;import javax.swing.*;
import javax.swing.border.*;
import java.awt.Font;import java.awt.Dimension;
import java.awt.Color;
import java.util.Vector;
import java.io.File;//import Decoder;

public class CatiFrame extends JFrame {
	
	// program constant
	final int FREQWIDTH = 1000;
	final int TIMEWIDTH = 500000; 	 // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JTabbedPane tab_panel;
    private javax.swing.JPanel neural_panel;
    private javax.swing.JPanel bayesian_panel;
    private javax.swing.JPanel vector_panel;
    private javax.swing.JPanel info_panel;
    private javax.swing.JLabel status_line;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;	// Items in convert panel	private javax.swing.JPanel file_chooser_converter;	private javax.swing.JLabel expfile_name_label;	private javax.swing.JTextField expfile_name;
	private javax.swing.JLabel expfile_numgenre_label;
	private javax.swing.JTextField expfile_numgenre;
	private javax.swing.JLabel expfile_name_genre_label;
	private javax.swing.JTextField expfile_namegenre;	private javax.swing.JLabel expfile_index_label;    private javax.swing.JTextField expfile_index;
    private javax.swing.JButton open_file_button;    private javax.swing.JTextArea midifile_log;
    private javax.swing.JTextArea expfile_log;	
	private javax.swing.JButton convert_file_button;	private javax.swing.JButton clear_button;
	private javax.swing.JFileChooser fc;
    private java.util.Vector midifiles;
    private java.util.Vector expfiles;
	private java.util.Vector inputfiles;		// decoder class	Decoder decode;
    	// End of variables declaration//GEN-END:variables
    	
    
    /** Creates new form CatiFrame */
    public CatiFrame() {
		decode = new Decoder(TIMEWIDTH,FREQWIDTH);
		inputfiles = new Vector();		initComponents ();
        //pack ();		
    }		
	private void initComponents() {//GEN-BEGIN:initComponents		// Set frame bounds
		this.setSize(new Dimension(1024,768));		this.setBounds(0,0,1024,768);
		//this.setEnabled(true);
		//this.setVisible(true);
		//this.setSize(new Dimension(1000,700)); 				
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        tab_panel = new javax.swing.JTabbedPane();
        neural_panel = new javax.swing.JPanel();
        bayesian_panel = new javax.swing.JPanel();
        vector_panel = new javax.swing.JPanel();
        info_panel = new javax.swing.JPanel();
        status_line = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();		// convert panel items
		file_chooser_converter = new javax.swing.JPanel();
		expfile_name_label = new javax.swing.JLabel();		expfile_name = new javax.swing.JTextField();
		expfile_numgenre_label = new javax.swing.JLabel();		expfile_numgenre = new javax.swing.JTextField();		expfile_name_genre_label = new javax.swing.JLabel();		expfile_namegenre = new javax.swing.JTextField();
		expfile_index_label = new javax.swing.JLabel();
        expfile_index = new javax.swing.JTextField("0");        open_file_button = new javax.swing.JButton();
        midifile_log = new javax.swing.JTextArea();
        expfile_log = new javax.swing.JTextArea();		convert_file_button = new javax.swing.JButton();		clear_button = new javax.swing.JButton();
        fc = new javax.swing.JFileChooser("midifiles//");
        midifiles = new java.util.Vector();
        expfiles = new java.util.Vector();		
        fileMenu.setText("File");
          openMenuItem.setText("Open");
            openMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    openMenuItemActionPerformed(evt);
                }
            }
			);
            fileMenu.add(openMenuItem);
            
          saveMenuItem.setText("Save");
            fileMenu.add(saveMenuItem);
            
          saveAsMenuItem.setText("Save As ...");
            fileMenu.add(saveAsMenuItem);
            
          exitMenuItem.setText("Exit");
            exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    exitMenuItemActionPerformed(evt);
                }
            }
            );
            fileMenu.add(exitMenuItem);
            menuBar.add(fileMenu);
          
        editMenu.setText("Edit");
          
          cutMenuItem.setText("Cut");
            editMenu.add(cutMenuItem);
            
          copyMenuItem.setText("Copy");
            editMenu.add(copyMenuItem);
            
          pasteMenuItem.setText("Paste");
            editMenu.add(pasteMenuItem);
            
          deleteMenuItem.setText("Delete");
            editMenu.add(deleteMenuItem);
            menuBar.add(editMenu);
          
        helpMenu.setText("Help");
          
          contentsMenuItem.setText("Contents");
            helpMenu.add(contentsMenuItem);
            
          aboutMenuItem.setText("About");
            aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    aboutMenuItemActionPerformed(evt);
                }
            }
            );
        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu);
        getContentPane().setLayout(null);
        setName("MainFrame");
        setTitle("Catimidi : MIDI Categorizer");
        setBackground(java.awt.Color.blue);
        //setState(Frame.NORMAL);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        }
        );
        
        tab_panel.setPreferredSize(new java.awt.Dimension(600, 400));
        tab_panel.setBorder(new javax.swing.border.EtchedBorder(0));
        tab_panel.setName("tab_panel");
        tab_panel.setMinimumSize(new java.awt.Dimension(640, 480));
        tab_panel.setDoubleBuffered(true);
        tab_panel.setBackground(java.awt.Color.blue);				// file chooser converter
        file_chooser_converter.setLayout(null);
        file_chooser_converter.setBorder(new javax.swing.border.SoftBevelBorder(0));
        file_chooser_converter.setBounds(20, 150, 320, 510);
                  file_chooser_converter.add(expfile_name_label);
  		    expfile_name_label.setName("expfile_name_label");
            expfile_name_label.setText("Name of exp file");
            expfile_name_label.setForeground(java.awt.Color.black);
            expfile_name_label.setFont(new java.awt.Font ("Tempus Sans ITC", 0, 14));
            expfile_name_label.setBounds(20, 20, 140, 30);

          file_chooser_converter.add(expfile_name);
			expfile_name.setName("expfile_name");
            expfile_name.setBounds(155, 20, 150, 30);
		    
          file_chooser_converter.add(expfile_numgenre_label);
   		    expfile_numgenre_label.setName("expfile_numgenre_label");
            expfile_numgenre_label.setText("Number of genre in exp file");
            expfile_numgenre_label.setForeground(java.awt.Color.black);
            expfile_numgenre_label.setFont(new java.awt.Font ("Tempus Sans ITC", 0, 14));
            expfile_numgenre_label.setBounds(20, 55, 200, 30);

          file_chooser_converter.add(expfile_numgenre);
			expfile_numgenre.setName("expfile_numgenre");
            expfile_numgenre.setBounds(250, 55, 30, 30);
			
		  file_chooser_converter.add(expfile_namegenre_label);
   		  expfile_namegenre_label.setName("expfile_namegenre_label");
          expfile_namegenre_label.setText("Number of genre in exp file");
          expfile_namegenre_label.setForeground(java.awt.Color.black);
          expfile_namegenre_label.setFont(new java.awt.Font ("Tempus Sans ITC", 0, 14));
          expfile_namegenre_label.setBounds(20, 85, 200, 30);

          file_chooser_converter.add(expfile_namegenre);
			expfile_namegenre.setName("expfile_namegenre");
            expfile_namegenre.setBounds(250, 85, 30, 30);
		    		  file_chooser_converter.add(expfile_index_label);
            expfile_index_label.setName("expfile_index_label");
            expfile_index_label.setText("Index of the exp file");
            expfile_index_label.setForeground(java.awt.Color.black);
            expfile_index_label.setFont(new java.awt.Font ("Tempus Sans ITC", 0, 14));
            expfile_index_label.setBounds(70, 115, 220, 30);

		  file_chooser_converter.add(expfile_index);			expfile_index.setName("expfile_index");
            expfile_index.setBounds(70, 150, 180, 30);

          file_chooser_converter.add(open_file_button);
            open_file_button.setIcon(new javax.swing.ImageIcon("Catimidi/pic/open.gif"));
            open_file_button.setLabel("Choose Midi files");
            open_file_button.setName("open_file_button");
            open_file_button.setBounds(70, 200, 180, 40);
            open_file_button.addMouseListener(new java.awt.event.MouseAdapter() {
              public void mouseClicked(java.awt.event.MouseEvent evt) {
                  open_file_buttonMouseClicked(evt);
              }
            });
          
          file_chooser_converter.add(midifile_log);
            midifile_log.setEditable(true);
            midifile_log.setName("midifile_log");
            midifile_log.setBounds(10, 260, 230, 170);			midifile_log.setBackground(new Color(140,140,240));

          file_chooser_converter.add(expfile_log);
            expfile_log.setEditable(true);
            expfile_log.setName("expfile_log");			expfile_log.setBackground(new Color(240,140,140));
            expfile_log.setBounds(250, 260, 30, 170);
          		  file_chooser_converter.add(convert_file_button);
            convert_file_button.setLabel("Convert files");
            convert_file_button.setName("convert_file_button");
            convert_file_button.setBounds(440, 400, 180, 30);
			convert_file_button.addMouseListener(new java.awt.event.MouseAdapter() {
			  public void mouseClicked(java.awt.event.MouseEvent evt) {
				  convert_file_buttonMouseClicked(evt);
			  }
			  });
            
		  file_chooser_converter.add(clear_button);
            clear_button.setLabel("Clear");
            clear_button.setName("clear_button");
            clear_button.setBounds(320, 400, 90, 30);
			clear_button.addMouseListener(new java.awt.event.MouseAdapter() {
			  public void mouseClicked(java.awt.event.MouseEvent evt) {
				  clear_buttonMouseClicked(evt);
			  }
			  });
		
	    tab_panel.addTab("MIDI Conversion", file_chooser_converter);		file_chooser_converter.setBounds(20,20,640,480);		
        neural_panel.setName("neural_panel");
        tab_panel.addTab("Neural Networks", neural_panel);
          
          
        bayesian_panel.setName("bayesian_panel");
        tab_panel.addTab("Bayesian Networks", bayesian_panel);
          
        vector_panel.setName("vector_panel");  
        tab_panel.addTab("Vector Method", vector_panel);
          
          
        getContentPane().add(tab_panel);		tab_panel.setBounds(30, 40, 960, 480);
        
        
        info_panel.setBorder(new javax.swing.border.SoftBevelBorder(0));
        info_panel.setName("info_panel");
        
        //status_line.setBorder(new TitledBorder(
        //  new EtchedBorder(), "Status", 2, 2,
        //  new Font ("Dialog", 0, 12)));
          status_line.setName("status_line");
          status_line.setText("Catimidi ready to run.");
          status_line.setForeground(new java.awt.Color (0, 51, 51));
          status_line.setBackground(new java.awt.Color (51, 51, 255));
          info_panel.add(status_line);
          
          
        getContentPane().add(info_panel);
        info_panel.setBounds(50, 580, 680, 90);
        
        
        
        jButton1.setText("jButton1");
          jToolBar1.add(jButton1);
          
          
        jButton2.setText("jButton2");
          jToolBar1.add(jButton2);
          
          
        jButton3.setText("jButton3");
          jToolBar1.add(jButton3);
          
          
        jButton4.setText("jButton4");
          jToolBar1.add(jButton4);
          
          
        jButton5.setText("jButton5");
          jToolBar1.add(jButton5);
          
          
        getContentPane().add(jToolBar1);
        jToolBar1.setBounds(40, 0, 390, 40);
        
        
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("catimidi.jpg")));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        }
        );
        
        getContentPane().add(jButton6);
        jButton6.setBounds(790, 530, 210, 160);
        
        setJMenuBar(menuBar);				setEnabled(true);
        
    }//GEN-END:initComponents

  private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
// Add your handling code here:
    String myoutstring = "CS 478 Machine Learning Project\nMidi File Categorizer\nWritten by Daniel Dantas & Chee Yong Lee\nCornell University\n May 2001\nVersion 1.0\n";
	JOptionPane.showInternalMessageDialog(null, "information", "information", JOptionPane.INFORMATION_MESSAGE);//    JOptionPane.showInternalMessageDialog(this, "About Catimidi",myoutstring, JOptionPane.INFORMATION_MESSAGE);
    JOptionPane.showMessageDialog(this,myoutstring,"About Catimidi",JOptionPane.OK_OPTION);  }//GEN-LAST:event_aboutMenuItemActionPerformed

  private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
// Add your handling code here:
  }//GEN-LAST:event_jButton6ActionPerformed
    
  private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
      // Add your handling code here:
      // selects a particular file
      JFileChooser chooser = new JFileChooser();
      ExampleFileFilter filter = new ExampleFileFilter();
      filter.addExtension("exp");
      filter.setDescription("sample examples");
      chooser.setFileFilter(filter);
      int returnVal = chooser.showOpenDialog(this);
      if(returnVal == JFileChooser.APPROVE_OPTION) {
          status_line.setText("You chose to open this file: "+chooser.getSelectedFile().getName());}
  }//GEN-LAST:event_openMenuItemActionPerformed
  
    private void exitMenuItemActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit (0);
    }//GEN-LAST:event_exitMenuItemActionPerformed
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit (0);
    }//GEN-LAST:event_exitForm

	/**
	 * Choose the name wanted
	 */	 public void open_file_buttonMouseClicked(java.awt.event.MouseEvent evt)	 {
		fc.showOpenDialog(null);
        String s1 = (fc.getSelectedFile()).getName();
        String s2 = expfile_index.getText();
        midifile_log.append(s1 + "\n");
        expfile_log.append(s2 + "\n");
        midifiles.addElement(s1);
        expfiles.addElement(s2);		inputfiles.addElement(fc.getSelectedFile());
	    int val = Integer.parseInt(expfile_index.getText())+1;	    Integer Ival = new Integer(val);	    expfile_index.setText(Ival.toString());
		convert_file_button.setEnabled(true);	 }	 	 /**
	  * 
	  */	  public void convert_file_buttonMouseClicked(java.awt.event.MouseEvent evt)	  {
		convert_file_button.setEnabled(false);
	       /*
		Audiofft myfft = new Audiofft();		
	    String outputfile = expfile_name.getText();
		
		String numgenre = expfile_numgenre.getText();		int output_no = Integer.parseInt(numgenre);		*/
				MidiStruct convertstruct = new MidiStruct(FREQWIDTH, TIMEWIDTH);
		for (int i=0;i<midifiles.size();i++)	    {
			String inputfile = (String)(midifiles.elementAt(i));		    int source_index = Integer.parseInt((String)(expfiles.elementAt(i)));
		    MidiStruct tempStruct = decode.cv_midi_struct((File)inputfiles.elementAt(i), source_index);
			convertstruct.merge_struct(tempStruct);		 }		convertstruct.mixup();		//System.out.println(convertstruct.toString());
		/*
	    System.out.println("Mixing.");
	    String[] myargs=new String[3];	    myargs[0]= "1";	    myargs[1]="exp//"+outputfile+".exp";	    myargs[2]="exp//"+outputfile+".exp";
	    myfft.MixSamples(myargs);
		convert_file_button.setEnabled(true);		expfile_name.setText("");
		expfile_numgenre.setText("");		expfile_index.setText("");
		expfile_log.setText("");
		midifile_log.setText("");				midifiles.removeAllElements();		expfiles.removeAllElements();
		*/	  }	  	  /**
	   * Clear the log files
	   */
	  public void clear_buttonMouseClicked(java.awt.event.MouseEvent evt)
	  {		  		//expfile_name.setText("");
		//expfile_numgenre.setText("");		expfile_index.setText("0");
		expfile_log.setText("");
		midifile_log.setText("");				midifiles.removeAllElements();		expfiles.removeAllElements();		inputfiles.removeAllElements();
	  }	
    /**
     * @param args the command line arguments
     */
    public static void main (String args[]) {
        new CatiFrame ().show ();
    }
    
   
    
}

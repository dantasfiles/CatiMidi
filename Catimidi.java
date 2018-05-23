/*
 * Catimidi.java
 *
 * Created on March 16, 2001, 3:26 AM
 */

/**
 *Main Class for the project. Calls the CatiFrame to do most of the work. 
 */package Catimidi;
public class Catimidi extends java.lang.Object {

    /** Creates new Catimidi */
    public Catimidi() {
    }

    /**
    * @param args the command line arguments
    */
    public static void main (String args[]) {
        CatiFrame myFrame = new CatiFrame();
        myFrame.setVisible(true);
    }

}

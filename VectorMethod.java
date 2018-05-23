
 /* Created by Chee Yong Lee, Daniel Dantas
   Catimidi
   
   Class name: VectorMethod
   Performs Vector Space similarity comparisions
   Development Duration: March 2001 to May 2001
*/
package Catimidi;import java.io.*;
import java.util.Random;
import java.lang.Number;
import java.lang.Float;
import java.util.Vector;

public class VectorMethod extends Object {
	VectorSpace[] datalist;
    VectorMethod mymethod;
	Vector mydata;
	int[] personindex;
	int freqno = 24;
	int classnum = 4;
	int count = 0;
	int correctcount = 0;
	int testcount = 0;
	float[] weightvector;	// weighing scale
	int[] classcount = new int[classnum];
		
	// dummy constructor
	public VectorMethod()
	{
	}
        
    // init the values 
    public void init(int n, int freqn, int peoplen)
    {
        datalist = new VectorSpace[n];
	    personindex = new int[n];
        freqno = freqn;
	    classnum = peoplen;
        count = 0;
	    correctcount = 0;
	    testcount = 0;
	    weightvector = new float[freqno];
        for (int i=0;i<classnum;i++)
		   classcount[i]=0;
	    for (int i=0;i<freqno;i++){ 
		   weightvector[i]=0;
	    }
    }
	
	// training stage: add new data element into the training set
	public void adddata(Vector p,int pindex)
	{
		datalist[count]= new VectorSpace(p);
		personindex[count++]= pindex;
		classcount[pindex]++;
		for (int i=0;i<freqno;i++)
		{
			weightvector[i] += ((Float)p.elementAt(i)).floatValue();
		}
	}
	
	// calculates the correct percentage
	public float correctpercent()
	{
		return (float)((float)correctcount/(float)testcount);
	}
	
	/**
	 * Resets the statistics
	 */
	public void stat_reset()
	{
		testcount = 0;
		correctcount = 0;
	}
	
	/**
	 *  classify: Classify the examples
	 */
	public void classify(Vector p, int pindex)
	{
		// data structures for counting which category it belongs to
		float[] freqscore = new float[classnum];
		// initialization
		
		for (int i=0;i<classnum;i++)
		{
		//	classcount[i] = 0;
			freqscore[i] = (float)0.0;
		}
		
		// finding similarity with each document in training set
		for (int j=0;j<count;j++)
		{
			freqscore[personindex[j]] += datalist[j].cosineval(p,weightvector);
		//	classcount[personindex[j]]++;
		}
		
		// find the class it has highest average freqscore
		float highestscore = (float)-999.0;
		int highestindex =-9;
		for (int k=0;k<classnum;k++)
		{
			float temp = (float)(freqscore[k]/(float)classcount[k]);
			if (temp>highestscore)
			{
				highestscore = temp;
				highestindex = k;
			}
		}
		testcount++;
		// Comparing the results
		if (highestindex == pindex)
		{
			correctcount++;
			//System.out.print("Yes ");
		}
		else
		{
			//System.out.print("No ");
		}
	}
}

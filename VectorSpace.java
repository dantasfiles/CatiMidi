
 /* Created by Chee Yong Lee, Daniel Dantas
   Catimidi
   
   Class name: VectorMethod
   The basic vectorspace class to perform similarity calculations.   Development Duration: March 2001 to May 2001
*/

package Catimidi;import java.util.Vector;

class VectorSpace extends Object
{ 
	private Vector data;
	public VectorSpace(Vector v)
	{
		data=v;
	}
	
	// find cosine between two angles
	public float cosineval(Vector v,float weightvector[])
	{
		float sum = 0;
		float mag1 = 0;
		float mag2 = 0 ;
		// dot product
		for (int i=0;i<v.size();i++)
		{
			float a = getfloat(data.elementAt(i));
			float b = getfloat(v.elementAt(i));
			sum += a*b*(b/weightvector[i]);
			mag1 += a*a;
			mag2 += b*b;
		}
		// dividing by magnitude
		sum = (float)(sum/(Math.sqrt((double)mag1)*Math.sqrt((double)mag2)));
		return sum;
	}
	
	// get the float value of the object
	public float getfloat(Object v)
	{
		return ((Float)v).floatValue();
	}

}
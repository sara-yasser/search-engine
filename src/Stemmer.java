
/*

   Porter stemmer in Java. The original paper is in

       Porter, 1980, An algorithm for suffix stripping, Program, Vol. 14,
       no. 3, pp 130-137,

   See also http://www.tartarus.org/~martin/PorterStemmer

   History:

   Release 1

   Bug 1 (reported by Gonzalo Parra 16/10/99) fixed as marked below.
   The words 'aed', 'eed', 'oed' leave k at 'a' for step 3, and b[k-1]
   is then out outside the bounds of b.

   Release 2

   Similarly,

   Bug 2 (reported by Steve Dyrdahl 22/2/00) fixed as marked below.
   'ion' by itself leaves j = -1 in the test for 'ion' in step 5, and
   b[j] is then outside the bounds of b.

   Release 3

   Considerably revised 4/9/00 in the light of many helpful suggestions
   from Brian Goetz of Quiotix Corporation (brian@quiotix.com).

   Release 4

*/
import java.io.*;

import org.tartarus.snowball.ext.PorterStemmer;

/**
 * Stemmer, implementing the Porter Stemming Algorithm
 *
 * The Stemmer class transforms a word into its root form.  The input
 * word can be provided a character at time (by calling add()), or at once
 * by calling one of the various stem(something) methods.
 */

class Stemmer 
{
	void Stemmer2 () {}

	String stemTerm (String term) 
	{
		PorterStemmer stemmer = new PorterStemmer( );
		 stemmer.setCurrent(term);
		 stemmer.stem();
		 String result = stemmer.getCurrent();
		 return result;
	}
	public static void main(String args[])
    {
		Stemmer stemmer =  new Stemmer();	
      System.out.println("Jsoup Can read HTML page from URL, title : " 
                                     + stemmer.stemTerm("riding"));
     
    }
	    
	    }
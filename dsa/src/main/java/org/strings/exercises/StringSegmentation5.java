/*
 * String Segmentation
 * https://www.geeksforgeeks.org/word-break-problem-using-backtracking/
 * 
 * Approach: Backtracking
 * 
 * Steps:
 * 1. We start scanning the input sentence from the left from the index 0 to n (length):
 *   iteration 1: 0 to 1
 *   iteration 2: 0 to 2
 *   ...
 *   iteration n: 0 to n
 * 2. As we find a valid word, we need to check whether the rest of the sentence can make valid words or not.
 * Because in some situations the first found word from the left side can leave a remaining portion that is
 * not further separable.
 *  e.g.: ice cream
 *        icecream
 *        
 *        sam sung
 *        samsung
 *        
 * So, in that case, we should come back and leave the currently found word (e.g.: ice) and keep on searching
 * for the next word. And this process is recursive because to find out whether the right portion is separable
 * or not, we need the same logic.
 * 
 * So we will use recursion and backtracking to solve this problem. To keep track of the found words
 * we will use a stack. Whenever the right portion of the string does not make valid words, we pop the top string
 * from the stack and continue finding.
 */
package org.strings.exercises;

import java.util.Arrays;
import java.util.List;

public class StringSegmentation5 {

    // Prints all possible word breaks of given string
    static void wordBreak(List<String> dict, String s)
    {
    	String ans="";
    	int n = s.length();
    	
    	wordBreakHelper(n, s, dict, ans);
    }
    
    /**
     * returns true if the word can be segmented into parts such
     * that each part is contained in dictionary
     * 
     * Time Complexity: O(2^N), 2^N combinations in the worst case.
     * Auxiliary Space: O(N^2), recursive stack of wordBreakHelper() in the worst case, n = length of the input string.
     */
    static void wordBreakHelper(int n, String s, List<String> dict, String ans)
    {
    	for(int i = 1; i <= n; i++)
    	{
    		// Extract substring from 0 to i in prefix
	        String prefix=s.substring(0, i); // left portion
	   
	        // If dictionary contains this prefix, then
	        // we check for remaining string. Otherwise
	        // we ignore this prefix (there is no else for
	        // this if) and try next
	        if(dict.contains(prefix))
	        {
	        	// If no more elements are there, print it
	        	if(i == n)
	        	{
		            // Add this element to previous prefix
		            ans += prefix;
		            System.out.println(ans);
		            return; // return out function, stack for backtracking
	        	}
	        	wordBreakHelper(n - i, s.substring(i,n), dict, ans+prefix+" "); // right portion
	        }
    	}
    }
    
    public static void main(String args[])
    {
        // test 1:
        System.out.println("First Test:");

    	List<String> dict1 = Arrays.asList("pine","pineapple","apple");
        String str1 = "pineapple";

        wordBreak(dict1,str1);
        /* output:
		pine apple
		pineapple
		*/
        
        /***************************************/
        
    	// test 2:
        System.out.println("\nSecond Test:");

        // List of strings in dictionary
        List <String> dict2 = Arrays.asList("mobile","samsung","sam","sung",
                                          "man","mango", "icecream","and",
                                          "go","i","love","ice","cream");
        
        String str2 = "iloveicecreamandmango"; 	// for first test case
          
        // call to the method
        wordBreak(dict2,str2);
        /* output:
		i love ice cream and man go
		i love ice cream and mango
		i love icecream and man go
		i love icecream and mango
		*/

        /***************************************/
        
        // test 3:
        System.out.println("\nThird Test:");

        String str3 ="ilovesamsungmobile";     	// for second test case

        // call to the method
        wordBreak(dict2,str3);
        /* output:		
		i love sam sung mobile
		i love samsung mobile
         */
    }
}

/*
notes:

* test 1:
input = "pineapple"
dict = "pine","apple","pineapple"

ans = ""

pineapple
p
pi
pin
pine	-> dict contains it
		   ans = "pine"
  
  wb(apple): recursion, ans = "pine "
  a
  ap
  app
  appl
  apple -> dict contains it
       	   ans = "pine apple" -> print()
       	   return
       	   
  pinea
  pineap
  pineapp
  pineappl
  pineapple -> dict contains it
  		 	   ans = "pineapple" -> print()
  		 	   return

* test 2:
input = "iloveicecreamandmango"; // n = length = 21
dict = "mobile","samsung","sam","sung","man","mango", "icecream","and","go","i","love","ice","cream"

ans = ""

iter i	prefix	dict.contains(prefix)	wordBreakHelper(n, str, dict, ans+prefix+" ")
1 		i		true					(20, "loveicecreamandmango", dict, "i ")

iloveicecreamandmango

 i-wb(loveicecreamandmango) -> "i" is valid:
							   recursion the rest (loveicecreamandmango)
							   
   wb(loveicecreamandmango): n=20, ans="i "
   l-wb(oveicecreamandmango)
   lo-wb(veicecreamandmango)
   lov-wb(eicecreamandmango)
   love-wb(icecreamandmango) -> "love" is valid
							    recursion the rest (icecreamandmango)
								
     wb(icecreamandmango): n=16, ans="i love "
     i-wb(cecreamandmango) -> "i" is valid:
							  recursion the rest (cecreamandmango)
							  
	   wb(cecreamandmango): n=15, ans="i love i "
   	   ce-wb(creamandmango)
       cec-wb(reamandmango)
	   cecr-wb(eamandmango)
	   cecre-wb(amandmango)
	   cecrea-wb(mandmango)
	   cecream-wb(andmango)
	   cecreama-wb(ndmango)
	   cecreaman-wb(dmango)
  	   cecreamand-wb(mango)
  	   cecreamandm-wb(ango)
  	   cecreamandma-wb(ngo)
  	   cecreamandman-wb(go)
  	   cecreamandmang-wb(o)

     ic-wb(ecreamandmango)
	 ice-wb(creamandmango) -> "ice" is valid:
							  recursion the rest (creamandmango)
	   
	   wb(creamandmango): n=13, ans="i love ice "
	   c-wb(reamandmango)
	   cr-wb(eamandmango)
	   cre-wb(amandmango)
	   crea-wb(mandmango)
	   cream-wb(andmango) -> "cream" is valid:
						     recursion the rest (andmango)
							 
	     wb(andmango): n=8, ans="i love ice cream "
		 ...
	     and-wb(mango): -> "and" is valid:
					       recursion the rest (mango)
		
		   wb(mango): n=5, ans="i love ice cream and "
		   m-wb(ango)
		   ...
		   man-wb(go) -> "man" is valid:
						 recursion the rest (go)
						 
		     wb(go): n=2, ans="i love ice cream and man "
			 g-wb(o)
			 go -> "go" is valid:
						no more elements
						ans="i love ice cream and man go" -> print()
						return
		   
		   mang-wb(o)
		   mango -> "mango" is valid:
					     no more elements
						 ans="i love ice cream and mango" -> print()
						 return
*/
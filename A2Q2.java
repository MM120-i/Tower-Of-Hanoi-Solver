import java.util.ArrayList;
public class A2Q2 {

    /**
     * A class representing a single move in the TOH solution,
     * i.e., moving the disk at the top of fromPeg to the top of toPeg
     * Note: a move is invalid if it results in a larger disk being above a smaller disk.
     * DO NOT MODIFY THIS CLASS.
     */
    static class Move {

        private final int fromPeg;
        private final int toPeg;

        public Move(int from, int to) {
        	
            this.fromPeg = from;
            this.toPeg = to;
        }

        public String toString() {
        	
            return String.format("%d to %d", this.fromPeg, this.toPeg);
        }
    }
    
    private static class constants { // Constants that are used in threepeg and fourpeg problem.
    	
    	public final static int ORIGIN_PEG = 1;
    	public final static int DEST_PEG = 2;
    	public final static int THIRD_PEG = 3;
    	public final static int FOURTH_PEG = 4;
    }

    /**
     * Return the sequence of moves that solves the 3-peg TOH problem with n disks.
     * Assumptions:
     * - The pegs are numbered 1, 2, 3
     * - The origin peg is 1.
     * - The destination peg is 2.
     * - n > 0
     */
    public static ArrayList<Move> threePegTOH(int n) {

    	ArrayList<Move> List = new ArrayList<Move>();
    	Move steps = new Move(0,0);
    	
    	if(n == 1) { // If we got one disk, by common sense we can solve it in one step.
    		
    		steps = new Move(constants.ORIGIN_PEG, constants.DEST_PEG);
    		List.add(steps);
    		return List;
    	}
    	else { // Otherwise...
    		
    	    try {
    	    	
    	    	return solver3(List, n, constants.ORIGIN_PEG, constants.DEST_PEG, constants.THIRD_PEG); // call helper function
    	    }
    	    catch(IllegalArgumentException e) {} // Exception if the pre condition is not met.
    	    
    	}
		return List;

    }
    
    private static ArrayList<Move> solver3(ArrayList<Move> List2, int disks, int origin, int destination, int third) throws IllegalArgumentException{ // Helper function for 3peg
    	
    	int difference = disks - 1;   
    	
    	if(disks == 0) { // Base case
    		
    		return List2;
    	}
    	else if (disks == 1) { // Base case
        	
        	List2.add(new Move(origin, destination));
            return List2;
        }
        else if(disks > 1) {   
        	
        	solver3(List2, difference, origin, third, destination);   // Recursive call
        	
        	List2.add(new Move(origin, destination));  // Adds to the list.
            
            solver3(List2, difference, third, destination, origin);   // Recursive call
            
            return List2;
        }
        else { // If the precondition is not met by any chance, then return empty list (no moves).
        
          throw new IllegalArgumentException(); // Exception is thrown if the pre condition is not met.
        }
    }
    
    /**
     * Return the sequence of moves that solves the 4-peg TOH problem with n disks,
     * with the strategy described in the assignment handout
     * Assumptions:
     * - The pegs are numbered 1, 2, 3, 4
     * - The origin peg is 1.
     * - The destination peg is 2.
     * - n > 0
     */
    public static ArrayList<Move> fourPegTOH(int n) {    	
    	
    	ArrayList<Move> List = new ArrayList<Move>();
    	Move step = new Move(0,0);
    	
    	if(n == 1) { // If we got one disk, by common sense we can solve it in one step.
    		
    		step = new Move(constants.ORIGIN_PEG, constants.DEST_PEG);
    		List.add(step);
    		return List;
    	}
    	else{ // Otherwise...
  
			try {
				
				return solver4(List, n, constants.ORIGIN_PEG, constants.DEST_PEG, constants.THIRD_PEG, constants.FOURTH_PEG); // call helper function
				
			} 
			catch (IllegalArgumentException e) {} // Exception if the pre condition is not met. 
    	}
    	
		return List;
    }
    
    private static ArrayList<Move> solver4(ArrayList<Move> List2, int disks, int origin, int destination, int third, int fourth) throws IllegalArgumentException {  // Helper function for 4peg
    	
    	
     int difference = disks - 2;
    	
   	 if (disks == 0) {  // Base case
   		
   	    return List2;
   	 }
   	 else if (disks == 1) { // Base case
   		
   		List2.add(new Move(origin, destination));
   	    return List2;
   	 }
   	 else if(disks > 1) {
   		 
   		solver4(List2, difference, origin, third, fourth, destination);  // Recursive call
   	   	
   		// Adds to the list:
   		List2.add(new Move(origin, fourth));
   		List2.add(new Move(origin, destination));
   		List2.add(new Move(fourth, destination));
   	   	 
   	    solver4(List2, difference, third, destination, origin, fourth);  // Recursive call
   	    
   	    return List2;
   	 }
   	 else { 
   		 
   		throw new IllegalArgumentException(); // Exception is thrown if the pre condition is not met.
   	 }
   	 	
   	}
    
    public static void main(String[] args) {
    	
    	System.out.println(threePegTOH(1));
    	System.out.println(threePegTOH(2));
    	System.out.println(threePegTOH(3));
    	System.out.println(threePegTOH(4));
    	System.out.println(threePegTOH(10));
    	System.out.println(fourPegTOH(1));
    	System.out.println(fourPegTOH(2));
    	System.out.println(fourPegTOH(3));
    	System.out.println(fourPegTOH(4));
    	System.out.println(fourPegTOH(10));      

    }
}

/*
   The square class allows the board class
   to have objects for each square on the perimeter of 
   the sorry! board. 
   
   required functions:
   
   1. initial method -square(string)
   2. hasPawn(pawn p) @returns boolean if the location of the square == the location of given pawn 
   3. Type() @returns the enum value correlated to type of square.
   4. checkPlayerPawn() @returns the color of the pawn 
   5. setPosition()
   6. getPosition() 
*/

public class square {
    
    private String type; //type of square: blank, arrow, line, circle, start, safe, end
    private int location; //0-59 normal tiles, 101-105 yellow safe zone, 106-109 yellow end zone, 111-114 yellow start etc...
    private String color; //color: yellow, green, red, blue
    private Pawn pawn;
    
    //public enum squareType {invalid, reg, triangle, end, slide, color, home, start}
    //private String squareType;
    //private squareType sqt;
    
    public square(String type, String color, int location) { //enum tells us which type of square pawn is on
        this.type = type;
        this.location = location;
        this.color = color;
    }
    
    public boolean hasPawn(){
      if(!(this.pawn == null)){
         return true;
      }
      else{
         return false;
      }
    }
    
    public void setPawn(Pawn pawn){
    	this.pawn = pawn;
    }
    public Pawn removePawn(){
    	Pawn tempPawn = this.pawn;
    	this.pawn = null;
    	return tempPawn;
    }
    public Pawn getPawn(){
    	return pawn;
    }
    public String getPawnColor(){
    	return pawn.getColor();
    }
    
    public int getPosition(){
		return location;
    }
    public String getColor(){
    	return color;
    }
    public String getType(){
    	return type;
    }
    
    //public squareType type(String s){
      //reg, triangle, end, slide, color, home, start
      
    //  switch (s){
    //     case"reg": return sqt.reg;
    //     case"triangle": return sqt.triangle;
    //     case"end": return sqt.end;
    //     case"slide": return sqt.slide;
    //     case"color": return sqt.color;
    //     case"home": return sqt.home;
    //     case"start": return sqt.start;
    //     default:  return sqt.invalid;
    //  }
      //...
}
    
    
    
    /*public void setPawn(Pawn p){
        square.p = p;
    }
    
    public boolean checkPawn(){
        return checkPawn;
    }
    
    
    public static boolean hasPawn(){ //needs to be changed
        if (checkPawn = true){
            System.out.println("This square has a pawn");
        }
        else{
            System.out.println("Square does not have pawn");
        }
    }*/
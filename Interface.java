import java.io.*;

public class Interface {
    public static void main(String[] args) throws IOException, DictionaryException {
    	
    	if (args.length == 0) {
    		System.out.println("no input file detected");
    	} // if
    	
    	// create out bst dictionary used throughout the interface
    	BSTDictionary bstdictionary = new BSTDictionary();
    	
    	// tries to read file passed through argument
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            // inialize variables for try/atch
        	int    fileType;
        	String label;
            String line;
            String data;
            
            
            // read each line from file until null
            while ((line = reader.readLine()) !=  null) {
            	// sets first line to the label, sets second line to line
            	label = line;
            	line = reader.readLine();
                
            	// sets the format of the file
                if      (line.startsWith("/"))   fileType = 2;
                else if (line.startsWith("-"))   fileType = 3;
                else if (line.startsWith("+"))   fileType = 4;
                else if (line.startsWith("*"))   fileType = 5;
                else if (line.endsWith(".jpg"))  fileType = 6;
                else if (line.endsWith(".gif"))  fileType = 7;
                else if (line.endsWith(".html")) fileType = 8;
                else 							 fileType = 1;
                
                // if filetype is 2,3,4,5, set data to the second line (excluding the first character)
                if (fileType ==  2 || fileType ==  3 || fileType ==  4 || fileType ==  5) data = line.substring(1);
                // else set the data to second line
                else data = line;
                
                // with the data, create the object
                bstdictionary.put(new Record(new Key(label, fileType), data));
            } // while
        } // try 
        
        
        catch (IOException e) {
            System.out.println("Error:  " + e.getMessage());
            throw new IOException("IO ERROR");
        } // catch
        catch (DictionaryException e) { // handles a necessary error for the dictionary
        	System.out.println("Error:  " + e.getMessage());
        	throw new DictionaryException("DICTIONARY ERROR");
        } // catch
        
        
        StringReader user = new StringReader();
        boolean doLoop = true;
        
        while (doLoop) {
        	// create the objects used to handle the files
        	PictureViewer pictureViewer = new PictureViewer();
        	SoundPlayer soundPlayer = new SoundPlayer();
        	ShowHTML htmlShower = new ShowHTML(); // shower: as in to show, not to wash 
        	
        	// prompts the user for commamnd
        	String line = user.read("Enter command: ");
        	
        	// this splits up the command into different parts ->  each part is an element
        	String[] command = line.split(" ");
        	
 
        	String fileName = "";
        	String fileData = "";
        	int fileType = 0;
        	Record record;
        	
        	// base is the first section of the command
        	String base = command[0].toLowerCase();
        	
       
        	// storing the file data, file type, and file name 
        	
        	// if command has at least 2
        	if (command.length > 1) {
	        	fileName = command[1];
	        	// if command has at least 3 parts
	        	if (command.length > 2) {
	        	    fileType = Integer.parseInt(command[2]);
	        	    // if command has 4 parts
	        	    if (command.length > 3) fileData = command[3];
	        	} // if
        	}
        	
        	
        	// sets base command
        	switch (base) {
        	
        	case "define":
        		// creates key and checks if it is in 
        		Key define = new Key(fileName, 1);
        		record = bstdictionary.get(define);
        		
        		if (record !=  null) System.out.println(record.getDataItem()); // if in dictionary, print out the record data
        		else System.out.println("The word " + fileName + " is not in the ordered dictionary"); // print error
        		break;
        		
    		// translate follows the exact same logic as define
        	case "translate":
        		Key translate = new Key(fileName, 2);
        		record = bstdictionary.get(translate);
        		
        		if (record !=  null) System.out.println(record.getDataItem()); 
        		else System.out.println("There is no definition for the word " + fileName);
        		break;
        	
        		
            case "sound":
                Key sound = new Key(fileName, 3);
                record = bstdictionary.get(sound);

                if (record !=  null) { // puts it in try catch to handle multimedia exception
					try {soundPlayer.play(record.getDataItem());} // soundplayer.play plays the sound
                	catch (MultimediaException e) {e.printStackTrace();}
                } // if
                else System.out.println("There is no sound file for " + fileName);
                break;
            
            // play and say follows the exact same logic as sound
            case "play":
                Key play = new Key(fileName, 4);
                record = bstdictionary.get(play);

                if (record !=  null) {
					try {soundPlayer.play(record.getDataItem());} 
                	catch (MultimediaException e) {e.printStackTrace();}
                } // if
				else System.out.println("There is no music file for " + fileName);
                break;

            case "say":
                Key say = new Key(fileName, 5);
                record = bstdictionary.get(say);

                if (record !=  null) {
					try {soundPlayer.play(record.getDataItem());} 
                	catch (MultimediaException e) {e.printStackTrace();}
                } // if
                else System.out.println("There is no voice file for " + fileName);
                break;

            
            case "show":
                Key show = new Key(fileName, 6);
                record = bstdictionary.get(show);

                if (record !=  null) {
					try {pictureViewer.show(record.getDataItem());} // picture viewer shows the image or gif
                	catch (MultimediaException e) {e.printStackTrace();}
                } // if
                else System.out.println("There is no image file for " + fileName);
                break;

            // animate and browse follows the exact same logic as show
            case "animate":
                Key animate = new Key(fileName, 7);
                record = bstdictionary.get(animate);

                if (record !=  null) {
					try {pictureViewer.show(record.getDataItem());} 
                	catch (MultimediaException e) {e.printStackTrace();}
                } // if
                else System.out.println("There is no animated image file for " + fileName);
                break;

            case "browse":
                Key browse = new Key(fileName, 8);
                record = bstdictionary.get(browse);

                if (record !=  null) htmlShower.show(record.getDataItem());
                else System.out.println("There is no webpage called " + fileName);
                break;
                
        	
            // functional base commands
                
            case "delete":
            	// simply calls the remove function from the binary search tree dictionary class (remove from BinarySearchTree)
            	try {bstdictionary.remove(new Key(fileName, fileType));}
            	catch (DictionaryException e) {System.out.println("No record in the ordered dictionary has key (" + fileName + "," + fileType + ")");}
            	break;
            	
            case "add":
            	// simply calls the put function from the binary search tree dictionary class (insert from BinarySearchTree)
                try {bstdictionary.put(new Record(new Key(fileName, fileType), fileData));} 
                catch (DictionaryException e) {System.out.println("A record with the given key (" + fileName + "," + fileType + ") is already in the ordered dictionary.");}
                break;
            	
            case "list":
            	boolean found = false;
                Record matchingRecord = bstdictionary.successor(new Key(fileName, -1));

                //if the successor is not null or it does not start with prefix
                if (matchingRecord == null || !matchingRecord.getKey().getLabel().startsWith((fileName)))System.out.println("No label attributes in the ordered dictionary start with prefix " + fileName);
                else { //else print the record label
                    System.out.println(matchingRecord.getKey().getLabel());
                    
                    // keep getting the next successor and printing labels
                    while (matchingRecord.getKey().getLabel().startsWith(fileName)){
                        System.out.println(matchingRecord.getKey().getLabel());
                        matchingRecord = bstdictionary.successor(matchingRecord.getKey());
                    } // while
                } // else
                break;
            	
            case "first":
            	Record smallestRecord = bstdictionary.smallest();
        		if (smallestRecord !=  null) {
        			System.out.println(String.format(smallestRecord.getKey().getLabel() + "," + smallestRecord.getKey().getType() + "," + smallestRecord.getDataItem()));
        		} // if
            	break;
            	
            case "last":
            	Record largestRecord = bstdictionary.largest();
        		if (largestRecord !=  null) {
        			System.out.println(String.format(largestRecord.getKey().getLabel() + "," + largestRecord.getKey().getType() + "," + largestRecord.getDataItem()));
        		} // is
            	break;
            	
            case "exit":
            	// simply stop the program
            	System.out.println("Quitting program.");
                System.exit(0);
            	break;
            	
            default:
            	// assume the command was incorrect in some way
            	System.out.println("Invalid command.");
            	break;
            	
        	} // switch
        } // while
        
    } // method main
} // class interface
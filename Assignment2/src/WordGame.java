
	import javax.swing.JOptionPane;

	public class WordGame{
		private static int score = 0;
		private static String[] wordList = {  "star", "candy", "wings", "salutations", "string",
											  "cat", "obnoxious", "cow", "dog", "Java", "coffee",
											  "Russia", "Washington", "Florida State", "red", "awesome",
											  "game", "mathematics", "science", "computers"};
		private static int numWords = wordList.length;

		public static void main(String[] args){
			int choice = JOptionPane.showConfirmDialog(null, "Want to play the word game?", "Word Game?", JOptionPane.YES_NO_OPTION);

			if(choice == JOptionPane.NO_OPTION){
				JOptionPane.showMessageDialog(null, "Goodbye :(", "Disappointment", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
			
			// Play the game

			Timer t1 = new Timer();	//Construct the Timer object
			
			t1.start();				// Some code to time.
			
			JOptionPane.showMessageDialog(null, "To play the game, type the word you see into the text box.");
			for (int i=0; i<numWords; i++){
				Timer t2 = new Timer();	//Construct the Timer object
				
				t2.start();				// Some code to time.
				JOptionPane.showMessageDialog(null, "Round " + (i+1) + " !", "Game", JOptionPane.INFORMATION_MESSAGE);
				String answer = JOptionPane.showInputDialog(null, wordList[i] + ":");
				if (answer.equals(wordList[i])){
					score = score + 1;
					System.out.println("correct");
				}
				else {
					System.out.println("incorrect");
				}
				t2.stop();
				t2.printReport();
			}

			// Score Report
			t1.stop();
			
			if(1.0*score/numWords < 0.75){
				JOptionPane.showMessageDialog(null, "You scored below 75%\n You got " + score + " correct.", "Score Report",
											  JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "Good Job!\n You got " + score + " correct.", "Score Report",
							  				  JOptionPane.INFORMATION_MESSAGE);
			}
			
			System.out.println("It took you "); 
			t1.printReport();
			System.out.println(" to play WordGame."); 
			
		}
	}


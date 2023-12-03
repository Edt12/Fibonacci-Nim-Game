import java.util.ArrayList;
import java.util.Scanner;

public class FibonacciNim {
    static final String REMAINING_COINS_MESSAGE = "Remaining coins: %d, %d, %d";
    static final String CHOOSE_HEAP_MESSAGE = "%s: choose a heap: ";
    static final String CHOOSE_COIN_NUM_MESSAGE
            = "Now choose a number of coins between %d and %d: ";
    static final String CHOOSE_COIN_NUM_MESSAGES_SHORTENED
            = "Now choose between %d and %d: ";
    static final String PLAYER_ONE_INSERT = "Player 1";
    static  final String PLAYER_TWO_INSERT = "Player 2";
    static  final String WIN_MESSAGE = "%s wins!";
    static final int MIN_COIN_NUM = 1;
    static final String ENTER_INTEGER_MESSAGE_HEAP_SELECT
            = "Sorry you must enter an integer in the range -3 to 3, excluding zero.";
    static final String ENTER_INTEGER_MESSAGE_COIN_SELECT
            = "Sorry you must enter an integer.";
    static final String ILLEGAL_HEAP_CHOICE_MESSAGE
            = "Sorry that's not a legal heap choice.";
    static final String ILLEGAL_COIN_NUM
            = "Sorry that's not a legal number of coins for that heap.";
    static final String HEAP_MESSAGE_ERROR
            = "Error illegal heap message in switch";
    static boolean isOdd(double number){
        //divides num by 2 and then rounds it to figure out if odd or even
        double numberHalfed =number/2;
        boolean odd = false;
        if (numberHalfed == Math.round(numberHalfed)){
            odd = false;
        } else if (numberHalfed != Math.round(numberHalfed)) {
            odd = true;

        }
        return odd;
    }
    static int minusOne(int number){
        return number - 1;
    }
    static int multiplyByTwo(int number){
        return number * 2;
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        int maxCoinsAllowed = 12;
        double loopCounter = 0;
        int[] pileNumbers = {13, 13, 13};
        ArrayList<Integer> prevNumTakenPileOne = new ArrayList<Integer>();
        ArrayList<Integer> prevNumTakenPileTwo = new ArrayList<Integer>();
        ArrayList<Integer> prevNumTakenPileThree = new ArrayList<Integer>();
        int pileOneCoinNum = pileNumbers[0];
        int pileTwoCoinNum = pileNumbers[1];
        int pileThreeCoinNum = pileNumbers[2];
        boolean playerTwoActive =false;

        while ((pileOneCoinNum != 0)|| (pileTwoCoinNum != 0)
                || (pileThreeCoinNum != 0)) {

            boolean pileOneSelected = false;
            boolean pileTwoSelected = false;
            boolean pileThreeSelected = false;
            playerTwoActive = false;

            if (isOdd(loopCounter)) {
                playerTwoActive = true;

            }
            String formattedRemCoins;
            if (loopCounter == 0) {
                formattedRemCoins = String.format(REMAINING_COINS_MESSAGE,
                        pileOneCoinNum, pileTwoCoinNum, pileThreeCoinNum);
                System.out.println(formattedRemCoins);

            }

            String formattedChooseHeapMessage;
            if (playerTwoActive) {
                formattedChooseHeapMessage = String.format(CHOOSE_HEAP_MESSAGE,
                        PLAYER_TWO_INSERT);
                System.out.print(formattedChooseHeapMessage);

            } else {

                formattedChooseHeapMessage = String.format(CHOOSE_HEAP_MESSAGE,
                        PLAYER_ONE_INSERT);
                System.out.print(formattedChooseHeapMessage);

            }
            //Checks to see if user has entered Integer and complies with heap rules
            int playerHeapSelect;
            do {
                while (!userInput.hasNextInt()){
                    System.out.println(ENTER_INTEGER_MESSAGE_HEAP_SELECT);
                    System.out.print(formattedChooseHeapMessage);
                    userInput.nextLine();
                }
                playerHeapSelect = userInput.nextInt();
                userInput.nextLine();
                if (playerHeapSelect != 1 && playerHeapSelect != 2 && playerHeapSelect != 3){
                    System.out.println(ILLEGAL_HEAP_CHOICE_MESSAGE);
                    System.out.print(formattedChooseHeapMessage);
                }

            } while (playerHeapSelect != 1 && playerHeapSelect != 2
                    && playerHeapSelect != 3);


            //Determines which pile has been selected
            switch (playerHeapSelect) {
                case 1 -> pileOneSelected = true;
                case 2 -> pileTwoSelected = true;
                case 3 -> pileThreeSelected = true;
                default -> System.out.print(HEAP_MESSAGE_ERROR);

            }
            
            /* Checks which pile was selected and
            uses previous values taken from each piles arraylists first value to
            work out the maximum allowed amount of coins for the pile selected
             */
            if (pileOneSelected) {

                int sizePileOne = prevNumTakenPileOne.size();


                if (sizePileOne == 0) {
                    maxCoinsAllowed = minusOne(pileOneCoinNum);
                } else {
                    int prevNumTaken = prevNumTakenPileOne.get(minusOne(sizePileOne));
                    maxCoinsAllowed = multiplyByTwo(prevNumTaken);
                }
                if (maxCoinsAllowed > pileOneCoinNum) {
                    maxCoinsAllowed = pileOneCoinNum;
                }


            } else if (pileTwoSelected) {
                int sizePileTwo = prevNumTakenPileTwo.size();


                if (sizePileTwo == 0) {
                    maxCoinsAllowed = minusOne(pileTwoCoinNum);

                } else {
                    int prevNumTaken = prevNumTakenPileTwo.get(minusOne(sizePileTwo));
                    maxCoinsAllowed = multiplyByTwo(prevNumTaken);
                }

                if (maxCoinsAllowed > pileTwoCoinNum) {
                    maxCoinsAllowed = pileTwoCoinNum;
                }

            } else if (pileThreeSelected) {

                int sizePileThree = prevNumTakenPileThree.size();

                if (sizePileThree == 0) {
                    maxCoinsAllowed = minusOne(pileThreeCoinNum);
                } else {
                    int prevNumTaken = prevNumTakenPileThree.get(minusOne(sizePileThree));
                    maxCoinsAllowed = multiplyByTwo(prevNumTaken);
                }

                if (maxCoinsAllowed > pileThreeCoinNum) {
                    maxCoinsAllowed = pileThreeCoinNum;

                }

            }

            String formattedChooseCoinNum;
            formattedChooseCoinNum = String.format(CHOOSE_COIN_NUM_MESSAGE,
                    MIN_COIN_NUM, maxCoinsAllowed);

            System.out.print(formattedChooseCoinNum);

            String formattedChooseCoinNumNoSpace =
                    String.format(CHOOSE_COIN_NUM_MESSAGES_SHORTENED, MIN_COIN_NUM,
                            maxCoinsAllowed);

            //Checks to see if user has entered Integer and doesn't enter an illegal value
            int playerCoinNum;
            do {
                while (!userInput.hasNextInt()){
                    System.out.println(ENTER_INTEGER_MESSAGE_COIN_SELECT);
                    System.out.print(formattedChooseCoinNumNoSpace);
                    userInput.nextLine();
                }
                playerCoinNum = userInput.nextInt();
                userInput.nextLine();
                if (playerCoinNum > maxCoinsAllowed || playerCoinNum < MIN_COIN_NUM){
                    System.out.println(ILLEGAL_COIN_NUM);
                    System.out.print(formattedChooseCoinNum);
                }
            }while (playerCoinNum > maxCoinsAllowed || playerCoinNum < MIN_COIN_NUM);

            /* Subtracts amount entered by user from pile selected
            adds the amount subtracted from each pile to that piles array
            which is then used to keep track of how many were subtracted from
            that pile last round
             */
            if (pileOneSelected) {
                pileOneCoinNum -= playerCoinNum;
                prevNumTakenPileOne.add(playerCoinNum);

            } else if (pileTwoSelected) {
                pileTwoCoinNum -= playerCoinNum;
                prevNumTakenPileTwo.add(playerCoinNum);

            } else if (pileThreeSelected) {
                pileThreeCoinNum -= playerCoinNum;
                prevNumTakenPileThree.add(playerCoinNum);


            }

            formattedRemCoins = String.format(REMAINING_COINS_MESSAGE,
                    pileOneCoinNum, pileTwoCoinNum, pileThreeCoinNum);

            //Stops remaining coins from printing after a player has won
            if (pileOneCoinNum!=0 || pileTwoCoinNum!=0 || pileThreeCoinNum != 0){
                System.out.println(formattedRemCoins);
            }

            loopCounter += 1;
        }
        //Uses which players turn it last was to figure out winner and print win message
        if (playerTwoActive) {
            String formattedWinMessagePlayerTwo =
                    String.format(WIN_MESSAGE, PLAYER_TWO_INSERT);
            System.out.println(formattedWinMessagePlayerTwo);
        } else {
            String formattedWinMessagePlayerOne =
                    String.format(WIN_MESSAGE, PLAYER_ONE_INSERT);
            System.out.println(formattedWinMessagePlayerOne);

        }

    }
}

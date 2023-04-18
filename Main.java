import java.util.*;

public class Main {

    //Created a static list as it needs to be known by all the classes and not change
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {

        char[][] gameBoard =  {{' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '}};

        printGameBoard(gameBoard);


//while statement helps the code to stay in loop and keep running to take in more values
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your placement between 1-9: ");
            int playerPos = sc.nextInt();
            System.out.println(playerPos);
            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)){
                System.out.println("position taken! Enter a correct position");
                playerPos = sc.nextInt();
            }

            placePiece(gameBoard, playerPos, "player");

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPositions)){
              //  System.out.println("position taken! Enter a correct position");
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPos, "cpu");


            printGameBoard(gameBoard);

           String result = checkWinner();
            System.out.println(result);

        }



    }

    //create a method to print gameborad
    public static void printGameBoard(char[][] gameBoard) {

        //printing the lines using loop

        for(char[] row : gameBoard){
            for (char c : row){
                System.out.print(c);
            }
            System.out.println();
        }

    }

    public static void placePiece(char[][] gameBoard, int position, String user){

        char symbol = ' ';
        if(user.equals("player")){
            symbol = 'X';
            playerPositions.add(position);
        }else if (user.equals("cpu")){
            symbol = 'O';
            cpuPositions.add(position);
        }


        switch (position){
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }

    }

    public static String checkWinner() {

        List topRow = Arrays.asList(1,2,3);
        List middleRow = Arrays.asList(4,5,6);
        List bottomRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List middleCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(3,5,7);

//added all the winning conditions to a list which can also be called an array list
        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(middleRow);
        winning.add(bottomRow);
        winning.add(leftCol);
        winning.add(middleCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for(List l : winning){
            if (playerPositions.containsAll(l)){
                return "Congratulations you won!";
            }else if (cpuPositions.contains(l)){
                return "CPU wins! Sorry :( ";
            }else if(playerPositions.size() + cpuPositions.size() == 9){
                return "No Winners!";
            }
        }

        return "";
    }
}
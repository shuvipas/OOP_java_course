import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner scanner;
    public HumanPlayer() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void playTurn(Board board, Mark mark) {
        
        //Scanner scanner = new Scanner(System.in);
        boolean inputSuccess = false;
        while (!inputSuccess) {
            try {
                if (scanner.hasNextInt()) {
                    int num = scanner.nextInt();
                    int row = num / 10 - 1;
                    int col = num % 10 - 1;
                    inputSuccess = board.putMark(mark, row, col);
                    if (!inputSuccess) {
                        System.out.println("Invalid input. Please enter a valid integer and place.");
                        scanner.nextLine();
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.nextLine(); // consume the invalid input
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // consume the invalid input
            }
                        
        }
//        in.close();

    }

}

import javafx.util.Pair;

import java.util.*;

public class TicTacToe {

    private static String board[][] = new String[3][3];

    public static void main(String[] args) {
        System.out.println("Game Start! Player1: X, Player2: O");
        init();
        int steps = 1;
        while (steps <= 10) {
            while (true) {
                System.out.print("Player " + (steps % 2 != 0 ? "1" : "2") + " input: ");
                Scanner scanner = new Scanner(System.in);
                try {
                    Pair<Integer, Integer> input = validate(scanner.nextLine());
                    if (!exists(input)) {
                        board[input.getKey()][input.getValue()] = steps % 2 != 0 ? "X" : "O";
                        draw();
                        if (win()) {
                            System.out.println("Player " + (steps % 2 != 0 ? "1" : "2") + " wins the game. Game Over!");
                            System.exit(1);
                        } else {
                            steps++;
                            break;
                        }
                    } else {
                        System.out.print("Position already taken. ");
                    }

                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
            }

        }
    }

    private static void add(List<String> list, String data) {
        if (!data.equals(" ")) {
            list.add(data);
        }
    }

    private static boolean win() {
        List<String> list;
        for (int i = 0; i < 2; i++) {
            list = new ArrayList<>(2);
            add(list, board[i][0]);
            add(list, board[i][1]);
            add(list, board[i][2]);
            if (list.size() == 3 && list.stream().distinct().count() == 1) {
                return true;
            }
        }

        for (int i = 0; i < 2; i++) {
            list = new ArrayList<>(2);
            add(list, board[0][i]);
            add(list, board[1][i]);
            add(list, board[2][i]);
            if (list.size() == 3 && list.stream().distinct().count() == 1) {
                return true;
            }
        }

        list = new ArrayList<>(2);
        add(list, board[0][0]);
        add(list, board[1][1]);
        add(list, board[2][2]);

        if (list.size() == 3 && list.stream().distinct().count() == 1) {
            return true;
        }

        list = new ArrayList<>(2);
        add(list, board[0][2]);
        add(list, board[1][1]);
        add(list, board[2][0]);

        if (list.size() == 3 && list.stream().distinct().count() == 1) {
            return true;
        }

        return false;
    }

    private static boolean exists(Pair<Integer, Integer> pair) {
        return !board[pair.getKey()][pair.getValue()].equals(" ");
    }

    private static void init() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static Pair<Integer, Integer> validate(String line) {
        String[] splits = line.split(",");
        if (splits.length != 2) {
            throw new IllegalArgumentException("Invalid input. ");
        } else {
            int first = Integer.parseInt(splits[0]);
            int second = Integer.parseInt(splits[1]);
            if (first < 4 && first > 0 && second < 4 && second > 0) {
                return new Pair<>(first - 1, second - 1);
            } else {
                throw new IllegalArgumentException("Invalid input. ");
            }

        }
    }

    private static void draw() {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |");
        System.out.println("|---|---|---|");
    }
}

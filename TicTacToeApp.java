import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TicTacToeGame {
    private String[][] board;
    private String currentPlayer;

    public TicTacToeGame() {
        board = new String[3][3];
        currentPlayer = "X";
    }

    public boolean makeMove(int row, int col) {
        if (board[row][col] == null) {
            board[row][col] = currentPlayer;
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            return true;
        }
        return false;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public String checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != null && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                return board[i][0];
            }
            if (board[0][i] != null && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                return board[0][i];
            }
        }
        if (board[0][0] != null && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            return board[0][0];
        }
        if (board[0][2] != null && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            return board[0][2];
        }
        return null;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) return false;
            }
        }
        return true;
    }
}

class TicTacToeUI extends JFrame {
    private TicTacToeGame game;
    private JButton[][] buttons;

    public TicTacToeUI() {
        game = new TicTacToeGame();
        buttons = new JButton[3][3];
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(e -> {
                    JButton button = (JButton) e.getSource();
                    int row = Integer.parseInt(button.getActionCommand().split(",")[0]);
                    int col = Integer.parseInt(button.getActionCommand().split(",")[1]);
                    if (game.makeMove(row, col)) {
                        button.setText(game.getCurrentPlayer().equals("X") ? "O" : "X");
                        String winner = game.checkWinner();
                        if (winner != null) {
                            JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
                            resetBoard();
                        } else if (game.isBoardFull()) {
                            JOptionPane.showMessageDialog(this, "It's a draw!");
                            resetBoard();
                        }
                    }
                });
                buttons[i][j].setActionCommand(i + "," + j);
                add(buttons[i][j]);
            }
        }

        setTitle("Tic-Tac-Toe (OOP)");
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        game = new TicTacToeGame();
    }

    public static void main(String[] args) {
        new TicTacToeUI();
    }
}

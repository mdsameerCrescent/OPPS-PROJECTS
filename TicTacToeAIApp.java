import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TicTacToeAI {
    private String[][] board;
    private String currentPlayer;

    public TicTacToeAI() {
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
        // Similar logic to check winner
        return null;
    }

    public boolean isBoardFull() {
        return true;
    }
}

class TicTacToeAIUI extends JFrame {
    private TicTacToeAI game;
    private JButton[][] buttons;

    public TicTacToeAIUI() {
        game = new TicTacToeAI();
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
                        }
                    }
                });
                buttons[i][j].setActionCommand(i + "," + j);
                add(buttons[i][j]);
            }
        }

        setTitle("Tic-Tac-Toe with AI");
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void resetBoard() {
        // reset the board after a winner
    }

    public static void main(String[] args) {
        new TicTacToeAIUI();
    }
}

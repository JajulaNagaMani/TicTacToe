package com.nagamani.tictactoe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private boolean isXTurn = true;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initializeButtons();
        setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("")) return;

        clicked.setText(isXTurn ? "X" : "O");
        clicked.setForeground(isXTurn ? Color.BLUE : Color.RED);
        isXTurn = !isXTurn;

        String winner = checkWinner();
        if (winner != null) {
            JOptionPane.showMessageDialog(this, winner + " wins!");
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
        }
    }

    private String checkWinner() {
        String[][] combos = new String[3][3];
        for (int i = 0; i < 9; i++) {
            combos[i / 3][i % 3] = buttons[i].getText();
        }

        for (int i = 0; i < 3; i++) {
            // Check rows and columns
            if (!combos[i][0].equals("") && combos[i][0].equals(combos[i][1]) && combos[i][1].equals(combos[i][2]))
                return combos[i][0];
            if (!combos[0][i].equals("") && combos[0][i].equals(combos[1][i]) && combos[1][i].equals(combos[2][i]))
                return combos[0][i];
        }

        // Check diagonals
        if (!combos[0][0].equals("") && combos[0][0].equals(combos[1][1]) && combos[1][1].equals(combos[2][2]))
            return combos[0][0];
        if (!combos[0][2].equals("") && combos[0][2].equals(combos[1][1]) && combos[1][1].equals(combos[2][0]))
            return combos[0][2];

        return null;
    }

    private boolean isBoardFull() {
        for (JButton b : buttons) {
            if (b.getText().equals("")) return false;
        }
        return true;
    }

    private void resetBoard() {
        for (JButton b : buttons) {
            b.setText("");
        }
        isXTurn = true;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(TicTacToe::new);
    }
}


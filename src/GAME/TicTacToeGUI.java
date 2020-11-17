package GAME;

import ADT.DoublyLinkedList;
import ADT.Score;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private Container pane;
    private String currentPlayer;
    private JButton[][] board;
    private boolean hasWinner;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem quit;
    private JMenuItem newGame;

    private static final int[][] WIN_COMBO = {{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{2,5,7}};

    DoublyLinkedList nodeX = new DoublyLinkedList();
    DoublyLinkedList nodeO = new DoublyLinkedList();


    public TicTacToeGUI() {
        super();

        pane = getContentPane();
        pane.setLayout(new GridLayout(3, 3));
        setTitle("Tic Tac Toe");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        currentPlayer = "x";
        board = new JButton[3][3];
        hasWinner = false;
        intializeBoard();
        intializeMenuBar();
    }

    private void intializeMenuBar() {
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");

        newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    resetBoard();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        quit = new JMenuItem("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(newGame);
        menu.add(quit);
        menuBar.add(menu);
        setJMenuBar(menuBar);

    }

    private void resetBoard() throws Exception {
        currentPlayer = "x";
        hasWinner = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText("");
            }
        }
    }

    private void intializeBoard() {
        int finalCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton();
                btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
                board[i][j] = btn;
                finalCount = finalCount + 1;
                int finalCount1 = finalCount;
                int finalCount2 = finalCount;

                btn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (((JButton) e.getSource()).getText().equals("") && hasWinner == false) {
                            btn.setText(currentPlayer);
                            setData(finalCount2);
//                            System.out.println(currentPlayer + " Count :" + finalCount1 + ",");
                            hasWinner();
                            togglePlayer();

                        }
                    }
                });
                pane.add(btn);

            }
        }
    }

    private void setData(int data){
        if(currentPlayer.equals("x")) {
            try {
                Score scoreX = new Score("X", data);
                nodeX.addData(scoreX);
                nodeX.displayAll();


            } catch (Exception exception) {
                exception.printStackTrace();
            }

        } else{

            try {
                Score scoreO = new Score("O", data);
                nodeO.addData(scoreO);
                nodeO.displayAll();

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void togglePlayer() {
        if (currentPlayer.equals("x")) {
            currentPlayer = "o";
        } else {
            currentPlayer = "x";
        }
    }

    private void hasWinner() {

        if (board[0][0].getText().equals(currentPlayer) && board[1][0].getText().equals(currentPlayer) && board[2][0].getText().equals(currentPlayer)) {
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
            hasWinner = true;

        } else if (board[0][1].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[2][1].getText().equals(currentPlayer)) {
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
            hasWinner = true;

        } else if (board[0][2].getText().equals(currentPlayer) && board[1][2].getText().equals(currentPlayer) && board[2][2].getText().equals(currentPlayer)) {
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
            hasWinner = true;

        } else if (board[0][0].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[2][2].getText().equals(currentPlayer)) {
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
            hasWinner = true;

        } else if (board[0][2].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[2][0].getText().equals(currentPlayer)) {
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
            hasWinner = true;

        } else if (board[0][0].getText().equals(currentPlayer) && board[0][1].getText().equals(currentPlayer) && board[0][2].getText().equals(currentPlayer)) {
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
            hasWinner = true;

        } else if (board[1][0].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[1][2].getText().equals(currentPlayer)) {
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
            hasWinner = true;

        } else if (board[2][0].getText().equals(currentPlayer) && board[2][1].getText().equals(currentPlayer) && board[2][2].getText().equals(currentPlayer)) {
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won");
            hasWinner = true;

        }

    }
}

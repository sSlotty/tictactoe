package GAME;

import ADT.linkedList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TicTacToeGUI extends JFrame {

    private static final int[][] WIN_COMBO = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {2, 5, 7}};
    ArrayList<Integer> PP;

    linkedList nodeX = new linkedList();
    linkedList nodeO = new linkedList();

    private Container pane;
    private String currentPlayer;
    private JButton[][] board;
    private boolean hasWinner;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem quit;
    private JMenuItem newGame;


    public TicTacToeGUI() {
        super();

        pane = getContentPane();
        pane.setLayout(new GridLayout(3, 3));
        setTitle("Tic Tac Toe");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
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
                    clearGame();
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
                board[i][j].setBackground(Color.BLUE);
                finalCount = finalCount + 1;
                int finalCount1 = finalCount;
                int finalCount2 = finalCount;

                btn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (((JButton) e.getSource()).getText().equals("") && hasWinner == false) {
                            btn.setText(currentPlayer);
                            setData(finalCount2);
                            hasWinner();
                            togglePlayer();

                        }
                    }
                });
                pane.add(btn);

            }
        }
    }

    private void setData(int data) {
        if (currentPlayer.equals("x")) {
            try {
                nodeX.push(data);
                nodeX.head = nodeX.mergeSort(nodeX.head);
                System.out.println();
//                nodeX.printList(nodeX.head);


            } catch (Exception exception) {
                exception.printStackTrace();
            }

        } else {

            try {
                nodeO.push(data);
                nodeO.head = nodeO.mergeSort(nodeO.head);
                System.out.println();
//                nodeO.printList(nodeO.head);


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

    private void clearGame() {
        nodeX.head = null;
        nodeO.head = null;
        hasWinner = false;
        currentPlayer = "x";
        PP.clear();
    }

    private void hasWinner() {
        String player = "A";
        if (currentPlayer.equals("x")) {
            player = "X";
            PP = nodeX.allData(nodeX.head);
        } else if (currentPlayer.equals("o")) {
            player = "O";
            PP = nodeO.allData(nodeO.head);
        } else {
            PP = null;
        }


        for (int i = 0; i < WIN_COMBO.length; i++) {
            int count = 0;
            for (int j = 0; j < PP.size(); j++) {

                for (int k = 0; k <= 2; k++) {
                    if (WIN_COMBO[i][k] == PP.get(j)) {
                        count = count + 1;
                        System.out.println(count);
                        if (count == 3) {
                            hasWinner = true;
                            String winner = "Player : " + player + " is winner";
                            JOptionPane.showMessageDialog(null, winner);
                            break;
                        }
                    }

                }
            }
        }

    }
}

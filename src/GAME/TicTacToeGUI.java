package GAME;

import ADT.linkedList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TicTacToeGUI extends JFrame {

    private static final int[][] WIN_COMBO = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};
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
        setTitle("Tic Tac Toe for KID");
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

                Color bt = Color.decode("#1B4965");
                btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
                btn.setForeground(Color.WHITE);
                btn.setBackground(bt);
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
                            try {
                                hasWinner();
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
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



            } catch (Exception exception) {
                exception.printStackTrace();
            }

        } else {

            try {
                nodeO.push(data);
                nodeO.head = nodeO.mergeSort(nodeO.head);
                System.out.println();



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

    private void hasWinner() throws Exception {
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

            for (int j = 0; j < Objects.requireNonNull(PP).size(); j++) {


                for (int k = 0; k <= 2; k++) {

                    System.out.print("(" + WIN_COMBO[i][k] + ")");

                    if (WIN_COMBO[i][k] == PP.get(j)) {

                        count = count + 1;
                        System.out.println(PP.get(j) + " : " + count);

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
        if(PP.size() >= 5 && !hasWinner){
            int try_again = JOptionPane.showOptionDialog(null, "Try again", "DRAW !", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                if(try_again ==JOptionPane.OK_OPTION) {
                    resetBoard();
                    clearGame();
                }else{
                    System.exit(0);
                }

        }

        System.out.println();
        System.out.println("Player " + player + " : " + PP);
        System.out.println("Size of List : " + PP.size());

        System.out.println("-----");

        long start = System.nanoTime();

        long end = System.nanoTime();

        long elapsedTime = end - start;

        double elapsedTimeInSecond = (double) elapsedTime / 1_000;

        System.out.println(elapsedTimeInSecond + " seconds");

        // TimeUnit
        long convert = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);

        System.out.println(convert + " seconds");
    }
}

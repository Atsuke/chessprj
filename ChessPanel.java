package chessprj;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**********************************************************************
 * Creates the essentials for our GUI
 * Creates board and buttons that are implemented throughout game
 **********************************************************************/

public class ChessPanel extends JPanel {

    private JButton[][] board;
    private JButton undo;
    private ChessModel model;
    private JToggleButton toggle;

    /** white icons */
    private ImageIcon wRook;
    private ImageIcon wBishop;
    private ImageIcon wQueen;
    private ImageIcon wKing;
    private ImageIcon wPawn;
    private ImageIcon wKnight;

    /** black icons */
    private ImageIcon bRook;
    private ImageIcon bBishop;
    private ImageIcon bQueen;
    private ImageIcon bKing;
    private ImageIcon bPawn;
    private ImageIcon bKnight;

    private boolean firstTurnFlag;
    private int fromRow;
    private int toRow;
    private int fromCol;
    private int toCol;

    private listener listener;

    public ChessPanel() {
        model = new ChessModel();
        board = new JButton[model.numRows()][model.numColumns()];
        listener = new listener();
        undo = new JButton("Undo");
        toggle = new JToggleButton("Activate Skynet", false);
        createIcons();

        JPanel boardpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
        boardpanel.setLayout(new GridLayout(model.numRows(), model.numColumns(), 1, 1));

        // creates the board of buttons
        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);
                }else if(model.pieceAt(r, c).player() == Player.BLACK)
                    placeBlackPieces(r, c);
                else if (model.pieceAt(r, c).player() == Player.WHITE)
                    placeWhitePieces(r, c);

                setBackGroundColor(r, c);

                boardpanel.add(board[r][c]);
            }//end inner for
        }//end outer for

        // adds the board and buttons
        add(boardpanel, BorderLayout.WEST);
        boardpanel.setPreferredSize(new Dimension(600, 600));
        buttonpanel.setLayout(new GridLayout(2,1));
        add(buttonpanel);
        buttonpanel.add(toggle);
        buttonpanel.add(undo);
        undo.addActionListener(listener);

        firstTurnFlag = true;
    }

    /**********************************************************************
     * Setter for background colors
     **********************************************************************/
    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(Color.LIGHT_GRAY);
        } else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2 == 1)) {
            board[r][c].setBackground(Color.BLUE);
        }
    }

    /**********************************************************************
     * Places initial white pieces
     *
     * @param r - row
     * @param c - column
     **********************************************************************/
    private void placeWhitePieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, wPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, wRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, wKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, wBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, wQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, wKing);
            board[r][c].addActionListener(listener);
        }
    }//end placeWhitePieces

    /**********************************************************************
     * Places initial black pieces
     *
     * @param r - row
     * @param c - column
     **********************************************************************/
    private void placeBlackPieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, bPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, bRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, bKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, bBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, bQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, bKing);
            board[r][c].addActionListener(listener);
        }
    }//end placeBlackPieces

    /**********************************************************************
     * Sets image icons for all pieces
     **********************************************************************/
    private void createIcons() {
        // Sets the Image for white player pieces
        wRook = new ImageIcon("./src/images/wRook.png");
        wBishop = new ImageIcon("./src/images/wBishop.png");
        wQueen = new ImageIcon("./src/images/wQueen.png");
        wKing = new ImageIcon("./src/images/wKing.png");
        wPawn = new ImageIcon("./src/images/wPawn.png");
        wKnight = new ImageIcon("./src/images/wKnight.png");

        // Sets the Image for black player pieces
        bRook = new ImageIcon("./src/images/bRook.png");
        bBishop = new ImageIcon("./src/images/bBishop.png");
        bQueen = new ImageIcon("./src/images/bQueen.png");
        bKing = new ImageIcon("./src/images/bKing.png");
        bPawn = new ImageIcon("./src/images/bPawn.png");
        bKnight = new ImageIcon("./src/images/bKnight.png");
    }

    /**********************************************************************
     * Updates board
     **********************************************************************/
    private void displayBoard() {

        // iterates through board
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++)

                if (model.pieceAt(r, c) == null)//no piece here
                    board[r][c].setIcon(null);

                else//piece is white

                    if (model.pieceAt(r, c).player() == Player.WHITE) {
                        if (model.pieceAt(r, c).type().equals("Pawn"))
                            board[r][c].setIcon(wPawn);

                        if (model.pieceAt(r, c).type().equals("Rook"))
                            board[r][c].setIcon(wRook);

                        if (model.pieceAt(r, c).type().equals("Knight"))
                            board[r][c].setIcon(wKnight);

                        if (model.pieceAt(r, c).type().equals("Bishop"))
                            board[r][c].setIcon(wBishop);

                        if (model.pieceAt(r, c).type().equals("Queen"))
                            board[r][c].setIcon(wQueen);

                        if (model.pieceAt(r, c).type().equals("King"))
                            board[r][c].setIcon(wKing);

                    }//end white If

                    else//piece is black
                        if (model.pieceAt(r, c).player() == Player.BLACK) {
                            if (model.pieceAt(r, c).type().equals("Pawn"))
                                board[r][c].setIcon(bPawn);

                            if (model.pieceAt(r, c).type().equals("Rook"))
                                board[r][c].setIcon(bRook);

                            if (model.pieceAt(r, c).type().equals("Knight"))
                                board[r][c].setIcon(bKnight);

                            if (model.pieceAt(r, c).type().equals("Bishop"))
                                board[r][c].setIcon(bBishop);

                            if (model.pieceAt(r, c).type().equals("Queen"))
                                board[r][c].setIcon(bQueen);

                            if (model.pieceAt(r, c).type().equals("King"))
                                board[r][c].setIcon(bKing);

                        }//end black If

        }//end outer for
        repaint();
    }//end displayBoard

    /**********************************************************************
     * Undo move
     **********************************************************************/
    public void undo(){
        model.undo();
        model.resetBlack();
        model.resetWhite();
        model.checkAllBlackMoves();
        model.checkAllWhiteMoves();
    }//end undo

    /**********************************************************************
     * Displays message if in check (or has won)
     **********************************************************************/
    public void inCheck(){
        if(model.inCheck()){
            JOptionPane.showMessageDialog(toggle, model.getMessage());
        }
        if(model.isComplete()){
            JOptionPane.showMessageDialog(toggle, model.getMessage());
        }
    }


    /**********************************************************************
     * Inner class that represents listeners
     **********************************************************************/
    private class listener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            model.threatChecks();
            if(event.getSource()== undo){
                undo();
                displayBoard();

            }//end if
            else

                // iterates through board
                for (int r = 0; r < model.numRows(); r++)
                    for (int c = 0; c < model.numColumns(); c++)
                        if (board[r][c] == event.getSource())
                            if (firstTurnFlag) {
                                fromRow = r;
                                fromCol = c;
                                firstTurnFlag = false;
                            } else {
                                toRow = r;
                                toCol = c;
                                firstTurnFlag = true;
                                Move m = new Move(fromRow, fromCol, toRow, toCol);

                                //check valid moves here
                                if ((model.isValidMove(m))) {

                                    model.copy();
                                    model.move(m);
                                    displayBoard();
                                    inCheck();

                                    model.AI();

                                }//end if

                            }//end else

            model.resetBlack();
            model.resetWhite();

        }//end actionPerformed
    }//end Class listener
}//end Class Chess Panel
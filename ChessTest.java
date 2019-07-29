package chessprj;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Atsuke
 */
public class ChessTest {

    public ChessTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /******************************************************************************
     *
     * Start Pawn Tests
     *
     *******************************************************************************/


    @Test
    public void whitePawnTestDiagonal1(){
        Move move = new Move(6,3,5,4);
        ChessModel testModel = new ChessModel();
        assertFalse(testModel.isValidMove(move));

    }//end whitePawnTestDiagonal1

    @Test
    public void whitePawnTestDiagonal2(){
        Move move = new Move(6,3,5,2);
        ChessModel testModel = new ChessModel();
        assertFalse(testModel.isValidMove(move));

    }//end whitePawnTestDiagonal2

    @Test
    public void blackPawnTestDiagonal1(){
        Move move = new Move(1,3,2,2);
        ChessModel testModel = new ChessModel();
        assertFalse(testModel.isValidMove(move));

    }//end blackPawnTestDiagonal1

    @Test
    public void blackPawnTestDiagonal2(){
        Move move = new Move(1,3,2,4);
        ChessModel testModel = new ChessModel();
        assertFalse(testModel.isValidMove(move));

    }//end blackPawnTestDiagonal2


    @Test
    public void pawnTestLeftImpact(){
        Move move = new Move(6,3,6,2);
        ChessModel testModel = new ChessModel();
        assertFalse(testModel.isValidMove(move));

    }//end pawnTestLeftImpact

    @Test
    public void pawnTestRightImpact(){
        Move move = new Move(6,3,6,4);
        ChessModel testModel = new ChessModel();
        assertFalse(testModel.isValidMove(move));

    }//end pawnTestRightImpact

    @Test
    public void whitePawnTestRearImpact(){
        Move move = new Move(6,3,7,3);
        ChessModel testModel = new ChessModel();
        assertFalse(testModel.isValidMove(move));

    }//end whitePawnTestRearImpact

    @Test
    public void blackPawnTestRearImpact(){
        Move move = new Move(1,3,0,3);
        ChessModel testModel = new ChessModel();
        assertFalse(testModel.isValidMove(move));

    }//end blackPawnTestRearImpact

    @Test
    public void whitePawnTestCapture1(){
        Move move = new Move(6,3,5,2);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(5, 2, new Pawn(Player.BLACK,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end whitePawnTestCapture1


    @Test
    public void whitePawnTestCapture2(){
        Move move = new Move(6,3,5,4);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(5, 4, new Pawn(Player.BLACK,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end whitePawnTestCapture2

    @Test
    public void blackPawnTestCapture1(){
        Move move = new Move(1,3,2,2);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(2, 2, new Pawn(Player.WHITE,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end blackPawnTestCapture1

    @Test
    public void blackPawnTestCapture2(){
        Move move = new Move(1,3,2,4);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(2, 4, new Pawn(Player.WHITE,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end blackPawnTestCapture2

    @Test
    public void pawnTestFirstMove1(){
        Move move = new Move(6,3,4,3);
        ChessModel testModel = new ChessModel();
        testModel.move(move);
        Move move2 = new Move(4,3,2,3);
        assertFalse(testModel.isValidMove(move2));

    }//end pawnTestFirstMove1

    @Test
    public void pawnTestFirstMove2(){
        Move move = new Move(6,3,3,3);
        ChessModel testModel = new ChessModel();
        assertFalse(testModel.isValidMove(move));

    }//end pawnTestFirstMove2

    /******************************************************************************
     *
     * Start Bishop Tests
     *
     *******************************************************************************/


    @Test
    public void bishopTestLeftUpImpact(){
        Move move = new Move(7,2,6,1);
        ChessModel testModel = new ChessModel();
        assertFalse(testModel.isValidMove(move));

    }//end bishopTestLeftUpImpact

    @Test
    public void bishopTestRightUpImpact(){
        Move move = new Move(7,2,6,3);
        ChessModel testModel = new ChessModel();
        assertFalse(testModel.isValidMove(move));

    }//end bishopTestRightUpImpact

    @Test
    public void bishopTestLeftDownImpact(){
        Move move = new Move(0,2,1,1);
        ChessModel testModel = new ChessModel();
        assertFalse(testModel.isValidMove(move));

    }//end bishopTestLeftDownImpact

    @Test
    public void bishopTesRightDownImpact(){
        Move move = new Move(0,2,1,3);
        ChessModel testModel = new ChessModel();
        assertFalse(testModel.isValidMove(move));

    }//end bishopTestRightDownImpact

    @Test
    public void bishopDiagonalTest1(){
        Move move = new Move(4,3,2,1);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Bishop(Player.WHITE,testModel));
        assertTrue(testModel.isValidMove(move));
        move = new Move(4,3,2,5);
        assertTrue(testModel.isValidMove(move));


    }//end bishopDiagonalTest1

    @Test
    public void bishopDiagonalTest2(){
        Move move = new Move(4,3,2,5);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Bishop(Player.WHITE,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end bishopDiagonalTest2


    @Test
    public void bishopDiagonalTest3(){
        Move move = new Move(4,3,5,4);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Bishop(Player.WHITE,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end bishopDiagonalTest3


    @Test
    public void bishopDiagonalTest4(){
        Move move = new Move(4,3,5,2);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Bishop(Player.WHITE,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end bishopDiagonalTest4

    @Test
    public void bishopCaptureTest1(){
        Move move = new Move(4,3,3,2);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Bishop(Player.WHITE,testModel));
        testModel.setPiece(3, 2, new Pawn(Player.BLACK,testModel));
        assertTrue(testModel.isValidMove(move));


    }//end bishopCaptureTest1

    @Test
    public void bishopCaptureTest2(){
        Move move = new Move(4,3,3,4);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Bishop(Player.WHITE,testModel));
        testModel.setPiece(3, 4, new Pawn(Player.BLACK,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end bishopCaptureTest2

    @Test
    public void bishopCaptureTest3(){
        Move move = new Move(4,3,5,4);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Bishop(Player.WHITE,testModel));
        testModel.setPiece(5, 4, new Pawn(Player.BLACK,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end bishopCaptureTest3

    @Test
    public void bishopCaptureTest4(){
        Move move = new Move(4,3,5,2);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Bishop(Player.WHITE,testModel));
        testModel.setPiece(5, 2, new Pawn(Player.BLACK,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end bishopCaptureTest4

    /******************************************************************************
     *
     * Start Rook Tests
     *
     *******************************************************************************/


    @Test
    public void rookImpactTest1(){
        Move move = new Move(7,7,7,6);
        ChessModel testModel = new ChessModel();
        assertFalse(testModel.isValidMove(move));

    }//end rookImpactTest1


    @Test
    public void rookImpactTest2(){
        Move move = new Move(7,7,6,7);
        ChessModel testModel = new ChessModel();
        assertFalse(testModel.isValidMove(move));

    }//end rookImpactTest2


    @Test
    public void rookImpactTest3(){
        Move move = new Move(0,0,1,0);
        ChessModel testModel = new ChessModel();
        assertFalse(testModel.isValidMove(move));

    }//end rookImpactTest3


    @Test
    public void rookImpactTest4(){
        Move move = new Move(0,0,0,1);
        ChessModel testModel = new ChessModel();
        assertFalse(testModel.isValidMove(move));

    }//end rookImpactTest4

    @Test
    public void rookDiagonalTest1(){
        Move move = new Move(4,3,3,2);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Rook(Player.WHITE,testModel));
        assertFalse(testModel.isValidMove(move));

    }//end rookDiagonalTest1

    @Test
    public void rookDiagonalTest2(){
        Move move = new Move(4,3,3,4);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Rook(Player.WHITE,testModel));
        assertFalse(testModel.isValidMove(move));

    }//end rookDiagonalTest2

    @Test
    public void rookDiagonalTest3(){
        Move move = new Move(4,3,5,2);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Rook(Player.WHITE,testModel));
        assertFalse(testModel.isValidMove(move));

    }//end rookDiagonalTest3

    @Test
    public void rookDiagonalTest4(){
        Move move = new Move(4,3,5,4);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Rook(Player.WHITE,testModel));
        assertFalse(testModel.isValidMove(move));

    }//end rookDiagonalTest4

    @Test
    public void rookOffsetTest1(){
        Move move = new Move(4,3,2,2);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Rook(Player.WHITE,testModel));
        assertFalse(testModel.isValidMove(move));

    }//end rookOffsetTest1

    @Test
    public void rookOffsetTest2(){
        Move move = new Move(4,3,2,4);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Rook(Player.WHITE,testModel));
        assertFalse(testModel.isValidMove(move));

    }//end rookOffsetTest

    @Test
    public void rookOffsetTest3(){
        Move move = new Move(4,3,5,1);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Rook(Player.WHITE,testModel));
        assertFalse(testModel.isValidMove(move));

    }//end rookOffsetTest3

    @Test
    public void rookOffsetTest4(){
        Move move = new Move(4,3,5,5);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Rook(Player.WHITE,testModel));
        assertFalse(testModel.isValidMove(move));

    }//end rookOffsetTest4

    @Test
    public void rookHorizontalTest1(){
        Move move = new Move(4,3,4,0);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Rook(Player.WHITE,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end rookHorizontalTest1

    @Test
    public void rookHorizontalTest2(){
        Move move = new Move(4,3,4,7);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Rook(Player.WHITE,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end rookHorizontalTest1

    @Test
    public void rookVerticalTest1(){
        Move move = new Move(5,3,2,3);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(5, 3, new Rook(Player.WHITE,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end rookVerticalTest1

    @Test
    public void rookVerticalTest2(){
        Move move = new Move(2,3,5,3);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(2, 3, new Rook(Player.WHITE,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end rookVerticalTest2

    @Test
    public void rookHorizontalEnemyTest1(){
        Move move = new Move(4,3,4,0);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Rook(Player.WHITE,testModel));
        testModel.setPiece(4, 2, new Rook(Player.BLACK,testModel));
        assertFalse(testModel.isValidMove(move));

    }//end rookHorizontalEnemyTest1

    @Test
    public void rookHorizontalEnemyTest2(){
        Move move = new Move(4,3,4,7);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Rook(Player.WHITE,testModel));
        testModel.setPiece(4, 4, new Rook(Player.BLACK,testModel));
        assertFalse(testModel.isValidMove(move));

    }//end rookHorizontalEnemyTest2

    @Test
    public void rookVerticalEnemyTest1(){
        Move move = new Move(2,3,5,3);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(2, 3, new Rook(Player.WHITE,testModel));
        testModel.setPiece(4, 3, new Rook(Player.BLACK,testModel));
        assertFalse(testModel.isValidMove(move));

    }//end rookVerticalEnemyTest1

    @Test
    public void rookVerticalEnemyTest2(){
        Move move = new Move(5,3,2,3);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(5, 3, new Rook(Player.WHITE,testModel));
        testModel.setPiece(3, 3, new Rook(Player.BLACK,testModel));
        assertFalse(testModel.isValidMove(move));

    }//end rookVerticalEnemyTest2

    @Test
    public void rookCaptureTest1(){
        Move move = new Move(4,3,2,3);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Rook(Player.WHITE,testModel));
        testModel.setPiece(2, 3, new Rook(Player.BLACK,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end rookCaptureTest1

    @Test
    public void rookCaptureTest2(){
        Move move = new Move(4,3,4,1);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Rook(Player.WHITE,testModel));
        testModel.setPiece(4, 1, new Rook(Player.BLACK,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end rookCaptureTest2

    @Test
    public void rookCaptureTest3(){
        Move move = new Move(4,3,4,7);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Rook(Player.WHITE,testModel));
        testModel.setPiece(4, 7, new Rook(Player.BLACK,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end rookCaptureTest3

    @Test
    public void rookCaptureTest4(){
        Move move = new Move(2,3,5,3);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(2, 3, new Rook(Player.WHITE,testModel));
        testModel.setPiece(5, 3, new Rook(Player.BLACK,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end rookCaptureTest4

    /******************************************************************************
     *
     * Working on the Knight Moves
     * Dont judge me. I've waited my whole life to make a Bob Seger pun
     *******************************************************************************/

    @Test
    public void knightMoves1(){
        Move move = new Move(4,3,2,2);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Knight(Player.WHITE,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end knightMoves1

    @Test
    public void knightMoves2(){
        Move move = new Move(4,3,2,4);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Knight(Player.WHITE,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end knightMoves2

    @Test
    public void knightMoves3(){
        Move move = new Move(4,3,3,1);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Knight(Player.WHITE,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end knightMoves3

    @Test
    public void knightMoves4(){
        Move move = new Move(4,3,3,5);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Knight(Player.WHITE,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end knightMoves4

    @Test
    public void knightMoves5(){
        Move move = new Move(4,3,5,1);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Knight(Player.WHITE,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end knightMoves5

    @Test
    public void knightMoves6(){
        Move move = new Move(4,3,5,5);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Knight(Player.WHITE,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end knightMoves6

    @Test
    public void knightMoves7(){
        Move move = new Move(3,3,5,2);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(3, 3, new Knight(Player.WHITE,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end knightMoves7

    @Test
    public void knightMoves8(){
        Move move = new Move(3,3,5,4);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(3, 3, new Knight(Player.WHITE,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end knightMoves8

    @Test
    public void knightMoves9(){
        Move move = new Move(4,3,5,3);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Knight(Player.WHITE,testModel));
        assertFalse(testModel.isValidMove(move));

    }//end knightMoves9

    @Test
    public void knightMoves10(){
        Move move = new Move(4,3,2,7);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Knight(Player.WHITE,testModel));
        assertFalse(testModel.isValidMove(move));

    }//end knightMoves10

    @Test
    public void knightMoves11(){
        Move move = new Move(4,3,4,7);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Knight(Player.WHITE,testModel));
        assertFalse(testModel.isValidMove(move));

    }//end knightMoves11

    @Test
    public void knightMoves12(){
        Move move = new Move(4,3,5,2);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Knight(Player.WHITE,testModel));
        assertFalse(testModel.isValidMove(move));

    }//end knightMoves12

    @Test
    public void knightMoves13(){
        Move move = new Move(4,3,6,2);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Knight(Player.WHITE,testModel));
        assertFalse(testModel.isValidMove(move));

    }//end knightMoves13

    @Test
    public void knightMoves14(){
        Move move = new Move(4,3,0,4);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Knight(Player.WHITE,testModel));
        assertFalse(testModel.isValidMove(move));

    }//end knightMoves13

    @Test
    public void knightCapture1(){
        Move move = new Move(4,3,2,2);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Knight(Player.WHITE,testModel));
        testModel.setPiece(2,2, new Pawn(Player.BLACK,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end knightCapture1

    @Test
    public void knightCapture2(){
        Move move = new Move(4,3,2,4);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Knight(Player.WHITE,testModel));
        testModel.setPiece(2,4, new Pawn(Player.BLACK,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end knightCapture2

    @Test
    public void knightCapture3(){
        Move move = new Move(4,3,3,1);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Knight(Player.WHITE,testModel));
        testModel.setPiece(3,1, new Pawn(Player.BLACK,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end knightCapture3

    @Test
    public void knightCapture4(){
        Move move = new Move(4,3,3,5);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Knight(Player.WHITE,testModel));
        testModel.setPiece(3,5, new Pawn(Player.BLACK,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end knightCapture4

    @Test
    public void knightCapture5(){
        Move move = new Move(4,3,5,1);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Knight(Player.WHITE,testModel));
        testModel.setPiece(5,1, new Pawn(Player.BLACK,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end knightCapture5

    @Test
    public void knightCapture6(){
        Move move = new Move(4,3,5,5);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Knight(Player.WHITE,testModel));
        testModel.setPiece(5,5, new Pawn(Player.BLACK,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end knightCapture6

    @Test
    public void knightCapture7(){
        Move move = new Move(3,3,5,2);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(3, 3, new Knight(Player.WHITE,testModel));
        testModel.setPiece(5,2, new Pawn(Player.BLACK,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end knightCapture7

    @Test
    public void knightCapture8(){
        Move move = new Move(3,3,5,4);
        ChessModel testModel = new ChessModel();
        testModel.setPiece(3, 3, new Knight(Player.WHITE,testModel));
        testModel.setPiece(5,4, new Pawn(Player.BLACK,testModel));
        assertTrue(testModel.isValidMove(move));

    }//end knightCapture8

    @Test
    public void arrayTest(){

        ChessModel testModel = new ChessModel();
        testModel.checkAllBlackMoves();
        assertTrue(testModel.getAllBlackMoves() == 20);

    }//end arrayTest

    @Test
    public void arrayTes2(){

        ChessModel testModel = new ChessModel();
        testModel.checkAllWhiteMoves();
        assertTrue(testModel.getAllWhiteMoves() == 20);

    }//end arrayTest

    @Test
    public void threatTest1(){
        ChessModel testModel = new ChessModel();
       // assertTrue(testModel.isThreatened(2, 1)==true);

    }//end threatTest1

    @Test
    public void threatTest2(){
        ChessModel testModel = new ChessModel();
        //assertTrue(testModel.isThreatened(3, 1)==true);

    }//end threatTest2

    @Test
    public void threatTest3(){
        ChessModel testModel = new ChessModel();
        testModel.setPiece(4, 3, new Knight(Player.BLACK,testModel));
        //assertTrue(testModel.isThreatened(6, 4)==true);

    }//end threatTest3






}//end Class Chess Test
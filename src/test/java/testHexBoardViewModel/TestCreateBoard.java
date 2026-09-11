package testHexBoardViewModel;

import edu.westga.dsdm2project1.ViewModel.HexBoardViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit Test for the Create Board method in the viewmodel.
 */
public class TestCreateBoard {
    /**
     * Tests creating a valid board of size 3.
     */
    @Test
    void testCreateValidBoardSizeThree() {
        HexBoardViewModel viewModel = new HexBoardViewModel();

        viewModel.boardSizeProperty().set("3");
        viewModel.cellSizeProperty().set("30");

        viewModel.createBoard();

        assertEquals(19, viewModel.getBoardCoordinates().size());

        assertEquals(0, viewModel.queenCountProperty().get());

        assertEquals("Created board with 19 cells.", viewModel.messageProperty().get());
    }

    /**
     * Tests creating a valid board of size 6.
     */
    @Test
    void testCreateValidBoardSizeSix() {
        HexBoardViewModel viewModel = new HexBoardViewModel();

        viewModel.boardSizeProperty().set("6");
        viewModel.cellSizeProperty().set("30");

        viewModel.createBoard();

        assertEquals(91, viewModel.getBoardCoordinates().size());

        assertEquals("Created board with 91 cells.", viewModel.messageProperty().get());
    }

    /**
     * Tests creating a board with an invalid board size.
     */
    @Test
    void testCreateBoardWithZeroBoardSize() {
        HexBoardViewModel viewModel = new HexBoardViewModel();

        viewModel.boardSizeProperty().set("0");
        viewModel.cellSizeProperty().set("30");

        viewModel.createBoard();

        assertEquals("Board size and cell size must be greater than 0.", viewModel.messageProperty().get());
    }

    /**
     * Tests creating a board with an invalid cell size.
     */
    @Test
    void testCreateBoardWithZeroCellSize() {
        HexBoardViewModel viewModel = new HexBoardViewModel();

        viewModel.boardSizeProperty().set("3");
        viewModel.cellSizeProperty().set("0");

        viewModel.createBoard();

        assertEquals("Board size and cell size must be greater than 0.", viewModel.messageProperty().get());
    }

    /**
     * Tests creating a board with non-numeric input.
     */
    @Test
    public void testCreateBoardWithNonNumericInput() {
        HexBoardViewModel viewModel = new HexBoardViewModel();

        viewModel.boardSizeProperty().set("hello");
        viewModel.cellSizeProperty().set("30");

        viewModel.createBoard();

        assertEquals("Board size and cell size must be numbers.", viewModel.messageProperty().get());
    }
}

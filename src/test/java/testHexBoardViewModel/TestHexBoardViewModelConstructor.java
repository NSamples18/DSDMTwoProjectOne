package testHexBoardViewModel;

import edu.westga.dsdm2project1.ViewModel.HexBoardViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests the constructor of the HexBoardViewModel class.
 */
public class TestHexBoardViewModelConstructor {
    /**
     * Tests the default board size property.
     */
    @Test
    public void testConstructorSetsDefaultBoardSize() {
        HexBoardViewModel viewModel = new HexBoardViewModel();

        assertEquals("3", viewModel.boardSizeProperty().get());
    }

    /**
     * Tests the default cell size property.
     */
    @Test
    public void testConstructorSetsDefaultCellSize() {
        HexBoardViewModel viewModel = new HexBoardViewModel();

        assertEquals("30", viewModel.cellSizeProperty().get());
    }

    /**
     * Tests the initial message.
     */
    @Test
    public void testConstructorSetsEmptyMessage() {
        HexBoardViewModel viewModel = new HexBoardViewModel();

        assertEquals("", viewModel.messageProperty().get());
    }

    /**
     * Tests the initial queen count.
     */
    @Test
    public void testConstructorSetsQueenCountToZero() {
        HexBoardViewModel viewModel = new HexBoardViewModel();

        assertEquals(0, viewModel.queenCountProperty().get());
    }

    /**
     * Tests that the default board contains
     * the correct number of coordinates.
     */
    @Test
    public void testConstructorCreatesSizeThreeBoard() {
        HexBoardViewModel viewModel = new HexBoardViewModel();

        assertEquals(19, viewModel.getBoardCoordinates().size());
    }

    /**
     * Tests that board coordinates are initialized.
     */
    @Test
    public void testConstructorInitializesBoardCoordinates() {
        HexBoardViewModel viewModel = new HexBoardViewModel();

        assertNotNull(viewModel.getBoardCoordinates());
    }

    /**
     * Tests the default current cell size.
     */
    @Test
    public void testConstructorSetsCurrentCellSize() {
        HexBoardViewModel viewModel = new HexBoardViewModel();

        assertEquals(30.0, viewModel.getCurrentCellSize());
    }
}

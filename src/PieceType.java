import java.util.ArrayList;
import java.util.List;

abstract class PieceType {
	static final PieceType PAWN = newPieceRelativeCapture(new int[][]{
			{-1, -1},
			{1, -1}
	}, "Pawn");

	static final PieceType KNIGHT = newPieceRelativeCapture(new int[][]{
			{-1, -2},
			{1, -2},
			{-1, 2},
			{1, 2},
			{-2, -1},
			{2, -1},
			{-2, 1},
			{2, 1}
	}, "Knight");

	static final PieceType KING = newPieceRelativeCapture(new int[][]{
			{-1, -1},
			{0, -1},
			{1, -1},
			{-1, 0},
			{1, 0},
			{-1, 1},
			{0, 1},
			{1, 1}
	}, "King");

	static final PieceType ROOK = newPieceLookDirections(new int[][]{
			{-1, 0},
			{1, 0},
			{0, -1},
			{0, 1}
	}, "Rook");

	static final PieceType BISHOP = newPieceLookDirections(new int[][]{
			{-1, -1},
			{1, -1},
			{-1, 1},
			{1, 1}
	}, "Bishop");

	static final PieceType QUEEN = newPieceLookDirections(new int[][]{
			{-1, -1},
			{0, -1},
			{1, -1},
			{-1, 0},
			{1, 0},
			{-1, 1},
			{0, 1},
			{1, 1}
	}, "Queen");

	private String name;

	private PieceType(String name) {
		this.name = name;
	}

	private static PieceType newPieceLookDirections(int[][] lookDirections, String name) {
		return new PieceType(name) {
			@Override
			List<int[]> canCapture(int[] location, Board board) {
				List<int[]> answer = new ArrayList<>();
				for (int[] lookDirection : lookDirections) {
					int[] testLocation = {location[0] + lookDirection[0], location[1] + lookDirection[1]};
					for (int i = 2; board.onBoard(testLocation); i++) {
						if (board.getPiece(testLocation) != null) {
							answer.add(testLocation);
							break;
						}
						testLocation = new int[]{location[0] + i * lookDirection[0], location[1] + i * lookDirection[1]};
					}
				}
				return answer;
			}
		};
	}

	private static PieceType newPieceRelativeCapture(int[][] relativeCaptures, String name) {
		return new PieceType(name) {
			@Override
			List<int[]> canCapture(int[] location, Board board) {
				List<int[]> answer = new ArrayList<>();
				for (int[] relativeCapture : relativeCaptures) {
					int[] captureLocation = {location[0] + relativeCapture[0], location[1] + relativeCapture[1]};
					if (board.onBoard(captureLocation) && board.getPiece(captureLocation) != null) {
						answer.add(captureLocation);
					}
				}
				return answer;
			}
		};
	}

	abstract List<int[]> canCapture(int[] location, Board board);
}

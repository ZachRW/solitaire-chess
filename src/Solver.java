import java.util.ArrayList;
import java.util.List;

class Solver {
	public static void main(String[] args) {
		Board board = new Board(new PieceType[][]{
				{PieceType.KNIGHT, PieceType.BISHOP, null, PieceType.ROOK},
				{null, PieceType.PAWN, null, PieceType.ROOK},
				{null, null, null, PieceType.PAWN},
				{null, PieceType.BISHOP, PieceType.KNIGHT, null}
		});
		List<Move> solution = solve(board);
		System.out.println("done");
	}

	private static List<Move> solve(Board board) {
		return solve(board, new ArrayList<>());
	}

	private static List<Move> solve(Board board, List<Move> prevMoves) {
		if (board.pieceCount() == 1) {
			return prevMoves;
		}
		for (int x = 0; x < board.getWidth(); x++) {
			for (int y = 0; y < board.getHeight(); y++) {
				int[] location = new int[]{x, y};
				PieceType piece = board.getPiece(location);
				if (piece != null) {
					for (int[] captureLocation : piece.canCapture(location, board)) {
						Board boardTest = new Board(board);
						List<Move> moveTest = new ArrayList<>(prevMoves);
						moveTest.add(new Move(location, captureLocation));
						boardTest.setPiece(location, null);
						boardTest.setPiece(captureLocation, piece);
						List<Move> boardTestSolve = solve(boardTest, moveTest);
						if (boardTestSolve != null) {
							return boardTestSolve;
						}
					}
				}
			}
		}
		return null;
	}
}

class Move {
	int[] startLocation;
	int[] endLocation;

	Move(int[] startLocation, int[] endLocation) {
		this.startLocation = startLocation;
		this.endLocation = endLocation;
	}
}

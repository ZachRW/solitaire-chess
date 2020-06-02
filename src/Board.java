class Board {
	private PieceType[][] pieces;

	Board(PieceType[][] pieces) {
		this.pieces = pieces;
	}

	Board(Board board) {
		pieces = new PieceType[board.getWidth()][board.getHeight()];
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				int[] location = new int[]{x, y};
				setPiece(location, board.getPiece(location));
			}
		}
	}

	PieceType getPiece(int[] location) {
		return pieces[location[0]][location[1]];
	}

	void setPiece(int[] location, PieceType piece) {
		pieces[location[0]][location[1]] = piece;
	}

	int getWidth() {
		return pieces.length;
	}

	int getHeight() {
		return pieces[0].length;
	}

	int pieceCount() {
		int pieceCount = 0;
		for (PieceType[] pieceY : pieces) {
			for (PieceType piece : pieceY) {
				if (piece != null) {
					pieceCount++;
				}
			}
		}
		return pieceCount;
	}

	boolean onBoard(int[] location) {
		return location[0] >= 0 && location[0] < getWidth() &&
				location[1] >= 0 && location[1] < getHeight();
	}
}

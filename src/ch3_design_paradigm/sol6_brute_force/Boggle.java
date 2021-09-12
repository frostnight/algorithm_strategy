package ch3_design_paradigm.sol6_brute_force;

public class Boggle {

	static final int[] dx = {-1, -1, -1, 1, 1, 1, 0, 0};
	static final int[] dy = {-1, 0, 1, -1, 0, 1, -1, 1};
	static final String[][] BOARD = {
		{"U", "R", "L", "P", "M"},
		{"X", "P", "R", "E", "T"},
		{"G", "I", "A", "E", "T"},
		{"X", "T", "N", "Z", "Y"},
		{"X", "O", "Q", "R", "S"}
	};

	public static void main(String[] args){
		boolean result = false;
		for(int i=0; i < BOARD.length; i++){
			for (int j=0; j < BOARD[i].length; j++){
				result = hasWord(i, j, "REPEAT");
				if(result) {
					break;
				}
			}
			if(result) {
				break;
			}
		}
		System.out.println("result = " + result);
	}

	public static boolean hasWord(int y, int x, String word){
		// 기저 사례 1: 시작 위치가 보드 밖
		if(!inRange(y, x)) {
			return false;
		}
		// 기저 사례 2: 첫 글자가 다름
		if(!BOARD[y][x].equals(word.substring(0, 1))) {
			return false;
		}
		// 기저 사례 3: 1글자
		if(word.length() == 1) {
			return true;
		}
		// 인접 여덟 칸 검사
		for(int direction = 0; direction < 8; ++direction){
			int nextY = y + dy[direction];
			int nextX = x + dx[direction];
			// 다음 칸 검사
			if(hasWord(nextY, nextX, word.substring(1))){
				return true;
			}
		}

		return false;
	}

	public static boolean inRange(int y, int x){
		try{
			String s = BOARD[y][x];
		} catch(IndexOutOfBoundsException e){
			return false;
		}
		return true;
	}
}

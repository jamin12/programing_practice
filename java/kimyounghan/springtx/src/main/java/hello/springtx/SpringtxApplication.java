package hello.springtx;

public class SpringtxApplication {

	public static void main(String[] args) {
		Solution solution = new Solution();

		String solution1 = solution.solution(new String[] {"영수", "광수", "영철", "상철"}, new int[] {0, 0, 0, 50}, 10,
			new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		System.out.println(solution1);
	}
}

class Solution {
	public Solution() {
	}

	public String solution(String[] playerNames, int[] errorRates, int maxGameCount, int[] randomValues) {
		Player[] players = new Player[playerNames.length];
		//playerNames, errorRates 를 사용하여 players 만드는 코드를 작성해주세요.
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player(playerNames[i], errorRates[i]);
		}

		return playGame(players, maxGameCount, new Random(maxGameCount, randomValues));
	}

	private String playGame(Player[] players, int maxGameCount, Random random) {
		StringBuilder answer = new StringBuilder();
		for (int i = 1; i < maxGameCount + 1; i++) {
			int playerIndex = (i - 1) % players.length;
			if (players[playerIndex].getErrorRate() == 0) {
				answer.append(players[playerIndex].getName()).append(": ").append(do369(i)).append("\n");
			} else if (players[playerIndex].getErrorRate() == 100) {
				answer.append(players[playerIndex].getName()).append(": ").append(do369(i)).append("\n");
				return answer.toString();
			} else {
				random.getNextInt();
			}
		}
		return answer.toString();
	}

	private String do369(int number) {

		String numberString = String.valueOf(number);
		if (numberString.contains("3") || numberString.contains("6") || numberString
			.contains("9")) {
			return "clap";
		}
		return numberString;
	}

	static class Player {

		private String name;

		private int errorRate;

		public Player(String name, int errorRate) {
			this.name = name;
			this.errorRate = errorRate;
		}

		public String getName() {
			return name;
		}

		public int getErrorRate() {
			return errorRate;
		}
	}

	/**
	 * 이 클래스는 수정하지 마세요.
	 */
	static private class Random {

		private int currentCount;

		private int maxCount;

		private int[] randomValues;

		public Random(int maxCount, int[] randomValues) {
			if (randomValues.length != maxCount) {
				throw new IllegalArgumentException("Random 클래스 초기화 실패");
			}
			this.maxCount = maxCount;
			this.randomValues = randomValues;
		}

		/**
		 * 0~99까지의 값을 리턴하는 함수 각 숫자는 모두 나올 확율이 같다고 가정하면된다.
		 */
		public int getNextInt() {
			return randomValues[currentCount++];
		}
	}
}
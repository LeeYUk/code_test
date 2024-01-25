class Solution {
	public int[] solution(String today, String[] terms, String[] privacies) {

		String[] TodaySplit = today.split("\\."); // 오늘 날짜를 .을 기준으로 문자열을 나누기
		int TodayYear = Integer.parseInt(TodaySplit[0]); // 년
		int TodayMon = Integer.parseInt(TodaySplit[1]); // 월
		int TodayDay = Integer.parseInt(TodaySplit[2]); // 일

		int count = 0; // 파기할 정보를 카운트하는 변수

		for (int i = 0; i < privacies.length; i++) {
			String[] privacy = privacies[i].split(" "); // 공백을 기준으로 날짜와 약관종류 분리 ,날짜와 약관종류가 들어있는 배열
			String[] DateSplit = privacy[0].split("\\."); // privacies의 날짜를 년,월,일로 분리
			int Year = Integer.parseInt(DateSplit[0]);
			int Mon = Integer.parseInt(DateSplit[1]);
			int Day = Integer.parseInt(DateSplit[2]);

			// 개인정보 수집 후 지난 개월 수를 계산
			int MonPass = (TodayYear - Year) * 12 + TodayMon - Mon; // 먼저 오늘년도와 수집년도를 빼서 몇년이 지났는지 계산후
																	// 개월 수를 확인하는것이기 떄문에 1년(12개월) 곱하기를 함
																	// 그다음 현재 월과 수집년도의 월을 빼고 앞서 계산한 값에 더 해주면 총 지난 개월 수를 구할수있음
																	
			if (Day > TodayDay) {
				MonPass--; // 수집년도의 날짜가 올해 날짜 보다 크면 1달 -1시킴
							// 이유는 유효기간이 월단위로 계산되기 때문, 예로들어 개인정보가 2020년 1월 15일에 수집, 오늘날짜가 2020년 6월 10일 이라고 한다면
							// 월 단위로 계산시 5개월이 지나간것이지만, 실제 정확하게 5개월이 지난것이 아니기 때문에 (4개월 25일이 지난것) -1개월 시킴
			}

			int termMons = FindTerm(terms, privacy[1].charAt(0)); //privacy 배열은 공백을 기준으로 첫번째 인덱스에 날짜, 두번째 인덱스에 약관의 종류(알파벳)가져옴
																
			if (MonPass >= termMons) {  //MonPass는 개인정보 수집된 후 지난시간 , termMons는 약관의 유효기간이다 둘이 비교시 정해진 유효기간이 지나면
				count++; //파기할 정보 개수를 카운트
			}

		}
        //이제부터 유효기간이 지난 인덱스를 구분하는 작업
		int[] answer = new int[count];// 파기해야할 개인정보의 인덱스를 저장할 배열 파기해야할 개수만큼 배열의 크기가 증가
		int index = 0; // 결과 배열의 인덱스
		

		for (int i = 0; i < privacies.length; i++) {  //여기는 위의 과정이랑 같음
			String[] privacy = privacies[i].split(" ");
			String[] dateSplit = privacy[0].split("\\.");
			int Year = Integer.parseInt(dateSplit[0]);
			int Mon = Integer.parseInt(dateSplit[1]);
			int Day = Integer.parseInt(dateSplit[2]);

			int MonPass = (TodayYear - Year) * 12 + TodayMon - Mon;
			if (Day > TodayDay) {
				MonPass--;
			}

			int termMons = FindTerm(terms, privacy[1].charAt(0));
			if (MonPass >= termMons) { //정해진 유효기간이 지나면
				answer[index++] = i + 1; //해당 인덱스를 저장, 실제 배열의 인덱스는 0부터 카운트 함으로 1을 더해 인덱스번호를 맞춰줌
			}
		}
		return answer;// 계산된 결과 리턴
	}

	public int FindTerm(String[] terms, char termsType) { //약관의 종류와 를 비교하는 메소드
		for (int i = 0; i < terms.length; i++) {
			// terms은 "약관 종류 유효기간" 알파벳문자열
			if (terms[i].charAt(0) == termsType) {// 배열의 인덱스의 값이 일치한다면(약관종류 일치)
				return Integer.parseInt(terms[i].split(" ")[1]);// 배열을 공백을 기준으로 나눈 후 두번째 인덱스(유효기간)를 정수로 변환
			}
		}
		return 0; // 일치하는 약관 종류가 없으면 리턴
	}
}
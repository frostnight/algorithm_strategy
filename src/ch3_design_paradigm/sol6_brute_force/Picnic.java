package ch3_design_paradigm.sol6_brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
3
2 1
0 1
4 6
0 1 1 2 2 3 3 0 0 2 1 3
6 10
0 1 0 2 1 2 1 3 1 4 2 3 2 4 3 4 3 5 4 5
 */
public class Picnic {

	public static void main(String[] args){

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			int testCase = Integer.parseInt(br.readLine());
			List<List<Boolean>> areFriends = new ArrayList<>();
			List<Boolean> taken = null;
			List<Boolean> tempList = null;
			List<Integer> results = new ArrayList<>();
			int loop = 0;

			while(loop < testCase){
				loop++;
				String[] studentInfo = br.readLine().split(" ");
				int studentNumbers = Integer.parseInt(studentInfo[0]);
				int totalPairCount = Integer.parseInt(studentInfo[1]);
				String[] StudentPairs = br.readLine().split(" ");
				taken = new ArrayList<>(studentNumbers);

				for(int i=0; i < studentNumbers; i++){
					tempList = new ArrayList<>();
					for(int j=0; j < studentNumbers; j++){
						tempList.add(false);
					}
					areFriends.add(tempList);
					tempList = null;
				}

				for(int index=0; index < StudentPairs.length - 1;){
					int studentNumber = Integer.parseInt(StudentPairs[index]);
					int pairNumber = Integer.parseInt(StudentPairs[index + 1]);

					tempList = areFriends.get(studentNumber);
					tempList.set(pairNumber, true);
					areFriends.set(studentNumber, tempList);
					tempList = null;

					tempList = areFriends.get(pairNumber);
					tempList.set(studentNumber, true);
					areFriends.set(pairNumber, tempList);
					tempList = null;

					index += 2;
				}

				for(int i=0; i < studentNumbers; i++){
					taken.add(false);
				}

				results.add(countPairings(studentNumbers, areFriends, taken));
				areFriends.clear();
			}

			results.forEach(ret -> System.out.println(ret));

		} catch (IOException ioException){
			ioException.printStackTrace();
		} finally {
			try{ if(br != null) br.close(); } catch (Exception e) {e.printStackTrace();}
		}
	}

	static int countPairings(final int studentNumbers, final List<List<Boolean>> areFriends
				, List<Boolean> taken){
		// 남은 학생들 중 가장 빠른 번호를 찾는다.
		int firstFree = -1;
		for(int i=0; i < studentNumbers; i++){
			if(!taken.get(i)){
				firstFree = i;
				break;
			}
		}
		// 기저 사례 : 모든 학생이 짝을 찾았으면 한 가지 방법을 찾았으니 종료
		if(firstFree == -1) return 1;
		int ret = 0;

		// 이 학생과 짝 지을 학생을 결정
		for(int pairWith = firstFree + 1; pairWith < studentNumbers; pairWith++){
			if(!taken.get(pairWith) && areFriends.get(firstFree).get(pairWith)){
				taken.set(firstFree, true);
				taken.set(pairWith, true);
				ret += countPairings(studentNumbers, areFriends, taken);
				taken.set(firstFree, false);
				taken.set(pairWith, false);
			}
		}
		return ret;
	}
}

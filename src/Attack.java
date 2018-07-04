

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Attack {
	// 攻撃
		public void setAttack(Network net) {
			ArrayList<Integer> queue = new ArrayList<Integer>();// 隣接点の探索が終わってない点
			ArrayList<Integer> memberList = new ArrayList<Integer>();// 連結成分の頂点番号
			ArrayList<Integer> visitN = new ArrayList<Integer>();// 未訪問の点
			double[][] sort = new double[net.n][2];// 次数の高い順に並べる
			boolean[] visitQ = new boolean[net.n];
			net.ave = new double[100];
			double max = -1;// 暫定の最大連結成分
			int v0;
			double f = 0;
			// 頂点の番号と次数をセットにする
			for (int i = 0; i <net. n; i++) {
				sort[i][0] = net.degreeList[i];
//				sort[i][0] = net.scoreList[i];
				sort[i][1] = i;
			}
			// for(int i=0;i<n;i++)System.out.println(sort[i][0]+"\t"+sort[i][1]);

				Arrays.sort(sort, new Comparator<double[]>() {
				@Override
				public int compare(double[] a, double[] b) {
//					int temp = (int)(b[0] - a[0]);
	//
//					return temp;
					int temp;
					if(b[0]>a[0]) temp=1;
					else if(b[0]==a[0]) temp=0;
					else temp=-1;
					return temp;
				}
				});

				boolean combo =false;
				ArrayList<Integer> startList = new ArrayList<Integer>();//同じ区間の始まりの頂点
				ArrayList<Integer> endList = new ArrayList<Integer>();//同じ区間の終わりの頂点
				for(int i=1;i<sort.length;i++){
					if(combo==true){
						if(sort[i][0]!=sort[i][1]){
							combo=false;
							endList.add(i-1);
						}else{
							if(sort[i][0]==sort[i-1][0]){
								combo=true;
								startList.add(i-1);
							}
						}
					}
				}if(startList.size()!=endList.size())endList.add(sort.length-1);
				ArrayList<Integer> temp_array = new ArrayList<Integer>();//シャッフルするため
				for(int i=0;i<startList.size();i++){
					for(int j=startList.get(i);j<=endList.get(i);j++){
						temp_array.add((int)(sort[j][1]));
					}
					for(int j=startList.get(i);j<=endList.get(i);j++){
						int random_index=(int)(temp_array.size()*Math.random());
						int temp_element=temp_array.get(random_index);
						sort[j][1]=temp_element;
						temp_array.remove(random_index);
					}
				}

			for (int t = 0; t <100; t++) {
				f += 0.01;
				max = 0;
				for (int i = 0; i < net.n; i++) {
					if (i < (int) (net.n * f)) {
						visitQ[(int)(sort[i][1])] = true;
					} else {
						visitQ[(int)(sort[i][1])] = false;
						visitN.add((int)(sort[i][1]));
					}
				}

				/*
				 * for (int i = 0; i < (int)(n*f); i++)
				 * System.out.println(sort[i][1]+"\t"+visitQ[sort[i][1]]);
				 */

				while (visitN.size() != 0) {
					memberList.clear();
					queue.add(visitN.get(0));
					while (queue.size() != 0) {
						v0 = queue.get(0);
						if (visitQ[v0] == false) {
							visitQ[v0] = true;
							visitN.remove(visitN.indexOf(v0));
						}
						for (int j = 0; j < net.degreeList[v0]; j++) {
							if (visitQ[net.neighborList[net.addressList[v0] + j]] == false) {
								memberList.add(net.neighborList[net.addressList[v0] + j]);
								queue.add(net.neighborList[net.addressList[v0] + j]);
								visitQ[net.neighborList[net.addressList[v0] + j]] = true;
								visitN.remove(visitN.indexOf(net.neighborList[net.addressList[v0] + j]));
							}
						}
						queue.remove(0);

					}
					if (memberList.size() >max) {
						max = memberList.size();
						 System.out.println(max);
					}

				}
				net.ave[t]=0+max;
			}
		}
}

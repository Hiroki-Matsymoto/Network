import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class random {
	public static int[] adddressList;
	public static int[][] pairList;
	public static int[] neighborList;
	public static int[] degreeList;
	public static int[] addressList;
	public static double[] scorList;
	public static int[] cursor;
	public static double[] ave;

	public int m;
	public int n;

	public random(int n) {
		this.n = n;
		this.degreeList = new int[n];
		this.cursor = new int[n];
		this.addressList = new int[n];
		this.ave = new double[100];

	}
	public void makeNeighborLIst () throws FileNotFoundException {
		int pairList[][]=new int[m][2];
		Scanner file=new Scanner(new File("C:\\Users\\Hiroki Matsumoto\\Documents\\eclipse\\pleiades\\eclipse\\out.txt "));
		while(file.hasNextInt()){
			for(int i=0;i<m;i++){
				for(int t=0;t<2;t++){
					pairList[i][t]=file.nextInt();
					//System.out.println(pairList[i][t]);
					for(int s=0;s<n;s++){
						if(pairList[i][t]==s)degreeList[s]++;
					}
				}
				//System.out.println();
			}
		}
		file.close();
		 addressList=new int[n];//neighborListの何番目に入ってるか
		 neighborList=new int[m*2];//隣接する頂点を把握
		int[] cursor=new int[n];//作業用
		double[]cluster=new double[n];
		addressList[0]=0;cursor[0]=0;
		for(int i=1;i<n;i++){
			addressList[i]=degreeList[i-1]+addressList[i-1];
			cursor[i]=addressList[i];
		}
		for(int i=0;i<m;i++){
				neighborList[cursor[pairList[i][0]]]=pairList[i][1];
				cursor[pairList[i][0]]++;
				neighborList[cursor[pairList[i][1]]]=pairList[i][0];
				cursor[pairList[i][1]]++;
	}
	}

//	// ランダムプログラム
//	public void random1() {
//		double[] degreeDistribution = new double[n];
//		double[] P = new double[n];
//		double[] Kcum = new double[n];
//		double N = 0, p;
//		int Kmax = (int) (n * 0.1);
//		m = 0;
//		// 一段階：P[k]の頂点決め
//		for (int k = 2; k <= Kmax; k++) {
//			N += Math.pow(k, -3.5);
//			P[k] = Math.pow(k, -3.5);
//			Kcum[k] = 0;
//			// System.out.println(i+"\t"+P[i]);
//		}
//		for (int k = 2; k <= Kmax; k++) {
//			P[k] /= N;
//			// System.out.println(k+"\t"+P[k]);
//		}
//		for (int k = 2; k <= Kmax; k++) {
//			for (int i = k; i <= Kmax; i++) {
//				Kcum[k] += P[i];
//			}
//			// System.out.println(k+"\t"+Kcum[k]);
//		}
//
//		// degreeList作成
//		for (int i = 0; i < n; i++) {
//			degreeDistribution[i] = 0;
//		}
//		for (int i = 0; i < n; i++) {
//			p = Math.random();
//			for (int k = 2; k <= Kmax; k++) {
//				if (Kcum[Kmax] >= p) {
//					degreeList[i] = k;
//				} else if (Kcum[k] >= p && p > Kcum[k + 1]) {
//					degreeList[i] = k;
//					break;
//				}
//			}
//			degreeDistribution[degreeList[i]]++;
//			addressList[i] = m;
//			m += degreeList[i];
//			// System.out.println(degreeDistribution[degreeList[i]]);
//		}
//		if (m % 2 == 1) {
//			degreeList[0]++;
//			m++;
//			for (int j = 1; j < n; j++)
//				addressList[j]++;
//		}
//		/*
//		 * for(int i=2;i<=n*0.1;i++) { if(degreeDistribution[i]>0)
//		 * System.out.println(i+"\t"+degreeDistribution[i]/n);
//		 *
//		 * }
//		 */
//		// Random2();
//
//	}
//
//	// ランダムに辺をつなぐ
//	public void Random2() {
//		neighborList = new int[m];
//		ArrayList<Integer> stubList = new ArrayList<Integer>();
//		int pos1 = 0, pos2 = 0, node1 = 0, node2 = 0, judg = 0;
//		for (int i = 0; i < m; i++)
//			neighborList[i] = 0;
//		for (int i = 0; i < n; i++)
//			cursor[i] = addressList[i];
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < degreeList[i]; j++)
//				stubList.add(i);
//		}
//		do {
//			do {
//				pos1 = (int) (Math.random() * stubList.size());
//				pos2 = (int) (Math.random() * stubList.size());
//				node1 = stubList.get(pos1);
//				node2 = stubList.get(pos2);
//				for (int i = addressList[node1]; i < cursor[node1]; i++) {
//					if (neighborList[i] == node2) {
//						judg = 1;
//						break;
//					} else
//						judg = 0;
//				} // System.out.println(Node.size()+"\t"+node1+"\t"+node2+"\t"+judg);
//			} while (pos1 == pos2 || node1 == node2 || judg == 1);
//			neighborList[cursor[node1]] = node2;
//			neighborList[cursor[node2]] = node1;
//			cursor[node1]++;
//			cursor[node2]++;
//			if (pos1 < pos2) {
//				stubList.remove(pos2);
//				stubList.remove(pos1);
//			} else {
//				stubList.remove(pos1);
//				stubList.remove(pos2);
//			}
//
//		} while (stubList.size() != 0);
//
//	}
//
//	// 連結成分を求める(最大連結成分)
//	public void connect1() {
//		ArrayList<Integer> queue = new ArrayList<Integer>();// 隣接点の探索が終わってない点
//		ArrayList<Integer> memberList = new ArrayList<Integer>();// 連結成分の頂点番号
//		boolean[] visitQ = new boolean[n];
//		ArrayList<Integer> visitN = new ArrayList<Integer>();// 未訪問の点
//		int max = -1;// 暫定の最大連結成分
//		int v0;
//		for (int i = 0; i < n; i++) {
//			visitQ[i] = false;
//			visitN.add(i);
//		} // System.out.println(visitN.size());
//		while (visitN.size() != 0) {
//			memberList.clear();
//			queue.add(visitN.get(0));
//			while (queue.size() != 0) {
//				v0 = queue.get(0);
//				if (visitQ[v0] == false) {
//					visitQ[v0] = true;
//					visitN.remove(visitN.indexOf(v0));
//				}
//				for (int j = 0; j < degreeList[v0]; j++) {
//					if (visitQ[neighborList[addressList[v0] + j]] == false) {
//						memberList.add(neighborList[addressList[v0] + j]);
//						queue.add(neighborList[addressList[v0] + j]);
//						visitQ[neighborList[addressList[v0] + j]] = true;
//						visitN.remove(visitN.indexOf(neighborList[addressList[v0] + j]));
//					}
//				}
//				queue.remove(0);
//
//			}
//			if (memberList.size() > max) {
//				max = memberList.size();
//
//			}
//			System.out.println(memberList.size());
//		}
//	}
//
//	// 連結成分:課題(3)
//	public void connect2() {
//
//		ArrayList<Integer> queue = new ArrayList<Integer>();// 隣接点の探索が終わってない点
//		ArrayList<Integer> memberList = new ArrayList<Integer>();// 連結成分の頂点番号
//		boolean[] visitQ = new boolean[n];
//		ArrayList<Integer> visitN = new ArrayList<Integer>();// 未訪問の点
//		int max = -1;// 暫定の最大連結成分
//		int v0;
//		double f = 0;
//		for (int s = 1; s <= 100; s++) {
//			f += 0.01;
//			max = -1;
//			for (int i = 0; i < n; i++) {
//				double F = Math.random();
//				if (f <= F) {// 正常
//					visitQ[i] = false;
//					visitN.add(i);
//				} else {// 故障
//					visitQ[i] = true;
//				}
//
//			}
//			// System.out.println(visitN.size());
//
//			while (visitN.size() != 0) {
//				memberList.clear();
//				queue.add(visitN.get(0));
//				while (queue.size() != 0) {
//					v0 = queue.get(0);
//					if (visitQ[v0] == false) {
//						visitQ[v0] = true;
//						visitN.remove(visitN.indexOf(v0));
//					}
//					for (int j = 0; j < degreeList[v0]; j++) {
//						if (visitQ[neighborList[addressList[v0] + j]] == false) {
//							memberList.add(neighborList[addressList[v0] + j]);
//							queue.add(neighborList[addressList[v0] + j]);
//							visitQ[neighborList[addressList[v0] + j]] = true;
//							visitN.remove(visitN.indexOf(neighborList[addressList[v0] + j]));
//						}
//					}
//					queue.remove(0);
//
//				}
//				// System.out.println(memberList.size());
//				if (memberList.size() > max) {
//					max = memberList.size();
//					System.out.println(max);
//				}
//
//			}
//			System.out.println(f * 100 + "%" + "\t" + max);
//
//		}
//	}
//	public void scor(){
//		ArrayList<Integer> queue = new ArrayList<Integer>();// 隣接点の探索が終わってない点
//		boolean[] visitQ = new boolean[n];
//		ArrayList<Integer> visitN = new ArrayList<Integer>();// 未訪問の点
//		double sum1,sum2,x=0;
//		scorList = new double[n];
//		for(int v=0;v<n;v++){sum1=1;
//			for(int i=0;i<n;i++)visitQ[i] = false;
//			visitQ[v]=true;
//			for(int j=0;j<degreeList[v];j++){
//				queue.add(neighborList[addressList[v] + j]);
//				visitQ[neighborList[addressList[v] + j]] = true;
//				scorList[v]++;
//			}while(queue.size()!=0){
//				sum1++;sum2=0;
//				while(queue.size()!=0){
//					visitN.add(queue.get(0));
//					queue.remove(0);
//				}
//				while(visitN.size()!=0){
//					for(int j=0;j<degreeList[visitN.get(0)];j++){
//						if(visitQ[neighborList[addressList[visitN.get(0)]+j]]==false){
//						queue.add(neighborList[addressList[visitN.get(0)]+j]);
//						visitQ[neighborList[addressList[visitN.get(0)]+j]]=true;
//						sum2++;
//						}
//					}visitN.remove(0);
//				}
//			scorList[v]+=sum2*Math.pow(x, sum1);
//			//System.out.println(scorList[v]);
//
//
//				}
//			//System.out.println(v+"\t"+degreeList[v]+"\t"+scorList[v]);
//		}
//
//	}
//
//	// 攻撃
//	public void attack() {
//		ArrayList<Integer> queue = new ArrayList<Integer>();// 隣接点の探索が終わってない点
//		ArrayList<Integer> memberList = new ArrayList<Integer>();// 連結成分の頂点番号
//		ArrayList<Integer> visitN = new ArrayList<Integer>();// 未訪問の点
//		double[][] sort = new double[n][2];// 次数の高い順に並べる
//		boolean[] visitQ = new boolean[n];
//		double max = -1;// 暫定の最大連結成分
//		int v0;
//		double f = 0;
//		// 頂点の番号と次数をセットにする
//		for (int i = 0; i < n; i++) {
////			sort[i][0] = degreeList[i];
//			sort[i][0] = scorList[i];
////			sort[i][0] = 0.123;
//			sort[i][1] = i;
//		}
//		// for(int i=0;i<n;i++)System.out.println(sort[i][0]+"\t"+sort[i][1]);
//
//			Arrays.sort(sort, new Comparator<double[]>() {
//			@Override
//			public int compare(double[] a, double[] b) {
////				int temp = (int)(b[0] - a[0]);
////
////				return temp;
//				int temp;
//				if(b[0]>a[0]) temp=1;
//				else if(b[0]==a[0]) temp=0;
//				else temp=-1;
//				return temp;
//			}
//			});
//
//			boolean combo =false;
//			ArrayList<Integer> startList = new ArrayList<Integer>();//同じ区間の始まりの頂点
//			ArrayList<Integer> endList = new ArrayList<Integer>();//同じ区間の終わりの頂点
//			for(int i=1;i<sort.length;i++){
//				if(combo==true){
//					if(sort[i][0]!=sort[i][1]){
//						combo=false;
//						endList.add(i-1);
//					}else{
//						if(sort[i][0]==sort[i-1][0]){
//							combo=true;
//							startList.add(i-1);
//						}
//					}
//				}
//			}if(startList.size()!=endList.size())endList.add(sort.length-1);
//			ArrayList<Integer> temp_array = new ArrayList<Integer>();//シャッフルするため
//			for(int i=0;i<startList.size();i++){
//				for(int j=startList.get(i);j<=endList.get(i);j++){
//					temp_array.add((int)(sort[j][1]));
//				}
//				for(int j=startList.get(i);j<=endList.get(i);j++){
//					int random_index=(int)(temp_array.size()*Math.random());
//					int temp_element=temp_array.get(random_index);
//					sort[j][1]=temp_element;
//					temp_array.remove(random_index);
//				}
//			}
//
//		for (int t = 0; t <100; t++) {
//			f += 0.01;
//			max = 0;
//			for (int i = 0; i < n; i++) {
//				if (i < (int) (n * f)) {
//					visitQ[(int)(sort[i][1])] = true;
//				} else {
//					visitQ[(int)(sort[i][1])] = false;
//					visitN.add((int)(sort[i][1]));
//				}
//			}
//
//			/*
//			 * for (int i = 0; i < (int)(n*f); i++)
//			 * System.out.println(sort[i][1]+"\t"+visitQ[sort[i][1]]);
//			 */
//
//			while (visitN.size() != 0) {
//				memberList.clear();
//				queue.add(visitN.get(0));
//				while (queue.size() != 0) {
//					v0 = queue.get(0);
//					if (visitQ[v0] == false) {
//						visitQ[v0] = true;
//						visitN.remove(visitN.indexOf(v0));
//					}
//					for (int j = 0; j < degreeList[v0]; j++) {
//						if (visitQ[neighborList[addressList[v0] + j]] == false) {
//							memberList.add(neighborList[addressList[v0] + j]);
//							queue.add(neighborList[addressList[v0] + j]);
//							visitQ[neighborList[addressList[v0] + j]] = true;
//							visitN.remove(visitN.indexOf(neighborList[addressList[v0] + j]));
//						}
//					}
//					queue.remove(0);
//
//				}
//				if (memberList.size() >max) {
//					max = memberList.size();
//					// System.out.println(max);
//				}
//
//			}
//			ave[t]+=max;
//			//System.out.println(f  + "\t" + ave[t]);
//		}
//	}
//
//	public static void main(String[] args) throws FileNotFoundException  {
//
//		double f=0;
//		random random1 = new random(18871);
//		random1.m=198050;
//		random1.makeNeighborLIst();
//		int sum=100;
//		//for(int i=0;i<sum;i++){
////		random1.random1();
////		random1.Random2();
//		random1.scor();
//		random1.attack();
//		//}
//
//		for(int i=0;i<sum;i++){
//			f+=0.01;
//
//			System.out.println(f+"\t"+ave[i]/sum);
//		}

}

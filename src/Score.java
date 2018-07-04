import java.util.ArrayList;

public class Score {
//	public static int[] adddressList;
//	public static int[] neighborList;
//	public static int[] degreeList;
//	public static int[] addressList;
//  public static double[] net.scoreList;
//	public static int[] cursor;
//	public int m;
//	public int n;

//	public score(Config config1) {
//		this.n = config1.n;
//		this.degreeList =config1.degreeList;
//		this.cursor = config1.cursor;
//		this.addressList = config1.addressList;
//	}

	public void setScore(Network net){
		ArrayList<Integer> queue = new ArrayList<Integer>();// 隣接点の探索が終わってない点
		boolean[] visitQ = new boolean[net.n];
		ArrayList<Integer> visitN = new ArrayList<Integer>();// 未訪問の点
		double sum1,sum2,x=1;
		net.scoreList = new double[net.n];
		for(int v=0;v<net.n;v++){sum1=0;
			for(int i=0;i<net.n;i++)visitQ[i] = false;
			visitQ[v]=true;
			for(int j=0;j<net.degreeList[v];j++){
				queue.add(net.neighborList[net.addressList[v] + j]);
				visitQ[net.neighborList[net.addressList[v] + j]] = true;
				net.scoreList[v]++;
			}while(queue.size()!=0){
				sum1++;sum2=0;
				while(queue.size()!=0){
					visitN.add(queue.get(0));
					queue.remove(0);
				}
				while(visitN.size()!=0){
					for(int j=0;j<net.degreeList[visitN.get(0)];j++){
						if(visitQ[net.neighborList[net.addressList[visitN.get(0)]+j]]==false){
						queue.add(net.neighborList[net.addressList[visitN.get(0)]+j]);
						visitQ[net.neighborList[net.addressList[visitN.get(0)]+j]]=true;
						sum2++;
						}
					}visitN.remove(0);
				}
			net.scoreList[v]+=sum2*Math.pow(x, sum1);


				}
			System.out.println(v+"\t"+net.scoreList[v]);
		}

	}
}

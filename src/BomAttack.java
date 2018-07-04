import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BomAttack {
	public void attack(Network net){
		ArrayList<Integer> target = new ArrayList<Integer>();// 隣接点の探索が終わってない点
		net.visitQ = new boolean[net.n];
		ArrayList<Integer> nextTarget = new ArrayList<Integer>();// 未訪問の点
		double[][] sort = new double[net.n][2];// 次数の高い順に並べる
		net.scoreList = new double[net.n];
		int v0;int sum=0;
		for(int i=0; i<net.n;i++)net.scoreList[i]=100;

		// 次数の高い順に並べなおす
			for (int i = 0; i <net. n; i++) {
				sort[i][0] = net.degreeList[i];
//				sort[i][0] = net.scoreList[i];
				sort[i][1] = i;
			}
		// for(int i=0;i<n;i++)System.out.println(sort[i][0]+"\t"+sort[i][1]);

			Arrays.sort(sort, new Comparator<double[]>() {
			@Override
			public int compare(double[] a, double[] b) {
//				int temp = (int)(b[0] - a[0]);
//
//				return temp;
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

			for(int t=0;t<1000;t++){
				for(int i=0;i<net.n;i++){
					if(net.scoreList[i]<=0){
						net.visitQ[i]=true;
						}else{
							net.visitQ[i]=false;
						}
					}
				int s=0;
				do{
					if(net.visitQ[(int)(sort[t+s][1])]==false){
						target.add((int)(sort[t+s][1]));
					}else s++;
					}while(target.size()==0);
			for(int wave=0;wave<3;wave++){
			while (target.size() != 0){
					v0 = target.get(0);
					if(net.visitQ[v0]==false){
						net.visitQ[v0] = true;
					net.scoreList[v0]=net.scoreList[v0]-(100-10*wave);
//					System.out.println(v0+"\t"+net.scoreList[v0]);
					}
				for (int j = 0; j < net.degreeList[v0]; j++) {
					if (net.visitQ[net.neighborList[net.addressList[v0] + j]] == false) {
						net.visitQ[net.neighborList[net.addressList[v0] + j]] = true;
							nextTarget.add(net.neighborList[net.addressList[v0] + j]);
						}
					}
					target.remove(0);

				}
				while(nextTarget.size()!=0){
					if(wave<2){
						target.add(nextTarget.get(0));
					}
						nextTarget.remove(0);
					}
				for(int i=0;i<net.n;i++){
					if(net.scoreList[i]<=0){
						net.visitQ[i]=true;
						sum++;
						}else{
							net.visitQ[i]=false;
						}
					}
//				System.out.println(sum);

			}
//			Connect con = new Connect();
//			con.connect(net);
//			for(int i =0;i<net.n;i++)System.out.println(net.visitQ[i]);

			new Connect().connect(net);
		  }




		 }

	}

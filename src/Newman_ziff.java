import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Newman_ziff {
	private int[] ptr;
	private int[] ordered_List;
	public void set_nodes(Network net){
			double[][] sort = new double[net.n][2];// 次数の高い順に並べる
			 ptr= new int[net.n];
			 ordered_List = new int[net.n];
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
				else if(b[0]==a[0])temp=0;
				else temp=-1;
				return temp;
			}
			});
			boolean combo =false;
			ArrayList<Integer> startList = new ArrayList<Integer>();//同じ区間の始まりの頂点
			ArrayList<Integer> endList = new ArrayList<Integer>();//同じ区間の終わりの頂点
			for(int i=1;i<sort.length;i++){
				if(combo==true){
					if(sort[i][0]!=sort[i-1][0]){
						combo=false;
						endList.add(i-1);
					}
					}else{
						if(sort[i][0]==sort[i-1][0]){
							combo=true;
							startList.add(i-1);
						}
					}
				}
			if(startList.size()!=endList.size())endList.add(sort.length-1);
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
				for(int i=0;i<net.n;i++){
					ordered_List[i]=(int)(sort[i][1]);
				}
		}
	int find_root(int i){
		if(ptr[i]<0)return i;
		else return ptr[i]= find_root(ptr[i]);
	}
	public void Verification(Network net){
		for(int i=0;i<net.n;i++)ptr[i]=0;
		double big = 1.0;//最大クラスターサイズ
		int r1,r2,s1,s2;

		for(int i=0;i<net.n;i++){
			r1=s1=ordered_List[i];
			ptr[s1]=-1;
			for(int j=net.addressList[s1];j<net.addressList[s1]+net.degreeList[s1];j++){
				s2=net.neighborList[j];//edgListはネイバーのこと？
				if(ptr[s2]!=0){
					r2=find_root(s2);
					if(r2!=r1){
						if(ptr[r1]>ptr[r2]){
							ptr[r2]+=ptr[r1];ptr[r1]=r2;r1=r2;
						}else ptr[r1]+=ptr[r2];ptr[r2]=r1;

					}
					if(-ptr[r1]>big){big=-ptr[r1];}
				}
			}
		}
		}

}



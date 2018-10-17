import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class makeList extends Network{
	 public makeList(int n){
			this.n = n;
			this.degreeList = new int[n];
			this.cursor = new int[n];
			this.addressList = new int[n];
	 }
	 public void setList()throws FileNotFoundException{
		 m=2612;
			int pairList[][]=new int[m][2];
			Scanner file=new Scanner(new File("C:\\Users\\hiroki\\Dropbox\\研究"));
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
			 neighborList=new int[m];//隣接する頂点を把握
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

}

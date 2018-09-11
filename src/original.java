import java.util.ArrayList;

public class original {
	public void originalModel(Network net){
		ArrayList<Integer> true_List = new ArrayList<Integer>();// 破壊された頂点
		ArrayList<Integer> aliveList = new ArrayList<Integer>();// 破壊された頂点とつながっていた点

		for(int i=0; i<net.n;i++) {
			if(net.visitQ[i]==true) {
				true_List.add(i);
			}
		}
		do {
			for(int i=0;i<net.degreeList[true_List.get(0)];i++) {
				if(net.visitQ[net.neighborList[net.addressList[true_List.get(0)+i]]]==true) {
					aliveList.add(net.neighborList[net.addressList[true_List.get(0)+i]]);
				}
			}
			true_List.remove(0);
		}while(true_List.size()==0);//破壊された頂点とつながっていた点をpick up
		int aliveSize=aliveList.size();
		int pos1, pos2,node1,node2,judg = 0;
		for(int i=0;i<aliveSize;i++) {
			int count=0;
			do {
				pos1=(int)(Math.random()*aliveList.size());
				pos2=(int)(Math.random()*aliveList.size());
				node1=aliveList.get(pos1);
				node2=aliveList.get(pos2);
				for(int j=0;j<net.degreeList[node1];j++) {
					if(node2==net.neighborList[net.addressList[node1]+j]) {
						judg=1;
						break;
					}else judg=0;
				}count++;
			}while(pos1 == pos2 || node1 == node2 || judg ==0||count<100);
			if(count<100) {
				for(int j=0;j<net.degreeList[node1];j++) {
					if(net.visitQ[net.neighborList[net.addressList[node1]+j]]==true) {
						net.neighborList[net.addressList[node1]+j]=node2;
					}
				}
				for(int j=0;j<net.degreeList[node2];j++) {
					if(net.visitQ[net.neighborList[net.addressList[node2]+j]]==true) {
						net.neighborList[net.addressList[node2]+j]=node1;
					}
				}
				aliveList.remove(aliveList.indexOf(node1));
				aliveList.remove(aliveList.indexOf(node2));

			}
		}

	}

}

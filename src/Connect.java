import java.util.ArrayList;

public class Connect {

	public void connect(Network net) {

		ArrayList<Integer> queue = new ArrayList<Integer>();// 隣接点の探索が終わってない点
		ArrayList<Integer> memberList = new ArrayList<Integer>();// 連結成分の頂点番号
		ArrayList<Integer> visitN = new ArrayList<Integer>();// 未訪問の点
		int max = 0;// 暫定の最大連結成分
		int v;
		int f = 0;
//		for (int s = 1; s <= 100; s++) {
//			f += 0.01;
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
			// System.out.println(visitN.size());
		for(int i=0;i<net.n;i++){
			if(net.scoreList[i]<=0){
				net.visitQ[i]=true;
				}else{
					net.visitQ[i]=false;
					visitN.add(i);
				}
			}
			while (visitN.size() != 0) {
				memberList.clear();
				queue.add(visitN.get(0));
				while (queue.size() != 0) {
					v = queue.get(0);
					if (net.visitQ[v] == false) {
						net.visitQ[v] = true;
						visitN.remove(visitN.indexOf(v));
					}
					for (int j = 0; j < net.degreeList[v]; j++) {
						if (net.visitQ[ net.neighborList[ net.addressList[v] + j]] == false) {
							memberList.add( net.neighborList[ net.addressList[v] + j]);
							queue.add( net.neighborList[ net.addressList[v] + j]);
							net.visitQ[ net.neighborList[ net.addressList[v] + j]] = true;
							visitN.remove(visitN.indexOf( net.neighborList[ net.addressList[v] + j]));
						}
					}
					queue.remove(0);

				}
//				 System.out.println(memberList.size());
				if (memberList.size() > max) {
					max = memberList.size();
//					System.out.println(memberList.size()+"\t"+max);
				}

			}
//			for(int i=0;i<net.n;i++){
//				if(net.scoreList[i]<=0){
//					net.visitQ[i]=true;
//					visitN.add(i);
//					}else{
//						net.visitQ[i]=false;
//						}
//				}
//				while (visitN.size() != 0) {
//					memberList.clear();
//					queue.add(visitN.get(0));
//					while (queue.size() != 0) {
//						v0 = queue.get(0);
//						if (net.visitQ[v0] == true) {
//							net.visitQ[v0] = false;
//							visitN.remove(visitN.indexOf(v0));
//						}
//						for (int j = 0; j < net.degreeList[v0]; j++) {
//							if (net.visitQ[ net.neighborList[ net.addressList[v0] + j]] == true) {
//								memberList.add( net.neighborList[ net.addressList[v0] + j]);
//								queue.add( net.neighborList[ net.addressList[v0] + j]);
//								net.visitQ[ net.neighborList[ net.addressList[v0] + j]] = false;
//								visitN.remove(visitN.indexOf( net.neighborList[ net.addressList[v0] + j]));
//							}
//							}
//						queue.remove(0);
//
//					}
////					 System.out.println(memberList.size());
//					if (memberList.size() > f) {
//						f = memberList.size();
//					}
//
//				}
//				System.out.println(f+"\t"+max);

		}
	}
//}

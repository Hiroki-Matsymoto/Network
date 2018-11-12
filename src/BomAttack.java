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
        ArrayList<Integer> queue = new ArrayList<Integer>();// 隣接点の探索が終わってない点
        ArrayList<Integer> memberList = new ArrayList<Integer>();// 連結成分の頂点番号
        ArrayList<Integer> visitN = new ArrayList<Integer>();// 未訪問の点
        double f = 0;
        int v0;int v;int max;
        score_degree score=new score_degree();
        score.setScore(net);

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
            int t=0;
            while(true){
                f+=0.01;
                max = 0;// 暫定の最大連結成分
            for(int time=0;time<(int)(net.n*0.01);time++){
                for(int i=0;i<net.n;i++){
                    if(net.scoreList[i]<=0){
                        net.visitQ[i]=true;
                        }else{
                            net.visitQ[i]=false;
                        }
                    }
                int s=0;
                do{
                    if(net.visitQ[(int)(sort[time+s][1])]==false){
                        target.add((int)(sort[time+s][1]));
                    }else s++;
                    }while(target.size()==0&&(time+s)<net.n);//
            for(int wave=0;wave<10;wave++){
            	double damage =200/(Math.pow(2, wave));
//            	double damage =10-(1*wave);
            while (target.size() != 0){
                    v0 = target.get(0);
                    if(net.visitQ[v0]==false){
                        net.visitQ[v0] = true;
                    net.scoreList[v0]=net.scoreList[v0]-damage;
//					System.out.println(net.scoreList[v0]);
                    }
                for (int j = 0; j < net.degreeList[v0]; j++) {
                    if (net.visitQ[net.neighborList[net.addressList[v0] + j]] == false) {
                        net.visitQ[net.neighborList[net.addressList[v0] + j]] = true;
                            nextTarget.add(net.neighborList[net.addressList[v0] + j]);
                        }
                    }
                    target.remove(0);
//					System.out.println(nextTarget.size());
                }
                while(nextTarget.size()!=0){
                        target.add(nextTarget.get(0));
//						System.out.println(target.size());
                        nextTarget.remove(0);
                    }
                if(damage<1){
                	target.clear();
                	break;
                }

            }
//			Connect con = new Connect();
//			con.connect(net);
//			for(int i =0;i<net.n;i++)System.out.println(net.visitQ[i]);

//オリジナル挿入
//            for(int i=0;i<net.n;i++){
//                if(net.scoreList[i]<=0){
//                    net.visitQ[i]=true;
//                    }else{
//                        net.visitQ[i]=false;
//                    }
//                }
//
//            original ori = new original();
//			ori.originalModel(net);
            }
            double averagedegree=0;
            int trueNode=0;
            for(int i=0;i<net.n;i++){
                if(net.scoreList[i]<=0){
                    net.visitQ[i]=true;
                    trueNode++;
//					System.out.println(i);
                    }else{
                        net.visitQ[i]=false;
                        visitN.add(i);
                    }
                }
            for(int i=0;i<net.n;i++){
                for(int j=0;j<net.degreeList[i];j++){
                	if(net.visitQ[net.neighborList[ net.addressList[i] + j]]==false){
                		averagedegree++;
                	}
                }
            }
                while (visitN.size() != 0) {
                    memberList.clear();
                    queue.add(visitN.get(0));
                    while (queue.size() != 0) {
                        v = queue.get(0);
                        if (net.visitQ[v] == false) {
                            net.visitQ[v] = true;
                            memberList.add(v);
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
//					 System.out.println(memberList.size());
                    if (memberList.size() > max) {
                        max = memberList.size();
//						System.out.println(memberList.size()+"\t"+max);
                    }
                }
//                net.ave[t]+=max;
                t++;
//            System.out.println(f+"\t"+max);
//            System.out.println(f+"\t"+averagedegree/net.m);
              System.out.println(f+"\t"+trueNode);
            if(f>1)break;
          }




         }

    }


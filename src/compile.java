

public class compile {

	public static void main(String[] args) {
		Config net =new Config(10000);
//		makeList net =new makeList(1000);
//		Score score = new Score();
//		Attack atk = new Attack();
		BomAttack ba= new BomAttack();
		BomAttack2 ba2=new BomAttack2();
		net.ave = new double[100];
	    net.max_sum=new double[1000000];
		int sum=1000;double f=0;
//		for(int i=0;i<sum;i++){
//		net.count=0;
		net.random1();
		net.random2();

//		score.setScore(net);
//		atk.setAttack(net);
//		atk.activeAttack(net);
//		net.count++;
//		System.out.println(net.max_sum[0]);
//		test tes=new test();
//		tes.onion(net);
//		atk.activeAttack(net);
		ba.attack(net);
//		ba2.setBomAttack(net);
//		System.out.println(i);
//			}
//		for(int i=0;i<100;i++){
//			 f+=0.01;
//			System.out.println(f+"\t"+net.ave[i]);
//		}
//		net.f=0;
//		for(int i=0;i<100;i++){
//			ba2.attack(net);
//			net.f+=0.01;
//	        score_degree score=new score_degree();
//	        score.setScore(net);
//		}

		int sum1=0;
//		int pairList[][]=new int [net.m][2];
//		for(int i=0;i<net.n;i++){
//			for(int j=0;j<net.degreeList[i];j++){
//				pairList[sum1][0]=i;
//				pairList[sum1][1]=net.neighborList[net.addressList[i]+j];
//				sum1++;
//			}
//		}
//		for(int i=0;i<net.m;i++)System.out.println(pairList[i][0]+"\t"+pairList[i][1]);
	}

}

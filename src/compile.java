

public class compile {

	public static void main(String[] args) {
		Config net =new Config(10000);
//		Score score = new Score();
//		Attack atk = new Attack();
		BomAttack ba= new BomAttack();
		int sum=0;
		net.random1();
		net.random2();
		ba.attack(net);
//		score.setScore(net);
//		atk.setAttack(net);

//		int sum1=0;
//		int pairList[][]=new int [net.m][2];
//		for(int i=0;i<net.n;i++){
//			for(int j=0;j<net.degreeList[i];j++){
//				pairList[sum][0]=i;
//				pairList[sum][1]=net.neighborList[net.addressList[i]+j];
//				sum++;
//			}
//		}
//		for(int i=0;i<net.m;i++)System.out.println(pairList[i][0]+"\t"+pairList[i][1]);
	}

}

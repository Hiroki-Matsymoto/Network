
public class score_degree {
	public void setScore(Network net){
		for(int i=0;i<net.n;i++){
			net.scoreList[i]=net.degreeList[i]*200;
		}
	}
}

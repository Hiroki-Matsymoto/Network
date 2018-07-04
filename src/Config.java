import java.util.ArrayList;

public class Config extends Network{

	public Config(int n) {
		this.n = n;
		this.degreeList = new int[n];
		this.cursor = new int[n];
		this.addressList = new int[n];

	}


	public void random1( ) {
		double[] degreeDistribution = new double[n];
		double[] P = new double[n];
		double[] Kcum = new double[n];
		double N = 0, p;
		int Kmax = (int) (n * 0.1);
		m = 0;
		// 一段階：P[k]の頂点決め
		for (int k = 2; k <= Kmax; k++) {
			N += Math.pow(k, -2.5);
			P[k] = Math.pow(k, -2.5);
			Kcum[k] = 0;
			// System.out.println(i+"\t"+P[i]);
		}
		for (int k = 2; k <= Kmax; k++) {
			P[k] /= N;
			// System.out.println(k+"\t"+P[k]);
		}
		for (int k = 2; k <= Kmax; k++) {
			for (int i = k; i <= Kmax; i++) {
				Kcum[k] += P[i];
			}
			// System.out.println(k+"\t"+Kcum[k]);
		}

		// degreeList作成
		for (int i = 0; i < n; i++) {
			degreeDistribution[i] = 0;
		}
		for (int i = 0; i < n; i++) {
			p = Math.random();
			for (int k = 2; k <= Kmax; k++) {
				if (Kcum[Kmax] >= p) {
					degreeList[i] = k;
				} else if (Kcum[k] >= p && p > Kcum[k + 1]) {
					degreeList[i] = k;
					break;
				}
			}
			degreeDistribution[degreeList[i]]++;
			addressList[i] = m;
			m += degreeList[i];
			// System.out.println(degreeDistribution[degreeList[i]]);
		}
		if (m % 2 == 1) {
			degreeList[0]++;
			m++;
			for (int j = 1; j < n; j++)
				addressList[j]++;
		}
		/*
		 * for(int i=2;i<=n*0.1;i++) { if(degreeDistribution[i]>0)
		 * System.out.println(i+"\t"+degreeDistribution[i]/n);
		 *
		 * }
		 */
		// Random2();

	}

	// ランダムに辺をつなぐ
	public void random2() {
//		pairList = new int[m][2];
		neighborList = new int[m];
		ArrayList<Integer> stubList = new ArrayList<Integer>();
		int pos1 = 0, pos2 = 0, node1 = 0, node2 = 0, judg = 0;
		for (int i = 0; i < m; i++)
			neighborList[i] = 0;
		for (int i = 0; i < n; i++)
			cursor[i] = addressList[i];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < degreeList[i]; j++)
				stubList.add(i);
		}
		do {
			do {
				pos1 = (int) (Math.random() * stubList.size());
				pos2 = (int) (Math.random() * stubList.size());
				node1 = stubList.get(pos1);
				node2 = stubList.get(pos2);
				for (int i = addressList[node1]; i < cursor[node1]; i++) {
					if (neighborList[i] == node2) {
						judg = 1;
						break;
					} else
						judg = 0;
				}  //System.out.println(node1+"\t"+node2+"\t"+judg);
			} while (pos1 == pos2 || node1 == node2 || judg == 1);
			neighborList[cursor[node1]] = node2;
			neighborList[cursor[node2]] = node1;
			cursor[node1]++;
			cursor[node2]++;
			if (pos1 < pos2) {
				stubList.remove(pos2);
				stubList.remove(pos1);
			} else {
				stubList.remove(pos1);
				stubList.remove(pos2);
			}

		} while (stubList.size() != 0);

	}

}

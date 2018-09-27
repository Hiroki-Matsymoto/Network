public class test {
	public void onion(Network net){
        int nod1,nod2,nod3,nod4,pos1,pos2,judg = 0;
        do{
            do{
                nod1=(int)(Math.random()*net.n);
                nod2=(int)(Math.random()*net.n);
                pos1=(int)(Math.random()*net.degreeList[nod1]);
                pos2=(int)(Math.random()*net.degreeList[nod2]);
                nod3=net.neighborList[net.addressList[nod1]+pos1];
                nod4=net.neighborList[net.addressList[nod2]+pos2];
                for(int i=0;i<net.degreeList[nod1];i++){
                	if(net.neighborList[net.addressList[nod1]+i]==nod4){
                		judg=1;
                		break;
                	}else judg=0;
                }
                if(judg==0){
                    for(int i=0;i<net.degreeList[nod2];i++){
                    	if(net.neighborList[net.addressList[nod2]+i]==nod3){
                    		judg=1;
                    		break;
                    	}else judg=0;
                    }
                }

            }while(nod1==nod2||judg==1);

            net.neighborList[net.addressList[nod1]+pos1]=nod4;
            net.neighborList[net.addressList[nod2]+pos2]=nod3;
            for(int i=0;i<net.degreeList[nod3];i++){
            	if(net.neighborList[net.addressList[nod3]+i]==nod1){
            		net.neighborList[net.addressList[nod3]+i]=nod2;
            		break;
            	}
            }
            for(int i=0;i<net.degreeList[nod4];i++){
            	if(net.neighborList[net.addressList[nod4]+i]==nod2){
            		net.neighborList[net.addressList[nod4]+i]=nod1;
            		break;
            	}
            }
    		Attack atk1 = new Attack();
    		atk1.setAttack(net);
    		if(net.max_sum[net.count-1]>net.max_sum[net.count]){
    	        net.neighborList[net.addressList[nod1]+pos1]=nod3;
    	        net.neighborList[net.addressList[nod2]+pos2]=nod4;
    	        for(int i=0;i<net.degreeList[nod3];i++){
    	        	if(net.neighborList[net.addressList[nod3]+i]==nod2){
    	        		net.neighborList[net.addressList[nod3]+i]=nod1;
    	        		break;
    	        	}
    	        }
    	        for(int i=0;i<net.degreeList[nod4];i++){
    	        	if(net.neighborList[net.addressList[nod4]+i]==nod1){
    	        		net.neighborList[net.addressList[nod4]+i]=nod2;
    	        		break;
    	        	}
    	        }
        		Attack atk2 = new Attack();
        		atk2.setAttack(net);
//        		System.out.println(net.max_sum[net.count]+"\t"+net.max_sum[net.count-1]+"\t"+"false");
    		}else{
    			System.out.println(net.count);
    			net.count++;
    		}
    		if(net.count-1>1001&&(net.max_sum[net.count-1]-net.max_sum[net.count-1000])<(net.max_sum[net.count-2]-net.max_sum[net.count-1002])*0.01)break;
        }while(true);
//compileに0回目の試行時net.max_sum[0]=net.max_sum[1]と入れる
	}
}

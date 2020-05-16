
public class PageRank {
 
 
 static void fillGraph(int size, int[][] graph) {           // modify this to the real matrix
	 for(int i = 0; i < size; i++)
	 {
		 for(int j = 0; j < size; j++)
		 {
			 if(i == j)
				 graph[i][j] = 0; //to solve the problem of link nt pointing to anything for now
			 else if(j*i/3*i % 2 == 0)
				 graph[i][j] = 1;
			 else
				 graph[i][j] = 0;
		 }
	 }
 }
 
 static void normalize(float[] rank) {   
	 float sum = 0;
	 for(int i = 0; i < rank.length; i++)
		 sum += rank[i];
		 
	 for(int i = 0; i < rank.length; i++)
		 rank[i] /= sum;
 }
 
 public static void main(String[] args) {
	 int[][] graph = new int[5][5];
	 float[] rank0, rank1;
	 int[] L = new int[5];
	 
	 rank0 = new float[5];
	 rank1 = new float[5];
	 
	 fillGraph(5,graph);
	 
	 //initial itr
	 for(int i = 0; i < rank0.length; i++)
	 {
		 rank0[i] = 1/(float)rank0.length;
		 // how many pages each page refer to
		 int count = 0;
		 for(int j = 0; j < rank0.length; j++)
		 {
			 if (graph[i][j] == 1)
				 count++;
		 }
		 L[i] = count;
	 }
	 
	 for(int i = 0; i < rank0.length; i++)
	 {
		 for(int j = 0; j < rank0.length; j++)
		 {
			 System.out.print(graph[i][j]);
		 }
		 System.out.print("\n");
	 }
	 
	 int itr = 0;
	 float lampda = 0.5f;
	 for(int i = 0; i < 20; i++)
	 {
		 if(itr%2 == 0)
		 {
			 for(int j = 0; j < rank0.length; j++)
			 {
				 float pr = 0;
				 for(int k = 0; k < rank0.length; k++)
				 {
					 if(graph[k][j] == 1)
					 {
						 pr = pr + (rank0[k]/L[k]);
					 }
				 }
				 rank1[j] = pr;
	//			 rank1[j] = (1-lampda)*rank0[j]+lampda*pr;
//				 System.out.println(rank1[j]);
			 }
			 normalize(rank1);
		 }
		 
		 else
		 {
			 for(int j = 0; j < rank0.length; j++)
			 {
				 float pr = 0;
				 for(int k = 0; k < rank0.length; k++)
				 {
					 if(graph[k][j] == 1)
					 {
						 pr = pr + (rank1[k]/L[k]);
//						 System.out.println("pr"+pr);
					 }
				 }
				 rank0[j] = pr;
//				 System.out.println(rank0[j]);
	//			 rank1[j] = (1-lampda)*rank0[j]+lampda*pr;
			 }
			 normalize(rank0);
		 }
		 
		 itr++;
	 }
	 for(int j = 0; j < rank0.length; j++)
	 {
		 if(itr%2 == 0)
			 System.out.println(rank0[j]);
		 else
			 System.out.println(rank1[j]);
	 }
	 
	}
}

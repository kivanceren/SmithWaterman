import java.lang.reflect.Array;
import java.util.Map;
import static java.lang.Math.max;

interface degerler
{
	final static int GAP_SCORE=-1;
	final static int MISS_MATCH=-1;
	final static int MATCH=2;
}

class Solution implements degerler
{
	
	private String a;
	private String b;
	private int matrix[][],i,j;
	private String a_final,b_final;
	
	public Solution(String a1,String b1,int i1,int j1)
	{
		a=a1; b=b1; i=i1; j=j1;
		matrix=new int [a.length()+1][b.length()+1] ;
		for(int k=0;k<i;k++) matrix[k][0]=0;
		for(int k=0;k<j;k++) matrix[0][k]=0;
		a_final="";
		b_final="";
	
	}
	public int[][] build_Matrix()
	{
		for(int i1=1;i1<i;i1++)
		{
			 for(int j1=1;j1<j;j1++)
			 {
				 
				 if(a.charAt(i1)==b.charAt(j1)) //Match
				 {
					 
					 matrix[i1][j1]=matrix[i1-1][j1-1]+MATCH;
					 
				 }
				 else
				 {
					find_value(i1,j1);
				 }
			 }
		}
		return matrix;
	}
	
	
	public void find_value(int i1,int j1)
	{
		 
		 if(matrix[i1-1][j1] > matrix [i1-1][j1-1] && matrix[i1-1][j1] > matrix[i1][j1-1] )
		 {
			 matrix[i1][j1]=matrix[i1-1][j1]+GAP_SCORE;
			 
		 }
		 
		 else if(matrix[i1-1][j1-1] > matrix [i1-1][j1] && matrix[i1-1][j1-1] > matrix[i1][j1-1] )
		 {
			 matrix[i1][j1]=matrix[i1-1][j1-1]+GAP_SCORE;
			 
		 }
		 
		 else if(matrix[i1][j1-1] > matrix [i1-1][j1-1] && matrix[i1][j1-1] > matrix[i1-1][j1] ) 
		 {
			 matrix[i1][j1]=matrix[i1][j1-1]+GAP_SCORE;
			 
		 }
	     
	}
	
	public void Find_String()
	{
		int r_max=0;
		int max = matrix[0][0];
		int i_temp=0,j_temp=0;
		
	    for(int i1=0;i1<i;i1++)
	          for(int j1=0;j1<j;j1++){
				if(matrix[i1][j1]>max) max = matrix[i1][j1];
				i_temp=i1;
				j_temp=j1;
	          }
	    
	    
	   
	    while(true)
	    {
	    	
	    	r_max=return_max(matrix[i_temp-1][j_temp-1],matrix[i_temp][j_temp-1],matrix[i_temp-1][j_temp]);
	    	
	    	if(matrix[i_temp-1][j_temp-1]==r_max) // çapraz max ise
	    	{
	    		a_final=a_final+a.charAt(i_temp);
	    		b_final=b_final+b.charAt(j_temp);
	    		i_temp=i_temp-1;
	    		j_temp=j_temp-1;
	    	}
	    	
	    	else if (matrix[i_temp][j_temp-1]==r_max) //sol max ise
	    	{
	    		a_final=a_final+"-";
	    		b_final=b_final+b.charAt(j_temp);
	    		j_temp=j_temp-1;
	    	}
	    	
	    	else if (matrix[i_temp-1][j_temp]==r_max) //yukarı max ise
	    	{
	    		a_final=a_final+a.charAt(i_temp);
	    		b_final=b_final+"-";
	    		i_temp=i_temp-1;
	    		
	    	}
	    	if(i_temp ==0 && j_temp==0 ) break;	    	
	    }
	    StringBuilder new_A=new StringBuilder(a_final).reverse();
	    StringBuilder new_B=new StringBuilder(b_final).reverse();
	    System.out.println(new_A);
	    System.out.println(new_B);
	}
	
	public int return_max(int n1,int n2,int n3)
	{
		return max(max(n1,n2),n3);
	}
}



public class SmithWaterman {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String a="-AGCACACA";
		String b="-ACACACTA";
		Solution s=new Solution(a, b,a.length(),b.length());
		int matrix[][]=s.build_Matrix(); 
		for(int i=0;i<a.length();i++)
		{
			for(int j=0;j<b.length();j++)
			{
				System.out.print(matrix[i][j]+"  ");
			}
			System.out.println();
		}
		s.Find_String();
	    
	    
	}
}

	

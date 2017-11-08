package pca;

import Jama.Matrix;

public class pca {
	static public void main(String []args){
		Matrix A;
		Matrix B;
		A=new Matrix(10,1);
		B=new Matrix(10,1);
		/*for(int i=0;i<10;i++){
			System.out.print("Please input number "+i);
			Scanner sc = new Scanner(System.in);
			A.set(i,0,sc.nextDouble());
			System.out.print("Please input number "+i);
			Scanner sc2 = new Scanner(System.in);
			B.set(i,0,sc2.nextDouble());
		}*/
		A=Matrix.random(10, 1);
		B=Matrix.random(10, 1);
		
		double a=0;
		double b=0;
		
		for(int i=0;i<10;i++){
			a+=A.get(i,0);
		}
		for(int i=0;i<10;i++){
			b+=B.get(i,0);
		}
		a/=10;
		b/=10;
		
		for(int i=0;i<10;i++){
			A.set(i,0,A.get(i,0)-a);
			B.set(i,0,B.get(i,0)-b);
		}
		
		A.print(4, 4);
		B.print(4, 4);
		double cov_xy;
		double cov_xx;
		double cov_yy;

		Matrix cov_M = new Matrix(2,2);
		cov_xx=A.transpose().times(A).get(0, 0);
		cov_yy=B.transpose().times(B).get(0, 0);
		cov_xy=B.transpose().times(A).get(0, 0);
		cov_M.set(0, 0, cov_xx);
		cov_M.set(1, 1,cov_yy);
		cov_M.set(0, 1,cov_xy);
		cov_M.set(1, 0,0-cov_xy);
		
		cov_M.print(4,4);
		
		cov_M.eig().getV().print(4,4);
		cov_M.eig().getD().print(4,2);
		Matrix temp = new Matrix(1,2);
		Matrix temp2 = new Matrix(10,2);
		Matrix Result= new Matrix(10,1);
		
		if(cov_M.eig().getD().get(0, 0)> cov_M.eig().getD().get(1, 1)){
			temp=cov_M.eig().getV().getMatrix(0, 0, 0, 1);
		}
		else{
			temp=cov_M.eig().getV().getMatrix(1, 1, 0, 1);
		}
		temp2.setMatrix(0, 9, 0, 0, A);
		temp2.setMatrix(0, 9, 1, 1, B);
		temp.print(4, 4);
		Result=temp2.times(temp.transpose());
		Result.print(4, 4);
	}
}

package test.visualization;

import static org.math.array.DoubleArray.*;
import static org.math.array.LinearAlgebra.*;

import org.math.array.DoubleArray;

public class TestModifyArraysExample {
	public static void main(String[] args) {
 
//		// initial matrix and vector
//		double[][] A = { { 1.1, 1.2, 1.3, 1.4 }, { 2.1, 2.2, 2.3, 2.4 }, { 3.1, 3.2, 3.3, 3.4 } };
//		double[] a = {  10, 20, 30, 40 };
//		double[] b = {  100, 200, 300, 400 };
// 
//		// delete first and third rows
//		double[][] B = deleteRows(A, 0, 2);
//		
//		// delete first column
//		double[][] C = deleteColumns(A, 1);
//		
//		// delete first TO third elements of b
//		double[] b2 = deleteRange(b,0,2);
// 
//		// insert a between first and second rows of A
//		double[][] D = insertRows(A,1,a);
//		
//		// copy third and second rows of A
//		double[][] E = getRowsCopy(A,1,2);
// 
//		// copy second TO fourth columns of A
//		double[][] F = getColumnsRangeCopy(A,1,3);
//		
//		// merge a with b times
//		double[][] G = mergeRows(a,b);
//		// print matrices in command line
//		System.out.println("A = \n" + DoubleArray.toString(A));
//		System.out.println("a = \n" + DoubleArray.toString(a));
//		System.out.println("b = \n" + DoubleArray.toString(b));
//		System.out.println("B = \n" + DoubleArray.toString(B));
//		System.out.println("b2 = \n" + DoubleArray.toString(b2));
//		System.out.println("C = \n" + DoubleArray.toString(C));
//		System.out.println("D = \n" + DoubleArray.toString(D));
//		System.out.println("E = \n" + DoubleArray.toString(E));
//		System.out.println("F = \n" + DoubleArray.toString(F));
//		System.out.println("G = \n" + DoubleArray.toString(G));
//		
//		
//		

		
		// random 4 x 3 matrix
		double[][] A = random(4, 3);
 
		// random 4 x 3 matrix
		double[][] B = random(4, 3);
 
		// random 4 x 4 matrix + Id
		double[][] C = plus(random(4, 4),identity(4));
		
		// linear algebra
		double[][] D = plus(A, B);
 
		double[][] E = plus(A, 1);
 
		double[][] F = minus(A, B);
 
		double[][] G = minus(2, A);
		
		double[][] H = times(A, transpose(B));
 
		double[][] I = times(A, 10);
 
		double[][] J = divide(C, B);
 
		double[][] K = divide(A, 10);
 
		double[][] L = inverse(C);
 
		// print matrices in command line
		System.out.println("A = \n" + DoubleArray.toString(A));
		System.out.println("B = \n" + DoubleArray.toString(B));
		System.out.println("C = \n" + DoubleArray.toString(C));
		System.out.println("D = A + B = \n" + DoubleArray.toString(D));
		System.out.println("E = A + 1 = \n" + DoubleArray.toString(E));
		System.out.println("F = A - B = \n" + DoubleArray.toString(F));
		System.out.println("G = 2 - A = \n" + DoubleArray.toString(G));
		System.out.println("H = A * t(B) = \n" + DoubleArray.toString(H));
		System.out.println("I = A * 10 = \n" + DoubleArray.toString(I));
		System.out.println("J = C / B = \n" + DoubleArray.toString(J));
		System.out.println("K = A / 10 = \n" + DoubleArray.toString(K));
		System.out.println("L = C^-1 = \n" + DoubleArray.toString(L));

		
		
		
		
 
	}
}

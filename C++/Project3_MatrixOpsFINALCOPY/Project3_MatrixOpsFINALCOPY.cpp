// MatrixIO_Trial.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <fstream>
#include <string>

using namespace std;


const int M = 5, N = 5;
double readmat(double A[M][N]) {
	ifstream inFile;
	inFile.open("input1.txt");

	for (int x = 0; x < M; x++) {
		for (int y = 0; y < N; y++) {
			inFile >> A[x][y];
		}
	}
	return 1;
}

double printmat(double B[M][N], string x) {
	ofstream outFile;
	outFile.open("output1.txt", ios::app);

	outFile << x << endl;
	for (int x = 0; x < M; x++) {
		for (int y = 0; y < N; y++) {
			outFile << B[x][y] << "  ";
		}
		outFile << endl;
	}
	outFile << endl;
	return 1;
}

double copymat(double C[M][N], double D[M][N]) {
	for (int x = 0; x < M; x++) {										// copymat()					
		for (int y = 0; y < N; y++) {
			D[x][y] = C[x][y];
		}
	}
	return 1;
}
double identmat(double D[N][N]) {
	for (int x = 0; x < N; x++) {									// identmat()
		for (int y = 0; y < N; y++) {
			if (x == y) {
				D[x][y] = 1;
			}
			else {
				D[x][y] = 0 ;
			}
		}
	}

	return 1;
}

double invmat(double matrix[M][N], double identity[M][N]) {
	double numHeld;
	double tempHolder[M][N];

	for (int times = 0; times < N; times++) {
		numHeld = matrix[times][times];									// Updates Each Row
		for (int x = 0; x < N; x++) {
			matrix[times][x] /= numHeld;
			identity[times][x] /= numHeld;
		}

		for (int x = 0; x < M; x++) {									// Updates the other rows by subtracting numHeld * updated row
			numHeld = matrix[x][times];
			if (x != times) {
				for (int y = 0; y < N; y++) {
					matrix[x][y] -= numHeld * matrix[times][y];
					identity[x][y] -= numHeld * identity[times][y];
				}
			}
			else {
				continue;
			}
		}
	}

	for (int a = 0; a < M; a++) {
		for (int b = 0; b < N; b++) {
			tempHolder[a][b] = identity[a][b];
			identity[a][b] = matrix[a][b];
			matrix[a][b] = tempHolder[a][b];
		}
	}

	return 1;
}
double multmat(double matrixOne[M][N], double matrixTwo[M][N], double product[M][N]) {
	double productSum = 0;
	for (int row = 0; row < M; row++) {
		for (int times = 0; times < N; times++) {
			for (int column = 0; column < N; column++) {
				productSum += matrixOne[row][column] * matrixTwo[column][times];
			}
			product[row][times] = productSum;
			productSum = 0;
		}
	}

	return 1;
}
int main()
{
	double A[M][N], D[M][N], B[M][N], C[M][N];

	readmat(A);
	printmat(A,"Matrix A");
	
	copymat(A,D);
	printmat(D, "Matrix D, a copy of matrix A");
	
	identmat(B);
	printmat(B, "Matrix B, identity matrix");
	
	invmat(A, B);
	printmat(A, "Inverse of Matrix A");
	printmat(B, "Identity Matrix");

	multmat(D, B, C);
	printmat(C, " Product of Matrix Multiplication");

    return 0;
}


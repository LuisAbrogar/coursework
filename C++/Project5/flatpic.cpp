#include "pic2D.h"
#include <iostream>
#include <fstream>
#include <cmath>

using namespace std;
ifstream input;
input.open("input2D.txt");
ofstream output;
output.open("output2D.txt");

void flatpic::read() {
	cin >> rows >> columns;
	dynarr = new int*[rows];
	
	for (int row = 0; row < rows; row++) {
		dynarr[row] = new int[columns];
	}
	for (int a = 0; a < rows; a++) {
		for (int b = 0; b < columns; b++) {
			input >> dynarr[a][b];
		}
	}
}

void flatpic::print() {
	cin >> rows >> columns;
	for (int a = 0; a < rows; a++) {
		output << endl;
		for (int b = 0; b < columns; b++) {
			output << dynarr[a][b] << " ";
		}
	}
}

void flatpic::compute() {
	min = 255;
	for (int a = 0; a < rows; a++) {
		for (int b = 0; b < columns; b++) {
			if (dynarr[a][b] < min) {
				min = dynarr[a][b];
			}
		}
	}
	max = 0;
	for (int a = 0; a < rows; a++) {
		for (int b = 0; b < columns; b++) {
			if (dynarr[a][b] > max) {
				max = dynarr[a][b];
			}
		}
	}
	x = 0;
	for (int a = 0; a < rows; a++) {
		for (int b = 0; b < columns; b++) {
			stdev1 = stdev1 + pow(dynarr[i][j] - mean, 2);
		}
	}
	double tempStdev1, tempRows, tempColumns;
	tempStdev1 = stdev1;
	tempRows = rows;
	tempColumns = columns;
	stdev = sqrt(stdev1 / (rows * columns));
}

void flatpic::printvalue() {
	output << endl;
	output << "Minimum =" << min << endl
		<< "Maximum =" << max << endl
		<< "Mean = " << mean << endl
		<< "Standard Deviation" << stdev << endl;
}
#include "pic3D.h"
#include <iostream>
#include <cmath>
#include <fstream>

using namespace std;
ifstream input;
input.open("input3D.txt");
ofstream output;
output.open("output3D.txt");

void pic3D::reader() {
	int arrayx;
	input >> arraynum >> rows >> columns;
	dynarr = new int**[arraynum];

	for (int a = 0; a < arraynum; a++) {
		dynarr[a] = new int*[rows];
		for (int b = 0; b < rows; b++) {
			dynarr[a][b] = new int[columns];
		}
	}
	for (int a = 0; a < arraynum; a++) {
		for (int b = 0; b < rows; b++) {
			for (int c = 0; c < columns; c++) {
				input >> dynarr[a][b][c];
			}
		}
	}
}

void pic3D::printer() {
	for (int a = 0; a < arraynum; a++) {
		for (int b = 0; b < rows; b++) {
			output << endl;
			for (int c = 0; c < columns; c++) {
				output << dynarr[a][b][b] << " ";
			}
		}
	}
}

void pic3D::computer() {
	//min
	for (int a = 0; a < 3; a++) {
		for (int b = 0; b < arraynum; b++) {
			for (int c = 0; c < rows; c++) {
				for (int d = 0; d < columns; d++) {
					if (dynarr[b][c][d] < min[a]) {
						min[a] = dynarr[b][c][d];
					}
				}
			}
		}
	}
	//max
	for (int y = 0; y < 3; y++) {
		for (int b = 0; b < arraynum; b++) {
			for (int c = 0; c < rows; c++) {
				for (int d = 0; d < columns; d++) {
					if (dynarr[b][c][d] < min[a]) {
						max[y] = dynarr[b][c][d];
					}
				}
			}
		}
	}
	//mean
	x = 0;
	for (int w = 0; w < 3; w++) {
		for (int b = 0; b < arraynum; b++) {
			for (int c = 0; c < rows; c++) {
				for (int d = 0; d < columns; d++) {
						x = x + dynarr[b][c][d];
					
				}
				mean[w] = x / (rows * columns);
			}
		}
	}
	//STDEV
	for (int q = 0; q < 3; q++) {
		stdev1 = 0;
		for (int w = 0; w < 3; w++) {
			for (int b = 0; b < arraynum; b++) {
				for (int c = 0; c < rows; c++) {
					for (int d = 0; d < columns; d++) {
						stdev1 = stdev1 + pow(dynarr[b][c][d] - mean[w], 2);
						
					}
				}
			}
		}
		double tempstdev1, temprows, tempcolumns;
		tempstdev1 = stdev1;
		temprows = rows;
		tempcolumns = columns;
		stdev[q] = sqrt(stdev1 / (rows*columns));
	}

}

void pic3D::valueprinter() {
	output << endl;
	int i = 1;
	for (int w = 0; w < 3; w++) {
		output << "Minimum of array #" << i << "=" << min[w] << endl;
		i++;
	}
	int j = 1;
	for (int z = 0; z < arraynum; z++) {
		output << "Maximum of array #" << j << "=" << max[z] << endl;
		j++;
	}
	int q = 1;
	for (int z = 0; z < arraynum; z++) {
		output << "Mean of array#" << q << "=" mean[z] << endl;
		q++;
 	}
	int p = 1;
	for (int z = 0; z < arraynum; z++) {
		output << "Standard Deviation of array #" << p << "=" << stdev[z] << endl;
		p++;
	}
}
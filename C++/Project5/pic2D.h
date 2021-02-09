#pragma once

#ifndef pic2D_H
#define pic2D_H

class pic2D {
public:
	void read();
	void print();
	void compute();
	void printvalue();
private:
	int rows, columns, min, max;
	int stdev;
	double stdev1, mean, x;
	int **dynarr;
}

#endif
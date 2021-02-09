#pragma once

#ifndef flatpic_H
#define flatpic_H

class flatpic {
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
#pragma once

#ifndef pic3D_H
#define pic3D_H

class pic3D {
public:
	int rows, columns, arraynum;
	int min[3] = { 255, 255, 255 };
	int max[3] = {1, 1, 1}
	double mean[3];
	double stdv[3];
	double x;
	int stdev1;
	int ***dynarr;
	void reader();
	void printer();
	void computer();
	void valueprinter();
private:

};

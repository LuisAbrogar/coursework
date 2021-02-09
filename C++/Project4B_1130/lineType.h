#pragma once

#ifndef lineType_H
#define lineType_H
#include "pointType.h"

class lineType : public pointType {
public:
	void CheckerPrinter();			//Gives orientation of the line and its equation
	void intersect(lineType);
	lineType();
	lineType(double, double, double, double);

private:
	pointType p1;
	pointType p2;
};


//Implementation File

#include <iostream>
#include <cmath>
#include "pointType.h"

using namespace std;

void pointType::setX(double x) {
	xCoordinate = x;
}
void pointType::setY(double y) {
	yCoordinate = y;
}

void pointType::print() const {
	cout << "(" << xCoordinate << "," << yCoordinate << ")" << endl;
}

double pointType::getX() const {
	return xCoordinate;
}
double pointType::getY() const {
	return yCoordinate;
}
double pointType::distance(pointType p2) {
	double xdiff, ydiff, distance;

	xdiff = p2.xCoordinate - xCoordinate;
	xdiff = pow(xdiff, 2);
	ydiff = p2.yCoordinate - yCoordinate;
	ydiff = pow(ydiff, 2);

	distance = sqrt(xdiff + ydiff);
	return distance;
}
pointType::pointType(double x, double y) {
	xCoordinate = x;
	yCoordinate = y;
}

pointType::pointType()
{
	xCoordinate = 0;
	yCoordinate = 0;
}


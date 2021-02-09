#include <iostream>
#include <cmath>
#include "pointType.h"
#include "lineType.h"

using namespace std;

lineType::lineType(double point1X, double point1Y, double point2X, double point2Y) {
	p1.setX(point1X);
	p1.setY(point1Y);
	p2.setX(point2X);
	p2.setY(point2Y);
}

lineType::lineType() {
	double x1, x2, y1, y2;
	x1 = 0;
	x2 = 0;
	y1 = 0;
	y2 = 0;
}

void lineType::CheckerPrinter() {
	double x1, x2, y1, y2;
	double slope, yInt;

	x1 = p1.getX();
	x2 = p2.getX();
	y1 = p1.getY();
	y2 = p2.getY();

	if (x1 == x2) {
		cout << "The points create a vertical line" << endl;
		cout << "x =" << x1 << endl;
	}
	else if (y1 == y2) {
		cout << "The points create a horizontal line" << endl;
		cout << "y = " << y1 << endl;
	}
	else {
		slope = (y2 - y1) / (x2 - x1);
		yInt = y1 - (slope * x1);

		cout << "The points create a slanted line" << endl
			<< "Slope = " << slope << endl;
		<< "y =" << slope << "x + " << b << endl;
	}
}

void intersect(lineType obj2) {
	double b2, b1, m1, m2;
	double xintercept, yintercept;

	if (slope == obj2.slope) {
		cout << "The lines are parallel" << endl;
	}
	else if (b == obj2.b) {
		cout << "The lines are parallel" << endl;
	}
	else {
		b1 = b;
		m1 = slope;
		b2 = obj2.b;
		m2 = obj2.slope;
		xintercept = (b2 - b1) / (m1 - m2);
		yintercept = m1 * xintercept + b1;
		cout << "The point of intersection is at x=" << xintercept << "and y=" << yintercept << endl;
	}
}
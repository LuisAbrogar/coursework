
#include "stdafx.h"
#include <iostream>
#include "pointType.h"
#include "pointType.cpp"

int main() {
	pointType p1, p2;
	double difference;

	p1.setPoint(2, 5);
	p2.setPoint(3, 7);

	difference = p1.distance(p2);

	cout << difference << endl;

	return 0;
}


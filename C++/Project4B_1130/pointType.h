#pragma once

#ifndef pointType_H
#define pointType_H

class pointType {
public:
	void setX(double);
	void setY(double);
	void print() const;
	double getX() const;
	double getY()const;
	pointType(double, double);
	pointType();
	double distance(pointType);
protected:
	double xCoordinate;
	double yCoordinate;
};

#endif


#pragma once

#ifndef superSmart_H
#define superSmart_H
#include "smart.h"

class superSmart : public smart {
public:
	void print() const;
	void set(int, int, int);
	int manipulate();
	superSmart();
	superSmart(int, int, int);
private:
	int z;
};

#endif


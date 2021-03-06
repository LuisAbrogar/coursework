// Project4A-1116.cpp : Defines the entry point for the console application.
//
#include <iostream>
#include <math.h>
#include "stdafx.h"
#include "superSmart.h"


using namespace std;

// smart.h definitions -----------------------------------------------------------------------------------------

void smart::print() const {
	cout << "x = " << x
		<< "y = " << y
		<< "secret = " << secret << endl;
}

void smart::set(int one, int two) {
	x = one;
	y = two;
}

int smart::sum() {
	return x + y;
}

smart::smart(int first, int second) {
	first = x;
	second = y;
}


// superSmart.h definitions -------------------------------------------------------------------------------------

void superSmart::print() const {
	smart::print();
	cout << "z = " << z << endl;
}

void superSmart::set(int one, int two, int three) {
	smart::set(one, two);
	z = three;
}

int superSmart::manipulate() {
	int ans, prod;
	ans = smart::sum();
	prod = pow(ans, z);

	return prod;
}

superSmart::superSmart(int first, int second, int third) {
	smart::smart(first, second);
	third = z;
}


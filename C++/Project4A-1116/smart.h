#pragma once

#ifndef smart_H
#define smart_H

class smart {
public:
	void print() const;
	void set(int, int);
	int sum();
	smart();
	smart(int, int);
private:
	int x;
	int y;
	int secret();
};

#endif

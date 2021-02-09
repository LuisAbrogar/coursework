#pragma once
#include "stdafx.h"
#ifndef doublyLinkedList_H
#define doublyLinkedList_H

// NODE DEFINITION------------------
template <class Type>
struct nodeType {
	Type info;
	nodeType <Type> *next;
	nodeType<Type> *back;
};

template <class Type>
class doublyLinkedList
{
public:
	const doublyLinkedList<Type>& operator=(const doublyLinkedList<Type> &);
	void initializeList();
	bool isEmptyList();
	void destroy();
	void print() const;
	int length() const;
	bool search(const Type& searchItem) const;
	void insert(const Type& insertItem);
	void deleteSpecificNode(const Type& deleteItem);
	void deleteNodeAddress(const Type& address);
	doublyLinkedList();
	~doublyLinkedList();
protected:
	int count;
	nodeType<Type> *first;
	nodeType<Type> *last;
private:
	void copyList(const doublyLinkedList<Type>& otherList);
};


#endif
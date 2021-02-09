// Project6.cpp : Defines the entry point for the console application.
//
#include <iostream>
#include "stdafx.h"
#include "doublyLinkedList.h"

using namespace std;

//Class Definitions-----------------
template<class T>
const doublyLinkedList<T>:: operator=(const doublyLinkedList<T> & rightObject) {
	if (this != &rightObject) {
		*ptr = *(rightObject.ptr);
		return *this;
	}
}
template <class T> void doublyLinkedList<T>::initializeList() {
	nodeType<T> *current;
	int num;

	count = 0;
	cout << "Enter a list of integers ending with -999:" << endl;
	cin >> num;

	first = nullptr;
	for (int x = 0; x < count; x++) {
		cin >> num;
		current = new nodeType<T>;
		current->info = num;
		current->next = nullptr;

		if (first == nullptr) {
			first = current;
			last = current
		}

		else
		{
			last->back = current;
			last->next = current;
			last = newNode;
		}
		count++;
		cin >> num;
		
	}

	return first;
}

template <class T> bool doublyLinkedList<T>::isEmptyList() {
	if (count != 0) {
		return false;
	}
	else {
		return true;
	}
}

template <class T> void doublyLinkedList<T>::destroy() {
	nodeType<T> *temp;
	while (first != nullptr) {
		temp = first;
		first = first ->link;
		delete temp;
	}

	last = nullptr;
	count = 0;
}

template <class T> void doublyLinkedList<T>::print() {
	nodeType<T> *current;
	current = first;

	while (current != nullptr) {
		cout << current->info << " ";
		current = current->link;
	}
}

template <class T> void doublyLinkedList<T>::length() {
	int value;

	value = count;

	return value;
}

template <class T> bool doublyLinkedList<T>::search(const T& searchItem) {
	bool found = false;
	nodeType<T> *current;
	current = first;

	while (current != nullptr && !found) {
		if (current->info >= searchItem) {
			found = true;
		}
		else {
			current = current->link;
		}
	}
		if (found) {
			found = (current->info == searchItem);
		}
	
		return found;
}

template <class T> void doublyLinkedList<T>::insert(const T& insertItem) {
	nodeType<T> *current;
	nodeType<T> *trailer = nullptr;
	nodeType<T> *newNode;

	bool found;

	newNode = new nodeType<T>;
	newNode->info = insertItem;
	newNode->link = nullptr;

	if (first == nullptr) {
		first = newNode;
		last = newNode;
		count++;
	}
	else {
		current = first;
		found = false;
		while (current != nullptr && !found) {
			if (current->info >= insertItem) {
				found = true;
			else
			{
				trailer = current;
				current = current->link;
			}
			if (current == first) {
				newNode->link = first;
				first = newNode;
				count++
			}
			else {
				trailer->link = newNode;
				newNode->link = current;
				if (current == nullptr) {
					last = newNode;
				}
				count++
			}
			}
		}
	}
}

template <class T> void doublyLinkedList<T>::deleteSpecificNode(const T& deleteItem) {
	bool found = false;
	nodeType<T> *current;
	current = first;

	while (current != nullptr && !found) {
		if (current ->info == deleteItem->info) {
			found = true;
		}
		else {
			current = current->link;
		}
	}
	if (found) {
		delete deleteItem;
	}

}

template <class T> void doublyLinkedList<T>::deleteNodeAddress(const T& deleteItem) {
	nodeType<T> *holder;

	holder = deleteItem->link;
	deleteItem->link = holder->link;
	delete deleteItem;
}

template <class T> void doublyLinkedList<T>::copyList(const doublyLinkedList<T>& otherList) {
	nodeType<T> *newNode;
	nodeType<T> *current;

	if (first != nullptr) {
		destroy();
	}

	if (otherLIst.first == nullptr) {
		first = nullptr;
		last = nullptr;
		count = 0;
	}
	else {
		current = otherList.first;
		count = otherList.count;

		first = new nodeType<T>;
		first->info = current->info;
		first->link = nullptr;

		last = first;
		current = current->link;

		while (current != nullptr) {
			newNode = new nodeType<T>;
			newNode->info = current->info;
			newNode->link = nullptr;

			last->link = newNode;
			last = newNode;

			current = current->link;
		}
	}
}

template<T> doublyLinkedList<T>::doublyLinkedList() {
	initializeList();
}

template<T> doublyLinkedList<T>::~doublyLinkedList() {
	destroy();
}
int main()
{
	template<T> nodeType<T> *L1;
	template<T> nodeType<T> *L2;
	int option;

	cout << "Select an option:"
		<< "1: Initialize List"
		<< "2: Test if list is empty"
		<< "3: Destroy List"
		<< "4: Find length of list"
		<< "5: Print List"
		<< "6: Search List for specific Item"
		<< "7: Insert Item"
		<< "8: Delete item with position"
		<< "9: Delete specified item"
		<< "10: Copy LIst"
		<< "11: Overload =" << endl;

	switch (option) {
	case 1:
		template<T>doublyLinkedList<T>.initialize();
	case 2:
		template<T>doublyLinkedList<T>.isEmptyList();
	case 3:
		template<T>doublyLinkedList<T>.destroy();
	case 4:
		template<T>doublyLinkedList<T>.length();
	case 5:
		template<T>doublyLinkedList<T>.print();
	case 6:
		template<T> nodeType<T> *item;
		cout << "What are you looking for?" << endl;
		cin >> *item->info;
		template<T>doublyLinkedList<T>.search(item->info) const;
	case 7:
		template<T> nodeType<T> *item;
		cout << "What do you want to insert?" << endl;
		cin >> *item->info;
		template<T>doublyLinkedList<T>.insert(const item->info);
	case 8:
		template<T> nodeType<T> *item;
		cout << "Where is the item?" << endl;
		cin >> *item->info;
		template<T>doublyLinkedList<T>.deleteNodeAddress(const item->info);
	case 9:
		template<T> nodeType<T> *item;
		cout << "What is the item?" << endl;
		cin >> *item->info;
		template<T>doublyLinkedList<T>.deleteNodeAddress(const item->info);
	case 10:
		template<T> doublyLinkedList<T>.copyList(doublyLinkedList<T>& L2);
	case 11:
		cout << "done" << endl;
	default:
		cout << "invalid choice" << endl;

	}


    return 0;
}


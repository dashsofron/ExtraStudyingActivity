#pragma once
#include "letter.h"

//классы без шаблонов
/*class LRS
{
public:
	int lenght;
	letter *LRS_ar;
	LRS(int k);
	LRS(int k, letter * a);
	const letter last();
	letter& operator[](int index);
	letter shift(letter a);
	void shift_if(letter cond, letter a);
	LRS copy();
	~LRS();
};
*/
/*class LRS_INT
{
public:
	int lenght;
	int *LRS_ar;
	LRS_INT(int k);
	LRS_INT(int k, int *ar);
	int& operator[](int index);
	int shift(int a);
	~LRS_INT();
};*/

template <class T>
class LRS
{
public:
	int lenght;
	T*LRS_ar;
	LRS(int k);
	LRS(int k, T * a);
	const T last();
	T& operator[](int index);
	T shift(T a);
	void shift_if(T cond, T a);
	LRS copy();
	~LRS();
};



/*template <class T>
class cipher {
private:
	LRS<T> *sr0;
	int *key;
	LRS<T> out;
public:
	cipher()=default;
	~cipher() = default;
	virtual void init()const=0;
	virtual void step()const=0;
};*/

template <class T>
class bivium{
	LRS<T> *sr0;//93
	LRS<T> *sr1;//84
	LRS<T> *out;// count lenght
public:
	bivium(int k);
	~bivium();
	void step();
	T feedback_sr0();
	T feedback_sr1();
	T output();

};

template <class T>
class trivium {
private:

	LRS<T> *sr0;//93
	LRS<T> *sr1;//84
	LRS<T> *sr2;//11
	LRS<T> *out;// count lenght

public:
	trivium(int k);
	~trivium();
	void step();
	T feedback_sr0();
	T feedback_sr1();
	T feedback_sr2();
	T output();
};

template <class T>
class crypto1 {
private:
	LRS<T> *sr0;//48
	LRS<T> *out;// count lenght

public:
	crypto1(int k);
	~crypto1();
	void step();
	T f0();
	T f1();
	T f2();
	T f3();
	T f4();
	T feedback_sr0();
	T output();
};

template <class T>
class grain {
private:
	int count;
	LRS<T> *sr1;//80
	LRS<T> *sr0;//80
	LRS<T> *out;// count lenght
	int iterration_count_ = 160;
public:
	grain(int k);
	~grain();
	void print();
	void step(int c);
	int count_();
	T get_gamma_bit(int i);
	void get_gamma();
	T f0();
	T feedback_init_sr0();
	T feedback_init_sr1();
	T feedback_sr0();
	T feedback_sr1();
	T output();
	void init(int*key, int*key_vector,int*mask=0);
	void init_reg(int*key,int*mask=0);
};


/*template <class T>
class hitag2 :public cipher {
private:
	LRS<T> *sr0;//48
	LRS<T> *out;// count lenght
public:
	hitag2(int k);
	~hitag2();
	void step();
	T f0();
	T f1();
	T f2();
	T f3();
	T f4();
	T feedback_sr0();
	T output();
};*/
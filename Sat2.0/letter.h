#pragma once
class letter
{
public:
	int defined;
	int is_constant;
	long long num;

	letter(int isc = 0, int v = 0);
	long long  newvar();
	const letter neg();
	friend letter operator^(letter a, letter b);
	friend letter operator^(letter a, int b);
	friend letter operator&(letter  a, letter  b);
	friend letter operator&(letter  a, int  b);
	friend letter operator|(letter  a, letter  b);
	friend letter operator|(letter  a, int  b);
	friend letter operator==(letter a, letter b);
	friend letter operator==(letter a, int b);
	friend letter EQ(letter  a, letter  b);
	friend letter EQ(letter  a, long long  b);
	~letter();
};


#include <vector>
#include <stdio.h>
#include "functions.h"
#include "letter.h"
#include <stdarg.h>
using namespace std;
extern vector < vector < long long > > allclouse;
letter getbit(long long  a, long long  b)
{
	return(letter(1, (a >> b) & 1));
}
void addclause( long long   num, long long  first, ...)
{
	va_list args;
	va_start(args, num);
	vector < long long  > newvec;
	while (num--)
	{
		newvec.push_back(va_arg(args, long long));
	}
	va_end(args);
	allclouse.push_back(newvec);

}
letter majority(letter a, letter b, letter c)
{
	return((a&b) | (a & c) | (b & c));
}
int count_A5_funk(int a, int b, int c)
{
	return(a&b | a & c | b & c);
}
long long  countfunk(long long  x)
{
	return(((x >> 1) & 1) ^ ((x >> 3) & 1) ^ ((x >> 5) & 1));
}
void danial_push(LRS LR1, LRS LR2, LRS LR3)
{
	vector <long long> v;
	v.push_back(LR1[9].num);
	v.push_back(LR2[11].num);
	v.push_back(-LR3[11].num);
	v.push_back(LR3[10].num);
	allclouse.push_back(v);

	v.clear();
	v.push_back(LR1[9].num);
	v.push_back(-LR2[11].num);
	v.push_back(LR2[10].num);
	v.push_back(LR3[11].num);
	allclouse.push_back(v);

	v.clear();
	v.push_back(LR1[9].num);
	v.push_back(-LR1[8].num);
	v.push_back(-LR2[11].num);
	v.push_back(-LR3[11].num);
	allclouse.push_back(v);

	v.clear();
	v.push_back(-LR1[9].num);
	v.push_back(LR1[8].num);
	v.push_back(LR2[11].num);
	v.push_back(LR3[11].num);
	allclouse.push_back(v);

	v.clear();
	v.push_back(-LR1[9].num);
	v.push_back(LR2[11].num);
	v.push_back(-LR2[10].num);
	v.push_back(-LR3[11].num);
	allclouse.push_back(v);

	v.clear();
	v.push_back(-LR1[9].num);
	v.push_back(-LR2[11].num);
	v.push_back(LR3[11].num);
	v.push_back(-LR3[10].num);
	allclouse.push_back(v);
}
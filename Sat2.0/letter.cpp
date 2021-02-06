#include "letter.h"
#include "functions.h"
#include <vector>
using namespace std;

extern vector < vector < long long > > allclouse;

letter::letter(int isc, int v)
{
	is_constant = isc;
	if (isc == 0 && v == 0) num = newvar();
	else num = v;
}
long long  letter::newvar()
{
	static long long l = 1;
	return(l++);

}
const letter letter::neg()
{
	letter tmp(0, -num);
	return tmp;
}
letter operator^(letter a, letter b)
{
	if (a.is_constant && b.is_constant)
		return letter(1, a.num ^ b.num);
	else if (a.is_constant)
	{
		if (a.num)
			return b.neg();
		else
			return b;
	}
	else if (b.is_constant)
	{
		if (b.num)
			return a.neg();
		else
			return a;
	}
	else
	{
		letter v;
		addclause(3,-v.num, -a.num, -b.num);
		addclause(3, -v.num, a.num, b.num);
		addclause(3, v.num, -a.num, b.num);
		addclause(3, v.num, a.num, -b.num);
		return v;
	}
}
letter operator^(letter a, int b)
{
	if (a.is_constant)
		return letter(1, (a.num) ^ b);
	else
	{
		if (b)
			return a.neg();
		else
			return a;
	}
}
letter operator&(letter  a, letter  b)
{
	if (a.is_constant && b.is_constant)
		return letter(1, a.num & b.num);
	else if (a.is_constant)
	{

		if (a.num)
			return b;
		else
			return  letter(1, 0);
	}
	else if (b.is_constant)
	{
		if (b.num)
			return a;
		else
			return  letter(1, 0);
	}
	else
	{
		letter v;
		addclause(2,-v.num, a.num);
		addclause(2,-v.num, b.num);
		addclause(3,v.num, -a.num, -b.num);
		return v;
	}
}
letter operator&(letter  a, int  b)
{
	if(a.is_constant)
		return letter(1, a.num & b);

		else
		{
		if (b)
			return a;
		else
			return  letter(1, 0);
		}
}
letter operator|(letter  a, letter  b)
{
	if (a.is_constant && b.is_constant)
		return letter(1, a.num | b.num);
	else if (a.is_constant)
	{

		if (a.num)
			return a;
		else
			return b;
	}
	else if (b.is_constant)
	{
		if (b.num)
			return b;
		else
			return  a;
	}
	else
	{
		letter v;
		addclause(2,v.num, -a.num);
		addclause(2,v.num, -b.num);
		addclause(3,-v.num, a.num, b.num);
		return v;
	}
}
letter operator|(letter  a, int  b)
{
	if (a.is_constant)
		return letter(1, a.num | b);
	else
	{
		if (b)
			return b;
		else
			return  a;
	}
}
letter operator==(letter a, letter b)
{
	if (a.is_constant && b.is_constant)
		return letter(1, a.num == b.num);
	else if (a.is_constant)
	{

		if (a.num)
			return b;
		else
			return  b.neg();
	}

	else if (b.is_constant)
	{
		if (b.num)
			return a;
		else
			return  a.neg();
	}
	else
	{
		letter v;
		addclause(3,-v.num, a.num, -b.num);
		addclause(3,-v.num, -a.num, b.num);
		addclause(3,v.num, -a.num, -b.num);
		addclause(3,v.num, a.num, b.num);

		return v;
	}
}
letter operator==(letter a, int b)
{
	if (a.is_constant)
		return letter(1, a.num == b);
	else
	{
		if (b)
			return a;
		else
			return  a.neg();
	}
}
 letter EQ(letter  a, letter  b)
 {
	 {
		 if (a.is_constant && b.is_constant)
			 return letter(1, a.num == b.num);
		 else if (a.is_constant)
		 {
			 if (a.num)
				 addclause(1,b.num);
			 else
				 addclause(1,-b.num);
		 }
		 else if (b.is_constant)
		 {
			 if (b.num)
				 addclause(1,a.num);
			 else
				 addclause(1,-a.num);
		 }
		 else
		 {
			 letter v;
			 addclause(3,v.num, -a.num, -b.num);
			 addclause(3,v.num, a.num, b.num);
			 addclause(3,-v.num, -a.num, b.num);
			 addclause(3,-v.num, a.num, -b.num);
			 return v;
		 }

	 }
 }
 letter EQ(letter  a, long long  b)
 {
	 if (a.is_constant)
		 return letter(1, a.num == b);
	 else
	 {
		 if (b)
			 addclause(1,a.num);
		 else
			 addclause(1,-a.num);
	 }
 }


letter::~letter()
{
}

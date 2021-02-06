#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <math.h>
#include <vector>
#include <iostream>
#include "Cipher.h"
#include "Cipher.cpp"

#define n 8
#define numb 16
using namespace std;

long long  numvar = 1;

vector < vector < long long > > allclouse;
void printfout()
{
	vector < long long  > newvec;
	int vector_size;
	long long big_size;
	big_size = allclouse.size();
	printf("p cnf %lld %lld\n", numvar - 1, big_size);
	for (long long i = 0; i < big_size; i++)
	{
		newvec = allclouse.at(i);
		vector_size = newvec.size();
		for (int j = 0; j < vector_size; j++)
			printf("%lld ", newvec.at(j));
		printf("0\n");
	}
}

/*void addclause(long long  a)
	{
		vector < long long  > newvec;
		newvec.push_back(a);
		allclouse.push_back(newvec);
	}
void addclause(long long  a, long long  b)
	{
		vector < long long  > newvec;
		newvec.push_back(a);
		newvec.push_back(b);
		allclouse.push_back(newvec);
	}
void addclause(long long  a, long long  b, long long  v)
	{
		vector < long long  > newvec;
		newvec.push_back(a);
		newvec.push_back(b);
		newvec.push_back(v);
		allclouse.push_back(newvec);
	}*/
#include "letter.h"
#include "functions.h"



void initial(letter**f, long long *pw)
{
	for (long long k = 0; k < n; k++)
		for (long long x = 0; x < *pw; x++)
			f[k][x] = letter(1, countfunk(x));

}

void make_cnf_for_funk()
{
	freopen("cnf.txt", "w", stdout);
		long long  k;//счетчик функций
		long long  x;//счетчик количества переменных для функции
		long long  s;//счетчик битов переменной функции
		letter a[n][n] ;//массив номеров, которые должен вычислить sat
		letter  b[n] ;//массив номеров для sat 2.0
		letter *f[n];//масив значений функции
		long long  pw = pow(2, n);
		for (long long i = 0; i < n; i++)
			f[i] = (letter*)calloc(pw, sizeof(letter));
		initial(f, &pw);

		for (k = 0; k < n; k++)
			for (x = 0; x < pw; x++)
			{
				letter  v=letter(1,(long long)0);//новый номер
				for (s = 0; s < n; s++)
					 v = v ^ ( getbit(x,s) & a[k][s] ); 
				v = v ^ b[k];
				v=EQ(v, f[k][x]);
			}
		printfout();
		for (long long i = 0; i < n; i++)
			free(f[i]);
}



#include "Cipher.h"
// #define FF( A )  A[21] ^ A[20]		/* from A5/1 */
#define F19( A ) A[18]^A[17]^A[16]^A[13]
#define F22( A ) A[21]^A[20]
#define F23( A ) A[22]^A[21]^A[20]^A[7]

//#define OUTPUT_LENGHT 50

void found_LRS_answer_one(int number)
	{
	int NUM = 23;
	const int count =22;
	LRS<letter> ar( count );
	letter *gl;
	gl = (letter*)calloc(number, sizeof(letter));//выходной
	for (int i = 0; i < number; i++)
		gl[i] = ar.shift( F22( ar.LRS_ar ));
	int *gi = (int*)calloc(number, sizeof(int));
	int b[count] = {0,1,1,1,0,1,1,1,0,0,1,1,0,1,1,0,0,0,1,0,1,1};
	for (int i = 0; i < number; i++)
	{
		gi[i] = b[count-1];
		int tmp;
		//tmp = F22( b,1);
		tmp = F22(b);
		for (int j = count - 2; j >= 0; j--)
			b[j + 1] = b[j];
		b[0] = tmp;
	}
	for (int i = 0; i < number; i++)
		gl[i]=EQ(gl[i],gi[i]);
	free(gi);
	free(gl);
}

void found_LRS_answer_two(int number)
{
	int NUM = 23;
	const int count1 = 19;
	const int count2 = 22;
	LRS<letter> lar1(count1);
	LRS<letter> lar2(count2);

	letter *gl1;
	letter *gl2;
	gl1 = (letter*)calloc(number, sizeof(letter));
	gl2 = (letter*)calloc(number, sizeof(letter));
	for (int i = 0; i < number; i++)
		gl1[i] = lar1.shift(F19(lar1.LRS_ar));
	for (int i = 0; i < number; i++)
		gl2[i] = lar2.shift(F22(lar2.LRS_ar));
	letter *gl;
	gl = (letter*)calloc(number, sizeof(letter));
	for (int i = 0; i < number; i++)
		gl[i] = gl1[i] ^ gl2[i];

	int a1[count1] = { 0,1,0,0,1,1,0,0,1,0,1,0,1,0,1,0,1,1};
	int a2[count2] = { 0,1,1,1,0,1,1,1,0,0,1,1,0,1,1,0,0,0,1,0,1,1 };
	LRS<int> iar1(count1, a1);
	LRS<int> iar2(count2, a2);
	int *gi1 = (int*)calloc(number, sizeof(int));
	int *gi2 = (int*)calloc(number, sizeof(int));
	int *gi = (int*)calloc(number, sizeof(int));
	for (int i = 0; i < number; i++)
		gi1[i] = iar1.shift(F19(iar1.LRS_ar));
	for (int i = 0; i < number; i++)
		gi2[i] = iar2.shift(F22(iar2.LRS_ar));
	for (int i = 0; i < number; i++)
		gi[i] = gi1[i] ^ gi2[i];

	for (int i = 0; i < number; i++)
		EQ (gl[i],gi[i]);
	//освобождение памяти
	{
		free(gl1);
		free(gl2);
		free(gl);
		free(gi1);
		free(gi2);
		free(gi);
	}
}

void found_LRS_answer_three(int number)
{
	int NUM = 23;
	const int count1 = 19;
	const int count2 = 22;
	const int count3 = 23;
	LRS<letter> lar1(count1);
	LRS<letter> lar2(count2);
	LRS<letter> lar3(count3);

	//регистры сдвигов по каждому регистру шифра  ~FEEDBACK
	letter *gl1;
	letter *gl2;
	letter *gl3;
	gl1 = (letter*)calloc(number, sizeof(letter));
	gl2 = (letter*)calloc(number, sizeof(letter));
	gl3 = (letter*)calloc(number, sizeof(letter));

	for (int i = 0; i < number; i++)
		gl1[i] = lar1.shift(F19(lar1.LRS_ar));
	for (int i = 0; i < number; i++)
		gl2[i] = lar2.shift(F22(lar2.LRS_ar));
	for (int i = 0; i < number; i++)
		gl3[i] = lar3.shift(F23(lar3.LRS_ar));

	//выходной бит работы шифра ~OUTPUT	
	letter *gl;
	gl = (letter*)calloc(number, sizeof(letter));
	for (int i = 0; i < number; i++)
		gl[i] = gl1[i] ^ gl2[i] ^ gl3[i];

	int a1[count1] = { 1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1 };
	int a2[count2] = { 1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1 };
	int a3[count3] = { 1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0 };

	LRS<int> iar1(count1, a1);
	LRS<int> iar2(count2, a2);
	LRS<int> iar3(count3, a3);

	int *gi1 = (int*)calloc(number, sizeof(int));
	int *gi2 = (int*)calloc(number, sizeof(int));
	int *gi3 = (int*)calloc(number, sizeof(int));
	int *gi = (int*)calloc(number, sizeof(int));

	for (int i = 0; i < number; i++)
		gi1[i] = iar1.shift(F19(iar1.LRS_ar));
	for (int i = 0; i < number; i++)
		gi2[i] = iar2.shift(F22(iar2.LRS_ar));
	for (int i = 0; i < number; i++)
		gi3[i] = iar3.shift(F23(iar3.LRS_ar));

	for (int i = 0; i < number; i++)
		gi[i] = gi1[i] ^ gi2[i] ^ gi3[i];
	for (int i = 0; i < number; i++)
		EQ(gl[i], gi[i]);
	//освобождение памяти
	{
		free(gl1);
		free(gl2);
		free(gl3);
		free(gl);
		free(gi1);
		free(gi2);
		free(gi3);
		free(gi);
	}
}

void breakA5(int number)
{
	const int count1 = 19;
	const int count2 = 22;
	const int count3 = 23;
	LRS<letter> lar1(count1);
	LRS<letter>lar2(count2);
	LRS<letter> lar3(count3);
	
	letter *gl1;
	letter *gl2;
	letter *gl3;
	letter *gl;
	gl1 = (letter*)calloc(number, sizeof(letter));
	gl2 = (letter*)calloc(number, sizeof(letter));
	gl3 = (letter*)calloc(number, sizeof(letter));
	gl = (letter*)calloc(number, sizeof(letter));
	for (int i = 0; i < number; i++){
		letter tmp;
		if (i > 0)
			//danial_push(lar1, lar2, lar3);
		tmp = majority(lar1[8], lar2[10], lar3[10]);
		lar1.shift_if( lar1[8] == tmp, F19(lar1.LRS_ar) );
		lar2.shift_if(lar2[10] == tmp, F22(lar2.LRS_ar));
		lar3.shift_if(lar3[10] == tmp, F23(lar3.LRS_ar));
		gl1[i] = lar1.last();
		gl2[i] = lar2.last();
		gl3[i] = lar3.last();
		gl[i] = gl1[i] ^ gl2[i] ^ gl3[i];	
	}

/*
		IF( lar1[8] == tmp );
			lar1.shift(F19(lar1.LRS_ar));
		ELSE;
		ENDIF;
		*/
	int a1[count1] = { 1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1 };
	int a2[count2] = { 1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1 };
	int a3[count3] = { 1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0 };
	LRS<int> iar1(count1, a1);
	LRS<int> iar2(count2, a2);
	LRS<int> iar3(count3, a3);

	int *gi1 = (int*)calloc(number, sizeof(int));
	int *gi2 = (int*)calloc(number, sizeof(int));
	int *gi3 = (int*)calloc(number, sizeof(int));
	int *gi = (int*)calloc(number, sizeof(int));

	int tmpi;
	tmpi = count_A5_funk(iar1[8], iar2[10], iar3[10]);
	for (int i = 0; i < number; i++)
	{
		if (iar1[8]== tmpi)
			gi1[i] = iar1.shift(F19(iar1.LRS_ar));
		else 	gi1[i] = iar1[count1 -1];

		if (iar2[10]== tmpi)
			gi2[i] = iar2.shift(F22(iar2.LRS_ar));
		else 	gi2[i] = iar2[count2 - 1];;

		if (iar3[10] == tmpi)
			gi3[i] = iar3.shift(F23(iar3.LRS_ar));
		else
			gi3[i] = iar3[count3 - 1];;
	
		gi[i] = gi1[i] ^ gi2[i] ^ gi3[i];
			tmpi = count_A5_funk(iar1[8], iar2[10], iar3[10]);

	}
	for (int i = 0; i < number; i++)
		EQ(gl[i], gi[i]);	
	//освобождение памяти
	{
		free(gl1);
		free(gl2);
		free(gl3);
		free(gl);
		free(gi1);
		free(gi2);
		free(gi3);
		free(gi);
	}
}


FILE *stdin_old;

void guess_determ_atack(int number)
{
	const int count1 = 19;
	const int count2 = 22;
	const int count3 = 23;
	LRS<letter> lar1(count1);
	LRS<letter> lar2(count2);
	LRS<letter> lar3(count3);
	int a1[count1] = { 1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1 };
	int a2[count2] = { 1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1 };
	int a3[count3] = { 1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0 };

	for (int i = -4; i < 8; i++)
	{
		//#define EQ(a,b) EQ(a,b)
		#define EQ(a,b) a = letter(1,b)
		EQ(lar1[ 8 - i], a1[ 8 - i]);
		EQ(lar2[10 - i], a2[10 - i]);
		EQ(lar3[10 - i], a3[10 - i]);
		#undef EQ
	}

	letter *gl1;
	letter *gl2;
	letter *gl3;
	letter *gl;

	gl1 = (letter*)calloc(number, sizeof(letter));
	gl2 = (letter*)calloc(number, sizeof(letter));
	gl3 = (letter*)calloc(number, sizeof(letter));
	gl = (letter*)calloc(number, sizeof(letter));

	for (int i = 0; i < number; i++)
	{

		letter tmp;
		tmp = majority(lar1[8], lar2[10], lar3[10]);
		lar1.shift_if(lar1[8] == tmp, F19(lar1.LRS_ar));
		lar2.shift_if(lar2[10] == tmp, F22(lar2.LRS_ar));
		lar3.shift_if(lar3[10] == tmp, F23(lar3.LRS_ar));
		gl1[i] = lar1.last();
		gl2[i] = lar2.last();
		gl3[i] = lar3.last();
		gl[i] = gl1[i] ^ gl2[i] ^ gl3[i];
	}
	LRS<int> iar1(count1, a1);
	LRS<int> iar2(count2, a2);
	LRS<int> iar3(count3, a3);

	int *gi1 = (int*)calloc(number, sizeof(int));
	int *gi2 = (int*)calloc(number, sizeof(int));
	int *gi3 = (int*)calloc(number, sizeof(int));
	int *gi = (int*)calloc(number+100, sizeof(int));

	int tmpi;


	for (int i = 0; i < number; i++)
	{
		tmpi = count_A5_funk(iar1[8], iar2[10], iar3[10]);
		if (iar1[8] == tmpi)  iar1.shift(F19(iar1.LRS_ar));
		gi1[i] = iar1[count1 - 1];
		if (iar2[10] == tmpi) iar2.shift(F22(iar2.LRS_ar));
		gi2[i] = iar2[count2 - 1];
		if (iar3[10] == tmpi) iar3.shift(F23(iar3.LRS_ar));
		gi3[i] = iar3[count3 - 1];
		gi[i] = gi1[i] ^ gi2[i] ^ gi3[i];
	}

	for (int i = 0; i < number; i++) {
		EQ(gl[i], gi[i]);
	}

	for (int i = 0; i < lar1.lenght; i++) {
	}

	//освобождение памяти
	{
		free(gl1);
		free(gl2);
		free(gl3);
		free(gl);
		free(gi1);
		free(gi2);
		free(gi3);
		free(gi);
	}

}


/*int  main()
{
	//stdin_old = freopen("C:\\cygwin64\\home\\123\\danial.txt", "w", stdout);
	//int number=40;
	//breakA5(number);
	//printfout();
	
	grain<letter> gl(1);
	printf("%ld\n", numvar);
	grain<int> gl_i(8);
	printf("%ld\n", numvar);

	//gl.init_funk();
	//for (int i; i < 100; i++)
	//	gl.step();
	//gl.init_funk();
	//gl.print();
	//printf("\n");
	int key_i[80];
	for (int i = 0; i < 80; i++)
		key_i[i] = rand()%2;
	printf("%ld\n", numvar);
	//letter key[80];

	int key_vec_i[64];
	for (int i = 0; i < 64; i++)
		key_vec_i[i] = rand()%2;
	printf("%ld\n", numvar);
	//letter key_vec[64];

	//gl.init(key, key_vec);
	gl_i.init(key_i, key_vec_i);
	printf("%ld\n", numvar);
	int* mask = make_mask_int(144,142);
	printf("%ld\n", numvar);
	//letter key[144];
	//gl_i.init_reg(array_i);
	gl.init(key_i, key_vec_i,mask);
	printf("%ld\n", numvar);
		gl.step(gl.count_());
		printf("%ld\n", numvar);
		gl_i.step(gl_i.count_());
		printf("%ld\n", numvar);
	//gl_i.get_gamma();
	//printf("\n\n");
	//gl.get_gamma();


	for (int i = 0; i < gl.count_(); i++) {
		EQ(gl.get_gamma_bit(i), gl_i.get_gamma_bit(i));
	}
	printf("%ld\n", numvar);
	freopen("cnf.txt", "w", stdout);
	printfout();
	fflush(stdout);
	free (mask);
 	return 0; 
}
*/
int  main()
{
	grain<int> gl_i(80);
	int key_i[80] = 0;
	int key_vec_i[64] = 0;
	gl_i.init(key_i, key_vec_i);
	gl_i.step(gl_i.count_());
	gl_i.get_gamma();
	return 0;
}
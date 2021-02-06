#include "Cipher.h"

int* make_mask_int(int lenght,int num_1) {
	int* array = new int[lenght];
	for (int i = 0; i < lenght; i++)
		array[i] = 0;
	int k;
	for (int i = 0; i < num_1; i++)
	{
		k = rand() % lenght;
		while (array[k] == 1)
			k = rand() % lenght;
		array[k] = 1;
	}
	return array;
}

template <class T>
LRS<T>::LRS(int k){
	LRS_ar = new T[k];
	lenght = k;
}
template <class T>
LRS<T>::LRS(int k, T * a) {
	lenght = k;
	LRS_ar = a;
}
template <class T>
const T LRS<T>::last(){
	return LRS_ar[lenght - 1];
}
template <class T>
T& LRS<T>::operator[](int index){
	return LRS_ar[index];
}
template <class T>
T LRS<T>::shift(T a){
	for (int i = lenght - 2; i >= 0; i--)
		LRS_ar[i + 1] = LRS_ar[i];
	LRS_ar[0] = a;
	return LRS_ar[lenght - 1];
}
template <class T>
void LRS<T>::shift_if(T cond, T a){
	if (cond.is_constant == 0)
	{
		for (int i = lenght - 2; i >= 0; i--)
			LRS_ar[i + 1] = (LRS_ar[i] & cond) | (cond.neg() & LRS_ar[i + 1]); // что делать с или?
		LRS_ar[0] = (a & cond) | ((LRS_ar[0]) & cond.neg());
	}
	else
	{
		if (cond.num) shift(a);
	}
}
template <class T>
LRS<T> LRS<T>::copy() {
	T *tmp = new T[lenght];
	for (int i = 0; i < lenght; i++) {
		tmp[i] = LRS_ar[i];
	}
	return *(new LRS(lenght, tmp));
}
template <class T>
LRS<T>::~LRS(){}


/*
LRS::LRS(int k)
{
	LRS_ar = new letter[k];
	lenght = k;
}

LRS::LRS(int k, letter * a) {
	lenght = k;
	LRS_ar = a;
}
const letter LRS::last()
{
	return LRS_ar[lenght - 1];
}
letter& LRS::operator[](int index)
{
	return LRS_ar[index];
}
letter LRS::shift(letter a)
{
	for (int i = lenght - 2; i >= 0; i--)
		LRS_ar[i + 1] = LRS_ar[i];
	LRS_ar[0] = a;
	return LRS_ar[lenght - 1];
}
void LRS::shift_if(letter cond, letter a)
{
	if (cond.is_constant == 0)
	{
		for (int i = lenght - 2; i >= 0; i--)
			LRS_ar[i + 1] = (LRS_ar[i] & cond) | (cond.neg() & LRS_ar[i + 1]); // что делать с или?
		LRS_ar[0] = (a & cond) | ((LRS_ar[0]) & cond.neg());
	}
	else
	{
		if (cond.num) shift(a);
	}
}
LRS LRS::copy() {
	letter *tmp = new letter[lenght];
	for (int i = 0; i < lenght; i++) {
		tmp[i] = LRS_ar[i];
	}
	return *(new LRS(lenght, tmp));
}


LRS::~LRS()
{
}

LRS_INT::LRS_INT(int k, int *ar)
{
	LRS_ar = ar;//желательно копировать, а не просто кидать указатель
	lenght = k;
}
LRS_INT::LRS_INT(int k) {
	LRS_ar = new int(k);
	lenght = k;
}
int& LRS_INT::operator[](int index)
{
	return LRS_ar[index];
}
int LRS_INT::shift(int a)
{
	for (int i = lenght - 2; i >= 0; i--)
		LRS_ar[i + 1] = LRS_ar[i];
	LRS_ar[0] = a;
	return LRS_ar[lenght - 1];
}

LRS_INT::~LRS_INT()
{
}
*/


template <class T>
bivium<T>::bivium(int k) {
	sr0 = new LRS<T>(93);
	sr1 = new LRS<T>(84);
	out = new LRS<T>(k);
}

template <class T>
bivium<T>::~bivium() {
	delete[] sr0;
	delete[] sr1;
	delete[] out;
}
template <class T>
void bivium<T>::step() {
	static int i = 0;
	out->LRS_ar[i++] = output();
	sr0->shift(feedback_sr0());
	sr1->shift(feedback_sr1());
}
template <class T>
T bivium<T>::feedback_sr0() { return sr1->LRS_ar[0] ^ sr1->LRS_ar[15] ^ sr1->LRS_ar[2] & sr1->LRS_ar[1] ^ sr0->LRS_ar[24]; }
template <class T>
T bivium<T>::feedback_sr1() { return sr0->LRS_ar[27] ^ sr0->LRS_ar[0] ^ sr0->LRS_ar[2] & sr0->LRS_ar[1] ^ sr1->LRS_ar[6]; }
template <class T>
T bivium<T>::output() { return sr0->LRS_ar[0] ^ sr0->LRS_ar[27] ^ sr1->LRS_ar[15] ^ sr1->LRS_ar[0]; }

template <class T>
crypto1<T>::crypto1(int k) {
	sr0 = new LRS<T>(48);
	out = new LRS<T>(k);
}
template <class T>
crypto1<T>::~crypto1() {
	delete[] sr0;
	delete[] out;
}
template <class T>
void crypto1<T>::step() {
	static int i = 0;
	out->LRS_ar[i++] = output();
	sr0->shift(feedback_sr0());
}
template <class T>
T crypto1<T>::f0() { return sr0->LRS_ar[9] ^ sr0->LRS_ar[11] ^ sr0->LRS_ar[11] & sr0->LRS_ar[9] ^ sr0->LRS_ar[13] & sr0->LRS_ar[9] ^ sr0->LRS_ar[13] & sr0->LRS_ar[11] ^ sr0->LRS_ar[15] & sr0->LRS_ar[9] ^ sr0->LRS_ar[15] & sr0->LRS_ar[13] ^ sr0->LRS_ar[15] & sr0->LRS_ar[13] & sr0->LRS_ar[9] ^ sr0->LRS_ar[15] & sr0->LRS_ar[13] & sr0->LRS_ar[11]; }
template <class T>
T crypto1<T>::f1() { return sr0->LRS_ar[19] & sr0->LRS_ar[17] ^ sr0->LRS_ar[21] ^ sr0->LRS_ar[21] & sr0->LRS_ar[17] ^ sr0->LRS_ar[21] & sr0->LRS_ar[19] ^ sr0->LRS_ar[21] & sr0->LRS_ar[19] & sr0->LRS_ar[17] ^ sr0->LRS_ar[23] & sr0->LRS_ar[17] ^ sr0->LRS_ar[23] & sr0->LRS_ar[19] ^ sr0->LRS_ar[23] & sr0->LRS_ar[21] & sr0->LRS_ar[17] ^ sr0->LRS_ar[23] & sr0->LRS_ar[21] & sr0->LRS_ar[19]; }
template <class T>
T crypto1<T>::f2() { return sr0->LRS_ar[27] & sr0->LRS_ar[25] ^ sr0->LRS_ar[29] ^ sr0->LRS_ar[29] & sr0->LRS_ar[25] ^ sr0->LRS_ar[29] & sr0->LRS_ar[27] ^ sr0->LRS_ar[29] & sr0->LRS_ar[27] & sr0->LRS_ar[25] ^ sr0->LRS_ar[31] & sr0->LRS_ar[25] ^ sr0->LRS_ar[31] & sr0->LRS_ar[27] ^ sr0->LRS_ar[31] & sr0->LRS_ar[29] & sr0->LRS_ar[25] ^ sr0->LRS_ar[31] & sr0->LRS_ar[29] & sr0->LRS_ar[27]; }
template <class T>
T crypto1<T>::f3() { return sr0->LRS_ar[33] ^ sr0->LRS_ar[35] ^ sr0->LRS_ar[35] & sr0->LRS_ar[33] ^ sr0->LRS_ar[37] & sr0->LRS_ar[33] ^ sr0->LRS_ar[37] & sr0->LRS_ar[35] ^ sr0->LRS_ar[39] & sr0->LRS_ar[33] ^ sr0->LRS_ar[39] & sr0->LRS_ar[37] ^ sr0->LRS_ar[39] & sr0->LRS_ar[37] & sr0->LRS_ar[33] ^ sr0->LRS_ar[39] & sr0->LRS_ar[37] & sr0->LRS_ar[35]; }
template <class T>
T crypto1<T>::f4() { return sr0->LRS_ar[43] & sr0->LRS_ar[41] ^ sr0->LRS_ar[45] ^ sr0->LRS_ar[45] & sr0->LRS_ar[41] ^ sr0->LRS_ar[45] & sr0->LRS_ar[43] ^ sr0->LRS_ar[45] & sr0->LRS_ar[43] & sr0->LRS_ar[41] ^ sr0->LRS_ar[47] & sr0->LRS_ar[41] ^ sr0->LRS_ar[47] & sr0->LRS_ar[43] ^ sr0->LRS_ar[47] & sr0->LRS_ar[45] & sr0->LRS_ar[41] ^ sr0->LRS_ar[47] & sr0->LRS_ar[45] & sr0->LRS_ar[43]; }
template <class T>
T crypto1<T>::feedback_sr0() { return sr0->LRS_ar[0] ^ sr0->LRS_ar[5] ^ sr0->LRS_ar[9] ^ sr0->LRS_ar[10] ^ sr0->LRS_ar[12] ^ sr0->LRS_ar[14] ^ sr0->LRS_ar[15] ^ sr0->LRS_ar[17] ^ sr0->LRS_ar[19] ^ sr0->LRS_ar[24] ^ sr0->LRS_ar[25] ^ sr0->LRS_ar[27] ^ sr0->LRS_ar[29] ^ sr0->LRS_ar[35] ^ sr0->LRS_ar[39] ^ sr0->LRS_ar[41] ^ sr0->LRS_ar[42] ^ sr0->LRS_ar[43]; }
template <class T>
T crypto1<T>::output() { return f0() ^ f2()&f0() ^ f3()&f0() ^f3()&f1()&f0() ^ f3()&f2()&f1() ^f4() ^ f4()&f0() ^f4()&f1()&f0() ^f4()&f2()&f1()&f0() ^f4()&f3() ^ f4()&f3()&f0() ^ f4()&f3()&f1() ^f4()&f3()&f2()&f1(); }



template <class T>
grain<T>::grain(int k) {
	//sr0 = (LRS < T>*)calloc(80, sizeof(LRS < T>));
	sr0 = new LRS<T>(80, (T*)calloc(80, sizeof(T)));
	//sr1 = (LRS < T>*)calloc(80, sizeof(LRS < T>));
	sr1 = new LRS<T>(80,(T*)calloc(80, sizeof(T)));
	//out = (LRS < T>*)calloc(k, sizeof(LRS < T>));
	out = new LRS<T>(k, (T*)calloc(k, sizeof(T)));
	count = k;
}

template <class T>
grain<T>::~grain() {
	delete sr0;
	delete sr1;
	delete out;
}
template <class T>
void grain<T>::step(int c) {
	for (int i = 0; i < c; i++){
		out->LRS_ar[i] = output();
		sr0->shift(feedback_sr0());
		sr1->shift(feedback_sr1());
	}
}
template <class T>
void grain<T>::print() {
	for (int i = 0; i < 80; i++)
		printf("%lld ", sr0->LRS_ar[i].num);
	printf("\n");
	for (int i = 0; i < 80; i++)
		printf("%lld ", sr1->LRS_ar[i].num);
	printf("\n");

}

template <>
void grain<letter>::init(int*key, int*key_vector,int*mask){
	for (int i = 0; i < 80; i++)
		if (mask[i] == 1)
			sr1->LRS_ar[i] = letter(1,key[i]);
	for (int i = 0; i < 64; i++)
		if (mask[i+80] == 1)
			sr0->LRS_ar[i] = letter(1,key_vector[i]);
	for (int i = 64; i < 80; i++)
			sr0->LRS_ar[i] = letter(1, (long long) 1);
	//printf("array is:");
	//print();
	for (int i = 0; i < iterration_count_; i++)
	{
		sr1->shift(feedback_init_sr1());
		//sr1->LRS_ar[i % 80] = feedback_init_sr1();
		sr0->shift(feedback_init_sr0());
		//sr0->LRS_ar[i % 80] = feedback_init_sr0();
	}
}

template <>
void grain<int>::init(int* key, int* key_vector,int*mask) {
	for (int i = 0; i < 80; i++)
		sr1->LRS_ar[i] = key[i];
	for (int i = 0; i < 64; i++)
		sr0->LRS_ar[i] = key_vector[i];
	for (int i = 64; i < 80; i++)
		sr0->LRS_ar[i] = 1;
	//printf("array is:");
	//print();
	for (int i = 0; i < iterration_count_; i++)
	{
		sr1->shift(feedback_init_sr1());
		//sr1->LRS_ar[i % 80] = feedback_init_sr1();
		sr0->shift(feedback_init_sr0());
		//sr0->LRS_ar[i % 80] = feedback_init_sr0();
	}
}

template <>
void  grain<int>::init_reg(int*key,int*mask) {
	for (int i = 0; i < 80; i++)
		sr1->LRS_ar[i] = key[i];
	for (int i = 80; i < 144; i++)
		sr0->LRS_ar[i-80] = key[i];
	for (int i = 64; i < 80; i++)
		sr0->LRS_ar[i - 80] = 1;
}

template <>
void  grain<letter>::init_reg(int* key,int* mask) {
	for (int i = 0; i < 80; i++)
		if (mask[i] == 1)
			sr1->LRS_ar[i] = letter(1,key[i]);
	for(int i=80;i<144;i++)
		if (mask[i] == 1)
			sr0->LRS_ar[i-80] = letter(1,key[i]);
}

template <class T>
int grain<T>::count_() {
	return count;
}


template <>
void grain<letter>::get_gamma() {
	for (int i = 0; i < count; i++)
		printf("%lld ",out->LRS_ar[i].num);
}

template <>
void grain<int>::get_gamma() {
	for (int i = 0; i < count; i++)
		printf("%d ", out->LRS_ar[i]);
}

template <class T>
T grain<T>::get_gamma_bit(int i) {
	return out->LRS_ar[i];
}
/*template <>
letter grain<letter>::get_gamma_bit(int i) {
	return out->LRS_ar[i];
}*/
template <class T>
T grain<T>::f0() { return sr0->LRS_ar[1] ^ sr0->LRS_ar[2] ^ sr0->LRS_ar[4] ^ sr0->LRS_ar[10] ^ sr0->LRS_ar[31] ^ sr0->LRS_ar[43] ^ sr0->LRS_ar[56] ^ sr1->LRS_ar[25] ^ sr0->LRS_ar[63] ^ sr1->LRS_ar[3] & sr1->LRS_ar[64] ^ sr1->LRS_ar[46] & sr1->LRS_ar[64] ^ sr1->LRS_ar[64] & sr0->LRS_ar[63] ^ sr1->LRS_ar[3] & sr1->LRS_ar[25] & sr1->LRS_ar[46] ^ sr1->LRS_ar[3] & sr1->LRS_ar[46] & sr1->LRS_ar[64] ^ sr1->LRS_ar[3] & sr1->LRS_ar[46] & sr0->LRS_ar[63] ^ sr1->LRS_ar[25] & sr1->LRS_ar[46] & sr0->LRS_ar[63] ^ sr1->LRS_ar[46] & sr1->LRS_ar[64] & sr0->LRS_ar[63]; }
template <class T>
T grain<T>::feedback_init_sr0() { 
	return feedback_sr0() ^ f0();
}
template <class T>
T grain<T>::feedback_init_sr1() { return sr1->LRS_ar[62] ^ sr1->LRS_ar[51] ^ sr1->LRS_ar[38] ^ sr1->LRS_ar[23] ^ sr1->LRS_ar[13] ^ sr1->LRS_ar[0] ^ f0(); }
template <class T>
T grain<T>::feedback_sr0()
{
	T a = sr1->LRS_ar[0] ^ sr0->LRS_ar[62] ^ sr0->LRS_ar[60] ^ sr0->LRS_ar[52] ^ sr0->LRS_ar[45] ^ sr0->LRS_ar[37] ^ sr0->LRS_ar[33] ^ sr0->LRS_ar[28] ^ sr0->LRS_ar[21] ^ sr0->LRS_ar[14] ^ sr0->LRS_ar[9] ^ sr0->LRS_ar[0] ^ sr0->LRS_ar[63] & sr0->LRS_ar[60] ^ sr0->LRS_ar[37] & sr0->LRS_ar[33] ^ sr0->LRS_ar[15] & sr0->LRS_ar[9] ^ sr0->LRS_ar[60] & sr0->LRS_ar[52] & sr0->LRS_ar[45] ^ sr0->LRS_ar[33] & sr0->LRS_ar[28] & sr0->LRS_ar[21];
	T b = sr0->LRS_ar[63] & sr0->LRS_ar[45] & sr0->LRS_ar[28] & sr0->LRS_ar[9] ^ sr0->LRS_ar[60] & sr0->LRS_ar[52] & sr0->LRS_ar[37] & sr0->LRS_ar[33] ^ sr0->LRS_ar[63] & sr0->LRS_ar[60] & sr0->LRS_ar[21] & sr0->LRS_ar[15] ^ sr0->LRS_ar[63] & sr0->LRS_ar[60] & sr0->LRS_ar[52] & sr0->LRS_ar[45] & sr0->LRS_ar[37] ^ sr0->LRS_ar[33] & sr0->LRS_ar[28] & sr0->LRS_ar[21] & sr0->LRS_ar[15] & sr0->LRS_ar[9] ^ sr0->LRS_ar[52] & sr0->LRS_ar[45] & sr0->LRS_ar[37] & sr0->LRS_ar[33] & sr0->LRS_ar[28] & sr0->LRS_ar[21];
	return a ^ b;
}

template <class T>
T grain<T>::feedback_sr1() { return sr1->LRS_ar[62] ^ sr1->LRS_ar[51] ^ sr1->LRS_ar[38] ^ sr1->LRS_ar[23] ^ sr1->LRS_ar[13] ^ sr1->LRS_ar[0]; }
template <class T>
T grain<T>::output() {
	T a = sr0->LRS_ar[1] ^ sr0->LRS_ar[2] ^ sr0->LRS_ar[4] ^ sr0->LRS_ar[10] ^ sr0->LRS_ar[31] ^ sr0->LRS_ar[43] ^ sr0->LRS_ar[56] ^ sr1->LRS_ar[25] ^ sr0->LRS_ar[63] ^ sr1->LRS_ar[3] & sr1->LRS_ar[64] ^ sr1->LRS_ar[46] & sr1->LRS_ar[64] ^ sr1->LRS_ar[64] & sr0->LRS_ar[63];
	T b=sr1->LRS_ar[3] & sr1->LRS_ar[25] & sr1->LRS_ar[46] ^ sr1->LRS_ar[3] & sr1->LRS_ar[46] & sr1->LRS_ar[64] ^ sr1->LRS_ar[3] & sr1->LRS_ar[46] & sr0->LRS_ar[63] ^ sr1->LRS_ar[25] & sr1->LRS_ar[46] & sr0->LRS_ar[63] ^ sr1->LRS_ar[46] & sr1->LRS_ar[64] & sr0->LRS_ar[63]; 
return a ^ b;
}
/*
hitag2::hitag2(int k){
	sr0 = new LRS(48);
	sr0_int = new LRS_INT(48);
	out = new LRS(k);
	out_int = new LRS_INT(k);
}
hitag2::~hitag2() {
	delete[] sr0;
	delete[] sr0_int;
	delete[] out;
	delete[] out_int;
}
void hitag2::step() {
	static int i = 0;
	out->LRS_ar[i++] = output();
	sr0->shift(feedback_sr0());
}
letter hitag2::f0() { return ^sr0[2] & sr0[3] & sr0[5] ^ sr0[2] & sr0[5] ^ sr0[2] & sr0[6] ^ sr0[3] & sr0[5] ^ sr0[2] ^ sr0[3] ^ sr0[6]; }

letter hitag2::f1() { return ^sr0[8] & sr0[12] & sr0[15] ^ sr0[8] & sr0[14] & sr0[15] ^ sr0[12] & sr0[14] & sr0[15] ^ sr0[8] & sr0[12] ^ sr0[8] & sr0[14] ^ sr0[12] & sr0[14] ^ sr0[8] ^ sr0[12] ^ sr0[15]; }

letter hitag2::f2() { return ^sr0[17] & sr0[21] & sr0[26] ^ sr0[17] & sr0[23] & sr0[26] ^ sr0[21] & sr0[23] & sr0[26] ^ sr0[17] & sr0[21] ^ sr0[17] & sr0[23] ^ sr0[21] & sr0[23] ^ sr0[17] ^ sr0[21] ^ sr0[26]; }

letter hitag2::f3() { return ^sr0[28] & sr0[29] & sr0[33] ^ sr0[28] & sr0[31] & sr0[33] ^ sr0[29] & sr0[31] & sr0[33] ^ sr0[28] & sr0[29] ^ sr0[28] & sr0[31] ^ sr0[29] & sr0[31] ^ sr0[28] ^ sr0[29] ^ sr0[33]; }

letter hitag2::f4() { return ^sr0[34] & sr0[43] & sr0[44] ^ sr0[34] & sr0[44] ^ sr0[34] & sr0[46] ^ sr0[43] & sr0[44] ^ sr0[34] ^ sr0[43] ^ sr0[46]; }

letter hitag2::feedback_sr0() { return sr0->LRS_ar[0] ^ sr0->LRS_ar[2] ^ sr0->LRS_ar[3] ^ sr0->LRS_ar[6] ^ sr0->LRS_ar[7] ^ sr0->LRS_ar[8] ^ sr0->LRS_ar[16] ^ sr0->LRS_ar[22] ^ sr0->LRS_ar[23] ^ sr0->LRS_ar[26] ^ sr0->LRS_ar[30] ^ sr0->LRS_ar[41] ^ sr0->LRS_ar[42] ^ sr0->LRS_ar[43] ^ sr0->LRS_ar[46] ^ sr0->LRS_ar[47]; }

letter hitag2::output() { return f0()&f1()&f2()&f4() ^ f0()&f1()&f3()&f4() ^ f0()&f2()&f3() ^ f0()&f3()&f4() ^ f1()&f2()&f3() ^ f1()&f2()&f4() ^ f2()&f3()&f4() ^ f0()&f1() ^ f1()&f2() ^ f1()&f3() ^ f1()&f4() ^ f2()&f4() ^ f3()&f4() ^ f1() ^ f3() ^ ; }//x?

*/
template <class T>
trivium<T>::trivium(int k) {
	sr0 = new LRS<T>(93);
	sr1 = new LRS<T>(84);
	sr2 = new LRS<T>(11);
	out = new LRS<T>(k);
}
template <class T>
trivium<T>::~trivium() {
	delete[] sr0;
	delete[] sr1;
	delete[] sr2;
	delete[] out;
}
template <class T>
void trivium<T>::step() {
	static int i = 0;
	out->LRS_ar[i++] = output();
	sr0->shift(feedback_sr0());
	sr1->shift(feedback_sr1());
	sr2->shift(feedback_sr2());
}

template <class T>
T trivium<T>::feedback_sr0() { return sr2->LRS_ar[45] ^ sr2->LRS_ar[0] ^ sr2->LRS_ar[2] & sr2->LRS_ar[1] ^ sr0->LRS_ar[24]; }
template <class T>
T trivium<T>::feedback_sr1() { return sr0->LRS_ar[0] ^ sr0->LRS_ar[27] ^ sr0->LRS_ar[2] & sr0->LRS_ar[1] ^ sr1->LRS_ar[6]; }
template <class T>
T trivium<T>::feedback_sr2() { return sr1->LRS_ar[15] ^ sr1->LRS_ar[0] ^ sr1->LRS_ar[2] & sr1->LRS_ar[1] ^ sr2->LRS_ar[24]; }
template <class T>
T trivium<T>::output() { return sr0->LRS_ar[0] ^ sr0->LRS_ar[27] ^ sr1->LRS_ar[15] ^ sr1->LRS_ar[0] ^ sr2->LRS_ar[45] ^ sr2->LRS_ar[0]; }
//template <class T>
//T trivium<T>::output() { return sr0->LRS_ar[0] ^ sr0->LRS_ar[27] ^ sr1->LRS_ar[15] ^ sr1->LRS_ar[0] ^ sr2->LRS_ar[45] ^ sr2->LRS_ar[0]; }


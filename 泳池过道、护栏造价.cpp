#include<iostream>
using namespace std;
class yuan
{
	private:
		float radius;
	public:
		yuan(float r);
		float zhou();
		float area();
};
yuan::yuan(float r)
{
	radius=r;
}
float yuan::zhou()
{
	return 2*radius*3.14;
}
float yuan::area()
{
	return radius*radius*3.14;
}
main()
{
	float a;
	cout<<"��������Ӿ�صİ뾶��";
	cin>>a;
	yuan big(a+3);
	yuan small(a);
	cout<<"դ�������="<<big.zhou()*35<<endl;
	cout<<"���������="<<(big.area()-small.area())*20<<endl;
}

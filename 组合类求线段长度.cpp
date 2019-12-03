#include<iostream>
#include<cmath>
using namespace std;
class point
{
	private:
		float a,b;
	public:
		point(float x,float y);
		float getx();
		float gety();
		void output();
		
};
point::point(float x,float y)
{
	a=x;
	b=y;
}
float point::getx()
{
	return a;
}
float point::gety()
{
	return b;
}
class line
{
	private:
	point p1,p2;
	float d;
	public:
	line(point p,point q):p1(p),p2(q){};
	float getd();
} ;
float line::getd()
{
	float xx,yy;
	xx=p1.getx()-p2.getx();
	yy=p1.gety()-p2.gety();
	return sqrt(xx*xx+yy*yy); 
}
void point::output()
{
	cout<<"("<<a<<","<<b<<")"<<endl;
}
int main()
{
	float x1,x2,y1,y2,dis;
	cout<<"x1=";
	cin>>x1;
	cout<<"y1=";
	cin>>y1;
	cout<<"x2=";
	cin>>x2;
	cout<<"y2=";
	cin>>y2;
	point p(x1,y1);
	point q(x2,y2);
	cout<<"p";
	p.output();
	cout<<"q";
	q.output();
	line l(p,q);
	dis=l.getd();
	cout<<dis;

	return 0;
}










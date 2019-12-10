#include<iostream>
using namespace std;

class fenshu
{
	private:
		int fz,fm;
	public:
		fenshu(){ }
		fenshu(int a,int b)
		{
			fz=a;
			fm=b;
		}
		void output()
		{
			cout<<fz<<"/"<<fm<<endl;
		}
		fenshu add(fenshu f);
		int yf(int n,int m);
		
};
int fenshu::yf(int n,int m)
{
	int a;
	int i=1;
	while (i<=n || i<=m)
	{
		if(m%i==0 && n%i==0)
		a=i;
		i++;
	}
	return a;
}

fenshu fenshu::add(fenshu f)
{
	fenshu sum;
	sum.fz=fz*f.fm+fm*f.fz;
	sum.fm=fm*f.fm;
	int a=yf(sum.fz,sum.fm);
	sum.fz/=a;
	sum.fm/=a;
	return sum;
}

main()
{
	int x1,x2,y1,y2;
	cout<<"fz1=";
	cin>>x1;
	cout<<"fm1=";
	cin>>y1;
	cout<<"fz2=";
	cin>>x2;
	cout<<"fm2=";
	cin>>y2;
	fenshu f1(x1,y1),f2(x2,y2),f3;
	cout<<"f1=";
	f1.output();
	cout<<"f2=";
	f2.output();
	f3=f1.add(f2);
	cout<<"f3=";
	f3.output();
	
}

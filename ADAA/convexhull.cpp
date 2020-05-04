//凸包-Graham扫描
#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <iomanip>
#include <cstring>
#include <cstdio>
using namespace std;


struct Point
{
	double x;
	double y;
}p[40000];

//计算叉积，小于0说明p1在p2的逆时针方向(右边)，即p0p1的极角大于p0p2的极角
double cross_product(Point p0, Point p1, Point p2)
{
	return (p1.x - p0.x)*(p2.y - p0.y) - (p2.x - p0.x)*(p1.y - p0.y);
}

//计算距离
double dis(Point p1, Point p2)
{
	return sqrt((p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y));
}

bool com(const Point &p1, const Point &p2)
{
	double temp = cross_product(p[0], p1, p2);
	if (fabs(temp) < 1e-6)//极角相等按照距离从小到大排序
    {
        return dis(p[0], p1) < dis(p[0], p2);
    }
	else
    {
        return temp > 0;
    }
}

vector<Point> graham_scan(int n)
{
	vector<Point> ch;
	int top = 2;
	int index = 0;
	for (int i = 1; i < n; ++i)//选出Y坐标最小的点，若Y坐标相等，选择X坐标小的点
	{
		if (p[i].y < p[index].y || (p[i].y == p[index].y && p[i].x < p[index].x))
		{
			index = i;
		}
	}
	swap(p[0], p[index]);
	ch.push_back(p[0]);
	//按极角排序
	sort(p + 1, p + n, com);
	ch.push_back(p[1]);
	ch.push_back(p[2]);
	for (int i = 3; i < n; ++i)
	{
		while (top > 0 && cross_product(ch[top - 1], p[i], ch[top]) >= 0)
		{
			--top;
			ch.pop_back();
		}
		ch.push_back(p[i]);
		++top;
	}
	return ch;
}

//旋转卡壳法
double rotating_caliper(vector<Point> v)
{
	double max_dis = 0.0;
	int n = v.size();
	if (n == 2)
	{
		max_dis = dis(v[0], v[1]);
	}
	else
	{
		v.push_back(v[0]);
		int j = 2;
		for (int i = 0; i < n; ++i)
		{
			while (cross_product(v[i], v[i + 1], v[j]) < cross_product(v[i], v[i + 1], v[j + 1]))
			{
				j = (j + 1) % n;
			}
			max_dis = max(max_dis, max(dis(v[j], v[i]), dis(v[j], v[i + 1])));
		}
	}
	return max_dis;
}

/**
 * Given verts: Array(Points).
 */

/*
 * if we have more than 100 points use Akl-Toussaint heuristic to remove
 *  points that we know are surely not part of the hull.
 * S.G. Akl & Godfried Toussaint, 1977,  "A Fast Convex-hull Algorithm"
 */
if (verts.length > 100) {
    var min = Math.min,
        max = Math.max;
    var minU = minL = maxU = maxL = verts[0];
    var minUval = minU.x - minU.y;
    var minLval = minL.x + minL.y;
    var maxUval = maxU.x + maxU.y;
    var maxLval = maxL.x - maxL.y;
    var xMin = yMin = xMax = yMax = 0;
    var vertList = [];
    for (i = 0 ; i < verts.length; ++i) {
        var v = verts[i];
        var x = v.x;
        var y = v.y;
        if (!(x > xMin && x < xMax && y > yMin && y < yMax)) {
            vertList.push(v);
            var sum = x + y;
            var diff = x - y;
            if (diff < minUval) minU = v;
            if (diff > maxLval) maxL = v;
            if (sum < minLval) minL = v;
            if (sum > maxUval) maxU = v;
            minUval = minU.x - minU.y;
            maxLval = maxL.x - maxL.y;
            minLval = minL.x + minL.y;
            maxUval = maxU.x + maxU.y;
            xMin = max(minU.x, minL.x);
            yMin = max(minL.y, maxL.y);
            xMax = min(maxU.x, maxL.x);
            yMax = min(minU.y, maxU.y);
        }
    }

    // reset the vert's array, and do one more filtering pass 
    // on vertList
    verts.length = 0;
    for (i = 0 ; i < vertList.length; ++i) {
        var v = vertList[i];
        var x = v.x;
        var y = v.y;
        if (!(x > xMin && x < xMax && y > yMin && y < yMax))
            verts.push(v);
    }
    // verts now only contains a subset of vertices.
}
// Run a convexhull algorithm on verts.
// ...
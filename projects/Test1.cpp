int f(int a, int b) {
  if (a == b)
    return a;
  else if (a > b)
    return f(a - b, b);
  else
    return f(a, b - a);
}
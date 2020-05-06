#ifndef CStereoShape_HPP
#define CStereoShape_HPP

#include <iostream>

struct Formatting
{
    std::ios_base::fmtflags flag;
    std::streamsize pr;
};

class CStereoShape
{
public:
    CStereoShape() { numberOfObject++; }
    virtual double GetArea() const;
    virtual double GetVolume() const;
    virtual void Show() const;
    static int GetNumOfObject(){return numberOfObject;}

protected:
    Formatting SetFormat() const;
    void Restore(Formatting &f) const;

private:
    static int numberOfObject;
};

#endif
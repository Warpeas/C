#ifndef CCube_HPP
#define CCube_HPP

#include "CStereoShape.hpp"

class CCube : public CStereoShape{
    public:
        CCube();
        CCube(double length, double width, double height);
        CCube(const CCube &other);
        double GetArea() const;
        double GetVolume() const;
        void Show() const;

        private:
            double length;
            double width;
            double height;
};

#endif
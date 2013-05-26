package asteroids.model;

import java.util.Comparator;

import asteroids.Collision;

public class CollisionComparitor implements Comparator<Collision>
{
    @Override
    public int compare(Collision x, Collision y)
    {
        
        if (x.getTime() < y.getTime())
        {
            return -1;
        }
        if (x.getTime() > y.getTime())
        {
            return 1;
        }
        return 0;
    }
}

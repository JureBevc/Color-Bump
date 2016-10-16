package com.jure.colorbump.util;

import com.jure.colorbump.obj.Ball;
import com.jure.colorbump.obj.Wall;


public class Collision {

    public static void checkEdge(Ball b) {
        if (b.pos.x + b.vel.x + b.r > 1280 || b.pos.x + b.vel.x - b.r < 0) {
            b.vel.x = -b.vel.x;
        }
        if (b.pos.y + b.vel.y + b.r > 720 || b.pos.y + b.vel.y - b.r < 0) {
            b.vel.y = -b.vel.y;
        }
    }

    public static void checkBalls(Ball b) {
        for (Ball b2 : ListManager.balls) {
            if (!b2.equals(b)) {
                if (b.pos.add(b.vel).neg().add(b2.pos.add(b2.vel)).length() < b.r + b2.r) {
                    Vec relativePos = b2.pos.sub(b.pos);
                    Vec relativeVel = b2.vel.sub(b.vel);
                    float impulse = relativePos.norm().dot(relativeVel);

                    Vec add = relativePos.norm().mul(impulse);
                    b.vel = b.vel.add(add);
                    b2.vel = b2.vel.add(add.neg());
                    if (b.tagged || b2.tagged) {
                        b.tagBall();
                        b2.tagBall();
                    }
                }
            }
        }
    }

    public static void checkWall(Ball b) {
        for (Wall w : ListManager.walls) {
            if (b.pos.y + b.r > w.pos.y && b.pos.y - b.r < w.pos.y + w.size.y) {
                if ((b.pos.x + b.vel.x + b.r > w.pos.x && b.pos.x < w.pos.x + w.size.x) || (b.pos.x + b.vel.x - b.r < w.pos.x + w.size.x && b.pos.x > w.pos.x))
                    b.vel.x = -b.vel.x;
            }
            if (b.pos.x + b.r > w.pos.x && b.pos.x - b.r < w.pos.x + w.size.x) {
                if ((b.pos.y + b.vel.y + b.r > w.pos.y && b.pos.y < w.pos.y + w.size.y) || (b.pos.y + b.vel.y - b.r < w.pos.y + w.size.y && b.pos.y > w.pos.y))
                    b.vel.y = -b.vel.y;
            }
        }

    }

    public static void check(Ball b) {
        checkEdge(b);
        checkWall(b);
        checkBalls(b);
    }
}

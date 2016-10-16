package com.jure.colorbump.util;


public class Vec {
    public float x, y;

    public Vec(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec add(Vec vec) {
        return new Vec(this.x + vec.x, this.y + vec.y);
    }

    public Vec sub(Vec vec) {
        return new Vec(this.x - vec.x, this.y - vec.y);
    }

    public Vec mul(float m) {
        return new Vec(this.x * m, this.y * m);
    }

    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public float dot(Vec vec) {
        return this.x * vec.x + this.y * vec.y;
    }

    public Vec norm() {
        float l = this.length();
        return new Vec(this.x / l, this.y / l);
    }

    public Vec neg() {
        return new Vec(-this.x, -this.y);
    }

}

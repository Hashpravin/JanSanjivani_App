package com.example.jansanjivani;

import android.text.Editable;

public class Datasavehelper {

    String  Ap,An,Bp,Bn,Op,On,ABp,ABn ;

    public Datasavehelper( ) {

    }

    public Datasavehelper(String ap, String an, String bp, String bn, String op, String on, String ABp, String ABn) {
        Ap = ap;
        An = an;
        Bp = bp;
        Bn = bn;
        Op = op;
        On = on;
        this.ABp = ABp;
        this.ABn = ABn;
    }

    public String getAp() {
        return Ap;
    }

    public void setAp(String ap) {
        Ap = ap;
    }

    public String getAn() {
        return An;
    }

    public void setAn(String an) {
        An = an;
    }

    public String getBp() {
        return Bp;
    }

    public void setBp(String bp) {
        Bp = bp;
    }

    public String getBn() {
        return Bn;
    }

    public void setBn(String bn) {
        Bn = bn;
    }

    public String getOp() {
        return Op;
    }

    public void setOp(String op) {
        Op = op;
    }

    public String getOn() {
        return On;
    }

    public void setOn(String on) {
        On = on;
    }

    public String getABp() {
        return ABp;
    }

    public void setABp(String ABp) {
        this.ABp = ABp;
    }

    public String getABn() {
        return ABn;
    }

    public void setABn(String ABn) {
        this.ABn = ABn;
    }
}

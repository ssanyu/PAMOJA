/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FALayout.FALayout;

import java.util.ArrayList;

/**
 *
 * @author Ssanyu
 */
public class CFANode {
    private CFANodeKind kind;

    // only meaningful when Kind is STRING:
    private String name;

    // only meaningful when Kind is DOT or STICK
    private ArrayList<CFANode> elts;

    // only meaningful when Kind is STAR, PLUS, or OPTION
    private CFANode elt;
    
    // internal state numbers,
    // only meaningful when Kind is nkDot, nkStick, nkStar, (nkPlus, nkOption)
    private ArrayList<Integer> IStates;

    // layout information
    private int X;
    private int Y;
    private int Width;
    private int Height;

    public CFANode() {
        kind =null;
        name = "";
        elts = new ArrayList<CFANode>();
        elt = null;
        IStates=new ArrayList<Integer>();
        X=0;
        Y=0;
        Width=0;
        Height=0;
    }
        
    // Getters and Setters
    public CFANodeKind getKind() {
        return kind;
    }

    public void setKind(CFANodeKind kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<CFANode> getElts() {
        return elts;
    }

    public void setElts(ArrayList<CFANode> elts) {
        this.elts = elts;
    }

    public CFANode getElt() {
        return elt;
    }

    public void setElt(CFANode elt) {
        this.elt = elt;
    }

    public ArrayList<Integer> getIStates() {
        return IStates;
    }

    public void setIStates(ArrayList<Integer> IStates) {
        this.IStates = IStates;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public int getWidth() {
        return Width;
    }

    public void setWidth(int Width) {
        this.Width = Width;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int Height) {
        this.Height = Height;
    }

 }

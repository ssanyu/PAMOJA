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
public class CFANodeFactory {
    
        // FA factory ------------------------------------------------------------------

    public CFANode MkEmpty(){
    CFANode vResult=new CFANode();
    vResult.setKind(CFANodeKind.EMPTY);
    return vResult;
}
    
public CFANode MkEps(){
    CFANode vResult=new CFANode();
    vResult.setKind(CFANodeKind.EPS);
    return vResult;
}

public CFANode MkString(String aName){
    CFANode vResult=new CFANode();
    vResult.setKind(CFANodeKind.STRING);
    vResult.setName(aName);
    return vResult;
}

public CFANode MkDot(ArrayList<CFANode>aNode){
    CFANode vResult=new CFANode();
    vResult.setKind(CFANodeKind.DOT);
    vResult.setElts(aNode);
    return vResult;
}
public CFANode MkStick(ArrayList<CFANode>aNode){
    CFANode vResult=new CFANode();
    vResult.setKind(CFANodeKind.STICK);
    vResult.setElts(aNode);
    return vResult;
}

public CFANode MkStar(CFANode aNode){
    CFANode vResult=new CFANode();
    vResult.setKind(CFANodeKind.STAR);
    vResult.setElt(aNode);
    return vResult;
}

public CFANode MkPlus(CFANode aNode){
    CFANode vResult=new CFANode();
    vResult.setKind(CFANodeKind.PLUS);
    vResult.setElt(aNode);
    return vResult;
}
public CFANode MkOption(CFANode aNode){
    CFANode vResult=new CFANode();
    vResult.setKind(CFANodeKind.OPTION);
    vResult.setElt(aNode);
    return vResult;
}
   
}

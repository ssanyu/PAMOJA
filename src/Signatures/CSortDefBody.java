package Signatures;

import GrammarNotions.Context_Terms.CContext;
import GrammarNotions.Context_Terms.CContextKind;
import GrammarNotions.Context_Terms.CTerm;
import GrammarNotions.Context_Terms.CTermTuple;

//SortDefBody=C[memDefs:MemberDefinition*] T[ancestor:SortUse]<_Term

/**
 *
 * @author HP
 */

public class  CSortDefBody extends CTermTuple{

    /**
     *
     */
    protected CMemberDefinition_List fMemDefs;

    /**
     *
     */
    protected CSortUse fAncestor;

    /**
     *
     * @param aMemDefs
     * @param aAncestor
     */
    public CSortDefBody(CMemberDefinition_List aMemDefs,CSortUse aAncestor){
        fMemDefs=aMemDefs;
        fAncestor=aAncestor;
    }
    
    /**
     *
     * @return
     */
    public CMemberDefinition_List getMemDefs(){
        return fMemDefs;
    }
    
    /**
     *
     * @param aMemDefs
     */
    public void setMemDefs(CMemberDefinition_List aMemDefs){
        fMemDefs=aMemDefs;
    }
    
    /**
     *
     * @return
     */
    public CSortUse getAncestor(){
        return fAncestor;
    }
    
    /**
     *
     * @param aAncestor
     */
    public void setAncestor(CSortUse aAncestor){
        fAncestor=aAncestor;
    }

    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return CSignatureCodes.scSortDefBody;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "SortDefBody";
    }
    
    @Override
    public CContextKind contextKind(){
        return CContextKind.ckItem;
    }

    /**
     *
     * @return
     */
    @Override
    public int contextCount() {
        return 1;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public CContext getContext(int i) {
        if(i==0)
            return fMemDefs;
        else return super.getContext(i);
    }

    @Override
    public void setContext(int i, CContext aContext) {
        if(i==0)
            fMemDefs=(CMemberDefinition_List)aContext;
        else  super.setContext(i,aContext);
    }
    
    /**
     *
     * @return
     */
    @Override
     public int termCount(){
        return 1;
    }
      
   @Override
   public CTerm getTerm(int i){
        if(i==0)
            return fAncestor;
        else return super.getTerm(i);
    }
    
    @Override
    public void setTerm(int i,CTerm aTerm){
        if(i==0)
            fAncestor=(CSortUse)aTerm;
        else super.setTerm(i,aTerm);
    }
    
    /**
     *
     * @return
     */
    public String toText(){
        CMemberDefinition vMemDef;
        CMemberDefinition_List vCtxt=new CMemberDefinition_List();
        CMemberDefinition_List vTerm=new CMemberDefinition_List();
        CMemberDefinition_List vData=new CMemberDefinition_List();
        CMemberDefinition_List vRef=new CMemberDefinition_List();
        for(int i=0;i<fMemDefs.contextCount();i++){
            vMemDef=fMemDefs.getElt(i);
            if(vMemDef.getBody().getRole()=='C')
                vCtxt.addContext(vMemDef);
            else if(vMemDef.getBody().getRole()=='T')
                vTerm.addContext(vMemDef);
            else if(vMemDef.getBody().getRole()=='D')
                vData.addContext(vMemDef);
            else if(vMemDef.getBody().getRole()=='R')
                vRef.addContext(vMemDef);
        }
        
        StringBuilder vResult=new StringBuilder();
        if(vCtxt.contextCount()!=0){
            vResult.append("C[");
            vResult.append(vCtxt.getElt(0).getName());
            vResult.append(':');
            vResult.append(vCtxt.getElt(0).getBody().getType().toText());
            vResult.append(vCtxt.getElt(0).getBody().getModifier());
            for(int i=1;i<vCtxt.contextCount();i++){
                vResult.append(',');
                vResult.append(vCtxt.getElt(i).getName());
                vResult.append(':');
                vResult.append(vCtxt.getElt(i).getBody().getType().toText());
                vResult.append(vCtxt.getElt(i).getBody().getModifier());
                
            }
            vResult.append(']');
        }
        if(vTerm.contextCount()!=0){
            vResult.append(" T[");
            vResult.append(vTerm.getElt(0).getName());
            vResult.append(':');
            vResult.append(vTerm.getElt(0).getBody().getType().toText());
            vResult.append(vTerm.getElt(0).getBody().getModifier());
            for(int i=1;i<vTerm.contextCount();i++){
                vResult.append(',');
                vResult.append(vTerm.getElt(i).getName());
                vResult.append(':');
                vResult.append(vTerm.getElt(i).getBody().getType().toText());
                vResult.append(vTerm.getElt(i).getBody().getModifier());
                
            }
            vResult.append(']');
        }     
        if(vData.contextCount()!=0){
            vResult.append(" D[");
            vResult.append(vData.getElt(0).getName());
            vResult.append(':');
            vResult.append(vData.getElt(0).getBody().getType().toText());
            vResult.append(vData.getElt(0).getBody().getModifier());
            for(int i=1;i<vData.contextCount();i++){
                vResult.append(',');
                vResult.append(vData.getElt(i).getName());
                vResult.append(':');
                vResult.append(vData.getElt(i).getBody().getType().toText());
                vResult.append(vData.getElt(i).getBody().getModifier());
                
            }
            vResult.append(']');
        }     
        if(vRef.contextCount()!=0){
            vResult.append(" R[");
            vResult.append(vRef.getElt(0).getName());
            vResult.append(':');
            vResult.append(vRef.getElt(0).getBody().getType().toText());
            vResult.append(vData.getElt(0).getBody().getModifier());
            for(int i=1;i<vRef.contextCount();i++){
                vResult.append(',');
                vResult.append(vRef.getElt(i).getName());
                vResult.append(':');
                vResult.append(vRef.getElt(i).getBody().getType().toText());
                vResult.append(vRef.getElt(i).getBody().getModifier());
                
            }
            vResult.append(']');
        }
        vResult.append(" ");
        vResult.append('<');
        vResult.append(" ");
        vResult.append(fAncestor.toText());
        
        
        return vResult.toString();
    }

    
}
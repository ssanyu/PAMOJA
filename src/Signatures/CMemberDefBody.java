package Signatures;

import GrammarNotions.Context_Terms.CContextKind;
import GrammarNotions.Context_Terms.CTerm;
import GrammarNotions.Context_Terms.CTermTuple;

/**
 *
 * @author HP
 */
public class  CMemberDefBody extends CTermTuple{
    
    /**
     *
     */
    protected CSortUse fType;

    /**
     *
     */
    protected char fModif;

    /**
     *
     */
    protected char fRole;
    
    /**
     *
     * @param aType
     * @param aModif
     * @param aRole
     */
    public CMemberDefBody(CSortUse aType,char aModif,char aRole){
        fType=aType;
        fModif=aModif;
        fRole=aRole;
    }
    
    /**
     *
     * @return
     */
    public char getRole(){
        return fRole;
    }
    
    /**
     *
     * @param aRole
     */
    public void setRole(char aRole){
        fRole=aRole;
    }
    
    /**
     *
     * @return
     */
    public char getModifier(){
        return fModif;
    }
    
    /**
     *
     * @param aModif
     */
    public void setModifier(char aModif){
        fModif=aModif;
    }

    /**
     *
     * @return
     */
    public CSortUse getType(){
        return fType;
    }

    /**
     *
     * @param aType
     */
    public void setType(CSortUse aType){
        fType=aType;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int sortCode() {
        return CSignatureCodes.scMemberDefBody;
    }

    /**
     *
     * @return
     */
    @Override
    public String sortLabel() {
        return "MemberDefBody";
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
    public int dataCount() {
        return 2;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public String getData(int i) {
        if(i==0)
            return Character.toString(fRole);
        else if(i==1)
            return Character.toString(fModif);
        else return super.getData(i);
    }

    /**
     *
     * @param i
     * @param aData
     */
    @Override
    public void setData(int i,String aData){
        if(i==0){
            assert 
                ( aData.length()==1) &&
                  ( aData.charAt(0)==CRoles.CONTEXT ||
                    aData.charAt(0)==CRoles.DATA || 
                    aData.charAt(0)==CRoles.REFERENCE || 
                    aData.charAt(0)==CRoles.TERM ): 
                String.format(
                    "Unxpected String in CMemberDefBody.setData(): %s", 
                    aData);
            fRole=aData.charAt(0);
        }else if(i==1){
            assert 
                ( aData.length()==1) &&
                  ( aData.charAt(0)==CModifier.NONE ||
                    aData.charAt(0)==CModifier.OPTION || 
                    aData.charAt(0)==CModifier.STAR ):
                String.format(
                    "Unxpected String in CMemberDefBody.setData(): %s", 
                    aData);
            
            fModif=aData.charAt(0);
        }else super.setData(i,aData);
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
            return fType;
        else return super.getTerm(i);
    }
    
    /**
     *
     * @param i
     * @param aTerm
     */
    @Override
    public void setTerm(int i,CTerm aTerm){
        if(i==0)
            fType=(CSortUse)aTerm;
        else super.setTerm(i,aTerm);
    }
    
}
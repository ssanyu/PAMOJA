package Analyzers;


import General.CGeneral;
import Signatures.CMemberDefinition;
import Signatures.CMemberDefinition_List;
import Signatures.CRoles;
import Signatures.CSignature;
import Signatures.CSortDefBody;
import Signatures.CSortDefinition;
import Signatures.CSortDefinition_List;
import java.util.ArrayList;

/**
 * A class which contains operations for checking the well-formedness of a signature (the abstract syntax of a grammar). 
 *  
 * 
 * @author Jackline Ssanyu (jssanyu@kyu.ac.ug)
 */
public class CSignatureAnalyzer{

    
    protected CSignature fSignature;  

    
    protected StringBuilder vErrors=new StringBuilder();
     
    /**
     * Returns a string representation of errors.
     * 
     * @return 
     */
    public StringBuilder getErrorList(){
        return vErrors;
    }

    /**
     *
     * @param aSignature
     */
    public void setSignature(CSignature aSignature){
        fSignature=aSignature;
    }
    
    /**
     *
     * @return
     */
    public CSignature getSignature(){
        return fSignature; 
    }

    /**
     *
     * @param aSignatureStructure
     */
    public void analyzeSignature(CSignature aSignatureStructure){
        
        fSignature=aSignatureStructure;
        
        // check for duplicate definitions of sorts
        CSortDefinition_List vSortDefs=fSignature.getUserSortCtxt().getSortDefs();
        CSortDefinition vSorti, vSortj;
        for(int i=0;i<vSortDefs.contextCount();i++){
            vSorti = vSortDefs.getElt(i);
            for(int j=i+1;j<vSortDefs.contextCount();j++){
                vSortj=vSortDefs.getElt(j);
                if(vSorti != vSortj && vSorti.getName().equals(vSortj.getName())){
                    vErrors.append(String.format("Duplicate declaration of sort %s",vSorti.getName())).append("\n");
                }
            }
        }     
        
        // analyze each sort definition
        for(int i=0;i<fSignature.getUserSortCtxt().getSortDefs().contextCount();i++){
            CSortDefinition vSort=fSignature.getUserSortCtxt().getSortDefs().getElt(i);
            analyzeSortDefinition(vSort);
        }
    }
    
    private void analyzeSortDefinition(CSortDefinition aSort){
        CSortDefinition vAncestor;
        ArrayList<Character> vRoles;
        CMemberDefinition_List vMembers;
                
        //Check for valid name of aSort
        if(!validSortName(aSort.getName())){
            vErrors.append(String.format("Illegal Sortname: %s",aSort.getName())).append("\n");
        }
                
        //Check validity of ancestor        
        vAncestor=ancestor(aSort);
        if(!validAncestor(aSort,vAncestor)){
             vErrors.append(String.format("Invalid ancestor = %s for sort = %s "
                ,vAncestor.getName(),aSort.getName())).append("\n");
             return;
        }
        
        //Compute the roles used in the member list of aSort...
        vRoles=getRoles(members(aSort));
                
        //and compare them to the permitted roles
        
        if(atMost(vAncestor,findSortDef("_Term"))){
            
            for(int i=0;i<vRoles.size();i++){
                if(! CRoles.TERMROLES.contains(vRoles.get(i))){
                    vErrors.append(String.format("Role %c not allowed in descendants of %s",vRoles.get(i),"_Term")).append("\n");
                }
            }
                     
        }else if(atMost(vAncestor,findSortDef("_Item"))){
            for(int i=0;i<vRoles.size();i++){
                if(! CRoles.ITEMROLES.contains(vRoles.get(i))){
                    vErrors.append(String.format("Role %c not allowed in descendants of %s",vRoles.get(i),"_Item")).append("\n");
                }
            }
            
        }else if(atMost(vAncestor,findSortDef("_Ctxt"))){
            for(int i=0;i<vRoles.size();i++){
                if(! CRoles.CTXTROLES.contains(vRoles.get(i))){
                    vErrors.append(String.format("Role %c not allowed in descendants of %s",vRoles.get(i),"_Ctxt")).append("\n");
                }
            }
            
        }else if(atMost(vAncestor,findSortDef("_Data"))){
            for(int i=0;i<vRoles.size();i++){
                if(!CRoles.DATAROLES.contains(vRoles.get(i))){
                    vErrors.append(String.format("Role %c not allowed in descendants of %s",vRoles.get(i),"_Data")).append("\n");
                }
            }
       
        } 
        
        //check for valid member names
        vMembers=members(aSort);
        for(int i=0;i<memberCount(aSort);i++){
            //Check for valid member name
            String vMemName=vMembers.getElt(i).getName();
            if(!validMemberName(vMemName)){
                vErrors.append(String.format("Illegal Member name: %s in sort: %s ",vMemName,aSort.getName())).append("\n");
            }
            
            //Check for valid member type
            if(!validMemberType(vMembers.getElt(i))){
                vErrors.append(String.format("Illegal Type: %s for Member: %s in sort: %s ",vMembers.getElt(i).getBody().getType().getName(),vMemName,aSort.getName())).append("\n");
            }
        }
        
        //check for duplicate member names in this sort and its ancestors
        ArrayList<CMemberDefinition_List> vAllMembers=allMembers(aSort);
       
        CMemberDefinition_List vList=new CMemberDefinition_List();
        for(int j=0;j<vAllMembers.size();j++){
           vList.addAll(vAllMembers.get(j));
        }
        
        CMemberDefinition vMemDefi, vMemDefj;
        for(int i=0;i<vList.contextCount();i++){
            vMemDefi = vList.getElt(i);
            for(int j=i+1;j<vList.contextCount();j++){
                vMemDefj=vList.getElt(j);
                if(vMemDefi.getName().equals(vMemDefj.getName())){
                    vErrors.append(String.format("Duplicate declaration of member: %s in sort %s",vMemDefi.getName(),aSort.getName())).append("\n");
                }
            }
        }     
    }
       
    /**
     *
     * @param aMemDef
     * @return
     */
    public boolean validMemberType(CMemberDefinition aMemDef){
        CSortDefinition vSort=aMemDef.getBody().getType().getSortDef();
        char vRole=aMemDef.getBody().getRole();
        switch(vRole){
            case 'C':
                return less(vSort, findSortDef("_Ctxt"));
            case 'T':
                return less(vSort, findSortDef("_Term"));
            case 'D':
                return less(vSort, findSortDef("_Data"));
            case 'R':
                return less(vSort, findSortDef("_Item"));
            
        }
        return false;
    }
    
    /**
     *
     * @param aName
     * @return
     */
    public boolean validSortName(String aName){
        return CGeneral.isIdentifier(aName, CGeneral.csLetter, CGeneral.csLetterDigit);
        
    }
    
    /**
     *
     * @param aName
     * @return
     */
    public boolean validMemberName(String aName){
          return CGeneral.isIdentifier(aName, CGeneral.csChars, CGeneral.csLetterDigit);
    }
    
    
    /**
     * Returns the number of sorts in a signature
     * 
     * @return the number of sorts.
     */
        public int sortCount(){
        return fSignature.getUserSortCtxt().getSortDefs().contextCount();
    }
    
    
    /**
     * Returns the ith sort.
     * 
     * @param i the index of a sort to be returned.
     * @return the sort definition.
     */
        public CSortDefinition sortDef(int i){
        return fSignature.getUserSortCtxt().getSortDefs().getElt(i);
    }
    
   /**
     * Returns base ancestor of a sort.
     * 
     * @param aSort
     * @return
     */
        public CSortDefinition baseAncestor(CSortDefinition aSort){
        CSortDefinition vSort=aSort;
        while(vSort!=null && !isBaseSort(vSort)){
            vSort=ancestor(vSort);
        }
        return vSort;
    }
    
  
    /**
     * Determines if a specified sort is a base sort.
     * 
     * @param aSort the specified sort.
     * @return <code>true</code> if a given sort is a base sort, otherwise <code>false</code>.
     */
        public boolean isBaseSort(CSortDefinition aSort){  
        CSortDefinition_List vList=fSignature.getBaseSortCtxt().getSortDefs();
        for(int i=0;i<vList.contextCount();i++){
            if(vList.getElt(i)==aSort){
                return true;
            }
        }
        return false;
    }
  
    /**
     * Returns ancestor of a sort
     * 
     * @param aSort the specified sort definition.
     * @return the ancestor sort for the specified sort.
     */
        public CSortDefinition ancestor(CSortDefinition aSort){
         assert aSort!=null : "CSignatureAnalyzer.ancestor() failed: aSort==null";
         
       CSortDefBody vBody=aSort.getBody();
         if(vBody==null){
             return null;
         }else{
             return vBody.getAncestor().getSortDef(); 
         }
     }
    
    /**
     * Returns all members for a given sort.
     * 
     * @param aSort the given sort.
     * @return a list of all members for the given sort.
     */
        public CMemberDefinition_List members(CSortDefinition aSort){
         assert aSort!=null : "CSignatureAnalyzer.members() failed: aSort==null";
         
         CSortDefBody vBody=aSort.getBody();
         if(vBody==null){
             return new CMemberDefinition_List();
         }else{
             return aSort.getBody().getMemDefs(); 
         }
       
    }

    /**
     * Returns all members for aSort and all members of its ancestors
     * 
     * @param aSortDef
     * @return
     */
        public ArrayList<CMemberDefinition_List> allMembers(CSortDefinition aSortDef){
        ArrayList<CMemberDefinition_List> vList=new  ArrayList<CMemberDefinition_List>();
        CSortDefinition vSortDef=aSortDef;
        while(vSortDef!=null){
            vList.add(members(vSortDef));
            vSortDef=ancestor(vSortDef);
        }
      
        return vList;
    }
    
   /**
     * Returns a sort definition with a given name
     * 
     * @param aName
     * @return
     */
        public CSortDefinition findSortDef(String aName){
        CSortDefinition vSort=null;
        //First search the user-defined sorts
        for(int i=0;i<fSignature.getUserSortCtxt().getSortDefs().contextCount();i++){
            if(fSignature.getUserSortCtxt().getSortDefs().getElt(i).getName().equals(aName)){
                vSort=fSignature.getUserSortCtxt().getSortDefs().getElt(i);
                break;
            }
        }
        
        //if not found, search base sorts
        for(int i=0;i<fSignature.getBaseSortCtxt().getSortDefs().contextCount();i++){
            if(fSignature.getBaseSortCtxt().getSortDefs().getElt(i).getName().equals(aName)){
                vSort=fSignature.getBaseSortCtxt().getSortDefs().getElt(i);
                break;
            }
        }
        return vSort;
    }        
 

    /**
     * Returns index of a Sort in the list.
     * 
     * @param aName
     * @return
     */
        public int indexOfSortDef(String aName){
        int vIndex=-1;
        for(int i=0;i<fSignature.getUserSortCtxt().getSortDefs().contextCount();i++){
            if(fSignature.getUserSortCtxt().getSortDefs().getElt(i).getName().equals(aName)){
               vIndex=i;
                break;
            }
        }
        return vIndex;
    }
    
   /**
     * Returns true if this sortDef is in the list.
     * 
     * @param aName
     * @return
     */
        public boolean sortOccurs(String aName){
        return indexOfSortDef(aName)!=-1;
    }
 
    /**
     * Returns true if a given sort contains a member with the specified name.
     * 
     * @param aSort
     * @param aMemName
     * @return
     */
        public boolean hasMember(CSortDefinition aSort,String aMemName){
        boolean vResult=false;
        CMemberDefinition_List vList=members(aSort);
        
        for(int i=0;i<vList.contextCount();i++){
            if(vList.getElt(i).getName().equals(aMemName)){
                vResult=true;
                break;
            }
        }
        return vResult;
    }
    
   

    /**
     * Returns a sortDef of a member at index i in the given sort
     * 
     * @param aSort
     * @param i
     * @return
     */
        public CSortDefinition memberSort(CSortDefinition aSort, int i){
         CMemberDefinition_List vList=members(aSort);
         return (vList.getElt(i)).getBody().getType().getSortDef();
    }
    
 
    /**
     * Returns members with a specific role in a sort
     * 
     * @param aName
     * @param aRole
     * @return
     */
        public CMemberDefinition_List membersPerRole(String aName,char aRole){
        CMemberDefinition_List vResult;
        
        CSortDefinition vSort=findSortDef(aName);
        CMemberDefinition_List vList=members(vSort);
        
        vResult=new CMemberDefinition_List();
        
        for(int i=0;i<vList.contextCount();i++){
            if(vList.getElt(i).getBody().getRole()==aRole){
                vResult.add(vList.getElt(i));
            }
        }
        return vResult; 
    }
    
    /**
     *
     * @param aSort1
     * @param aSort2
     * @return
     */
    public boolean equal(CSortDefinition aSort1, CSortDefinition aSort2){
       return aSort1==aSort2;
    }
    
    /**
     *
     * @param aSort1
     * @param aSort2
     * @return
     */
    public boolean less(CSortDefinition aSort1, CSortDefinition aSort2){
       CSortDefinition vSort;
       
       vSort=aSort1;
      do{
           vSort=ancestor(vSort);
       }while(vSort!=null && vSort!=aSort2);
      
       return vSort==aSort2;
    }
    
    /**
     *
     * @param aSort1
     * @param aSort2
     * @return
     */
    public boolean atMost(CSortDefinition aSort1, CSortDefinition aSort2){
       CSortDefinition vSort;
       
       vSort=aSort1;
       while(vSort!=null && vSort!=aSort2){
           vSort=ancestor(vSort);
       }
       return vSort==aSort2;
    }
    


    /**
     * Returns role of a member
     * 
     * @param aMemDef
     * @return
     */
        public char getMemberRole(CMemberDefinition aMemDef){
        return aMemDef.getBody().getRole();
        
    }
    
 
    /**
     * Returns a list of roles defined in a sort
     * @param aList
     * @return
     */
        public ArrayList<Character> getRoles(CMemberDefinition_List aList){
           assert aList!=null :"CSignatureAnalyzer.getRoles() failed: aList==null";
           
           ArrayList<Character> vList=new  ArrayList<Character>();
           char vRole;
           for(int i=0;i<aList.contextCount();i++){
               vRole=getMemberRole(aList.getElt(i));
               if(!vList.contains(vRole)){
                   vList.add(vRole);
               }
           }
           return vList;
    }
          
   /**
     * Ensures that ancestor is valid(i.e no cycles in the ancestor chain)
     * 
     * @param aSort
     * @param aAncestor
     * @return
     */
        public boolean validAncestor(CSortDefinition aSort, CSortDefinition aAncestor){
        return (!atMost(aAncestor,aSort));
    }
    

    /**
     * Returns number of members in a sort
     * @param aSort
     * @return
     */
        public int memberCount(CSortDefinition aSort){
        return aSort.getBody().getMemDefs().contextCount();
    }
    
   
    /**
     * Returns all ancestors of a sort
     * @param aSort
     * @return
     */
        public CSortDefinition_List allAncestors(CSortDefinition aSort){
         CSortDefinition_List vList=new CSortDefinition_List();
         CSortDefinition vSort=aSort;
         
         while(vSort!=null){
            vList.add(vSort);
            vSort=ancestor(vSort);
         }
         return vList;
    }
    
    
}
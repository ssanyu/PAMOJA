package Java;

import Analyzers.CSignatureAnalyzer;
import Signatures.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*To Dos 
 * List classes should descend from either CContextList or CTermList. This should depend on the role of the member.
 * 
 */

/**
 *
 * @author HP
 */


public class CSignToJavaTranslator {
    private CSignature fSignature=null;
    private CSignatureAnalyzer fSignAnalyzer;
    private String fName; // Name of signature
    
    //Format Strings
    final String fmSignatureNameToSortCodesClassName="C%sSortCodes";
    final String fmSignatureNameToNodeFactoryClassName="C%sASTNodeFactory";
    final String fmSortDefToClass="C%s";
    final String fmSortDefToSortLabel="%s";
    final String fmSortDefToSortCode="sc%s";
    final String fmMemDefToField="f%s";
    final String fmMemDefToParameter="a%s";
    final String fmMemDefToGetter="get%s";
    final String fmMemDefToSetter="set%s";
    final String fmRoleToRoleName="%s";
    final String fmRoleToRoleCountMethod="%sCount";
    final String fmRoleToGetRoleMethod="get%s";
    final String fmRoleToSetRoleMethod="set%s";
    final String fmfLabeltoClassInitStmt=
            "fLabeltoClass=new HashMap<String,Class>(){{\n"+
            "%s\n"+
            "       }}";
    final String fmfCodetoClassInitStmt=
            "fCodetoClass=new HashMap<Integer,Class>(){{\n"+
            "%s\n"+
            "       }}";
    final String fmSortLabelStringArray=
            "\npublic static final String sortLabels[]={\n"+
            "      %s\n"+
            "  };";
    final String fmCodeToClassMethod=
            "protected Class codeToClass(int aCode) {\n"+
            "         Class vClass=fCodetoClass.get(aCode);\n"+
            "         return vClass;\n"+
            "    }";
    final String fmLabelToClassMethod=
            "protected Class labelToClass(String aLabel) {\n"+
            "         Class vClass=fLabeltoClass.get(aLabel);\n"+
            "         return vClass;\n"+
            "    }";
    
    final String fmMemDefBodyWithOptionModifierToOptionClass=
            "protected boolean fPresent;\n\n"+
            "protected %2$s fArg;\n\n"+
            
            "public %2$s_Option(){\n"+
            "super();\n"+
            "}\n\n"+
 
            "@Override\n"+
            "public void setPresent(CNode aArg){\n" +
            "   fPresent=true;\n" +
            "   fArg=(%2$s)aArg;\n" +
            "}\n\n"+
    
            "@Override\n"+
            "public void setAbsent(){\n"+
            "  fPresent=false;\n"+
            "}\n\n"+
   
            "@Override\n"+
            "public boolean isPresent() { \n"+
            "   return fPresent;\n"+
            "}\n\n"+

            "@Override\n"+
            "public CNode getArg(){\n"+
            "    if(fPresent==true){\n"+
            "        return fArg;\n"+
            "    }else {\n"+
            "       assert false: \"Method %2$s_Option.getArg() failed, because %2$s_Option.isPresent() yielded false.\";\n"+
            "       return null;\n"+
            "    }\n"+
            "}\n\n"+
       
            "@Override\n"+
            "public String sortLabel(){\n"+
            "    return \"%1$s_Option\";"+"\n"+
            "}\n\n"+
            
            "@Override\n"+            
            "public int sortCode() {\n"+
            "     return %3$s.sc%1$s_Option;"+"\n"+
            "}\n\n"+

            "@Override\n"+
            "public CNodeKind kind(){\n"+
            "  return CNodeKind.OPTION;\n"+
            "}\n\n"+
            
            "@Override\n"+
            "public int count(){\n"+
            "   if(fPresent==true)\n"+
            "       return 1;\n"+
            "   else return 0;\n"+
            "}\n\n"+
            
            "@Override\n"+
            "public CNode getNode(int i){\n"+
            "   assert 0<=i && i<count()&& fPresent==true : String.format(\"Precondition of %2$s_Option.getNode() failed\");\n"+
            "    switch(i){\n"+
            "        case 0:\n"+
            "          return fArg;\n"+
            "        default:\n"+
            "          return super.getNode(i);\n"+
            "    }\n"+
            "}\n\n"+

            "@Override\n"+
            "public void setNode(int i,CNode aNode){\n"+
            "   assert 0<=i && i<count()&& fPresent==true : String.format(\"Precondition of %2$s_Option.setNode() failed:\");\n"+
            "   switch(i){\n"+
            "      case 0:\n"+
            "         assert aNode instanceof %2$s : String.format(\"Precondition of %2$s_Option.setNode() failed:\");\n"+
            "         fArg=(%2$s) aNode;\n" +
            "      break;\n"+
            "     default:\n"+
            "        super.setNode(i,aNode);\n"+
            "       break;\n"+
            "  }\n"+
            "}\n";
        
    final String fmMemDefBodyWithStarModifierToContextList=
            "   public %2$s_List0(){\n"+
            "      super();\n"+
            "  } \n\n"+
            
            "   public %2$s getElt(int i){\n"+
            "      return (%2$s)getContext(i);\n"+
            "   }\n"+
            "   protected void setElt(int i, %2$s a%1$s){\n"+
            "      setContext(i,a%1$s);\n"+
            "   }\n"+
            "   @Override\n"+
            "   public String eltSortLabel() {\n"+
            "      return \"%1$s\";\n"+
            "   }\n"+

            "   @Override\n"+
            "   public int eltSortCode() {\n"+
            "       return %3$s.sc%1$s;"+"\n"+
            "   }\n"+
            "   public int sortCode() {\n"+
            "       return %3$s.sc%1$s_List0;"+"\n"+
            "   }\n"+

            "   public String sortLabel() {\n"+
            "        return \"%1$s_List0\";"+"\n"+
            "   }\n"+

            "   @Override\n"+
            "   public Class eltClass() {\n"+
            "       return %2$s.class;\n"+
            "   }\n"+
            "   public CContextKind contextKind() {\n"+
            "       return CContextKind.ckRecursive;\n"+
            "   }\n";
    final String fmMemDefBodyWithStarModifierToTermList=
            "   public %2$s_List0(){\n"+
            "      super();\n"+
            "  } \n\n"+
            
            "   public %2$s getElt(int i){\n"+
            "       return (%2$s)getTerm(i);\n"+
            "   }\n"+
            "   protected void setElt(int i, %2$s a%1$s){\n"+
            "       setTerm(i,a%1$s);\n"+
            "   }\n"+
            "   @Override\n"+
            "   public String eltSortLabel() {\n"+
            "       return \"%1$s\";\n"+
            "   }\n"+

            "   @Override\n"+
            "   public int eltSortCode() {\n"+
            "       return %3$s.sc%1$s;"+"\n"+
            "   }\n"+
            "   @Override\n"+
            "   public int sortCode() {\n"+
            "       return %3$s.sc%1$s_List0;"+"\n"+
            "   }\n"+

            "   @Override\n"+
            "   public String sortLabel() {\n"+
            "        return \"%1$s_List0\";"+"\n"+
            "   }\n\n"+
            
            "   @Override\n"+
            "   public Class eltClass() {\n"+
            "       return %2$s.class;\n"+
            "   }\n"+

            "   @Override\n"+
            "   public boolean canSetTerm(int i, CTerm aTerm) {\n"+
            "       return 0<=i & i<termCount();\n"+
            "   }\n";
    final String fmMemDefBodyWithPlusModifierToContextList=
            "   public %2$s_List1(){\n"+
            "      super();\n"+
            "      fList.add(new %2$s());\n"+
            "  } \n\n"+
            
            "   public %2$s getElt(int i){\n"+
            "      return (%2$s)getContext(i);\n"+
            "   }\n"+
            "   protected void setElt(int i, %2$s a%1$s){\n"+
            "      setContext(i,a%1$s);\n"+
            "   }\n"+
            "   @Override\n"+
            "   public String eltSortLabel() {\n"+
            "      return \"%1$s\";\n"+
            "   }\n"+

            "   @Override\n"+
            "   public int eltSortCode() {\n"+
            "       return %3$s.sc%1$s;"+"\n"+
            "   }\n"+
            "   public int sortCode() {\n"+
            "       return %3$s.sc%1$s_List1;"+"\n"+
            "   }\n"+

            "   public String sortLabel() {\n"+
            "        return \"%1$s_List1\";"+"\n"+
            "   }\n"+

            "   @Override\n"+
            "   public Class eltClass() {\n"+
            "       return %2$s.class;\n"+
            "   }\n"+
            "   public CContextKind contextKind() {\n"+
            "       return CContextKind.ckRecursive;\n"+
            "   }\n"+
            
            "   @Override\n" +
            "   public boolean canRemove(CNode aNode){\n" +
            "        return fList.indexOf(aNode)!=-1 & count()>1;\n" +
            "    }\n ";
    final String fmMemDefBodyWithPlusModifierToTermList=
            "   public %2$s_List1(){\n"+
            "      super();\n"+
            "      fList.add(new %2$s());\n"+
            "  } \n\n"+
            "   public %2$s getElt(int i){\n"+
            "       return (%2$s)getTerm(i);\n"+
            "   }\n"+
            "   protected void setElt(int i, %2$s a%1$s){\n"+
            "       setTerm(i,a%1$s);\n"+
            "   }\n"+
            "   @Override\n"+
            "   public String eltSortLabel() {\n"+
            "       return \"%1$s\";\n"+
            "   }\n"+

            "   @Override\n"+
            "   public int eltSortCode() {\n"+
            "       return %3$s.sc%1$s;"+"\n"+
            "   }\n"+
            "   @Override\n"+
            "   public int sortCode() {\n"+
            "       return %3$s.sc%1$s_List1;"+"\n"+
            "   }\n"+

            "   @Override\n"+
            "   public String sortLabel() {\n"+
            "        return \"%1$s_List1\";"+"\n"+
            "   }\n\n"+
            
            "   @Override\n"+
            "   public Class eltClass() {\n"+
            "       return %2$s.class;\n"+
            "   }\n"+

            "   @Override\n"+
            "   public boolean canSetTerm(int i, CTerm aTerm) {\n"+
            "       return 0<=i & i<termCount();\n"+
            "   }\n"+
            
            "   @Override\n" +
            "   public boolean canRemove(CNode aNode){\n" +
            "        return fList.indexOf(aNode)!=-1 & count()>1;\n" +
            "    }\n ";

    /**
     *
     */
    public CSignToJavaTranslator(){
        fSignature=new CSignature();
        fSignAnalyzer=new CSignatureAnalyzer();
    }
    
    /**
     *
     * @param aSignature
     */
    public void setSignature(CSignature aSignature){
        fSignature=aSignature;
        fSignAnalyzer.setSignature(fSignature);
    }
    
    /**
     *
     * @param aName
     */
    public void setName(String aName){
        fName=aName;
    }

    /**
     *
     * @return
     */
    public String getName(){
        return fName;
    }
    
    /**
     *
     * @return
     */
    public CompilationUnit_List signatureToCompilationUnitList(){
         CompilationUnit_List vList;
         HashMap<String,Character> vStarListRefs=new HashMap<String,Character>();
         HashMap<String,Character> vPlusListRefs=new HashMap<String,Character>();
         HashMap<String,Character> vOptionRefs=new HashMap<String,Character>();
         
         CSortDefinition_List vSortDefList=fSignature.getUserSortCtxt().getSortDefs();
         
         //Generate compilation units for sorts
         vList=sortDefListToCompilationUnitList(vSortDefList);
         
         //Generate a compilation unit(List class) for a member Defn. body with '*' modifier
         vStarListRefs.putAll(retrieveMemDefsWithStarModifier(vSortDefList));
         
         for(Map.Entry<String,Character> ref :vStarListRefs.entrySet()){
             vList.add( RefStarListToCompilationUnit  (ref.getKey(),ref.getValue())); // get name and role       
         }
         
         //Generate a compilation unit(List class) for a member Defn. body with '+' modifier
         vPlusListRefs.putAll(retrieveMemDefsWithPlusModifier(vSortDefList));
         
         for(Map.Entry<String,Character> ref :vPlusListRefs.entrySet()){
             vList.add( RefPlusListToCompilationUnit  (ref.getKey(),ref.getValue())); // get name and role       
         }
         
        //Generate a compilation unit(Option class) for a member Defn. body with '?' modifier
        
        vOptionRefs.putAll(retrieveMemDefsWithOptionModifier(vSortDefList));
         
         for(Map.Entry<String,Character> ref :vOptionRefs.entrySet()){
             vList.add( RefOptionToCompilationUnit  (ref.getKey())); // get name        
         }
         
         
         //Generate a List of Class Names to be used to initialize the node factory constructor
         CompilationUnit_List vCompUnitList=vList;
         ArrayList<String> vClassNames=new ArrayList<>();
         vClassNames=generateClassNames(vCompUnitList);
         
         //Generate a Compilation unit of sort codes and labels
         ArrayList<String> vNames=new ArrayList<>();
         for(int k=0;k<vSortDefList.contextCount();k++){
             vNames.add(vSortDefList.getElt(k).getName());
         }
         
         for(Map.Entry<String,Character> ref :vStarListRefs.entrySet()){
             vNames.add(ref.getKey()+"_List0");
             
         }
         for(Map.Entry<String,Character> ref :vPlusListRefs.entrySet()){
             vNames.add(ref.getKey()+"_List1");
             
         }
         for(Map.Entry<String,Character> ref :vOptionRefs.entrySet()){
             vNames.add(ref.getKey()+"_Option");
             
         }
                
         vList.add(sortDefListToSortCodesCompUnit(vNames));
       
         //Generate a node factory compilation unit
         vList.add(ASTNodeFactoryToCompilationUnit(vNames,vClassNames));
         return vList;
    }
    
    private CompilationUnit_List sortDefListToCompilationUnitList(CSortDefinition_List aList){
        
        JCompilationUnit vCompUnit;
        CompilationUnit_List vList=new CompilationUnit_List();
        
        //Generate a compilation unit(class) for each sort defn. and add to the list of Compilation units 
        for(int i=0;i<aList.contextCount();i++){
           vCompUnit=sortDefToCompilationUnit(aList.getElt(i));
           vList.add(vCompUnit);
        }
        
             
        return vList;
    }
    
    //JCompilationUnit(String pakage, ImportDec_List imports, TypeDec_List types)
     private JCompilationUnit  RefStarListToCompilationUnit  (String aName,char aRole){
       String vPackage="package "+fName+';';
       ImportDec_List vImports=new ImportDec_List();
       TypeDec_List vList=new TypeDec_List(); 
       
       if(aRole=='C'){
           vImports.add(new JImportDec("import GrammarNotions.Context_Terms.CContextKind"));
           vImports.add(new JImportDec("import GrammarNotions.Context_Terms.CContextList"));
       
       }else if(aRole=='T'){
           vImports.add(new JImportDec("import GrammarNotions.Context_Terms.CTerm"));
           vImports.add(new JImportDec("import GrammarNotions.Context_Terms.CTermList"));
       }else{
           assert false: String.format("CSignToJavaTranslator.RefStarToCompilationUnit.pre failed; name=%s, role = %c.",aName,aRole);
              
       }
       
       
       
       vList.add(memDefBodyWithStarModifierToClass(aName,aRole));
      
       JCompilationUnit cu=new JCompilationUnit(vPackage,
                                                vImports,
                                                vList);
       return cu;
    }
    
     //JCompilationUnit(String pakage, ImportDec_List imports, TypeDec_List types)
     private JCompilationUnit  RefPlusListToCompilationUnit  (String aName,char aRole){
       String vPackage="package "+fName+';';
       ImportDec_List vImports=new ImportDec_List();
       TypeDec_List vList=new TypeDec_List(); 
       
       if(aRole=='C'){
           vImports.add(new JImportDec("import GrammarNotions.Context_Terms.CContextKind"));
           vImports.add(new JImportDec("import GrammarNotions.Context_Terms.CContextList"));
           vImports.add(new JImportDec("import Nodes.CNode"));
       
       }else if(aRole=='T'){
           vImports.add(new JImportDec("import GrammarNotions.Context_Terms.CTerm"));
           vImports.add(new JImportDec("import GrammarNotions.Context_Terms.CTermList"));
           vImports.add(new JImportDec("import Nodes.CNode"));
       }else{
           assert false: String.format("CSignToJavaTranslator.RefPlusToCompilationUnit.pre failed; name=%s, role = %c.",aName,aRole);
              
       }       
       vList.add(memDefBodyWithPlusModifierToClass(aName,aRole));
      
       JCompilationUnit cu=new JCompilationUnit(vPackage,
                                                vImports,
                                                vList);
       return cu;
    }
     
     //JCompilationUnit(String pakage, ImportDec_List imports, TypeDec_List types)
     private JCompilationUnit  RefOptionToCompilationUnit  (String aName){
       String vPackage="package "+fName+';';
       ImportDec_List vImports=new ImportDec_List();
       TypeDec_List vList=new TypeDec_List(); 
       
       vImports.add(new JImportDec("import GrammarNotions.Context_Terms.CTermTuple"));
       vImports.add(new JImportDec("import Nodes.CNode"));
       vImports.add(new JImportDec("import Nodes.CNodeKind"));
       vImports.add(new JImportDec("import Nodes.INodeOption"));
            
       vList.add(memDefBodyWithOptionModifierToClass(aName));
      
       JCompilationUnit cu=new JCompilationUnit(vPackage,
                                                vImports,
                                                vList);
       return cu;
    }
     
     private JCompilationUnit sortDefListToSortCodesCompUnit(ArrayList<String> aNames){
       String vPackage="package "+fName+';';
       ImportDec_List vImports=new ImportDec_List();
       TypeDec_List vList=new TypeDec_List(); 
       
       vList.add(sortDefListToSortCodes(aNames));
      
       JCompilationUnit cu=new JCompilationUnit(vPackage,
                                                vImports,
                                                vList);
       return cu;
    } 
    
    //JCompilationUnit(String pakage, ImportDec_List imports, TypeDec_List types)
    private JCompilationUnit sortDefToCompilationUnit(CSortDefinition aSortDef){
       TypeDec_List vList; 
       ImportDec_List vImports; 
       ArrayList<Character> vRoles;
       boolean vBase;
       CSortDefinition vSortDef;
       
       String vPackage="package "+fName+';';
       
       vRoles=new ArrayList<>();
       vRoles=fSignAnalyzer.getRoles(aSortDef.getBody().getMemDefs()); 
       
       vImports=new ImportDec_List();
       for(int i=0;i<vRoles.size();i++){
           if(vRoles.get(i)=='T'){
             vImports.add(new JImportDec("import GrammarNotions.Context_Terms.CTerm"));  
           }else if(vRoles.get(i)=='C'){
             vImports.add(new JImportDec("import GrammarNotions.Context_Terms.CContext")); 
           }else if(vRoles.get(i)=='R'){
             vImports.add(new JImportDec("import GrammarNotions.Context_Terms.CItem")); 
           }
       }
       
       vSortDef=fSignAnalyzer.ancestor(aSortDef);
       if(vSortDef.getName().equals("ContextTuple")){
          vImports.add(new JImportDec("import GrammarNotions.Context_Terms.CContextKind"));
       }
       vBase=fSignAnalyzer.isBaseSort(vSortDef);
       if(vBase){
          vImports.add(new JImportDec("import GrammarNotions.Context_Terms."+ancestorNameToClassName(vSortDef.getName()))); 
       }
       
       
       vImports.add(new JImportDec("import Nodes.CNodeKind"));
       
       vList=new TypeDec_List(); 
       vList.add(SortDefToClassDec(aSortDef));
      
       JCompilationUnit cu=new JCompilationUnit(vPackage,
                                                vImports,
                                                vList);
       return cu;
    }
    
    
    
   /* private ArrayList<String> retrieveMemDefsWithStarModifier(CSortDefinition_List aList){
        CMemberDefinition vMemDef;
        CMemberDefinition_List vList1=new CMemberDefinition_List();
        ArrayList<String>  vList2=new ArrayList<>();
        for(int i=0;i<aList.contextCount();i++){
            if(aList.getElt(i).getBody().getMemDefs().contextCount()!=0){
                 vList1.addAll(aList.getElt(i).getBody().getMemDefs());
                 for(int j=0;j<vList1.contextCount();j++){
                    vMemDef=vList1.getElt(j);
                    if(vMemDef.getBody().getModifier()=='*' && !vList2.contains(vMemDef.getBody().getType().getName())){
                             vList2.add(vMemDef.getBody().getType().getName());
                        }
                    }
                }
        }
        return vList2;
     }*/
    private HashMap<String,Character> retrieveMemDefsWithStarModifier(CSortDefinition_List aList){
        CMemberDefinition vMemDef;
        CMemberDefinition_List vList1=new CMemberDefinition_List();
        HashMap<String,Character> vList2=new HashMap<>();
        for(int i=0;i<aList.contextCount();i++){
            if(aList.getElt(i).getBody().getMemDefs().contextCount()!=0){
                 vList1.addAll(aList.getElt(i).getBody().getMemDefs());
                 for(int j=0;j<vList1.contextCount();j++){
                    vMemDef=vList1.getElt(j);
                    if(vMemDef.getBody().getModifier()=='*'  && !vList2.containsKey(vMemDef.getBody().getType().getName())){
                             vList2.put(vMemDef.getBody().getType().getName(),vMemDef.getBody().getRole());
                        }
                    }
                }
        }
        return vList2;
     }
    private HashMap<String,Character> retrieveMemDefsWithPlusModifier(CSortDefinition_List aList){
        CMemberDefinition vMemDef;
        CMemberDefinition_List vList1=new CMemberDefinition_List();
        HashMap<String,Character> vList2=new HashMap<>();
        for(int i=0;i<aList.contextCount();i++){
            if(aList.getElt(i).getBody().getMemDefs().contextCount()!=0){
                 vList1.addAll(aList.getElt(i).getBody().getMemDefs());
                 for(int j=0;j<vList1.contextCount();j++){
                    vMemDef=vList1.getElt(j);
                    if(vMemDef.getBody().getModifier()=='+'  && !vList2.containsKey(vMemDef.getBody().getType().getName())){
                             vList2.put(vMemDef.getBody().getType().getName(),vMemDef.getBody().getRole());
                        }
                    }
                }
        }
        return vList2;
     }
    
    private HashMap<String,Character> retrieveMemDefsWithOptionModifier(CSortDefinition_List aList){
        CMemberDefinition vMemDef;
        CMemberDefinition_List vList1=new CMemberDefinition_List();
        HashMap<String,Character> vList2=new HashMap<>();
        for(int i=0;i<aList.contextCount();i++){
            if(aList.getElt(i).getBody().getMemDefs().contextCount()!=0){
                 vList1.addAll(aList.getElt(i).getBody().getMemDefs());
                 for(int j=0;j<vList1.contextCount();j++){
                    vMemDef=vList1.getElt(j);
                    if(vMemDef.getBody().getModifier()=='?' && !vList2.containsKey(vMemDef.getBody().getType().getName())){
                             vList2.put(vMemDef.getBody().getType().getName(),vMemDef.getBody().getRole());
                        }
                    }
                }
        }
        return vList2;
     }
     //JClassDeclaration(List<JModifier>modifiers, String name, String baseClass,ClassOrInterfaceType_List interfaces,String aMemText)
     private JClassDeclaration memDefBodyWithOptionModifierToClass(String aName){
        JModifiers vModifiers;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
        String vBaseClass="CTermTuple";
        
        JClassOrInterfaceType vInterface= new JClassOrInterfaceType("INodeOption");
        ClassOrInterfaceType_List vList=new ClassOrInterfaceType_List();
        vList.add(vInterface);
        //System.out.println(aName+" "+ sortDefNameToClassName(aName)+" "+signatureNameToSortCodesClassName(fName));
        JClassDeclaration cd=new JClassDeclaration(vModifiers,
                                                   'C'+aName+"_Option",
                                                   vBaseClass,
                                                   vList,
                                                   memDefBodyWithOptionModifierToOptionClass(aName,sortDefNameToClassName(aName),signatureNameToSortCodesClassName(fName)));
	return cd;
    }
  
     //JClassDeclaration(List<JModifier>modifiers, String name, String baseClass,ClassOrInterfaceType_List interfaces,String aMemText)
     private JClassDeclaration memDefBodyWithStarModifierToClass(String aName,char aRole){
        JModifiers vModifiers;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
        String vBaseClass="";
        
        switch(aRole){
            case 'C':
                vBaseClass="CContextList";
                break;
            case 'T':
                vBaseClass="CTermList";
                break;
            default: assert false: String.format("CSignToJavaTranslator.memDefBodyWithStarModifierToClass.pre failed; name=%s, role = %c.",aName,aRole);
                break;
        }
	
        JClassDeclaration cd=new JClassDeclaration(vModifiers,
                                                   'C'+aName+"_List0",
                                                   vBaseClass,
                                                   null,
                                                   memDefBodyWithStarModifierToListClass(aName,sortDefNameToClassName(aName),signatureNameToSortCodesClassName(fName),aRole));
	return cd;
    }
  
    private JClassDeclaration memDefBodyWithPlusModifierToClass(String aName,char aRole){
        JModifiers vModifiers;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
        String vBaseClass="";
        
        switch(aRole){
            case 'C':
                vBaseClass="CContextList";
                break;
            case 'T':
                vBaseClass="CTermList";
                break;
            default: assert false: String.format("CSignToJavaTranslator.memDefBodyWithPlusModifierToClass.pre failed; name=%s, role = %c.",aName,aRole);
                break;
        }
	
        JClassDeclaration cd=new JClassDeclaration(vModifiers,
                                                   'C'+aName+"_List1",
                                                   vBaseClass,
                                                   null,
                                                   memDefBodyWithPlusModifierToListClass(aName,sortDefNameToClassName(aName),signatureNameToSortCodesClassName(fName),aRole));
	return cd;
    }
    //JCompilationUnit(String pakage, ImportDec_List imports, TypeDec_List types)
     private JCompilationUnit ASTNodeFactoryToCompilationUnit(ArrayList<String> aNames,ArrayList<String> aClassNames){
       String vPackage="package "+fName+';';
       ImportDec_List vImports=new ImportDec_List();
       TypeDec_List vList=new TypeDec_List(); 
       
       
       vImports.add(new JImportDec("import Nodes.CNodeFactory"));
       vImports.add(new JImportDec("import java.util.HashMap"));
      // vImports.add(new JImportDec("import java.util.Map"));
       
       vList.add(signatureToASTNodeFactoryClass(aNames,aClassNames));
      
       JCompilationUnit cu=new JCompilationUnit(vPackage,
                                                vImports,
                                                vList);
       return cu;
    }  
   
     private JClassDeclaration signatureToASTNodeFactoryClass(ArrayList<String> aNames,ArrayList<String> aClassNames){
       JModifiers vModifiers;
                   
       vModifiers=new JModifiers();
       vModifiers.add(JModifiers.PUBLIC); 
       
       BodyDec_List members=new BodyDec_List();
       
       //members.add(new JFieldDec("protected Map<String,Class> fLabeltoClass;"));
       //members.add(new JFieldDec("protected Map<Integer,Class> fCodetoClass;"));
       members.add(nodeFactoryConstructor(aNames,aClassNames));
       
       
       members.add(new JMethodDec(fmCodeToClassMethod));
       members.add(new JMethodDec(fmLabelToClassMethod));
	
       JClassDeclaration cd=new JClassDeclaration(vModifiers,
                                                   signatureNameToNodeFactoryClassName(fName),
                                                   "CNodeFactory",
                                                   null,
                                                   members);
	return cd;
   }
      
   //JConstructorDec( List<JModifier> modifiers,String name, Parameter_List parameters,Statement_List statements)
    private JConstructorDec nodeFactoryConstructor(ArrayList<String> aNames,ArrayList<String> aClassNames){
        Parameter_List vPars=new Parameter_List(); 
        Statement_List vStmtList=new Statement_List();
        
        JModifiers vModifiers;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
        
        String vPut="";
        String vPutCodes="";
        for(int i=0,j=0;(i<aNames.size() && j<aClassNames.size());i++,j++){
           vPut=vPut+"              put(\""+aNames.get(i)+"\""+','+aClassNames.get(j)
+".class);\n";
           vPutCodes=vPutCodes+"              put(new Integer("+signatureNameToSortCodesClassName(fName)+'.'+sortDefNameToSortCode(aNames.get(i))+"),"+aClassNames.get(j)
+".class);\n";
        }
   
        vStmtList.add(new JExpressionStmt(labeltoClassInitStmt(vPut)));
        vStmtList.add(new JExpressionStmt(codetoClassInitStmt(vPutCodes)));
               
        JConstructorDec vConst=new JConstructorDec(vModifiers,
                                                   signatureNameToNodeFactoryClassName(fName),
                                                   vPars,
                                                   vStmtList);
        
        return vConst;
        
    }
     
    private JClassDeclaration sortDefListToSortCodes(ArrayList<String> aNames){
        JModifiers vModifiers;
        String vLabels="";
        
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
        
        BodyDec_List members=new BodyDec_List();
        
        for(int i=0;i<aNames.size();i++){
            members.add(new JFieldDec("public static final int "+sortDefNameToSortCode(aNames.get(i))+"="+i+';'));
            vLabels=vLabels+"\""+aNames.get(i)+"\""+',';
        }
        members.add(new JFieldDec(sortLabelStringArray(vLabels.substring(0,vLabels.length()-1))));
        JClassDeclaration cd=new JClassDeclaration(vModifiers,
                                                   signatureNameToSortCodesClassName(fName),
                                                   "",
                                                   null,
                                                   members);
    	return cd;  
        
    }
    
    
    private JClassDeclaration SortDefToClassDec(CSortDefinition aSortDef){
        JModifiers vModifiers;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
	
        BodyDec_List members=new BodyDec_List();
        members.addAll(sortDefBodyToClassBodyFieldDecs(aSortDef.getBody()));
        // Check if there is a need to generate an empty constructor
        if(allAncestorMemDefs(aSortDef).contextCount()>0){
            members.add(sortDefToEmptyConstructor(aSortDef));
        }
        members.add(sortDefToConstructor(aSortDef));
        members.addAll(memDefListToGetterMethodDecs(aSortDef.getBody().getMemDefs()));
        members.addAll(memDefListToSetterMethodDecs(aSortDef.getBody().getMemDefs()));
        members.add(sortDefToSortLabelDec(aSortDef));
        members.add(sortDefToSortCodeDec(aSortDef));
        members.add(sortDefToNodeKindDec());
        members.addAll(sortDefToRoleMethodDecs(aSortDef));
       
        //JClassDeclaration(List<JModifier>modifiers, String name, String baseClass,ClassOrInterfaceType_List interfaces,BodyDec_List members)
        JClassDeclaration cd=new JClassDeclaration(vModifiers,
                                                   sortDefNameToClassName(aSortDef.getName()),
                                                   ancestorNameToClassName(aSortDef.getBody().getAncestor().getName()),
                                                   null,
                                                   members);
    	return cd;
   }
    
  //private JClassDeclaration memDefBodyWithStarModifierToClassDec() 
   private BodyDec_List memDefListToBodyDecList(CMemberDefinition_List aList){
       JFieldDec vFieldDec;
       BodyDec_List vList=new BodyDec_List();
       
       for(int i=0;i<aList.contextCount();i++){
           vFieldDec=memDefToFieldDec(aList.getElt(i));
           vList.add(vFieldDec);
       }
     
       return vList;
   } 
   
   private BodyDec_List sortDefBodyToClassBodyFieldDecs(CSortDefBody aSortDefBody){
      
       BodyDec_List vResult;
      
       vResult=memDefListToBodyDecList(aSortDefBody.getMemDefs());
    
       return vResult;
       
   }
    
   //JFieldDec(List<JModifier> modifiers,JType type, String name)
   private JFieldDec memDefToFieldDec(CMemberDefinition aMemDef){
        JModifiers vModifiers;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PROTECTED); 
        
        JFieldDec fd=new JFieldDec(vModifiers,
                                   memDefBodyToType(aMemDef.getBody()), 
                                   memDefNameToField(aMemDef.getName()));
        
        return fd;
   }
    
    private JType memDefBodyToType(CMemberDefBody aMemDefBody){
        // if member type descends from Data, use type name itself;
        // otherwise, map the type name to the corresponding class name
        CSortDefinition vSortDef = aMemDefBody.getType().getSortDef();
        CSignatureAnalyzer vAnalyzer = new CSignatureAnalyzer();
        vAnalyzer.setSignature(fSignature);
        CSortDefinition vDataDef = vAnalyzer.findSortDef("Data");
        String vName;
        if (vAnalyzer.less(vSortDef, vDataDef)){
            vName=vSortDef.getName();
        }else{
            vName = sortDefNameToClassName(vSortDef.getName());
        }
        
        if(aMemDefBody.getModifier()=='*' ){
            vName+="_List0";
        }else if(aMemDefBody.getModifier()=='+'){
            vName+="_List1";
        }else if(aMemDefBody.getModifier()=='?'){
            vName+="_Option";
        }
            
        return new JClassOrInterfaceType(vName);
    }
    
    private ArrayList<CMemberDefinition_List> retrieveAncestorsmemDefs(CSortDefinition aSortDef){
        ArrayList<CMemberDefinition_List> vList=new  ArrayList<>();
        CSortDefinition vSortDef=aSortDef;
        while(vSortDef.getBody()!=null){
            vList.add(vSortDef.getBody().getMemDefs());
            vSortDef=vSortDef.getBody().getAncestor().getSortDef();
        }
      
        return vList;
    }
    //returns memDefs of aSortDef and all memDefs of its ancestors
    private CMemberDefinition_List allAncestorMemDefs(CSortDefinition aSortDef){
        CMemberDefinition_List vMemList=new CMemberDefinition_List();
        ArrayList< CMemberDefinition_List> vAncestors=new ArrayList<>();
        
        vAncestors.addAll(retrieveAncestorsmemDefs(aSortDef));
        for(int j=0;j<vAncestors.size();j++){
           vMemList.addAll(vAncestors.get(j));
        }
        return vMemList;
        
    }
    
    //JConstructorDec( List<JModifier> modifiers,String name, Parameter_List parameters,Statement_List statements)
    private JConstructorDec sortDefToEmptyConstructor(CSortDefinition aSortDef){ 
         JModifiers vModifiers;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
        
        JConstructorDec vConst=new JConstructorDec(vModifiers,
                                                   sortDefNameToClassName(aSortDef.getName()),
                                                   new Parameter_List(),
                                                   new Statement_List());
        
        return vConst;
        
        
    }
    
    //JConstructorDec( List<JModifier> modifiers,String name, Parameter_List parameters,Statement_List statements)
    private JConstructorDec sortDefToConstructor(CSortDefinition aSortDef){
        Parameter_List vPars;
        CMemberDefinition_List vMemList;
        Statement_List vStmtList;
        String vSuperInvoc="";
        JModifiers vModifiers;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
        
        ArrayList<CMemberDefinition_List> vAncestors=new  ArrayList<>();
        vAncestors.addAll(retrieveAncestorsmemDefs(aSortDef));
        
        vMemList=new CMemberDefinition_List();
        for(int j=1;j<vAncestors.size();j++){
           vMemList.addAll(vAncestors.get(j));
        }
        
        vPars=new Parameter_List();
        
        vStmtList=new Statement_List();
        JConstructorInvocationStmt ConstInvStmt;
        if(vMemList.contextCount()!=0){
           vPars.add(new JParameter(memDefBodyToType(vMemList.getElt(0).getBody()),
                                 memDefNameToParameter(vMemList.getElt(0).getName())));
           vSuperInvoc=vSuperInvoc+memDefNameToParameter(vMemList.getElt(0).getName());
           for(int k=1;k<vMemList.contextCount();k++){
               vPars.add(new JParameter(memDefBodyToType(vMemList.getElt(k).getBody()),
                                 memDefNameToParameter(vMemList.getElt(k).getName())));
               vSuperInvoc=vSuperInvoc+","+memDefNameToParameter(vMemList.getElt(k).getName());
           }
           ConstInvStmt=new  JConstructorInvocationStmt("super("+vSuperInvoc+");");
           vStmtList.add(ConstInvStmt);
        }else{
           ConstInvStmt=null;
        }
       
       
        
        vMemList=new CMemberDefinition_List();
        vMemList=aSortDef.getBody().getMemDefs();
        for(int i=0;i<vMemList.contextCount();i++){
               vPars.add(new JParameter(memDefBodyToType(vMemList.getElt(i).getBody()),
                                 memDefNameToParameter(vMemList.getElt(i).getName())));
               vStmtList.add(new JExpressionStmt(new JAssignExpr(memDefNameToField(vMemList.getElt(i).getName())+"="+memDefNameToParameter(vMemList.getElt(i).getName()))));
        
        }
        
        JConstructorDec vConst=new JConstructorDec(vModifiers,
                                                   sortDefNameToClassName(aSortDef.getName()),
                                                   vPars,
                                                   vStmtList);
        
        return vConst;
        
    }
  
    //JMethodDec(List<JModifier> modifiers,JType returnType, String name, Parameter_List parameters, Statement_List statements)
    private JMethodDec sortDefToSortLabelDec(CSortDefinition aSortDef){
        
        Statement_List vStatList;
        JModifiers vModifiers;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
        
        JReturnStmt rs=new JReturnStmt("\""+sortDefNameToSortLabel(aSortDef.getName())+"\"");
        
        vStatList=new Statement_List();
        vStatList.add(rs);
        
        JMethodDec md=new JMethodDec(vModifiers,
                                     new JClassOrInterfaceType("String"),
                                     "sortLabel",
                                     new Parameter_List(),
                                     vStatList);
        
        return md;
    }
    
    private JMethodDec sortDefToNodeKindDec(){
        
        Statement_List vStatList;
        
        JModifiers vModifiers;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
        
        JReturnStmt rs=new JReturnStmt("CNodeKind.TUPLE");
        
        vStatList=new Statement_List();
        vStatList.add(rs);
        
        JMethodDec md=new JMethodDec(vModifiers,
                                     new JReferenceType("CNodeKind"),
                                     "kind",
                                      new Parameter_List(),
                                      vStatList);
        
        return md;
    }
    
    private JMethodDec sortDefToSortCodeDec(CSortDefinition aSortDef){
        
        Statement_List vStatList;
        
        JModifiers vModifiers;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
        
        JReturnStmt rs=new JReturnStmt(signatureNameToSortCodesClassName(fName)+'.'+sortDefNameToSortCode(aSortDef.getName()));
        
        vStatList=new Statement_List();
        vStatList.add(rs);
        
        JMethodDec md=new JMethodDec(vModifiers,
                                     JPrimitiveType.INT,
                                     "sortCode",
                                      new Parameter_List(),
                                      vStatList);
        
        return md;
    }
    
    private BodyDec_List sortDefToRoleMethodDecs(CSortDefinition aSortDef){
        BodyDec_List vResult;
        CMemberDefinition vMem;
        CMemberDefinition_List vList;
        char vRole;
        int vCtxtCount=0;
        int vTermCount=0;
        int vDataCount=0;
        int vRefCount=0;
        
        vList=aSortDef.getBody().getMemDefs();
   
        for(int i=0;i<vList.contextCount();i++){
             vMem=vList.getElt(i);
             vRole=vMem.getBody().getRole();
             if(vRole==CRoles.CONTEXT){
                 vCtxtCount++;
             }else if(vRole==CRoles.TERM){
                 vTermCount++;
             }else if(vRole==CRoles.DATA){
                 vDataCount++;
             }else if(vRole==CRoles.REFERENCE){
                 vRefCount++;
             }
      }
        vResult=new BodyDec_List();
        if(vCtxtCount!=0){
           vResult.add(roleToRoleCountMethodDec(CRoles.CONTEXT,vCtxtCount));
           vResult.add(roleToRoleKindMethodDec());
           vResult.add(sortDefToGetRoleMethodDec(aSortDef,CRoles.CONTEXT));
           vResult.add(sortDefToSetRoleMethodDec(aSortDef,CRoles.CONTEXT));
        }
        if(vTermCount!=0){
           vResult.add(roleToRoleCountMethodDec(CRoles.TERM,vTermCount));
           vResult.add(sortDefToGetRoleMethodDec(aSortDef,CRoles.TERM));
           vResult.add(sortDefToSetRoleMethodDec(aSortDef,CRoles.TERM));
        }
        if(vDataCount!=0){
           vResult.add(roleToRoleCountMethodDec(CRoles.DATA,vDataCount));
           vResult.add(sortDefToGetRoleMethodDec(aSortDef,CRoles.DATA));
           vResult.add(sortDefToSetRoleMethodDec(aSortDef,CRoles.DATA));
        }
        if(vRefCount!=0){
           vResult.add(roleToRoleCountMethodDec(CRoles.REFERENCE,vRefCount));
           vResult.add(sortDefToGetRoleMethodDec(aSortDef,CRoles.REFERENCE));
           vResult.add(sortDefToSetRoleMethodDec(aSortDef,CRoles.REFERENCE));
        }
       
        return vResult;
    }
    private JMethodDec roleToRoleKindMethodDec(){
        Statement_List vStatList;
        
        JModifiers vModifiers;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
        
        JReturnStmt rs=new JReturnStmt("CContextKind.ckItem");
        
        vStatList=new Statement_List();
        vStatList.add(rs);
        
        JMethodDec md=new JMethodDec(vModifiers,
                                     new JReferenceType("CContextKind"),
                                     "contextKind",
                                      new Parameter_List(),
                                      vStatList);
        
        return md;
        
    }
    private JMethodDec roleToRoleCountMethodDec(char aChar,int aCount){
        
        Statement_List vStatList;
        
        JModifiers vModifiers;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
        JReturnStmt rs=new JReturnStmt("super."+roleToRoleCountMethodName(aChar)+"() + "+ aCount);
        
        vStatList=new Statement_List();
        vStatList.add(rs);
        
        JMethodDec md=new JMethodDec(vModifiers,
                                     JPrimitiveType.INT,
                                     roleToRoleCountMethodName(aChar),
                                     new Parameter_List(),
                                     vStatList);
        
        return md;
    } 
    
    private BodyDec_List memDefListToGetterMethodDecs(CMemberDefinition_List aList){
       JMethodDec vMethodDec;
       BodyDec_List vList=new BodyDec_List();
       
       for(int i=0;i<aList.contextCount();i++){
           vMethodDec=memberDefToGetterMethodDec(aList.getElt(i));
           vList.add(vMethodDec);
       }
     
       return vList;
   } 
    private BodyDec_List memDefListToSetterMethodDecs(CMemberDefinition_List aList){
       JMethodDec vMethodDec;
       BodyDec_List vList=new BodyDec_List();
       
       for(int i=0;i<aList.contextCount();i++){
           vMethodDec=memberDefToSetterMethodDec(aList.getElt(i));
           vList.add(vMethodDec);
       }
     
       return vList;
   } 
 
    private JMethodDec memberDefToGetterMethodDec(CMemberDefinition aMemDef){
        
        Statement_List vStatList;
        
        JModifiers vModifiers;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
        
        JReturnStmt rs=new JReturnStmt(memDefNameToField(aMemDef.getName()));
        
        vStatList=new Statement_List();
        vStatList.add(rs);
        
        JMethodDec md=new JMethodDec(vModifiers,
                                     memDefBodyToType(aMemDef.getBody()),
                                     memDefNameToGetter(aMemDef.getName()),
                                     new Parameter_List(),
                                     vStatList);
        
        return md;
    }
    
     private JMethodDec memberDefToSetterMethodDec(CMemberDefinition aMemDef){
        
        Statement_List vStatList;
        
        JModifiers vModifiers;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
        
        Parameter_List vPars=new Parameter_List();
        vPars.add(new JParameter(memDefBodyToType(aMemDef.getBody()),
                                 memDefNameToParameter(aMemDef.getName())));
        
        JExpressionStmt rs=new JExpressionStmt(memDefNameToField(aMemDef.getName()+"="+memDefNameToParameter(aMemDef.getName())));
        
        vStatList=new Statement_List();
        vStatList.add(rs);
        
        JMethodDec md=new JMethodDec(vModifiers,
                                     new JVoidType(),
                                     memDefNameToSetter(aMemDef.getName()),
                                     vPars,
                                     vStatList);
        
        return md;
    }
    
    private JMethodDec sortDefToGetRoleMethodDec(CSortDefinition aSortDef,char aRole){
        
        Parameter_List vPars;
        Statement_List vStatList;
        JAssertStmt vAssertStmt;
        SwitchEntryStmt_List vSwitchEntryList;
        Statement_List vStmtsListforSwitch;
        JSwitchStmt vSwitch;
        CMemberDefinition_List vList;
        int j;
        String returnValue="";
        
        JModifiers vModifiers;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
        
        vPars=new Parameter_List();
        vPars.add(new JParameter(JPrimitiveType.INT,
                                 "i"));
        
        
        switch(aRole){
            case 'D':
                  vAssertStmt=new JAssertStmt(new JBinaryExpr(new JBinaryExpr("0<=i"),
                                                new JBinaryExpr("i<dataCount()")
                                                         ,JBinaryExpr.Operator.and),
                                                "String.format(\"Precondition of "+sortDefNameToClassName(aSortDef.getName())+"."+"getData() failed: i=%d\",i)");
                  vSwitchEntryList=new SwitchEntryStmt_List();
                        
                  j=0;
                  vList=new CMemberDefinition_List();
                  vList=aSortDef.getBody().getMemDefs();
                  for(int i=0;i<vList.contextCount();i++){
                    CMemberDefinition mem=vList.getElt(i);
                    if(mem.getBody().getRole()=='D'){
                        vStmtsListforSwitch=new Statement_List();
                        returnValue=generateReturnValue(memDefBodyToType(mem.getBody()).toString(),memDefNameToField(mem.getName()));
                        vStmtsListforSwitch.add(new JReturnStmt(returnValue));
                        vSwitchEntryList.add(new JSwitchEntryStmt("case "+Integer.toString(j),
                                        vStmtsListforSwitch)
                                        );
                        j++;
                    }
                  }
                 
                  //Add default
                  vStmtsListforSwitch=new Statement_List();
                  vStmtsListforSwitch.add(new JReturnStmt("super.getData(i)"));
                  vSwitchEntryList.add(new JSwitchEntryStmt("default",vStmtsListforSwitch));
                 
                  vSwitch=new JSwitchStmt("i-super.dataCount()",
                                            vSwitchEntryList);
        
                  vStatList=new Statement_List();
                  vStatList.add(vAssertStmt);
                  vStatList.add(vSwitch);
                 
                  return new JMethodDec(vModifiers,
                                     new JClassOrInterfaceType("String"),   
                                     "getData",
                                     vPars,
                                     vStatList);
                
            case 'C':
            case 'T':
            case 'R':
                  vAssertStmt=new JAssertStmt(new JBinaryExpr(new JBinaryExpr("0<=i"),
                                                new JBinaryExpr("i<"+roleToRoleCountMethodName(aRole)+"()")
                                                         ,JBinaryExpr.Operator.and),
                                                "String.format(\"Precondition of "+sortDefNameToClassName(aSortDef.getName())+"."+roleToGetRoleMethodName(aRole)+"() failed: i=%d\",i)");
        
        
                 vSwitchEntryList=new SwitchEntryStmt_List();
                 
       
                 j=0;
                vList=new CMemberDefinition_List();
                  vList=aSortDef.getBody().getMemDefs();
                 for(int i=0;i<vList.contextCount();i++){
                    CMemberDefinition mem=vList.getElt(i);
                    if(mem.getBody().getRole()==aRole){
                        vStmtsListforSwitch=new Statement_List();
                        vStmtsListforSwitch.add(new JReturnStmt(memDefNameToField(mem.getName())));
                        vSwitchEntryList.add(new JSwitchEntryStmt("case "+Integer.toString(j),
                                        vStmtsListforSwitch)
                                        );
                        j++;
                    }
                 }
        
                //Add default
                vStmtsListforSwitch=new Statement_List();
                vStmtsListforSwitch.add(new JReturnStmt("super."+roleToGetRoleMethodName(aRole)+"(i)"));
                vSwitchEntryList.add(new JSwitchEntryStmt("default",vStmtsListforSwitch));
         
        
                vSwitch=new JSwitchStmt("i-super."+roleToRoleCountMethodName(aRole)+"()",
                                            vSwitchEntryList);
        
                vStatList=new Statement_List();
                vStatList.add(vAssertStmt);
                vStatList.add(vSwitch);
                String vRoleName;;
                 if(aRole=='R'){
                    vRoleName="CItem"; 
                 }else{
                   vRoleName = 'C' + roleToRoleName(aRole);
                 }
                return new JMethodDec(vModifiers,
                                     new JClassOrInterfaceType(vRoleName),   
                                     roleToGetRoleMethodName(aRole),
                                     vPars,
                                     vStatList);
            default: return null;
        
        }
     }
    
    private JMethodDec sortDefToSetRoleMethodDec(CSortDefinition aSortDef,char aRole){
        
        Parameter_List vPars;
        Statement_List vStatList=new Statement_List();
        JAssertStmt vAssertStmt;
        SwitchEntryStmt_List vSwitchEntryList;
        Statement_List vStmtsListforSwitch;
        JSwitchStmt vSwitch;
        CMemberDefinition_List vList;
        int j;
        
        JModifiers vModifiers;
                   
        vModifiers=new JModifiers();
        vModifiers.add(JModifiers.PUBLIC); 
        
        switch(aRole){
            case 'D':
                vPars=new Parameter_List();
                vPars.add(new JParameter(JPrimitiveType.INT,
                                 "i"));
                vPars.add(new JParameter(new JClassOrInterfaceType("String"),
                                 "aData"));
                vAssertStmt=new JAssertStmt(new JBinaryExpr(new JBinaryExpr("0<=i"),
                                                new JBinaryExpr("i<dataCount()")
                                                         ,JBinaryExpr.Operator.and),
                                                "String.format(\"Precondition of "+sortDefNameToClassName(aSortDef.getName())+".setData() failed: i=%d\",i)");
                vSwitchEntryList=new SwitchEntryStmt_List();
                j=0;
                vList=new CMemberDefinition_List();
                vList=aSortDef.getBody().getMemDefs();
                for(int i=0;i<vList.contextCount();i++){
                    CMemberDefinition mem=vList.getElt(i);
                    if(mem.getBody().getRole()=='D'){
                        vStmtsListforSwitch=new Statement_List();
                        vStmtsListforSwitch.add(new JExpressionStmt(generateAssignValue(memDefBodyToType(mem.getBody()).toString(),memDefNameToField(mem.getName()))));            
                        vStmtsListforSwitch.add(new JBreakStmt());
                        vSwitchEntryList.add(new JSwitchEntryStmt("case "+Integer.toString(j),
                                        vStmtsListforSwitch)
                                 );
                        j++;
                    }
                }
                 //Add default
                vStmtsListforSwitch=new Statement_List();
                vStmtsListforSwitch.add(new JExpressionStmt("super.setData(i,aData)"));
                vStmtsListforSwitch.add(new JBreakStmt());
                vSwitchEntryList.add(new JSwitchEntryStmt("default",vStmtsListforSwitch));
         
        
                vSwitch=new JSwitchStmt("i-super.dataCount()",
                                            vSwitchEntryList);
        
                vStatList=new Statement_List();
                vStatList.add(vAssertStmt);
                vStatList.add(vSwitch);
                
                return new JMethodDec(vModifiers,
                                     new JVoidType(),   
                                     "setData",
                                     vPars,
                                     vStatList);
                
            case 'C':
            case 'T':
            case 'R':
                 vPars=new Parameter_List();
                 vPars.add(new JParameter(JPrimitiveType.INT,
                                 "i"));
                 String vRoleName;;
                 if(aRole=='R'){
                    vRoleName="CItem"; 
                 }else{
                   vRoleName = 'C' + roleToRoleName(aRole);
                 }
                 vPars.add(new JParameter(new JClassOrInterfaceType(vRoleName),
                                 "a"+roleToRoleName(aRole)));
        
                 vAssertStmt=new JAssertStmt(new JBinaryExpr(new JBinaryExpr("0<=i"),
                                                new JBinaryExpr("i<"+roleToRoleCountMethodName(aRole)+"()")
                                                         ,JBinaryExpr.Operator.and),
                                                "String.format(\"Precondition of "+sortDefNameToClassName(aSortDef.getName())+"."+roleToSetRoleMethodName(aRole)+"() failed: i=%d\",i)");
        
                 vSwitchEntryList=new SwitchEntryStmt_List();
                 j=0;
                 vList=new CMemberDefinition_List();
                 vList=aSortDef.getBody().getMemDefs();
                 for(int i=0;i<vList.contextCount();i++){
                    CMemberDefinition mem=vList.getElt(i);
                    if(mem.getBody().getRole()==aRole){
                        vStmtsListforSwitch=new Statement_List();
                        vStmtsListforSwitch.add(new JAssertStmt(new JInstanceOfExpr("a"+roleToRoleName(aRole)+" instanceof "+memDefBodyToType(mem.getBody())),
                                                     "String.format(\"Precondition of "+sortDefNameToClassName(aSortDef.getName())+"."+roleToSetRoleMethodName(aRole)+"() failed: i=%d , aTerm.sortLabel=%s\",i,"+"a"+roleToRoleName(aRole)+".sortLabel())")); 
                        vStmtsListforSwitch.add(new JExpressionStmt(memDefNameToField(mem.getName())+"=("+memDefBodyToType(mem.getBody())+")"+"a"+roleToRoleName(aRole)));            
                        vStmtsListforSwitch.add(new JBreakStmt());
                        vSwitchEntryList.add(new JSwitchEntryStmt("case "+Integer.toString(j),
                                        vStmtsListforSwitch)
                                 );
                        j++;
                    }
                }
        
                 //Add default
                vStmtsListforSwitch=new Statement_List();
                vStmtsListforSwitch.add(new JExpressionStmt("super."+roleToSetRoleMethodName(aRole)+"(i,"+"a"+roleToRoleName(aRole)+')'));
                vStmtsListforSwitch.add(new JBreakStmt());
                vSwitchEntryList.add(new JSwitchEntryStmt("default",vStmtsListforSwitch));
         
        
                vSwitch=new JSwitchStmt("i-super."+roleToRoleCountMethodName(aRole)+"()",
                                            vSwitchEntryList);
        
                vStatList=new Statement_List();
                vStatList.add(vAssertStmt);
                vStatList.add(vSwitch);
        
                return new JMethodDec(vModifiers,
                                     new JVoidType(),   
                                     roleToSetRoleMethodName(aRole),
                                     vPars,
                                     vStatList);
        
       
        
         default: return null;
        
        }
     
    }
    
    private ArrayList<String> generateClassNames(CompilationUnit_List vCompUnitList){
        JCompilationUnit vCompUnit;
        TypeDec_List vTypeDecList;
        JTypeDec vDec;
        ArrayList<String> vList=new ArrayList<>();
        String vClassName="";
        for(int i=0;i<vCompUnitList.count();i++){
            vCompUnit=vCompUnitList.getNode(i);
            vTypeDecList=vCompUnit.types;
            for(int j=0;j<vTypeDecList.count();j++){
                vDec=vTypeDecList.getNode(j);
                if(vDec instanceof JClassDeclaration){
                    vClassName=vDec.name;
                }
                vList.add(vClassName);
            }
        }
        return vList;
    }
      
     // Format functions
     private String sortDefNameToClassName(String aName){
         return String.format(fmSortDefToClass,aName);
     }
     private String sortDefNameToSortLabel(String aName){
         return String.format(fmSortDefToSortLabel, aName);
     }
     
     private String roleToRoleName(char aChar){
        switch(aChar){
             case 'C':
                 return String.format(fmRoleToRoleName, "Context");
             case 'T':
                 return String.format(fmRoleToRoleName, "Term");
             case 'D':
                 return String.format(fmRoleToRoleName, "String");
             case 'R':
                 return String.format(fmRoleToRoleName, "Reference");
             default:assert false:
                   "Error in CSignToJavaTranslator.roleToRoleName"+Character.toString(aChar);
                return ""; // to satisfy compiler return ""
         } 
     }
    
     private String roleToRoleCountMethodName(char aChar){
         switch(aChar){
             case 'C':
                 return String.format(fmRoleToRoleCountMethod, "context");
             case 'T':
                 return String.format(fmRoleToRoleCountMethod, "term");
             case 'D':
                 return String.format(fmRoleToRoleCountMethod, "data");
             case 'R':
                 return String.format(fmRoleToRoleCountMethod, "reference");
             default: assert false:
                    "Error in CSignToJavaTranslator.roleToGetRoleMethodName"+Character.toString(aChar);
                return ""; // to satisfy compiler return "";
         }
         
     }
     
     private String roleToGetRoleMethodName(char aChar){
        switch(aChar){
             case 'C':
                 return String.format(fmRoleToGetRoleMethod, roleToRoleName('C'));
             case 'T':
                 return String.format(fmRoleToGetRoleMethod, roleToRoleName('T'));
             case 'D':
                 return String.format(fmRoleToGetRoleMethod, "Data");
             case 'R':
                 return String.format(fmRoleToGetRoleMethod, roleToRoleName('R'));
             default:assert false:
                   "Error in CSignToJavaTranslator.roleToGetRoleMethodName"+Character.toString(aChar);
                return ""; // to satisfy compiler return ""
         } 
     }
     private String roleToSetRoleMethodName(char aChar){
        switch(aChar){
             case 'C':
                 return String.format(fmRoleToSetRoleMethod, roleToRoleName('C'));
             case 'T':
                 return String.format(fmRoleToSetRoleMethod, roleToRoleName('T'));
             case 'D':
                 return String.format(fmRoleToSetRoleMethod, "Data");
             case 'R':
                 return String.format(fmRoleToSetRoleMethod, roleToRoleName('R'));
             default:assert false:
                   "Error in CSignToJavaTranslator.roleToSetRoleMethodName"+Character.toString(aChar);
                return ""; // to satisfy compiler return ""
         } 
     }
     private String sortDefNameToSortCode(String aName){
         return String.format(fmSortDefToSortCode, aName);
     }
     private String ancestorNameToClassName(String aName){
         return String.format(fmSortDefToClass,aName);
     }
     
     private String memDefNameToField(String aName){
         return String.format(fmMemDefToField,aName);
     }
     private String memDefNameToParameter(String aName){
         return String.format(fmMemDefToParameter,aName);
     }
     private String memDefNameToGetter(String aName){
         return String.format(fmMemDefToGetter,aName);
     }
     private String memDefNameToSetter(String aName){
         return String.format(fmMemDefToSetter,aName);
     }
     
     private String memDefBodyWithStarModifierToListClass(String aName,String aClass,String aCodesName,char aRole ){
         switch(aRole){
            case 'C':
                return String.format(fmMemDefBodyWithStarModifierToContextList,aName,aClass,aCodesName);
               
            case 'T':
                 return String.format(fmMemDefBodyWithStarModifierToTermList,aName,aClass,aCodesName);
               
            default: 
                assert false: String.format("CSignToJavaTranslator.memDefBodyWithStarModifierToListClass.pre failed; name=%s, role = %c.",aName,aRole);
                return "";
        }
       
     }
     private String memDefBodyWithPlusModifierToListClass(String aName,String aClass,String aCodesName,char aRole ){
         switch(aRole){
            case 'C':
                return String.format(fmMemDefBodyWithPlusModifierToContextList,aName,aClass,aCodesName);
               
            case 'T':
                 return String.format(fmMemDefBodyWithPlusModifierToTermList,aName,aClass,aCodesName);
               
            default: 
                assert false: String.format("CSignToJavaTranslator.memDefBodyWithPlusModifierToListClass.pre failed; name=%s, role = %c.",aName,aRole);
                return "";
        }
       
     }
     private String memDefBodyWithOptionModifierToOptionClass(String aName,String aClass,String aCodesName ){
            return String.format(fmMemDefBodyWithOptionModifierToOptionClass,aName,aClass,aCodesName);
               
                  
     }
     private String generateReturnValue(String aType,String aName){
         
         switch(aType){
             case "int":
                 return "Integer.toString("+aName+")";
             case "String":
                 return aName;
             default:
                 assert false: String.format("CSignToJavaTranslator.generateReturnValue.pre failed; type=%s, name=%s.",aType, aName);
                 return "";
         }
     }
     
     private String generateAssignValue(String aType,String aName){
         
         switch(aType){
             case "int":
                 return aName+"=Integer.parseInt(aData)";
             case "String":
                 return aName+"=aData";
             default:
                 assert false: String.format("CSignToJavaTranslator.generateAssignValue.pre failed; type=%s, name=%s.",aType, aName);
                 return "";
         }
     }
     
     private String signatureNameToSortCodesClassName(String aName){
         return String.format(fmSignatureNameToSortCodesClassName,aName);
     }
     
     private String signatureNameToNodeFactoryClassName(String aName){
         return String.format(fmSignatureNameToNodeFactoryClassName,aName);
     }
     
     private String sortLabelStringArray(String aLabels){
         return String.format(fmSortLabelStringArray,aLabels);
     }
     private String labeltoClassInitStmt(String aPuts){
         return String.format(fmfLabeltoClassInitStmt,aPuts);
     }
     private String codetoClassInitStmt(String aPuts){
         return String.format(fmfCodetoClassInitStmt,aPuts);
     }
}
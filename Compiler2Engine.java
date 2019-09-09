public class Compiler2Engine {
	private String vmOutput;
	
	private SymbolTable[] SymbolTableArray = new SymbolTable[2];
	
	public Compiler2Engine() {}//Empty constructor

	//http://nand2tetris.org/lectures/PDF/lecture%2011%20compiler%20II.pdf
	
	public String runCompliationEngine(Tag t) {
		vmOutput="";
		// TODO Auto-generated method stub
		//method function or constructor - need to create a new scope of symbol tables
		
		vmClass(t);
		
		return vmOutput;
	}
	
	public void vmClass(Tag t) {
		
		SymbolTableArray[0]=new SymbolTable();//creates the class scope symbol table
		
		for(Tag m : t.getChildren()) {
			if(m.typeEquals("classVarDec")) {
				this.vmClassVarDec(m);
			}else if(m.typeEquals("subroutineDec")) {
				this.vmSubroutineDec(m);
			}
		}
		
	}
	
	public void vmClassVarDec(Tag t) {
		String kind = t.getChildren()[0].getData();
		String type = t.getChildren()[1].getData();
		for(Tag m : t.getChildren()) {
			if(m.typeEquals("identifier")){
				SymbolTableArray[0].addSymbol(m.getData(), type, kind);
			}
		}
		
	}
	
	public void vmSubroutineDec(Tag t) {
		SymbolTableArray[1]=new SymbolTable();//creates new symbol table for the scope of the function/method/constructor
		
		
		this.vmParameterList(t.getChildren()[4]);//adds the arguments to the symbol table
		
		this.vmSubroutineBody(t.getChildren()[6]);//subroutine body tag
		
		SymbolTableArray[1]=null;
	}
	
	public void vmParameterList(Tag t) {

		for(int i=0;i<t.getChildren().length;i+=3) {
			String kind = "argument";
			String type = t.getChildren()[i].getData();
			String name = t.getChildren()[i+1].getData();
			SymbolTableArray[1].addSymbol(name, type, kind);
		}
		
	}
	
	public void vmSubroutineBody(Tag t) {
		for(Tag m : t.getChildren()) {
			if(m.typeEquals("varDec")){
				this.vmVarDec(m);
			}else if(m.typeEquals("statements")) {
				this.vmStatements(m);
			}
		}
	}
	
	public void vmVarDec(Tag t) {
		String kind = t.getChildren()[0].getData();
		String type = t.getChildren()[1].getData();
		for(Tag m : t.getChildren()) {
			if(m.typeEquals("identifier")){
				SymbolTableArray[1].addSymbol(m.getData(), type, kind);//adds the symbol to the function scope
			}
		}
	}
	
	public void vmStatements(Tag t) {
		for(Tag m : t.getChildren()) {
			if(m.typeEquals("letStatement")) {
				this.vmLetStatement(m);
			}else if(m.typeEquals("ifStatement")) {
				this.vmIfStatement(m);
			}else if(m.typeEquals("whileStatement")) {
				this.vmWhileStatement(m);
			}else if(m.typeEquals("doStatement")) {
				this.vmDoStatement(m);
			}else if(m.typeEquals("returnStatement")) {
				this.vmReturnStatement(m);
			}
		}
	}
	
	public void vmLetStatement(Tag t) {
		//THIS IS THE EXPRESSION TAG t.getChildren()[3]; - this.vmExpression(t.getChildren()[3]);
		
		this.SymbolTableHeirarchySearch(t.getChildren()[1].getData());
	}
	
	public void vmIfStatement(Tag t) {
		
	}
	
	public void vmWhileStatement(Tag t) {
		
	}
	
	public void vmDoStatement(Tag t) {
		
	}
	
	public void vmReturnStatement(Tag t) {
		
	}
	
	public void vmExpression(Tag t) {//NEED TO MAKE THIS
		
		
		
		if(t.getChildren()[0].getChildren()[0].typeEquals("integerConstant")) {// if exp is number n:
			addOutput("push constant "+t.getChildren()[0].getChildren()[0].getData());
		}
	}
	
	public void addOutput(String text) {//adds the string to the vmOuput with a new line underneath
		vmOutput=vmOutput+text+"\n";
	}
	
	public String SymbolTableHeirarchySearch(String name) {//searches in function scope first and then checks class scope
		if(SymbolTableArray[1]!=null && SymbolTableArray[1].findSymbol(name)!=null) {//if the symbol is found in the function scope
			return SymbolTableArray[1].findSymbol(name);
		}
		if(SymbolTableArray[0].findSymbol(name)!=null) {
			return SymbolTableArray[0].findSymbol(name);
		}
		throw new IllegalArgumentException("COULD NOT FIND SYMBOL");
		
	}
	
	
	
	//write a function that goes through the heirarchy of symbol tables and outputs the index of the variable on the symbol table
	
	
	
	
}
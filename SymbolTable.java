import java.util.ArrayList;

public class SymbolTable {
	private ArrayList<String> name;
	private ArrayList<String> type;
	private ArrayList<String> kind;
	private ArrayList<Integer> number;
	
	private int staticCounter;
	private int fieldCounter;
	private int argumentCounter;
	private int localCounter;
	
	public SymbolTable() {
		this.staticCounter=0;
		this.fieldCounter=0;
		this.argumentCounter=0;
		this.localCounter=0;
		this.name = new ArrayList<String>(0);
		this.type = new ArrayList<String>(0);
		this.kind = new ArrayList<String>(0);
		this.number = new ArrayList<Integer>(0);
	}
	
	private int incrementStaticCounter() {//returns the previous int and then increments the counter by 1
		this.staticCounter++;
		return this.staticCounter-1;
	}
	
	private int incrementFieldCounter() {//returns the previous int and then increments the counter by 1
		this.fieldCounter++;
		return this.fieldCounter-1;
	}
	
	private int incrementArgumentCounter() {//returns the previous int and then increments the counter by 1
		this.argumentCounter++;
		return this.argumentCounter-1;
	}
	
	private int incrementLocalCounter() {//returns the previous int and then increments the counter by 1
		this.localCounter++;
		return this.localCounter-1;
	}
	
	public String getName(int index) {
		return this.name.get(index);
	}
	
	public String getType(int index) {
		return this.type.get(index);
	}
	
	public String getKind(int index) {
		return this.kind.get(index);
	}
	
	public int getNumber(int index) {
		return (int)this.number.get(index);
	}
	
	public void addSymbol(String name, String type, String kind) {
		this.name.add(name);
		this.type.add(type);
		this.kind.add(kind);
		if(kind.equals("static")) {
			this.number.add(this.incrementStaticCounter());
		}else if(kind.equals("field")) {
			this.number.add(this.incrementFieldCounter());
		}else if(kind.equals("local")) {
			this.number.add(this.incrementLocalCounter());
		}else if(kind.equals("argument")) {
			this.number.add(this.incrementArgumentCounter());
		}
	}
	
	public String findSymbol(String name) {
		int index=this.name.indexOf(name);
		if(index!=-1) {
			return this.getKind(index) + " " + this.getNumber(index);
		}
		return null;
	}
	
}

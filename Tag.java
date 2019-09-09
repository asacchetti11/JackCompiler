/* Made By Alessandro Sacchetti
  5/20/2018
  Nand2Tetris Compiler I
  Tag.java is the database to store the XML framework
  is an object used in CompilerRun.java
*/

import java.util.ArrayList;

public class Tag {
	private ArrayList<Tag> children;
	private String type;
	private String data;
	
	public String toString() {
		return "\ntype:"+this.type+" \ndata:"+this.data+" \nnumChildren"+children.size();
	}
	
	public Tag(String t,String d) {
		this(new Tag[0],t,d);
	}
	
	public Tag(Tag[] c, String t, String d) {
		this.children= new ArrayList<Tag>(0) {{
			for(Tag m : c) {
				add(m);
			}
			}};
		this.type=t;
		this.data=d;
	}
	
	
	public Tag[] getChildren() {
		Tag[] temp = new Tag[this.children.size()];
		int i=0;
		for(Tag m : this.children) {
			temp[i]=m;
			i++;
		}
		
		return temp;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getData() {
		return this.data;
	}

	public void deleteChild(Tag child) {
		this.children.remove(child);
	}
	
	public void addChild(Tag newChild) {
		this.children.add(newChild);
	}
	
	public boolean typeEquals(String test){
		return this.type.equals(test);
	}
	
	public boolean dataEquals(String test){
		return this.data.equals(test);
	}

	
	//Should never need to use these but adding just for bug-testing
	public void setType(String t) {
		this.type=t;
	}
	
	public void setData(String d) {
		this.data=d;
	}

	
	
}

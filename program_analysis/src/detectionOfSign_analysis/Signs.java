package detectionOfSign_analysis;

import java.util.ArrayList;



public class Signs {
	boolean minus;
	boolean zero;
	boolean plus;
	
	Signs(){
		this.zero = true;
	}
	
	Signs(String str){
		assert str == "null" : "Only null can be passed to this constructor of Signs class as a string! ";	
	}
	
	Signs(int value){
		if(value > 0) this.plus =true;
		else if (value == 0) this.zero = true;
		else this.minus = true;
	}
	
	void add(Sign sign){
		switch(sign){
			case minus: this.minus = true;break;
			case zero: this.zero = true;break;
			case plus: this.plus = true;break;
			default: assert false : "default in swith!"; 
		}
	}
	
	void add(Signs signs){
		if (signs.minus) add(Sign.minus);
		else if (signs.zero) add(Sign.zero);
		else if (signs.plus) add(Sign.plus);
	}
	
	void setAll(){
		this.minus = true;
		this.zero = true;
		this.plus = true;
	}
	
	boolean isAll(){
		if(this.minus && this.zero && this.plus)
			return true;
		else
			return false;
	}
	
	ArrayList<Sign> getSigns(){
		ArrayList<Sign> result = new ArrayList<Sign>();
		if (this.minus) result.add(Sign.minus);
		if (this.zero) result.add(Sign.zero);
		if (this.plus) result.add(Sign.plus);
		return result;
	}
}

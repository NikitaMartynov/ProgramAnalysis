package detectionOfSign_analysis;

import java.util.ArrayList;



public class Signs {
	boolean minus;
	boolean zero;
	boolean plus;
	
	public Signs(){
		this.zero = true;
	}
	
	public Signs(Signs signs){
		if (signs.minus) add(Sign.minus);
		if (signs.zero) add(Sign.zero);
		if (signs.plus) add(Sign.plus);
	}
	
	Signs(String str){
		assert str == "null" : "Only null can be passed to this constructor of Signs class as a string! ";	
	}
	
	Signs(int value){
		if(value > 0) this.plus =true;
		else if (value == 0) this.zero = true;
		else this.minus = true;
	}
	
	public Signs(String cmd, Signs signs1, Signs signs2){
		assert (cmd == "mergeUnion") || (cmd == "mergeIntersection")  : "Only mergeAnd and mergeOr commands can be passed to this constructor of Signs class as a string! ";
		if (cmd == "mergeIntersection"){
			if((signs1 == null) || (signs2 == null)){
				setNone();
				return;
			}
			
			for (Sign sign : signs1.getSigns()){
				switch (sign){
					case minus: minus = signs2.isMinus(); break;
					case zero: zero = signs2.isZero(); break;
					case plus: plus = signs2.isPlus(); break;
					default: assert false : "default in swith!"; 
				}
			}
		}
		if(cmd == "mergeUnion"){
			if((signs1 == null) && (signs2 == null)){
				setNone();
				return;
			}
			if (signs1 == null){
				add(signs2);
				return;
			}
				
			if (signs2 == null){
				add(signs1);
				return;
			}
			add(signs1);
			add(signs2);
		}
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
		if (signs.zero) add(Sign.zero);
		if (signs.plus) add(Sign.plus);
	}
	
	void setAll(){
		this.minus = true;
		this.zero = true;
		this.plus = true;
	}
	
	Signs setNone(){
		this.minus = false;
		this.zero = false;
		this.plus = false;
		return this;
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
	
	boolean isAny(){
		if(this.minus || this.zero || this.plus)
			return true;
		else
			return false;
	}
	
	boolean isMinus(){
		if (this.minus) return true;
		else return false;
	}
	boolean isZero(){
		if (this.zero) return true;
		else return false;
	}
	
	boolean isPlus(){
		if (this.plus) return true;
		else return false;
	}
	
	boolean isSubsetOf(Signs signs){
		if(this.minus)
			if(!signs.minus)
				return false;
		if(this.zero)
			if(!signs.zero)
				return false;
		if(this.plus)
			if(!signs.plus)
				return false;

		
		return true;
	}
}

package SecurityAnalysis;

public enum SecCtx {
	CTX("ctx");
	
	 private SecCtx(String secCtx){
		    this.secCtx = secCtx;
		  }

	 private String secCtx;
	 
	 public String getSecCtx(){
		    return this.secCtx;
	 }
}

package service;

public class validateResult {
	private final int ResultCount;
	private final String result;
	
	public validateResult (int ResultCount, String result) {
		this.ResultCount = ResultCount;
		this.result = result;
	}
	
	public int getResultCount(){
		return ResultCount;
	}
	
	public String getResult() {
		return result;
	}
}
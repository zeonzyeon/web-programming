package ch08;

public class Calculator {
	
	public int getN1() {
		return n1;
	}

	public void setN1(int n1) {
		this.n1 = n1;
	}

	public int getN2() {
		return n2;
	}

	public void setN2(int n2) {
		this.n2 = n2;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}
	
	private int n1;
	private int n2;
	private String op;
	
	public long calc() {
		long result=0;
		switch(op) {
		case "+" : result = n1+n2; break;
		case "-" : result = n1-n2; break;
		case "*" : result = n1*n2; break;
		case "/" : result = n1/n2; break;
		}
		return result;
	}
}
/*
 * 모델 구현
 * 
 * 모델은 DAO, DO 등으로 구성할 수 있으나 여기서는 데이터베이스를 사용하지 않기 떄문에 Calculator 클래스 복사해서 사용 (ch07)
 */